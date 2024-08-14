FROM openjdk:17-jdk-bullseye
LABEL authors="oneka"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

#ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java","-jar","/app.jar"]