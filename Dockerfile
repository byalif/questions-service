FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} questionservice.jar

ENTRYPOINT ["java", "-jar", "/questionservice.jar"]

EXPOSE 8082