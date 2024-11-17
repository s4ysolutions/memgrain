import sbt.Keys.libraryDependencies

import scala.collection.Seq


lazy val commonSettings = Seq(
  scalaVersion := "3.5.2",
  organization := "solutions.s4y.memgrain",
  maintainer := "sergey@s4y.solutions",
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio" % "2.1.12",
    "dev.zio" %% "zio-http" % "3.0.1",
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "memgrain",
    version := "0.1.0-SNAPSHOT",

    libraryDependencies ++= Seq(
      // "dev.zio" %% "zio-streams" % "2.1.12",
      "dev.zio" %% "zio-test" % "2.1.12" % Test,
      "dev.zio" %% "zio-test-sbt" % "2.1.12" % Test
      // "org.scalameta" %% "munit" % "1.0.0" % Test
    )
  ).aggregate(
    edgeServiceRest
  )
/*
lazy val authService = project
  .in(file("auth-service"))
  .settings(
    version := "0.1.0-SNAPSHOT",
  )

lazy val userProfileService = project
  .in(file("users-directory-service"))
  .settings(
    version := "0.1.0-SNAPSHOT",
  )
*/

lazy val edgeServiceRest = (project in file("edge-service-rest"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    commonSettings,
    name := "edge-service-rest",
    version := "0.1.0-SNAPSHOT",
  )
