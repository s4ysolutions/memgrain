package solutions.s4y.memgrain.edge.rest.routes

import zio.*
import zio.http.*
import zio.http.codec.HttpCodec.Metadata
import zio.http.codec.{Doc, HttpCodec, PathCodec}
import zio.http.endpoint.Endpoint

private val epPing =
  Endpoint((RoutePattern.GET / "ping") ?? Doc.p("Diagnostic ping endpoint"))
    .query(HttpCodec.query[String]("payload").annotate(Metadata.Optional()).optional ?? Doc.p("An optional payload to include in the response"))
     .out[String](MediaType.text.plain) ?? Doc.p("200 Pong + payload if provided")

private val rPing = epPing.implement{
  payload => ZIO.succeed{
    payload match {
      case None => "pong"
      case Some(payload) => s"pong $payload"
    }
  }
}

val diagnosticEndpoints = List(epPing)
val diagnosticRoutes = Routes(rPing)