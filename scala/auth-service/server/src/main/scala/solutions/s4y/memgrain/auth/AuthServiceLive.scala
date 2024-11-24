package solutions.s4y.memgrain.auth

import solutions.s4y.memgrain.auth.dependencies.AuthStatusProvider
import zio.{Task, UIO, URLayer, ZLayer}

private final class AuthServiceLive(val authStatusProvider: AuthStatusProvider) extends AuthService {
  def verifyToken(token: AuthToken): Task[Boolean] =
    authStatusProvider.verifyToken(token)
}

object AuthServiceLive {
  def apply(authStatusProvider: AuthStatusProvider): AuthServiceLive = new AuthServiceLive(authStatusProvider)
  val layer: URLayer[AuthStatusProvider, AuthService] = ZLayer.fromFunction(AuthServiceLive(_))
}
