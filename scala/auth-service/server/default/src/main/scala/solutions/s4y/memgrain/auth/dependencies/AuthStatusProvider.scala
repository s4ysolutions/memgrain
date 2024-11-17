package solutions.s4y.memgrain.auth.dependencies

import solutions.s4y.memgrain.auth.AuthToken
import zio.Task


trait AuthStatusProvider {
  def verifyToken(token: AuthToken): Task[Boolean]
}
