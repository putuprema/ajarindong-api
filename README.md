# AjarinDong
AjarinDong is tutoring application where user can find courses in various IT topics and have certified mentors to guide you from start to finish. What motivates me to create this project is because a lot of my computer science friends struggles understanding courses in college, so I develop this as a platform for students to find courses with great mentors that they can consult anytime during the course. This repository is the backend of the application.

## Live Demo
- Endpoint Base URL: https://ajarindong.apps.purema.xyz
- Endpoint Docs (using Swagger): https://ajarindong.apps.purema.xyz/docs/swagger-ui.html

## Tech Stack
- Spring Boot
- Kotlin
- MySQL
- Docker

## Database schema
![AjarinDong DB](https://github.com/putuprema/ajarindong-api/blob/master/docs/AjarinDong%20Schema.svg)

## Implemented features
- Student registration and login
- Get available courses list (able to browse by category or keyword) and course details
- See course syllabus
- Choosing mentor for enrolled courses
- Purchase course with fake payment gateway

## Features not yet implemented
This project is not yet completed. I don't have enough time to finish it due to tight work deadlines. So, here are the features yet to be implemented:
- Admin endpoints to manage courses, mentors, students, and course enrollments (all data currently fed manually by inserting to DB)
- Downloadable course modules
- Schedule mentor appointment
- Student reward system through experience points
- Course exam
- Certificate of completion

## How to run in your local development machine
- Copy sample config file `src/main/resources/application.yml` to `config/application.yml` on the project root directory
- Configure your JWT secrets, server port (default is 8080) and database URL with its credentials in the newly copied config file
```
server:
  port: PORT_NUMBER
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/DB_NAME
    username: DB_USERNAME
    password: DB_PASSWORD
jwt:
  secret: JWT_SECRET
```
- Run the following command on the project root directory:
```
mvn spring-boot:run
```
The endpoints can be accessed on port 8080 or whatever port you specified in the config file

## Deploying to Docker
- Customize mounts for `/config` and `/data` folders in `docker-compose.yml` (default is `/root/docker/ajarindong-api`)
- Copy your `application.yml` file to the config folder mount specified in `docker-compose.yml`
- Run `deploy.sh` script on the project root directory
