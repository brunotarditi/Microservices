FROM openjdk:12
VOLUME /tmp
EXPOSE 8761
ADD ./target/eurekaservice-0.0.1-SNAPSHOT.jar eurekaservice.jar
ENTRYPOINT ["java", "-jar", "eurekaservice.jar"]
