FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]