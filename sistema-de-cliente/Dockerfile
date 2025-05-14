# Usa una imagen base de JDK 17
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado al contenedor
COPY target/sistema-de-cliente-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto del microservicio cliente
EXPOSE 8082

# Ejecuta el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]
