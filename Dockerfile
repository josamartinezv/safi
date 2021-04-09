FROM openjdk:8 as build
WORKDIR /api
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE
ADD ./target/attention-0.0.1-SNAPSHOT.jar backend-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandon","-jar", "backend-server.jar" ]