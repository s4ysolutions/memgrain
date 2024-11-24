package solutions.s4y.memgrain.edge.rest.handlers

import solutions.s4y.memgrain.edge.rest.errors.InternalServerError
import zio.http.Handler

trait AuthHandlers {
    val verifyTokenHandler: Handler[Any, InternalServerError, String, Boolean]
}
