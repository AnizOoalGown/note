FROM openjdk:11.0.8
VOLUME /tmp
ADD note-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=prod"]