# 🔒 Sentinel Nexus: Secure Microservices Observatory
*Production-ready Spring Boot microservices template with OAuth2 security 
and full observability stack (Prometheus, Grafana, Loki, Jaeger)*

> (Meaning: "Sentinel" represents security (Keycloak) and monitoring (observability stack), "Nexus" represents the central connection point (API Gateway) and service integration)

---

## GitHub Repository Structure:

- Submodules: config-server, gateway-service, discovery-service, service-a, service-b 

```
# io.github.abbassizied
sentinel-nexus/ (root project - monorepo)
├── config-server/   # Centralized config management (optional)
├── gateway-service/ # API gateway (entry point for requests)
├── discovery-service/ # Eureka service registry
├── service-a/ # Business logic microservice
├── service-b/
├── frontend/
├── docker/
│   ├── keycloak-setup/
├── compose.yml
├── README.md
```

## Service Startup Order

- To ensure proper functionality, please start the services in the following sequence:
	- Start **Config Server** first
	- Start **Eureka Server** (discovery-service) next
	- Start **Service-A** and **Service-B**
	- Finally, start **Gateway** last

## Running a Spring Boot Submodule from the Root Folder

### Option 1: Build and Run the JAR

```sh
# First build the entire project
mvn clean install -DskipTests

# Then run the specific module's JAR
java -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar
java -jar discovery-service/target/discovery-service-0.0.1-SNAPSHOT.jar
java -jar service-a/target/service-a-0.0.1-SNAPSHOT.jar
java -jar service-b/target/service-b-0.0.1-SNAPSHOT.jar
java -jar gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar
```

### Option 2: Using Maven from the Root Directory

```sh
# From the root directory (where the parent pom.xml is located)

# For config-server
mvn spring-boot:run -pl config-server 

# For discovery-service
mvn spring-boot:run -pl discovery-service 

# For service-a
mvn spring-boot:run -pl service-a

# For service-b
mvn spring-boot:run -pl service-b

# For gateway-service
mvn spring-boot:run -pl gateway-service
```
- **-pl** specifies the project (module) to run
- This will automatically handle dependencies between modules

---

## Component Details:

1. **Security**:
   - Keycloak integration for OAuth2/OIDC in all services
   - Pre-configured realm/docker setup in `/docker-compose/keycloak-setup`
   
2. Observability Stack:
	- Prometheus: Metrics scraping + storage
	- Grafana: Dashboards for metrics (Prometheus) + logs (Loki)
	- Loki: Log aggregation (with Promtail)
	- Jaeger: Distributed tracing

3. Docker Setup: Single compose.yml to launch:
	- All Spring Boot services (built from source)
	- Keycloak with pre-configured realm
	- Full observability stack
	- Dependent databases (PostgreSQL for Keycloak)

4. 🧩 Project dependencies by Service: 
	- Config Server: `spring-cloud-config-server`, `spring-boot-starter-web` 
	- Gateway: `spring-cloud-starter-gateway`, `spring-cloud-starter-netflix-eureka-client`, `OAuth2 Client`
	- Service discovery: `spring-cloud-starter-netflix-eureka-server`, `spring-boot-starter-web`  
	- Services ( service-a, service-b): `spring-boot-starter-web`, `spring-cloud-starter-netflix-eureka-client`, optional: JPA, actuator, etc.

## Technology Integration Plan:

1. **Service-to-Service Security**:
   - Keycloak adapters in each Spring service
   - JWT validation at API Gateway
   - Role-based access control

2. **Unified Observability**:
   - Spring Micrometer → Prometheus metrics
   - Sleuth/Zipkin → Jaeger tracing
   - Logback → Loki via Promtail
   - Grafana dashboards showing:
     - API Gateway latency
     - Service error rates
     - Keycloak authentication metrics
     - Jaeger trace waterfall diagrams

3. **Configuration Management**:
   - Config server connected to Git repo
   - Profile-specific properties for:
     - Service discovery
     - Keycloak endpoints
     - Tracing/Logging exporters

---

## API Endpoints for Testing

- Here's a breakdown of the API endpoints available for testing, categorized by access method:
```
###################################
### Via API Gateway (Port 8080) ###
###################################

# Service A:
http://localhost:8080/api/service-a/props
http://localhost:8080/api/service-a/config-maps

# Service B:
http://localhost:8080/api/service-b/props
http://localhost:8080/api/service-b/config-maps
http://localhost:8080/api/service-b/products

#############################
### Direct Service Access ###
#############################

# Service A (Port 8081):
http://localhost:8081/service-a/props
http://localhost:8081/service-a/config-maps

# Service B (Port 8082):
http://localhost:8082/service-b/props
http://localhost:8082/service-b/config-maps
http://localhost:8082/service-b/products
```
 

##

- 
- 

---
