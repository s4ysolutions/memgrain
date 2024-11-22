package solutions.s4y.memgrain.edge.rest.hadlers

import zio.{UIO, ZIO}

def diagPingHandler(payload: Option[String]): UIO[String] =
  ZIO.succeed(payload.map(value => s"pong: $value").getOrElse("Pong"))

def diagVersionHandler(version: String): Unit => ZIO[Any, Nothing, String] = _ => ZIO.succeed(version)
