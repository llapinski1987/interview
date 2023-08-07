# Getting Started

##### General Info

Just some interview APP with microservice architecture.

##### Module desciptions

1. **service-discovery**

As the name suggests its just simple service discovery for microservices

2. **backend-codes**

Main module with codes business logic

Logic Contains:

* Scheduler running once every day at midnight (problem of many instances)
* Code service that generate missing codes
* POST /codes/v1/ for getting the batch of codes

Swagger - http://localhost:8081/swagger-ui/index.html#/

3. **backend-organizations**

Module for storing data of organizations (its empty right now)

**TODO**

* Add organization schema by flyway or some other migration tool
* Add Client for getting the max numbers of inactive codes (FeignClient)
* Maybe add Cache for Organizations client (i guess properties update wont be frequent)

##### App start

To start the app first start ServiceDiscovery app then CodesApplication app

##### Examples of using API

Use OpenApi http://localhost:8081/swagger-ui/index.html#/ 
