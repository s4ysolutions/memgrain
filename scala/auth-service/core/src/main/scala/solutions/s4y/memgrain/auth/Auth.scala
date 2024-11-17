package solutions.s4y.memgrain.auth

import monix.eval.Task
import solutions.s4y.memgrain.auth.dependencies.AuthStatusProvider

class Auth(val authStatusProvider: AuthStatusProvider) {
  def verifyToken(token: AuthToken): Task[Boolean] = authStatusProvider.verifyToken(token)
}
