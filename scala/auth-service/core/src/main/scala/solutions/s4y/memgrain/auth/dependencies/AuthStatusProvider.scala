package solutions.s4y.memgrain.auth.dependencies

import solutions.s4y.memgrain.auth.AuthToken

trait AuthStatusProvider {
  def verifyToken(token: AuthToken): Task[Boolean]
}
