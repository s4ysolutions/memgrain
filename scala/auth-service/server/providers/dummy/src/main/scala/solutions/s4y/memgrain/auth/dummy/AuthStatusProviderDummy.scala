package solutions.s4y.memgrain.auth.dummy

import solutions.s4y.memgrain.auth.{AuthToken, AuthTokenString}
import solutions.s4y.memgrain.auth.dependencies.AuthStatusProvider
import zio._

class AuthStatusProviderDummy extends AuthStatusProvider {
  def init(): UIO[Unit] = ZIO.sleep(500.milliseconds)

  def verifyToken(token: AuthToken): Task[Boolean] =
    (token match {
      // return false if token ends with '0'
      case t: AuthTokenString => ZIO.succeed(!t.toString.endsWith("0"))
      // throw if token is not AuthTokenString
      case _ => ZIO.fail(new IllegalArgumentException("Invalid token type"))
    }).delay(500.milliseconds)
}

object AuthStatusProviderDummy {
  def apply(): AuthStatusProviderDummy = new AuthStatusProviderDummy()

  val layer: TaskLayer[AuthStatusProvider] =
    ZLayer {
      for {
        provider <- ZIO.succeed(AuthStatusProviderDummy())
        _ <- provider.init()
      } yield provider
    }
}
