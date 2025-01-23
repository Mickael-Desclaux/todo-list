# Todo List API - Java / Spring Boot

## Description

This project implements a REST API for a simple Todo List application using **Java** and **Spring Boot**. The API allows users to create, read, update, and delete tasks in a Todo List.

## Prerequisites

- **Java 17** or higher
- **Maven** for dependency management
- **Spring Boot** for backend development
- **Postman** or another tool for testing the API (optional)

## Features

- Add a task
- Retrieve all tasks
- Retrieve a task by its ID
- Update an existing task
- Delete a task

## Installation

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/your-username/todo-list-api.git
    ```

2. Navigate into the project directory:

    ```bash
    cd todo-list-api
    ```

3. Build and run the application using Maven:

    ```bash
    ./mvnw spring-boot:run
    ```

    This will start the Spring Boot application on the default port `8080`.

## API Endpoints

| Method  | URL                               | Description                      |
|---------|-----------------------------------|----------------------------------|
| GET     | `/api/tasks`                      | Retrieve all tasks              |
| GET     | `/api/tasks/{id}`                 | Retrieve a task by its ID       |
| POST    | `/api/tasks`                      | Add a new task                  |
| PUT     | `/api/tasks/{id}`                 | Update an existing task         |
| DELETE  | `/api/tasks/{id}`                 | Delete a task                   |

### Example Usage

1. **Add a Task** (POST method):

    URL: `/api/tasks`  
    Request body (JSON):

    ```json
    {
        "title": "My first task",
        "description": "This is a description"
    }
    ```

2. **Retrieve All Tasks** (GET method):

    URL: `/api/tasks`  
    Response body (JSON):

    ```json
    [
        {
            "id": 1,
            "title": "My first task",
            "description": "This is a description"
        },
        {
            "id": 2,
            "title": "My second task",
            "description": "Another description"
        }
    ]
    ```

## Testing

Unit tests are included to ensure the proper functionality of the API. You can run the tests with the following command:

```bash
mvn test
