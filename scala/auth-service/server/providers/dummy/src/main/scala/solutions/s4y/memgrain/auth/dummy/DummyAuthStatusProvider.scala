package solutions.s4y.memgrain.auth.dummy

import solutions.s4y.memgrain.auth.AuthToken
import solutions.s4y.memgrain.auth.dependencies.AuthStatusProvider
import zio._

class DummyAuthStatusProvider extends AuthStatusProvider {
  def verifyToken(token: AuthToken): Task[Boolean] =
    ZIO.sleep(500.milliseconds) *> ZIO.succeed(true)
}
