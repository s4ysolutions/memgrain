package solutions.s4y.memgrain.edge.rest

import zio.{Config, Layer, TaskLayer, ZIO, ZLayer}
import zio.config.*
import zio.config.magnolia.deriveConfig
import zio.schema.annotation.description

@description("Configuration for the edge rest service")
case class ServiceConfig(
    @description("The port the edge rest service will be listening on")
    port: Int,
)

object ServiceConfig {
  implicit val configDescriptor: Config[ServiceConfig] =
    deriveConfig[ServiceConfig].nested("edge-rest")
}
