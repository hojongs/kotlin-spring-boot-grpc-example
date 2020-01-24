# Kotlin Spring Boot Grpc Example
## Usage
### Run Local Server
```shell script
./gradlew bootRun
```
- HTTP API (port 8383)
  - `/hello`
- GRPC API (port 6565)
  - `grpcStub.SayHello(HelloReqeuest)`
### Generate Proto
```shell script
./gradlew generateProto
```

## Used features

- Kotlin Language
- Gradle build tool
  - Kotlin DSL
- Spring Boot 2
  - Webflux
  - Reactor
- GRPC (TODO)
  - Protocol Buffer (protobuf)
- JUnit 5
- Mockk
- Kotlintest-assertions
