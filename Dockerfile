FROM openjdk:14-alpine
COPY /build/libs/panel-*-all.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
