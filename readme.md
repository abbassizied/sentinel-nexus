# 🔒 Sentinel Nexus: Secure Microservices Observatory
*Production-ready Spring Boot microservices template with OAuth2 security 
and full observability stack (Prometheus, Grafana, Loki, Jaeger)*

> (Meaning: "Sentinel" represents security (Keycloak) and monitoring (observability stack), "Nexus" represents the central connection point (API Gateway) and service integration)

## GitHub Repository Structure:

- Submodules: config-server, gateway-service, discovery-service, service-a, service-b 

```
# io.github.abbassizied
sentinel-nexus/ (root project - monorepo)
├── config-server/
├── gateway-service/
├── discovery-service/
├── service-a/
├── service-b/
├── frontend/
├── docker-compose/
│   ├── keycloak-setup/
├── compose.yml
├── README.md
```

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

4. Projects dependencies: 
	- Config Server: `Config Server`, 
	- Gateway: `Gateway`, `Config Client`, `OAuth2 Client`
	- Service discovery: 
	- Services ( service-a, service-b): `Web`, `Actuator`, `Config Client`, `OAuth2 Resource Server`

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

##

- 
- 

##

- 
- 

---
