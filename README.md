# About
This is a spring boot app to show how to implement authentication with JWT (Json Web Token).
In this example, I used mongodb as database.

For the authentication, the username's value can be email or phone number. After a success authentication, the signin api will return a token.
This token will be used as a Bearer token (***Authorization: Bearer <token>***) in the header of each protected resource.

## Requirements
To run the application you need:
- Java
- Maven
- Mongodb

## How to start
- Clone the repository
- Download all dependencies

### Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.com.jwtauth.JwtAuthApplication` class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

```shell
mvn spring-boot:run
```

If the server is running, you can now use this url `htpp://localhost:8080/auth/signup` to register a user 
and the following one to login and get an authentication token: `htpp://localhost:8080/auth/signin`

## Note
Please if you need any clarification regarding this repo, you can contact me on my email. Thanks 😺 !