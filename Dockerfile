FROM openjdk:17-jdk-slim

WORKDIR /app

ENV APP_NAME=real-estate-backend
ENV VERSION=0.0.1-SNAPSHOT

COPY target/${APP_NAME}-${VERSION}.jar app.jar

# Expose cổng mặc định của Spring Boot (có thể thay đổi nếu bạn dùng cổng khác)
EXPOSE 8080

# Lệnh chạy ứng dụng, cho phép override biến môi trường qua -D hoặc ENV
ENTRYPOINT ["java", "-jar", "app.jar"]