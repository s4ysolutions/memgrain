package testapp

import zio.*

object TestApp extends ZIOAppDefault {
  private val env: ZEnvironment[Int & String] = ZEnvironment(42, "Hello, World!")
  val layer: ULayer[Int & String] = ZLayer.succeed(42) ++ ZLayer.succeed("Hello, World!")
  private val workflow: RIO[Int & String, Unit] = for {
    intValue <- ZIO.service[Int]
    stringValue <- ZIO.service[String]
    _ <- Console.printLine("Hello, World! " + intValue + "-" + stringValue)
  } yield ()

  def run: Task[Unit] = workflow.provideEnvironment(env)
}
