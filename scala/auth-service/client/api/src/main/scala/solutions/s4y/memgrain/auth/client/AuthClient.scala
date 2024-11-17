package solutions.s4y.memgrain.auth.client

import solutions.s4y.memgrain.auth.AuthToken
import zio.Task

trait AuthClient {
  def verifyToken(token: AuthToken): Task[Boolean]
}
