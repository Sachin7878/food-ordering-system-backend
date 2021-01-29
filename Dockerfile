FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/food-ordering-system.jar
COPY ${JAR_FILE} app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]