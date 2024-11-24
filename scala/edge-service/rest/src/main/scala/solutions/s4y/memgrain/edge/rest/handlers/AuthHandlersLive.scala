package solutions.s4y.memgrain.edge.rest.handlers

import solutions.s4y.memgrain.auth.client.{AuthClient, InProcAuthClient}
import solutions.s4y.memgrain.auth.{AuthService, AuthTokenFactory}
import solutions.s4y.memgrain.edge.rest.errors.{*, given}
import zio.ZLayer
import zio.http.Handler

class AuthHandlersLive(private val authClient: AuthClient,
                       private val authTokenFactory: AuthTokenFactory)
  extends AuthHandlers {
  val verifyTokenHandler: Handler[Any, InternalServerError, String, Boolean] =
    Handler.fromFunctionZIO {
      token => authClient.verifyToken(authTokenFactory.from(token)).mapInternalServerError
    }
}

object AuthHandlersLive {
  def apply(authClient: AuthClient, authTokenFactory: AuthTokenFactory): AuthHandlersLive =
    new AuthHandlersLive(authClient, authTokenFactory)

  val layer: zio.URLayer[AuthService & AuthTokenFactory, AuthHandlers] =
    ZLayer.fromFunction((authService: AuthService, authTokenFactory: AuthTokenFactory) =>
      AuthHandlersLive(InProcAuthClient(authService), authTokenFactory))
}

