# Memgrain (Scala 3 backend)

Memgrain is a follow-up project to the Memrise service gone in the beginning of 2024.

It is intended to provide a service for learning words and phrases in a foreign language.

## Microservices architecture overview

The project, being in its early stages and without a clear vision of future requirements, is designed for
maximum flexibility with stateless microservices' architecture.
The common microservice's structura consists of three main components: **Edge**, **core**, and **provider**.
Currently, the Edgy component is not implemented, and the core is a monolithic application.

- **Core**:  
  Acts as the central logic layer, defining dependencies on providers through simple, minimalistic interfaces.
  These interfaces specify only what is necessary for the core’s functionality, making providers easily replaceable.
  This design ensures the core is independent of specific implementations (e.g., external authentication systems)
  and minimizes code duplication by consolidating most of the service logic here.

- **Providers**:  
  Lightweight components responsible for implementing the interfaces defined by the core. They deliver the
  specific functionality required, serving as interchangeable adapters to external systems or tools.

- **API**:  
  Serves as the bridge between the core and the outside world. In its simplest form, the API is a thin
- wrapper around the core, enabling the service to be accessed via in-process calls.

It is important to note that the microservices architecture does not imply implementing separate microservices at
the current stage of the project. On the contrary, the final build is intended to be a monolithic application
to prioritize rapid development and minimize maintenance costs.

The highly decoupled architecture is designed to facilitate future changes, making them as seamless as possible.
This approach ensures the service is well-prepared to handle performance challenges and scalability demands
as the user base grows.
