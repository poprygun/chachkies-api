# WIP: Spring Boot Kotlin application using OpenAPI (formerly Swagger Specification)

OpenApi UI will be generated during the runtime based on config/api.yml and available [here](http://localhost:8080/api/swagger-ui.html)

## Local Development

Start the application locally

```bash
./gradlew bootRun
```

## Kubernetes environment

```bash
skaffold build
skaffold run
```
