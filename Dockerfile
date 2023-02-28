FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD /target/forum-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]