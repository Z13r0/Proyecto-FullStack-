FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el JAR compilado
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8761

# JVM settings
ENV JAVA_OPTS="-Xmx256m -Xms256m"

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8761/eureka/status || exit 1

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]