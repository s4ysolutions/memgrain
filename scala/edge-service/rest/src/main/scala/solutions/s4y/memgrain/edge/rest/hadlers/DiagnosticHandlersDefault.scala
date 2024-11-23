package solutions.s4y.memgrain.edge.rest.hadlers

import zio.{UIO, ULayer, ZIO}

class DiagnosticHandlersDefault extends DiagnosticHandlers {
  def diagPingHandler(payload: Option[String]): UIO[String] =
    ZIO.succeed(payload.map(value => s"pong: $value").getOrElse("Pong"))

  def diagVersionHandler(version: String): Unit => ZIO[Any, Nothing, String] = _ => ZIO.succeed(version)
}

object DiagnosticHandlersDefault extends DiagnosticHandlersDefault {
  def apply(): DiagnosticHandlersDefault = new DiagnosticHandlersDefault()
  val layer: ULayer[DiagnosticHandlers] = zio.ZLayer.succeed(DiagnosticHandlersDefault())
}