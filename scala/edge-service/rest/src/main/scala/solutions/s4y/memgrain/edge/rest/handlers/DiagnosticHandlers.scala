package solutions.s4y.memgrain.edge.rest.handlers

import zio.http.Handler

trait DiagnosticHandlers {
  val ping: Handler[Any, Nothing, Option[String], String]

  def version(version: String): Handler[Any, Any, Nothing, String]
}