FROM openjdk:12
VOLUME /tmp
EXPOSE 8888
ADD ./target/configservice-0.0.1-SNAPSHOT.jar configservice.jar
ENTRYPOINT ["java", "-jar", "configservice.jar"]