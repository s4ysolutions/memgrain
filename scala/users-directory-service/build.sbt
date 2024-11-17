name := "users-directory-service"

lazy val usersDirectoryCore = project
  .in(file("core"))

lazy val usersDirectoryProviderRTDB = project
  .in(file("provider-rtdb"))
  .dependsOn(usersDirectoryCore)

lazy val usersDirectoryProviderDummy = project
  .in(file("provider-dummy"))
  .dependsOn(usersDirectoryCore)
