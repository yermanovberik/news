FROM bellsoft/liberica-openjdk-debian:17
WORKDIR /app

COPY ./build/libs/news-0.0.1-SNAPSHOT.jar /app/news.jar

ENTRYPOINT ["java", "-jar", "/app/news.jar"]

EXPOSE 8080
