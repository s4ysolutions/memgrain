package solutions.s4y.memgrain.edge.rest

import solutions.s4y.memgrain.edge.rest.config.EdgeRestConfig
import solutions.s4y.memgrain.edge.rest.routes.root
import zio.*
import zio.config.typesafe.TypesafeConfigProvider
import zio.http.Server

object EdgeRest extends ZIOAppDefault {
  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    Runtime.setConfigProvider(
      TypesafeConfigProvider.fromResourcePath()
    )

  def run: ZIO[Any, Throwable, Unit] =
    for {
      config <- ZIO.config[EdgeRestConfig]
      _ <- ZIO.succeed(
        println(
          s"Rest Edge is listening on:\n" +
            s"\tport: ${config.port}\n"))
      _ <- Server
        .serve(root)
        .provide(Server.defaultWithPort(config.port))
        .race(Console.readLine("Press Enter to exit\n\n").as(()))
    } yield ()
}
