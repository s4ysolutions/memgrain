package solutions.s4y.memgrain.edge.rest.handlers

import zio.http.Handler
import zio.{ULayer, ZIO}

class DiagnosticHandlersLive extends DiagnosticHandlers {
  val ping: Handler[Any, Nothing, Option[String], String] =
    Handler.fromFunctionZIO {
      case Some(value) => ZIO.succeed(s"pong: $value")
      case None => ZIO.succeed("pong")
    }

  def version(version: String): Handler[Any, Any, Nothing, String] =
    Handler.fromZIO(ZIO.succeed(version))
}

object DiagnosticHandlersLive extends DiagnosticHandlersLive {
  def apply(): DiagnosticHandlersLive = new DiagnosticHandlersLive()
  // it seems to be over-engineered, but
  // for the sake of consistency, I will keep it
  val layer: ULayer[DiagnosticHandlers] = zio.ZLayer.succeed(DiagnosticHandlersLive())
}