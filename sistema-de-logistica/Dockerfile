FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/sistema-de-logistica-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]