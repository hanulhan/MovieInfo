FROM openjdk:11-jre-alpine
COPY target/*.jar /app.jar
EXPOSE 8083
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]

