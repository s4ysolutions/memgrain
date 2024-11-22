import sbt.Keys.libraryDependencies

import scala.collection.Seq

val zioVersion = "2.1.13"

lazy val commonSettings = Seq(
  scalaVersion := "3.5.2",
  organization := "solutions.s4y.memgrain",
  maintainer := "sergey@s4y.solutions",
  version := "0.1.0-SNAPSHOT",
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio" % "2.1.12",
    "dev.zio" %% "zio" % zioVersion,
    "dev.zio" %% "zio-test" % zioVersion % Test
  )
)
lazy val authCore = project
  .in(file("auth-service/core"))
  .settings(
    commonSettings,
    name := "auth-service-core",
  )
lazy val authServerApi = project
  .in(file("auth-service/server/api"))
  .settings(
    commonSettings,
    name := "auth-service-server-api",
  ).dependsOn(authCore)
lazy val authServer = project
  .in(file("auth-service/server/default"))
  .settings(
    commonSettings,
    name := "auth-service-server-default",
  ).dependsOn(authServerApi)
lazy val authServerStatusProvider = project
  .in(file("auth-service/server/providers/dummy"))
  .settings(
    commonSettings,
    name := "auth-service-status-provider",
  ).dependsOn(authServer)
lazy val authClientApi = project
  .in(file("auth-service/client/api"))
  .settings(
    commonSettings,
    name := "auth-service-client-api",
  ).dependsOn(authCore)
lazy val authClient = project
  .in(file("auth-service/client/inproc"))
  .settings(
    commonSettings,
    name := "auth-service-client-inproc",
  ).dependsOn(authClientApi, authServer)

/** ******************************************
  * Edge service
  * ******************************************
  */
lazy val edgeServiceRest = (project in file("edge-service/rest"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    commonSettings,
    name := "edge-service-rest",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-http" % "3.0.1",
      "dev.zio" %% "zio-config" % "4.0.2",
      "dev.zio" %% "zio-config-magnolia" % "4.0.2",
      "dev.zio" %% "zio-config-typesafe" % "4.0.2",
      "dev.zio" %% "zio-config-refined" % "4.0.2"
    )
  )
  .dependsOn(authClient)

