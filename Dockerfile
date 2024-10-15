FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Sao chép file JAR của ứng dụng vào container
COPY target/*.jar app.jar

# Expose cổng mà ứng dụng Spring Boot sẽ chạy
EXPOSE 8080

# Câu lệnh để chạy ứng dụng
CMD ["java", "-jar", "app.jar"]