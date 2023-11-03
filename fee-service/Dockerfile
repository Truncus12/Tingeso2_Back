FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} fee-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/fee-service-0.0.1-SNAPSHOT.jar"]