FROM openjdk:12
VOLUME /tmp
ADD ./zipkin-server-2.23.16-exec.jar zipkinservice.jar
ENTRYPOINT ["java", "-jar", "zipkinservice.jar"]