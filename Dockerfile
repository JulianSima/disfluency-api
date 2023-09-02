FROM eclipse-temurin:17-jdk-alpine
RUN mkdir -p /app/
ADD build/libs/disfluency-api-0.0.1-SNAPSHOT.jar /app/disfluency-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/disfluency-api-0.0.1-SNAPSHOT.jar"]