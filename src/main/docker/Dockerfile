FROM java:8
VOLUME /tmp
ADD subscribe-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "/app.jar" ]