package solutions.s4y.memgrain.edge.rest

import solutions.s4y.memgrain.edge.rest.errors.InternalServerError
import solutions.s4y.memgrain.edge.rest.handlers.{AuthHandlers, DiagnosticHandlers}
import zio.http.codec.HttpCodec.Metadata
import zio.http.codec.PathCodec.path
import zio.http.codec.{Doc, HttpCodec}
import zio.http.endpoint.{AuthType, Endpoint}
import zio.http.endpoint.openapi.{OpenAPIGen, SwaggerUI}
import zio.http.{Handler, MediaType, Response, RoutePattern, Routes, Status, handler}
import zio.{URLayer, ZIO, ZLayer}

class RoutesBuilderLive(private val diagnosticHandlers: DiagnosticHandlers, private val authHandlers: AuthHandlers) {
  def build(prefix: String, version: String): Routes[Any, Response] = {
    val epPing = Endpoint((RoutePattern.GET / prefix / "ping") ?? Doc.p("Diagnostic ping endpoint"))
      .query(HttpCodec.query[String]("payload").annotate(Metadata.Optional()).optional ?? Doc.p("An optional payload to include in the response"))
      .out[String](MediaType.text.plain) ?? Doc.p("200 Pong + payload if provided")

    val epVersion = Endpoint((RoutePattern.GET / prefix / "version") ?? Doc.p("Diagnostic version endpoint"))
      .out[String](MediaType.text.plain) ?? Doc.p("200 Version string")

    // val epAuthVerify: Endpoint[Unit, String, Any, Boolean, AuthType.None] = Endpoint((RoutePattern.GET / prefix / version / "auth" / "verify") ?? Doc.p("Verify token"))
    val epAuthVerify = Endpoint((RoutePattern.GET / prefix / version / "auth" / "verify") ?? Doc.p("Verify token"))
      .query(HttpCodec.query[String]("token") ?? Doc.p("Token to verify"))
      .out[Boolean](MediaType.application.json, Doc.p("200 true if token is valid, false otherwise"))
      .outError[InternalServerError](Status.InternalServerError)

    val openAPI = OpenAPIGen.fromEndpoints("Diagnostic", "SNAPSHOT", List(epPing, epVersion, epAuthVerify))

    Routes(
      epPing.implementHandler(diagnosticHandlers.ping),
      // epVersion.implementHandler(diagnosticHandlers.version(version)),
      epAuthVerify.implementHandler(authHandlers.verifyTokenHandler),
    ) ++ SwaggerUI.routes("docs" / "openapi", openAPI)
  }
}

object RoutesBuilderLive {
  def apply(diagnosticHandlers: DiagnosticHandlers, authHandlers: AuthHandlers): RoutesBuilderLive =
    new RoutesBuilderLive(diagnosticHandlers, authHandlers)

  val layer: URLayer[DiagnosticHandlers & AuthHandlers, RoutesBuilderLive] =
    ZLayer.fromFunction(
      (diagnosticHandlers: DiagnosticHandlers, authHandlers: AuthHandlers) => RoutesBuilderLive(diagnosticHandlers, authHandlers))
}
