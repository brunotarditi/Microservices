FROM openjdk:12
VOLUME /tmp
EXPOSE 8080
ADD ./target/gatewayservice-0.0.1-SNAPSHOT.jar gatewayservice.jar
ENTRYPOINT ["java", "-jar", "gatewayservice.jar"]