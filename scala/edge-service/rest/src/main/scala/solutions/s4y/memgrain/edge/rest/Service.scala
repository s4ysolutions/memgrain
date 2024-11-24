package solutions.s4y.memgrain.edge.rest

import solutions.s4y.memgrain.auth.dummy.AuthStatusProviderDummy
import solutions.s4y.memgrain.auth.{AuthServiceLive, AuthTokenFactoryLive}
import solutions.s4y.memgrain.edge.rest.handlers.{AuthHandlersLive, DiagnosticHandlersLive}
import zio.*
import zio.config.typesafe.TypesafeConfigProvider
import zio.http.Server

trait EdgeRest {
  val run: Task[Unit]
}

object EdgeRestService extends ZIOAppDefault {
  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    Runtime.setConfigProvider(
      TypesafeConfigProvider.fromResourcePath()
    )

  private val server =
    for {
      routeBuilder <- ZIO.service[RoutesBuilderLive]
      config <- ZIO.config[ServiceConfig]
      server <- Server
        .serve(routeBuilder.build("/", "1.0.0"))
        .provide(Server.defaultWithPort(config.port))
    } yield server

  def run: ZIO[Any, Throwable, Unit] =
    server.provide(
      AuthStatusProviderDummy.layer,
      AuthServiceLive.layer,
      AuthTokenFactoryLive.layer,
      DiagnosticHandlersLive.layer,
      AuthHandlersLive.layer,
      RoutesBuilderLive.layer)
}
