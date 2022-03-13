FROM openjdk:12
VOLUME /tmp
ADD ./target/studentservice-0.0.1-SNAPSHOT.jar studentservice.jar
ENTRYPOINT ["java", "-jar", "studentservice.jar"]