# Hibernate First Project

A simple Java Hibernate project that connects to MySQL and saves student records using Hibernate ORM.

---

## Tech Stack

- Java 22
- Hibernate ORM 6.4.10
- MySQL 8
- Maven
- Docker

---

## Docker Setup

### 1. Pull and Run MySQL Container

```bash
docker run --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=your_password \
  -e MYSQL_DATABASE=testdb \
  -p 3307:3306 \
  -d mysql:latest
```

> Replace `your_password` with your own password.

---

## Configuration

### Update `hibernate.cfg.xml`

Located at `src/main/resources/hibernate.cfg.xml`:

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3307/testdb</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">your_password</property>
```

> Change `your_password` to match the password you used in the Docker run command above.

---

## Build and Run

### 1. Build the JAR

```bash
mvn clean package -DskipTests
```

### 2. Run directly with Java

```bash
java -jar target/hibernate-first-1.0-SNAPSHOT.jar
```

---

## Run with Docker

### 1. Pull the app image

```bash
docker pull yashraj0912/hibernate-first-image:1.0
```

### 2. Build the app image (if building locally)

```bash
docker build -t hibernate-app .
```

### 3. Run the app container

```bash
docker run --network host hibernate-app
```

> `--network host` allows the app container to reach MySQL on localhost:3307.

---

## Project Structure

```
hibernate-first/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/example/entity/
│   │   │   │   └── Student.java
│   │   │   └── org/example/
│   │   │       └── Main.java
│   │   └── resources/
│   │       └── hibernate.cfg.xml
├── Dockerfile
└── pom.xml
```

---

## Student Entity

The `Student` table is auto-created in the `testdb` database with the following fields:

| Field      | Type     |
|------------|----------|
| id         | int (PK) |
| first_name | varchar  |
| last_name  | varchar  |
| email      | varchar  |

---

## Docker Hub

Image available at:  
[https://hub.docker.com/r/yashraj0912/hibernate-first-image](https://hub.docker.com/r/yashraj0912/hibernate-first-image)

```bash
docker pull yashraj0912/hibernate-first-image:1.0
```

---

## Notes

- Do **not** use port `3306` if your local MySQL is already running — use `3307` instead.
- The built-in Hibernate connection pool is used here — not intended for production.
- `hbm2ddl.auto` is set to `update` — table is created automatically on first run.
