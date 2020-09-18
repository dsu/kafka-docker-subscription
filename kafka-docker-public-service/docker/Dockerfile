FROM openjdk:8-jdk-alpine

ADD public-app*.jar /public-app.jar

ENTRYPOINT java -jar /public-app.jar --spring.config.location=classpath:/docker.properties
