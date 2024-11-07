FROM eclipse-temurin:17

COPY BetterJavaApplication/build/libs/BetterJavaApplication-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]