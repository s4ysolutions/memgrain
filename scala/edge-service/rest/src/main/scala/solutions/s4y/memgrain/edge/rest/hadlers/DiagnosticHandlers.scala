package solutions.s4y.memgrain.edge.rest.hadlers

import zio.{UIO, ZIO}

trait DiagnosticHandlers {
  def diagPingHandler(payload: Option[String]): UIO[String]
  def diagVersionHandler(version: String): Unit => ZIO[Any, Nothing, String]
}