FROM maven:3.6.3-jdk-11-slim as maven
COPY pom.xml /
RUN mvn dependency:go-offline

COPY . /
RUN mvn clean package -DskipTests


FROM openjdk:11
VOLUME /tmp
COPY --from=maven /target/millysapp-0.0.1-SNAPSHOT.jar /var/app.jar
RUN sh -c 'touch /var/app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/var/app.jar"]
EXPOSE 9000