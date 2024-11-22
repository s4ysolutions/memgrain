package solutions.s4y.memgrain.edge.rest.routes

import solutions.s4y.memgrain.edge.rest.hadlers.{diagPingHandler, diagVersionHandler}
import zio.*
import zio.http.*
import zio.http.codec.HttpCodec.Metadata
import zio.http.codec.{Doc, HttpCodec, PathCodec}
import zio.http.codec.PathCodec.path
import zio.http.endpoint.openapi.{OpenAPIGen, SwaggerUI}
import zio.http.endpoint.Endpoint

def diagnosticRoutes(prefix: String, version: String): Routes[Any, Response] = {
  val epPing = Endpoint((RoutePattern.GET / prefix / "ping") ?? Doc.p("Diagnostic ping endpoint"))
    .query(HttpCodec.query[String]("payload").annotate(Metadata.Optional()).optional ?? Doc.p("An optional payload to include in the response"))
    .out[String](MediaType.text.plain) ?? Doc.p("200 Pong + payload if provided")
  
  val epVersion = Endpoint((RoutePattern.GET / prefix / "version") ?? Doc.p("Diagnostic version endpoint"))
    .out[String](MediaType.text.plain) ?? Doc.p("200 Version string")
  
  val openAPI = OpenAPIGen.fromEndpoints("Diagnostic", "SNAPSHOT", epPing, epVersion)
  val routes =
    epPing.implement(diagPingHandler).toRoutes
        ++ epVersion.implement(diagVersionHandler(version)).toRoutes
      ++ SwaggerUI.routes("docs" / "openapi" / prefix, openAPI)
  routes
}