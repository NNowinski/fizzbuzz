# FizzBuzz App
FizzBuzzApp is springboot 2.5 and java 17 application.
It can be run in dev mode with hsqldb or in production mode with mariadb.
## 1. Launch app with Docker in production mode
### Requirements
- docker and docker-compose https://www.docker.com/get-started
### Launch
Run this command in root project

```bash
docker-compose up -d
```
## 2. Launch app in dev mode
### Requirements
- jdk 17 https://openjdk.java.net/projects/jdk/17/
- maven https://maven.apache.org/download.cgi
### Compile
Run this command in root project for build application
```bash
mvn clean install -Pdev -DskipTests
```
### Launch
Run this command in rest directory for launch springboot application
```bash
mvn spring-boot:run
```
## 3. Launch app in prod mode without docker
### Requirements
- jdk 17 https://openjdk.java.net/projects/jdk/17/
- maven https://maven.apache.org/download.cgi
- mariadb https://mariadb.org/download/
### Configuration
in rest\src\main\resources\application-prod.properties add the correct url/databse and user/mdp of your mariadb instance
### Compile
Run this command in root project for build application
```bash
mvn clean install -Pprod -DskipTests
```
### Launch
Run this command in rest/target directory for launch springboot application
```bash
java -jar fizzbuzz-app.jar
```
or this commands
```bash
jar -xf fizzbuzz-app.jar
```
```bash
java org.springframework.boot.loader.JarLauncher
```
## 4. Swagger
OpenAPI definition
```
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
``` 
## 5. Run JUnit Tests
Run this command in root project

```bash
mvn test
```