FROM arm64v8/eclipse-temurin:22-alpine
RUN mkdir /opt/app
COPY /target/jspring-0.0.1-SNAPSHOT.jar /opt/app/app.jar
EXPOSE 8080 8080
CMD [ "java", "-jar", "/opt/app/app.jar" ]
