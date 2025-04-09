FROM alpine/java:17-jdk

RUN apk add maven

WORKDIR /app

COPY . .

RUN mvn install

CMD ["java", "-cp", "target/devops-1.0-SNAPSHOT.jar", "com.example.Demo"]