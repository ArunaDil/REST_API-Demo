### RUN Mongod

`cd . && mongod`


### RUN Package

`mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar`


### RUN Docker

`docker build -t springio/gs-spring-boot-docker .`
`docker run -p 8080:8080 springio/gs-spring-boot-docker`