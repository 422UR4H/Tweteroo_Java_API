# Tweteroo_API (Java)

A REST API built in Java for provide posts (tweets) for a front-end client.

## Description

This is my second Web Service build in Java with Spring Boot and Maven for practice them and more, like: Hibernate, Spring Data JPA, Relationships, Custom Queries and Validations!

# Demo

[Tweteroo API Link Deployed on Render](https://boardcamp-java-api.onrender.com)

<br />

## Quick start

Clone the repository, prepare the database and run the application locally in development mode.

Create a .env file following the .env.example to connect the server to a database.

```bash
DB_URL=jdbc:postgresql://localhost:5432/db_name # local database name
DB_USERNAME=postgres # database username
DB_PASSWORD=postgres # database password
```

A local database or an deployed database can be used.

Finally, run clicking `run` in your favorite IDE!

## Usage

### How it works?

Owns the entities: `user` and `tweet`.

### Routes:

- GET `/health`: To get API state

- GET `/users`: To get all users

- GET `/users/:id`: To get a user by this id

- POST `/users`: To create a user with the body:

```http
{
  username: "your username",
  avatar: "https://your-avatar-icon.com"
}
```

- DELETE `/users/:id`: To delete a user by this id

- PUT `/users/:id`: To update a user by this id with the same POST body format

- GET `/tweets`: To get all tweets

- GET `/tweets/user/:userId`: To get all tweets of the user by your id

- POST `/tweets`: To create a user with the body:

```http
{
  userId: 1,
  text: "text of the tweet"
}
```

If the structure is not respected, a error is returned.

# Technologies used

For this project, I used:

- Java (version 17.0.9);
- Spring Boot (version 3.2.2);
- Maven;
- Lombok;
- SonarLint;
- Hibernate;
- Spring Data JPA;
