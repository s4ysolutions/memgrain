package solutions.s4y.memgrain.auth.client

import solutions.s4y.memgrain.auth.{AuthService, AuthToken}
import zio.Task

class InProcAuthClient(private val authService: AuthService) extends AuthClient {
  def verifyToken(token: AuthToken): Task[Boolean] =
    authService.verifyToken(token)
}
