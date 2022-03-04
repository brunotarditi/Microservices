FROM openjdk:11-oracle
COPY ./target/eurekaservice-0.0.1-SNAPSHOT.jar app/eurekaservice.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app/eurekaservice.jar"]
