package solutions.s4y.memgrain.edge.rest.routes

import zio.http.codec.PathCodec._
import zio.http.endpoint.openapi.{OpenAPIGen, SwaggerUI}

val openAPI = OpenAPIGen.fromEndpoints(title = "Memgrain", version = "0.1.0", endpoints = diagnosticEndpoints)
val openApiRoute = SwaggerUI.routes("docs" / "openapi", openAPI)

val root = diagnosticRoutes ++ openApiRoute

/*
val root =
  literal("v1") /
    (literal("diag") / diagnosticRoutes)
  ++ openApiRoute
 */


