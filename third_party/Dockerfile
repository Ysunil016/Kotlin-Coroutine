FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} tservice.jar

EXPOSE 8080 8080

ENTRYPOINT ["java","-jar","/tservice.jar"]