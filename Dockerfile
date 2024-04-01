FROM amazoncorretto:17-alpine-jdk AS builder

RUN mkdir /thinner
WORKDIR /thinner

COPY . .

RUN ./gradlew clean bootJar


FROM amazoncorretto:17.0.7-alpine

ENV PROFILE=local

RUN mkdir /thinner
WORKDIR /thinner

COPY --from=builder /thinner/build/libs/thinner-* /thinner/app.jar

CMD ["java", \
    "-Dspring.profiles.active=${PROFILE}", \
    "-jar", \
    "/thinner/app.jar"]