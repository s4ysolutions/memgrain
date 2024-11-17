name := "auth-service"

lazy val authCore = project
  .in(file("core"))

lazy val authProviderFirebase = project
  .in(file("provider-firebase"))
  .dependsOn(authCore)

lazy val authProviderDummy = project
  .in(file("provider-dummy"))
  .dependsOn(authCore)

// libraryDependencies +=
//  "me.vican.jorge" %% "monix-grpc-codegen" % "0.0.0+46-e1776191"
