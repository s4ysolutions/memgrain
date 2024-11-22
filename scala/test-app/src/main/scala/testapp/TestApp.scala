package testapp

import zio.*
import zio.Config
import zio.Config.{int, string}
import zio.config.*
import zio.config.typesafe.TypesafeConfigProvider
import zio.config.magnolia.deriveConfig

case class HttpServerConfig(host: String, port: Int)

object HttpServerConfig {
  implicit val configDescriptor: Config[HttpServerConfig] =
    deriveConfig[HttpServerConfig].nested("HttpServerConfig")
}

case class MyConfig(host: String, port: Int)

//HTTPSERVERCONFIG_HOST = localhost: HTTPSERVERCONFIG_PORT = 8080
/*
val myConfig: Config[MyConfig] =
  (string("HTTPSERVERCONFIG_HOST") zip int("HTTPSERVERCONFIG_PORT")).to[MyConfig]
 */

val myConfig = deriveConfig[MyConfig].nested("HttpServerConfig")
val map =
    Map(
      "HTTPSERVERCONFIG_HOST" -> "xyz",
      "HttpServerConfig.host" -> "xyz",
      "HTTPSERVERCONFIG_PORT" -> "8888",
      "HttpServerConfig.port" -> "8888",
    )

val source = ConfigProvider.fromMap(map)

object MainApp extends ZIOAppDefault {
  /*
  def run = for {
    config <- source.load(HttpServerConfig.configDescriptor)
    _ <- Console.printLine(
      "Application started with following configuration:\n" +
        s"\thost: ${config.host}\n" +
        s"\tport: ${config.port}"
    )
  } yield ()
   */
  /*
  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    Runtime.setConfigProvider(
      ConfigProvider.fromMap(map)
    )
   */
  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    Runtime.setConfigProvider(
      //ConfigProvider.fromMap(map)
      TypesafeConfigProvider.fromResourcePath()
    )
    /*

  def run: Task[Unit] = for {
    _ <- Console.printLine("Hello, World!")
    config <- ZIO.config[HttpServerConfig]
    _ <- Console.printLine(
      "Application started with following configuration:\n" +
        s"\thost: ${config.host}\n" +
        s"\tport: ${config.port}"
    )
  } yield ()*/

  val workflow: Task[Unit] =
    //ZIO.config[MyConfig].flatMap { config =>
    ZIO.config[HttpServerConfig].flatMap { config =>
      Console.printLine(
        "Application started with following configuration:\n" +
          s"\thost: ${config.host}\n" +
          s"\tport: ${config.port}"
      )
    }

  def run = workflow
}
