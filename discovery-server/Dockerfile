FROM adoptopenjdk/openjdk11 
#FROM openjdk:8-jdk-alpine
RUN mkdir /opt/app
COPY target/*.jar /opt/app/app.jar
EXPOSE 8761
CMD ["java", "-jar", "-Dspring.profiles.active=default", "/opt/app/app.jar"]
