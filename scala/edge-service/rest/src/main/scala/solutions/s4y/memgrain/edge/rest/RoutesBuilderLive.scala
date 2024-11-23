package solutions.s4y.memgrain.edge.rest

import solutions.s4y.memgrain.edge.rest.hadlers.DiagnosticHandlers
import zio.http.codec.HttpCodec.Metadata
import zio.http.codec.PathCodec.path
import zio.http.codec.{Doc, HttpCodec}
import zio.http.endpoint.Endpoint
import zio.http.endpoint.openapi.{OpenAPIGen, SwaggerUI}
import zio.http.{MediaType, Response, RoutePattern, Routes}
import zio.{URLayer, ZLayer}

class RoutesBuilderLive(private val diagnosticHandlers: DiagnosticHandlers) {
  def build(prefix: String, version: String): Routes[Any, Response] = {
    val epPing = Endpoint((RoutePattern.GET / prefix / "ping") ?? Doc.p("Diagnostic ping endpoint"))
      .query(HttpCodec.query[String]("payload").annotate(Metadata.Optional()).optional ?? Doc.p("An optional payload to include in the response"))
      .out[String](MediaType.text.plain) ?? Doc.p("200 Pong + payload if provided")

    val epVersion = Endpoint((RoutePattern.GET / prefix / "version") ?? Doc.p("Diagnostic version endpoint"))
      .out[String](MediaType.text.plain) ?? Doc.p("200 Version string")

    val openAPI = OpenAPIGen.fromEndpoints("Diagnostic", "SNAPSHOT", epPing, epVersion)
    val routes =
      epPing.implement(diagnosticHandlers.diagPingHandler).toRoutes
        ++ epVersion.implement(diagnosticHandlers.diagVersionHandler(version)).toRoutes
        ++ SwaggerUI.routes("docs" / "openapi", openAPI)
    routes
  }
}

object RoutesBuilderLive {
  def apply(diagnosticHandlers: DiagnosticHandlers): RoutesBuilderLive = new RoutesBuilderLive(diagnosticHandlers)
  val layer: URLayer[DiagnosticHandlers, RoutesBuilderLive] = ZLayer.fromFunction(RoutesBuilderLive(_))
}
