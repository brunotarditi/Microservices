FROM openjdk:12
VOLUME /tmp
ADD ./target/authservice-0.0.1-SNAPSHOT.jar authservice.jar
ENTRYPOINT ["java", "-jar", "authservice.jar"]