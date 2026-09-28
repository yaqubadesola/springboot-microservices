# Spring Boot Microservices Examples

A collection of example Spring Boot microservices projects demonstrating common microservice patterns and integrations (message brokers, service discovery, config server, API gateway) along with a small React frontend.

Summary

- Multiple example sets are included to showcase different integration patterns:
  - `springboot-kafka-microservices` — example services using Apache Kafka for messaging
  - `springboot-rabbitmq-microservices` — example services using RabbitMQ for messaging
  - `springboot-microservices` — Spring Cloud examples (API Gateway, Config Server, Service Registry, sample domain services, React frontend)
  - `springboot-rest-api` / `springboot-restful-webservices` — focused REST API examples
  - `v3/` and `v4/` — alternate/project-versioned copies of the microservices examples

Repository structure (high level)

- springboot-kafka-microservices/
  - base-domains/ (shared domain objects)
  - email-service/
  - order-service/
  - stock-service/
- springboot-microservices/
  - api-gateway/
  - config-server/
  - service-registry/
  - department-service/
  - employee-service/
  - organization-service/
  - react-frontend/ (React app for demo UI)
- springboot-rabbitmq-microservices/ (email/order/stock examples using RabbitMQ)
- springboot-rest-api/ and springboot-restful-webservices/ (additional REST examples)

Tech stack

- Java + Spring Boot (+ Spring Cloud for discovery/config/gateway)
- Build: Maven (mvn / ./mvnw / mvnw.cmd provided per module)
- Messaging: Apache Kafka and RabbitMQ (in different example sets)
- Frontend: React (create-react-app), Node.js/npm

Quick start (recommended order)

1. Start the Config Server (if using Spring Cloud examples):
   - Windows:

     mvnw.cmd spring-boot:run (run inside the module folder)

   - macOS / Linux:

     ./mvnw spring-boot:run

2. Start Service Registry (Eureka/consul depending on the example)
3. Start messaging broker (Kafka or RabbitMQ) when working with the Kafka/RabbitMQ example sets
4. Start individual services (department, employee, organization, email, order, stock, etc.)
5. Start the API Gateway last (it routes requests to backend services)
6. Start the React frontend:

   cd springboot-microservices/react-frontend
   npm install
   npm start

Build and run jar (any module)

- Build: `mvn -f <module>/pom.xml clean package`
- Run: `java -jar <module>/target/<artifact>.jar`

Notes

- Use the platform-specific wrapper in each module: on Windows use `mvnw.cmd`, on macOS/Linux use `./mvnw`.
- Many microservice examples expect the Config Server and Service Registry to be available before the services register — start those first when running Spring Cloud examples.
- There is no single orchestration file included (e.g., docker-compose) in the repository root; you can add one if you want a reproducible multi-service startup.

Contributing

- Feel free to open issues or PRs to add run scripts, docker-compose manifests, or to modernize example versions.

License

- This repository does not include a LICENSE file by default. Add one if you intend to publish or share this work.
