# Memgrain (Scala 3 backend)

A backend system for user management, implemented in Scala.

## Architecture
The system follows a microservices architecture, where each service consists of
three key components:
-	**Core**: Contains shared code used by both the client and the server.
-	**Server**: Implements the business logic for the service.
-	**Client**: Provides an interface to the server and abstracts away the underlying transport layer.

The only exception is the **Edge** service, which consists of a single thin layer and is
responsible for routing REST requests to the appropriate microservices.

The **Server** component often defines a set of dependencies that are injected into the
server and provides access to external infrastructure such as databases, frameworks,
and APIs. The implementations of these dependencies are typically located in the
`providers` package.

The **Client** component defines an API for the consumers of the service and provides an implementation
using a specific transport layer. It is responsible for tasks such as serializing and deserializing
data, making requests to the server, and handling responses.

The system is composed of several microservices:
- [Edge](edge-service): REST API that serves as a gateway to the other services
- [Auth](auth-service): Service that checks if a token is valid
- [Users directory](users-directory-service): Service that manages user data


## Deliverables
 - [x] live endpoint: http://prod2.s4y.solutions:8081/diag/ping
 - [x] Swagger OpenAPI endpoint: http://prod2.s4y.solutions:8081/docs/openapi

## Work breakdown
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

