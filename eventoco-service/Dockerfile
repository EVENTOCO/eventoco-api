FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/eventoco-service-1.0.0.jar
COPY ${JAR_FILE} eventoco-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "eventoco-service.jar"]