package solutions.s4y.memgrain.auth

import zio.Task

trait AuthService{
  def verifyToken(token: AuthToken): Task[Boolean]
}
