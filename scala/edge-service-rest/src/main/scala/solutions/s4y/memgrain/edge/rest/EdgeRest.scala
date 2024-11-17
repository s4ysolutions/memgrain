package solutions.s4y.memgrain.edge.rest

import solutions.s4y.memgrain.edge.rest.routes.PingRoutes
import zio.{ZIO, ZIOAppDefault}
import zio.http.Server

object EdgeRest extends ZIOAppDefault:
 def run: ZIO[Any, Throwable, Nothing] =
   Server
     .serve(PingRoutes())
     .provide(
       Server.defaultWithPort(8080)
     )

