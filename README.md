# Project Todo

## Overview
A full-stack Todo application with a React frontend and a Java Spring Boot backend using an Oracle database.

## Features
- Create, update, delete, and manage Todos.
- Basic Authentication for user login.
- Project creation and listing.
- Export project summary as a secret gist in markdown format.
- Mark Todos as pending or complete.

## Technologies Used
- **Frontend**: React.js
- **Backend**: Java Spring Boot
- **Database**: Oracle
- **Authentication**: Basic Auth

## Prerequisites

- **Backend**: Java 11+, Spring Boot, Oracle DB
- **Frontend**: Node.js, npm

## Setup Instructions

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Yadhukrishana/Todo-WebApp.git
   cd Todo-WebApp/backend
2. **Configure your Oracle DB connection in the `application.properties` file**:
   - Open `src/main/resources/application.properties`.
   - Modify the following properties with your Oracle DB credentials:
     ```properties
     spring.datasource.url=jdbc:oracle:thin:@<hostname>:<port>:<sid>
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
     spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
     ```

3. **Build and run the backend**:
   ```bash
   mvn clean install
   mvn spring-boot:run

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd Todo-WebApp/frontend

**npm start**
-The frontend will run on http://localhost:3000.
