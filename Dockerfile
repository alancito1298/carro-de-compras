
# Usa una imagen base de OpenJDK 17
FROM eclipse-temurin:21-jdk as build

    COPY . /app
    WORKDIR /app
    
    RUN chmod +x mvnw
    RUN ./mvnw package -DskipTest
    RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:21-jre

ARG PORT
ENV PORT=${PORT}

COPY --from=build /app/app.jar .


RUN useradd runtime
USER runtime


# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT  ["java", "-Dserver.port=${PORT}, "-jar", "/app.jar"]


EXPOSE 8080


















