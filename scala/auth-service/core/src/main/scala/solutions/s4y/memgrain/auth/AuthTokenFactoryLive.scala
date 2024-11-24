package solutions.s4y.memgrain.auth

import zio.ZLayer

class AuthTokenFactoryLive extends AuthTokenFactory {
    def from(string: String): AuthToken = AuthTokenString(string)
}

object AuthTokenFactoryLive {
    def apply(): AuthTokenFactoryLive = new AuthTokenFactoryLive
    val layer: zio.URLayer[Any, AuthTokenFactory] = ZLayer.succeed(AuthTokenFactoryLive())
}
