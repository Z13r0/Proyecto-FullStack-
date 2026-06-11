FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/ms-usuarios-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV EUREKA_URL=http://eureka-server:8761/eureka/
ENV DB_USERNAME=app_user
ENV DB_PASSWORD=app_password

HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]