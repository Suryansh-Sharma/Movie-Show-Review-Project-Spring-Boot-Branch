Movie Review System

https://user-images.githubusercontent.com/80879578/196358796-d63bf66d-d074-4ec5-ab68-4f5004f537ab.mp4

The Movie Review System is a web application built using React.js, Spring REST API, Spring Security, OpenAPI, and PostgreSQL. It allows users to review and rate movies, provides aggregated ratings and reviews, and offers personalized movie recommendations.
Features

    User Registration and Authentication: Users can create an account, log in, and log out securely using Spring Security.
    Movie Reviews: Users can submit reviews for movies, including a rating and text-based feedback.
    Review Aggregation: The system calculates average ratings, summarizes review statistics, and generates overall scores for movies.
    Personalized Recommendations: The recommendation engine suggests movies based on user preferences, previous reviews, and movie attributes.
    OpenAPI Documentation: The API endpoints are documented using OpenAPI to facilitate integration and development.
    Persistent Storage: PostgreSQL is used as the database to store movie reviews, user information, and other relevant data.

Prerequisites

Before running the application, ensure that you have the following prerequisites 

Database Data : YOu can restore data through file Movie-Review-Postgres-Backup.sql

installed:

    Node.js and npm: Download and Install Node.js
    Java Development Kit (JDK): Download and Install JDK
    PostgreSQL: Download and Install PostgreSQL

Installation

    Clone the repository:

    bash

git clone https://github.com/Suryansh-Sharma/Movie-Show-Review-Project-Spring-Boot-Branch.git

Frontend Setup:

    Navigate to the frontend directory:

    bash

cd frontend

Install dependencies:

npm install

Start the frontend server:

sql

    npm start

server-port : You can set baseurl and port no of api  in : src/context/Context.js . By default use can use localhost:8080/

    Backend Setup:
        Import the project in your favorite IDE (e.g., IntelliJ, Eclipse).
        Configure the database connection in the application.properties file.
        Build and run the Spring REST API.

    Access the application:
        Open a web browser and visit http://localhost:3000 to access the frontend.
        The API documentation can be accessed at http://localhost:8080/swagger-ui.html.

Configuration

    Backend Configuration: Modify the application.properties file to set up the database connection, server port, and other configuration properties.

License

This project is licensed under the MIT License.
Acknowledgments

    React.js
    Spring Framework
    OpenAPI
    PostgreSQL

Feel free to customize this README file based on your project's specific requirements, architecture, and additional information you may want to provide to users.





