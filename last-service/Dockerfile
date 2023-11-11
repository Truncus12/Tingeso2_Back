FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} last-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/last-service-0.0.1-SNAPSHOT.jar"]