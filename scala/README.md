# Memgrain (Scala 3 backend)

A ZIO-based backend application that serves a REST API and emulates communcation with microservices.

 - [x] live endpoint: http://prod2.s4y.solutions:8080/ping
 - [x] Swagger OpenAPI endpoint: http://prod2.s4y.solutions:8080/docs/openapi
 - [x] [REST Edge microservice](edge-service) makes calls to others microservices
   - [x] [Server](edge-service/rest/src/main/scala/solutions/s4y/memgrain/edge/rest/EdgeRest.scala)
   - [x] [Routing](edge-service/rest/src/main/scala/solutions/s4y/memgrain/edge/rest/routes/diagnostic.scala)
   - [x] [Root Routt](edge-service/rest/src/main/scala/solutions/s4y/memgrain/edge/rest/routes/root.scala)
- [ ] [Auth microservice](auth-service) checks if a token is valid
  - [ ] [Core](auth-service/core/src/main/scala/solutions/s4y/memgrain/auth/AuthToken.scala) Definitions common to server and client
  - [ ] [Server](auth-service/server/default/src/main/scala/solutions/s4y/memgrain/auth/Auth.scala) Business logic
  - [x] [Server dependencies](auth-service/server/default/src/main/scala/solutions/s4y/memgrain/auth/dependencies/AuthStatusProvider.scala) Interface of external auth providers
  - [x] [Dummy provider](auth-service/server/providers/dummy/src/main/scala/solutions/s4y/memgrain/auth/dummy/DummyAuthStatusProvider.scala) Dummy provider
  - [ ] [Firebase provider](auth-service/server/providers/firebase) Dummy provider

