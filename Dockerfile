# STAGE 1
FROM gradle:8.10.2-jdk21 AS builder
WORKDIR /app

COPY build.gradle .          
COPY settings.gradle .       
COPY src ./src               

RUN gradle clean bootJar --no-daemon -x test

# STAGE 2
FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 3000

CMD ["java", "-jar", "app.jar"]
