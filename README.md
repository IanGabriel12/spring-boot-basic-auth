# Spring Boot Basic Auth API
An API for studies in Spring Security and auth protocols with JWT strategy.

## About
This is just a study project. Its goal is to improve my understading about Spring Security and how the authentication and authorization process work in  Spring Boot framework.

## How to run
Create a `.env` file on root folder and add the secret key to generate JWT tokens in it. You should follow `.env.example` template
Then you should update `resources/application.properties` to work with your favorite DBMS. After that, just run `./mvnw spring-boot:run` on the project root folder and you are good to go.