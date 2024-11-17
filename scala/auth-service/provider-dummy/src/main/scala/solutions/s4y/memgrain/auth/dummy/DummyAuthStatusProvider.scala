package solutions.s4y.memgrain.auth.dummy

import solutions.s4y.memgrain.auth.AuthToken
import solutions.s4y.memgrain.auth.dependencies.AuthStatusProvider

import scala.concurrent.duration.DurationInt

class DummyAuthStatusProvider extends AuthStatusProvider {
  def verifyToken(token: AuthToken): Task[Boolean] = {
      Task
        .sleep(500.millis)
        .map(_ => true)
  }
}
