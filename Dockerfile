#start with a base image for java
FROM openjdk:17-jdk-slim

MAINTAINER sainikhil

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]

