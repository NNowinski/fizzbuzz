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
### Launch
Run this command in root project for build application
```bash
mvn clean install -Pdev -DskipTests
```
Run this command in root project for launch springboot application
```bash
mvn spring-boot:run
```
## 3. Run JUnit Tests
Run this command in root project

```bash
mvn test
```