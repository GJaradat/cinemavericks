![CineMavericks-07-03-2024 (3)](https://github.com/GJaradat/cinemavericks/assets/92640386/6473adbb-7f5b-4689-85f6-666f0cb6e22b)
![Like-TopGun-but-for-movies-07-03-2024 (1)](https://github.com/GJaradat/cinemavericks/assets/92640386/93e931aa-3c40-44d6-88bf-23473c52fca8)


![Axolotl at the cinemas](https://github.com/GJaradat/cinemavericks/assets/108727885/38e385b5-6cb2-4b21-a2c7-7147facb727d)

## Table of Contents
1. [Introduction](#introduction)
2. [Installation](#installation)
3. [Technology](#technology)
4. [MVP](#mvp)
6. [Class and ERD Diagram](#class-erd-diagram)
7. [Extension](#extension)
8. [Data Dictionary](#data-dictionary)

<a id="introduction"></a>
## Introduction

This project involves creating an API for a film review aggregation platform, which incorporates a comprehensive movie database. Users can leave reviews with star ratings on films they have seen and curate lists of films based around any theme of their choosing. Additionally, they have the capability to explore movies by genre, director, and other relevant criteria.

The motivation behind this project is to foster a dynamic and engaging platform where users can interact with films in a meaningful and personalized manner. This project have been created as part of BNTA (Bright Network Technology Academy) Cohort 13 Group 3 Collaboration Backend Project.

<a id="installation"></a>
## Installation
1. Fork Project
2. Clone Project to your local file
3. Open the project in a java editor
4. Run the application
5. Use it on POSTMAN (use the provided postman collection associated with this github)

<a id="technology"></a>
## Technology
Purpose of Technology usage:
The objective is to enhance learning and skill development as an integral component of the bootcamp experience hence why the following technologies were used.

**Tech Stack:**
- Java
- SpringBoot
- PostgreSQL Database

**The Spring Boot Libraries used:**
- Maven Project
- Spring Boot: Ver. 3.2.3
- Spring Boot Dev Tools
- Spring Web
- PostgreSQL Driver

**Tools Used:**
- POSTMAN
- Intellij
- Trello
- Excalidraw
- Zoom
- Slack
- Git

Entity Relationship Diagram (ERD) and Class Diagram - Both MVP and Extension :
https://excalidraw.com/#room=4bc1651ba3f81fbe936c,Ud0mJ7-NPaNVfieqM0HqCw

<a id="mvp"></a>
## MVP

Design and build an API with CRUD functionality. There will be a one-to-many relationship of Movies  and Users to the Reviews.

The Movie table will have columns for:
- Id
- Title
- Year - Movie Released
- Genre
- Director

The User table will have columns for:
- Id
- name


The Review table will have columns for:
- id
- Title
- Movie
- User
- Content
- Rating

The API must include routes enabling user to:

- View all movies
- Get movie by id
- Add movie
- Edit movie
- Get all the reviews of a movie

- Create review
- Edit review
- Delete review

- Get all users
- Get specific user
- Get all reviews by a specific user
- Get all movie lists by specific user
- Create a user
- Delete a user
  
- Get all movie lists
- Get specific movie list by id
- Create movie list
- Update movie list
- Delete movie list

<a id="class-erd-diagram"></a>
## Class Diagram and ERD Diagram:
**Class Diagram for CinemaMavericks**
![Class Diagram for CinemaMavericks](https://github.com/GJaradat/cinemavericks/assets/108727885/ace8874d-3191-43cd-b6e7-e72ba3503ab4)

**ERD Diagram for CinemaMavericks**
![ERD Diagram for CinemaMavericks](https://github.com/GJaradat/cinemavericks/assets/99983599/164bec3e-21f3-41a7-bfde-8e973acea3bc)

<a id="extension"></a>
## EXTENSION:
- Movie has a duration property
- User can filter movie search by duration

- Give movies an average rating property
- Filter movies by average rating
- Round average rating to 2dp

- Edit a user’s information
- User can set lists to private or public
- Display lists containing a specific film

- Automatically add current date to review (refactor to LocalDateTime)
- List reviews by most recent

- Allow movies to have multiple genres
- Allow users to leave comments and likes on reviews

<a id="data-dictionary"></a>
## Data Dictionary for CinemaMavericks
**Movies Table**

| Column Name | Description                 | Data Type    | Permitted Values |
|-------------|-----------------------------|--------------|------------------|
| id          | Unique identifier           | SERIAL       | numbers >= 1     |
| title       | Title of the movie          | VARCHAR(255) |                  |
| year        | Year of movie release       | INT          |                  |
| genre       | Genre of the movie          | VARCHAR(255) |                  |
| director    | Director of the movie       | VARCHAR(255) |                  |

**Users Table**

| Column Name | Description               | Data Type    | Permitted Values |
|-------------|---------------------------|--------------|------------------|
| id          | Unique identifier         | SERIAL       | numbers >= 1     |
| name        | Name of the user          | VARCHAR(255) |                  |

**Reviews Table**

| Column Name | Description                         | Data Type    | Permitted Values                |
|-------------|-------------------------------------|--------------|---------------------------------|
| id          | Unique identifier                   | SERIAL       | numbers >= 1                    |
| title       | Title of the review                 | VARCHAR(255) |                                 |
| content     | Content of the review               | TEXT         |                                 |
| rating      | Rating given to the movie           | INT          | 0 <= value <= 5                 |
| movie_id    | ID of movie being reviewed          | BIGINT       | Foreign Key ```movies.id```     |
| user_id     | ID of user leaving review           | BIGINT       | Foreign Key ```users.id```      |


**Movie Lists Table**

| Column Name | Description                       | Data Type    | Permitted Values            | 
|-------------|-----------------------------------|--------------|-----------------------------|
| id          | Unique identifier                 | SERIAL       | numbers >= 1                |
| title       | Title of the movie list           | VARCHAR(255) |                             |  
| user_id     | ID of User who created the list   | BIGINT       | Foreign Key ```users.id```  |


**Movies Movie Lists Table**

| Column Name | Description                                          | Data Type    | Permitted Values                  |
|-------------|------------------------------------------------------|--------------|-----------------------------------|
| id          | Unique identifier                                    | SERIAL       | numbers >= 1                      |
| movielist_id| ID referencing movie list in Movie Lists table       | BIGINT        | Foreign Key ```movies_lists.id``` |
| movie_id    | ID referencing movie in Movie table                  | BIGINT       | Foreign Key ```movies.id```       |

## Contributions
