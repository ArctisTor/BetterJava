# New (Java 21)
FROM eclipse-temurin:21-jdk-jammy

COPY BetterJavaApplication/build/libs/BetterJavaApplication-1.0-SNAPSHOT.jar app.jar
EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/app.jar"]