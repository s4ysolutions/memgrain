package solutions.s4y.memgrain.edge.rest.routes

import zio._
import zio.http._

object PingRoutes:
  def apply(): Routes[Any, Response] = Routes(
    Method.GET / "ping" -> handler {
      ZIO.succeed(Response.text("pong"))
    }
  )