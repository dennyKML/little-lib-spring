# Little Lib Spring

## Overview
Little Lib Spring is a Spring Boot application designed to manage a library system. It allows users to add, edit, and delete books, authors, and genres. The project utilizes a MySQL database and has been containerized using Docker for easy deployment.

## Features
- Add, edit, and delete books, authors, and genres.
- Store book details such as title, ISBN, and year of publication.
- Use MySQL as a relational database.
- Integrated with Docker for containerized deployment.
- Uses Testcontainers for integration testing.

## Prerequisites
To run this project, you need:
- Java 17+
- Maven
- Docker
- Docker Compose (optional, for easier orchestration)

## Setting up the Database in Docker
To run the MySQL database in Docker, execute the following command:

```sh
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:latest
```

If you need to customize the database name, user, and password, use:

```sh
docker run --name mysql-container \
    -e MYSQL_ROOT_PASSWORD=root_password \
    -e MYSQL_DATABASE=littlelibdb \
    -e MYSQL_USER=admin \
    -e MYSQL_PASSWORD=admin_password \
    -p 3306:3306 -d mysql:latest
```

### Verifying the Database
To check if the database container is running, use:

```sh
docker ps
```

To connect to MySQL inside the container:

```sh
docker exec -it mysql-container mysql -u root -p
```

## Running the Application
1. Clone the repository:
   ```sh
   git clone https://github.com/dennyKML/little-lib-spring.git
   cd little-lib-spring
   ```
2. Update `application.properties` to connect to the Dockerized MySQL instance:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/littlelibdb
   spring.datasource.username=admin
   spring.datasource.password=admin_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Build the application:
   ```sh
   mvn clean package
   ```
4. Run the application:
   ```sh
   java -jar target/little-lib-spring.jar
   ```

## Dockerizing the Application
1. Build the Docker image:
   ```sh
   docker build -t little-lib-spring .
   ```
2. Run the container:
   ```sh
   docker run -d -p 8080:8080 --name little-lib-app little-lib-spring
   ```

Now, the application should be accessible at `http://localhost:8080`.

## Running Integration Tests with Testcontainers
The project includes integration tests using Testcontainers to spin up a temporary MySQL instance for testing. To run the tests:

```sh
mvn test
```

## Conclusion
This project demonstrates the use of Spring Boot, Docker, and Testcontainers to build a fully containerized library management system. Contributions and improvements are welcome!

