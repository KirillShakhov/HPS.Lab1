FROM maven:3.8.5-openjdk-11-slim AS build
ENV HOME=/src
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN --mount=type=cache,target=/root/.m2 mvn -P wbd,swagger,no-liquibase verify --fail-never
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 mvn -P wbd,swagger,no-liquibase -DskipTests clean package

FROM openjdk:11-jre-slim
COPY --from=build /src/target/*.jar /app/app.jar
RUN mkdir /optp
RUN mkdir -p /storage/TR_IR_Photos
CMD ["java", "-XX:+UseContainerSupport","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]