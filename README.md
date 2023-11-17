# cinema-management-api-springboot
REST API for a cinema management system. It uses JWT for authentication and authorization based on roles (ADMIN and USER), which have their own distinct operations that can be performed for one of those roles.


## Cinema Management System API
Welcome to the Ticket Cinema API, a RESTful web service for managing a cinema system. This API leverages JSON Web Tokens (JWT) for authentication and implements role-based authorization, distinguishing between ADMIN and USER roles. Each role comes with specific operations tailored to its responsibilities.

## Key Features
. Authentication and Authorization
. JWT Authentication: Secure authentication using JSON Web Tokens.
. Role-Based Authorization: Two distinct roles, ADMIN and USER, each with its set of authorized operations.
## ADMIN Operations
. Movie Management (CRUD): Full control over creating, reading, updating, and deleting movies in the system.
. Cinemaroom Management (CRUD): Administrative capabilities for managing cinema rooms within the system.
. Reservation Handling: The ability to manage reservations made by users or clients.
## USER Operations
. View Movies and Showtimes: Access to a list of all available movies with their respective showtimes and cinema room details.
. Reservation Creation: Ability for users to make reservations for their preferred movie, specifying the desired showtime and selecting seats within the cinema room.

##Getting Started
To run the API locally, follow these steps:

Clone the repository: git clone https://github.com/your-username/ticket-cinema-api.git
Navigate to the project directory: cd ticket-cinema-api
Configure the database connection in application.properties.
Run the application in the TicketCinemaApiApplication Class
Explore the API and enjoy managing your cinema system efficiently!
