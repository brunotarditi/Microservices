FROM openjdk:12
VOLUME /tmp
ADD ./target/teacherservice-0.0.1-SNAPSHOT.jar teacherservice.jar
ENTRYPOINT ["java", "-jar", "teacherservice.jar"]