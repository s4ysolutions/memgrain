package solutions.s4y.memgrain.edge.rest.config

import zio.Config
import zio.config.*
import zio.config.magnolia.deriveConfig
import zio.schema.annotation.description

@description("Configuration for the edge rest service")
case class EdgeRestConfig(
    @description("The port the edge rest service will be listening on")
    port: Int,
)

object EdgeRestConfig {
  implicit val configDescriptor: Config[EdgeRestConfig] =
    deriveConfig[EdgeRestConfig].nested("edge-rest")
}
