# 🔒 Sentinel Nexus: Secure Microservices Observatory
*Production-ready Spring Boot microservices template with OAuth2 security 
and full observability stack (Prometheus, Grafana, Loki, Jaeger)*

> (Meaning: "Sentinel" represents security (Keycloak) and monitoring (observability stack), "Nexus" represents the central connection point (API Gateway) and service integration)

---

## Refs:

- [abbassizied/ams-ms-k8s-ziedab](https://github.com/abbassizied/ams-ms-k8s-ziedab/tree/main)
- [abbassizied/ams-ms-docker-compose-ziedab](https://github.com/abbassizied/ams-ms-docker-compose-ziedab)
- [abbassizied/ams-ms-on-k8s](https://github.com/abbassizied/ams-ms-on-k8s)
- [abbassizied/ams-ms](https://github.com/abbassizied/ams-ms)

- [spring-petclinic/spring-petclinic-microservices-config](https://github.com/spring-petclinic/spring-petclinic-microservices-config) +  [spring-petclinic/spring-petclinic-microservices](https://github.com/spring-petclinic/spring-petclinic-microservices/tree/main) + [spring-petclinic/spring-petclinic-cloud](https://github.com/spring-petclinic/spring-petclinic-cloud)

- [mohamedYoussfi/micro-services-app](https://github.com/mohamedYoussfi/micro-services-app)
- [mohamedYoussfi/observability-spring-grafana](https://github.com/mohamedYoussfi/observability-spring-grafana)


## GitHub Repository Structure:

- Submodules: config-server, gateway-service, discovery-service, service-a, service-b, secured-service
- Other services: angular-frontend, react-frontend  

```
# io.github.abbassizied
sentinel-nexus/ (root project - monorepo)
├── docker/
│   ├── keycloak-setup/
├── config-server/   # Centralized config management (optional)
├── gateway-service/ # API gateway (entry point for requests)
├── discovery-service/ # Eureka service registry
│   ### Business logic microservice ###
├── service-a/ # port: 8081 
├── service-b/ # port: 8082
├── secured-service/ # port: 8083
├── angular-frontend/ # port: 4200
├── react-frontend/ # port: 3000
├── compose.yml
│   ### Docker files ###
├── Dockerfile.config-server  
├── Dockerfile.discovery-service 
├── Dockerfile.gateway-service 
├── Dockerfile.service-a 
├── Dockerfile.service-b
├── Dockerfile.secured-service
├── Dockerfile.angular-frontend
├── Dockerfile.react-frontend
├── README.md
```

## Service Startup Order

- To ensure proper functionality, please start the services in the following sequence:
	- Start **Config Server** first
	- Start **Eureka Server** (discovery-service) next
	- Start **Service-A**, **Service-B** and **secured-service**
	- Finally, start **Gateway** last

## How to Clean & Update Dependencies in a Spring Boot Multi-Module Project

```sh
# Clean
mvn clean

# Check for updates
mvn versions:display-dependency-updates

# Update versions (careful!)
mvn versions:use-latest-versions

# Rebuild
mvn install
```

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
java -jar service-b/target/secured-service-0.0.1-SNAPSHOT.jar
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

# For secured-service
mvn spring-boot:run -pl secured-service

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
	- Gateway: `spring-cloud-starter-gateway`, `spring-cloud-starter-config`,  `spring-cloud-starter-netflix-eureka-client`, `spring-boot-starter-oauth2-resource-server`
	- Service discovery: `spring-cloud-starter-netflix-eureka-server`, `spring-boot-starter-web`  
	- Services ( service-a, service-b): `spring-boot-starter-web`, `spring-cloud-starter-netflix-eureka-client`, `spring-boot-starter-actuator`, `spring-cloud-starter-config`
   - optional: JPA, actuator, etc.

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
http://localhost:8989/service-a/props # HTTP Methods: GET
http://localhost:8989/service-a/config-maps # HTTP Methods: GET

# Service B:
http://localhost:8989/service-b/props # HTTP Methods: GET
http://localhost:8989/service-b/config-maps # HTTP Methods: GET
http://localhost:8989/service-b/products # HTTP Methods: GET

# Secured Service:
http://localhost:8989/secured-service/user # HTTP Methods: GET
http://localhost:8989/secured-service/admin # HTTP Methods: GET
http://localhost:8989/secured-service/what-i-can-do # HTTP Methods: GET, POST, PUT, DELETE

#############################
### Direct Service Access ###
#############################

# Service A (Port 8081):
http://localhost:8081/props # HTTP Methods: GET
http://localhost:8081/config-maps # HTTP Methods: GET

# Service B (Port 8082):
http://localhost:8082/props # HTTP Methods: GET
http://localhost:8082/config-maps # HTTP Methods: GET
http://localhost:8082/products # HTTP Methods: GET

# Secured Service:
http://localhost:8983/secured-service/user # HTTP Methods: GET
http://localhost:8983/secured-service/admin # HTTP Methods: GET
http://localhost:8983/secured-service/what-i-can-do # HTTP Methods: GET, POST, PUT, DELETE
```
 

##

- 
- 

---

## 🧪 Rebuild Images (just in case)

If you’ve previously built with bad config, rebuild:

```bash
docker-compose down -v --remove-orphans
docker-compose build --no-cache
docker-compose up
```

---