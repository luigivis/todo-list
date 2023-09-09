
# ToDo List

This is a practice project that utilizes MySQL, email, JWT, and bcrypt to manage a to-do list. The project includes the ability to create encrypted notes, tasks, and send reminders via email.

## Dependencies

Below are the dependencies used in this project and their purposes:

- `org.springframework.boot:spring-boot-starter-validation`: Provides input validation for Spring controllers.

- `org.springframework.boot:spring-boot-starter-web`: Includes essential dependencies for building web applications with Spring Boot.

- `org.springframework.boot:spring-boot-starter-actuator`: Offers monitoring and management functionalities for the application.

- `io.micrometer:micrometer-registry-prometheus`: Adds support for metrics and monitoring with Prometheus.

- `org.springframework.boot:spring-boot-configuration-processor`: Facilitates property configuration in Spring Boot.

- `org.springframework.boot:spring-boot-starter-data-jdbc`: Enables JDBC database access in the application.

- `org.springframework.boot:spring-boot-starter-data-jpa`: Provides support for JPA database access.

- `io.swagger.core.v3:swagger-annotations:2.2.8`: Used to annotate controllers and generate API documentation with Swagger.

- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.3`: Enables the generation of OpenAPI documentation for the web API.

- `org.springdoc:springdoc-openapi-ui:1.7.0`: Provides a user interface for exploring the generated OpenAPI documentation.

- `org.springframework.boot:spring-boot-devtools`: Facilitates development in Spring Boot by providing development tools.

- `com.mysql:mysql-connector-j`: The JDBC driver for MySQL, allowing connection to the MySQL database.

- `org.springframework.boot:spring-boot-starter-test`: Contains dependencies for unit and integration testing in Spring Boot.

- `org.projectlombok:lombok`: Simplifies Java class creation by automatically generating boilerplate code.

- `org.springframework.security:spring-security-core`: Provides security functionalities for the application.

- `org.springframework.security:spring-security-crypto`: Offers cryptographic utilities for Spring Security.

- `io.jsonwebtoken:jjwt-api:0.11.5`: Provides an API for working with JSON Web Tokens (JWT).

- `io.jsonwebtoken:jjwt-impl:0.11.5`: JWT implementation for runtime use.

- `io.jsonwebtoken:jjwt-jackson:0.11.5`: Integrates Jackson with JWT (you can use jjwt-gson if you prefer Gson).

- `org.springframework:spring-context`: Provides the Spring application context.

- `com.google.code.gson:gson:2.10.1`: A library for working with JSON in Java.

- `org.springframework.boot:spring-boot-starter-mail`: Adds support for sending emails from the application.

## File Tree

Below is the file tree structure of this project:


Here's a description of what each of the mentioned elements does in the project:

- **pom.xml**: This file is the Maven Project Object Model (POM) and serves as the project's configuration file. It defines project metadata, dependencies, plugins, and build settings. It is crucial for managing project dependencies and building the project.

- **src**: This directory is the root of the source code and resources of the project.

    - **main**: This directory contains the primary application code.

        - **java**: It contains all the Java source code files.

            - **com**: This is the main package of the application, following standard Java package naming conventions.

                - **example**: The example package of the application, which might be a placeholder or reflect the organization's structure.

                    - **todolist**: The primary package of the ToDo List application, where the core application logic resides.

                        - **TodoListApplication.java**: This is the main Spring Boot application class responsible for initializing and running the application.

                        - **adapter**: This package contains adapters and entities related to the database.

                            - **entity**: Here, you find entity classes that represent tables in the database. These classes define the structure of the data.

                                - **NotesEntity.java**: Represents the notes entity in the database.
                                - **NotesVersionEntity.java**: Represents the notes version entity in the database.
                                - **TaskEntity.java**: Represents the tasks entity in the database.
                                - **UserEntity.java**: Represents the users entity in the database.

                            - **repository**: This package contains repository interfaces that define methods for interacting with the database.

                                - **NotesRepository.java**: Provides methods to access and manipulate data in the notes table.
                                - **NotesVersionRepository.java**: Provides methods for the notes version table.
                                - **TaskRepository.java**: Provides methods for the tasks table.
                                - **UserRepository.java**: Provides methods for the users table.

                            - **service**: Contains services related to the application's business logic.

                                - **SendEmailService.java**: Offers functionality for sending emails.
                                - **TaskReminderLogic.java**: Implements logic for sending task reminders.
                                - **impl**: This sub-package contains concrete implementations of services.

                                    - **SendEmailServiceImpl.java**: Implements the email sending service.

                        - **command**: Contains command classes used within the application.

                            - **AuthCommand.java**: Command-related to authentication.
                            - **NotesCommand.java**: Command-related to notes.
                            - **TaskCommand.java**: Command-related to tasks.

                        - **configuration**: Houses Spring configuration classes.

                            - **CustomExceptionHandler.java**: Handles custom exceptions in the application.
                            - **ExceptionController.java**: Acts as a controller for handling exceptions.
                            - **SpringAsyncConfig.java**: Provides configuration for asynchronous task support.

                        - **controller**: Contains Spring controllers responsible for handling HTTP requests.

                            - **AuthController.java**: Manages authentication-related requests.
                            - **NotesController.java**: Handles requests related to notes.
                            - **TaskController.java**: Manages requests related to tasks.

                        - **dto**: Contains Data Transfer Object (DTO) classes used for transferring data between the application and the user interface.

                            - **...**: There are multiple DTO classes, each serving a different purpose.

                        - **filter**: Contains Spring filters used within the application.

                            - **TokenFilter.java**: Filters incoming requests to validate and process authentication tokens.

                        - **logic**: Holds interfaces and classes related to the application's business logic.

                            - **...**: There are multiple logic interfaces and classes for different aspects of the application.

                        - **schedule**: Houses components responsible for task scheduling.

                            - **TaskSchedulingNotification.java**: Implements logic for scheduling and sending task notifications.

                        - **util**: Contains utility classes and utilities that serve various purposes.

                            - **...**: Multiple utility classes are available for different tasks.

        - **resources**: Contains resource files used by the application.

            - **application-dev.properties**: Configuration properties specific to the development environment.
            - **application.properties**: Main configuration properties for the application.
            - **banner.txt**: A custom startup banner that may display when the application starts.
            - **logback.xml**: Configuration file for Logback, the logging framework used by the application.
            - **templates**: This directory contains HTML templates used by the application.

                - **registerNewUser.html**: A template notification for the user registration page.

 <pre>
 .
  ├── pom.xml
  ├── readme.md
  ├── src
  │   └── main
  │       ├── java
  │       │   └── com
  │       │       └── example
  │       │           └── todolist
  │       │               ├── TodoListApplication.java
  │       │               ├── adapter
  │       │               │   ├── entity
  │       │               │   │   ├── NotesEntity.java
  │       │               │   │   ├── NotesVersionEntity.java
  │       │               │   │   ├── TaskEntity.java
  │       │               │   │   └── UserEntity.java
  │       │               │   ├── repository
  │       │               │   │   ├── NotesRepository.java
  │       │               │   │   ├── NotesVersionRepository.java
  │       │               │   │   ├── TaskRepository.java
  │       │               │   │   └── UserRepository.java
  │       │               │   └── service
  │       │               │       ├── SendEmailService.java
  │       │               │       ├── TaskReminderLogic.java
  │       │               │       └── impl
  │       │               │           └── SendEmailServiceImpl.java
  │       │               ├── command
  │       │               │   ├── AuthCommand.java
  │       │               │   ├── NotesCommand.java
  │       │               │   └── TaskCommand.java
  │       │               ├── configuration
  │       │               │   ├── CustomExceptionHandler.java
  │       │               │   ├── ExceptionController.java
  │       │               │   └── SpringAsyncConfig.java
  │       │               ├── controller
  │       │               │   ├── AuthController.java
  │       │               │   ├── NotesController.java
  │       │               │   └── TaskController.java
  │       │               ├── dto
  │       │               │   ├── EmailUserRegistrationDto.java
  │       │               │   ├── enums
  │       │               │   │   ├── DaysCatalog.java
  │       │               │   │   ├── HeaderCatalog.java
  │       │               │   │   ├── RoleCatalog.java
  │       │               │   │   ├── RunExecutionCatalog.java
  │       │               │   │   ├── StatusResponses.java
  │       │               │   │   └── TaskStatusCatalog.java
  │       │               │   ├── request
  │       │               │   │   ├── login
  │       │               │   │   │   └── LoginRequestDto.java
  │       │               │   │   ├── notes
  │       │               │   │   │   ├── CreateNoteRequestDto.java
  │       │               │   │   │   └── UpdateNoteRequestDto.java
  │       │               │   │   ├── task
  │       │               │   │   │   └── CreateTaskRequestDto.java
  │       │               │   │   └── user
  │       │               │   │       ├── ConfirmEmailRequestDto.java
  │       │               │   │       └── RegisterUserRequestDto.java
  │       │               │   ├── response
  │       │               │   │   ├── GenericResponses.java
  │       │               │   │   ├── StatusDTO.java
  │       │               │   │   ├── notes
  │       │               │   │   │   └── ListNotesResponseDto.java
  │       │               │   │   └── task
  │       │               │   │       ├── CurrentlyObject.java
  │       │               │   │       └── ListTaskResponseDto.java
  │       │               │   └── times
  │       │               │       └── Time.java
  │       │               ├── filter
  │       │               │   └── TokenFilter.java
  │       │               ├── logic
  │       │               │   ├── AuthLogic.java
  │       │               │   ├── NotesLogic.java
  │       │               │   ├── TaskLogic.java
  │       │               │   └── impl
  │       │               │       ├── AuthLogicImpl.java
  │       │               │       ├── NotesLogicImpl.java
  │       │               │       └── TaskLogicImpl.java
  │       │               ├── schedule
  │       │               │   └── TaskSchedulingNotification.java
  │       │               └── util
  │       │                   ├── JwtUtils.java
  │       │                   ├── PageableTools.java
  │       │                   └── impl
  │       │                       ├── DatePattern.java
  │       │                       ├── Encrypt.java
  │       │                       ├── EnsureDataQuality.java
  │       │                       ├── ExceptionControl.java
  │       │                       ├── GetResourceFileImpl.java
  │       │                       ├── JwtUtilsImpl.java
  │       │                       ├── PageableToolsImpl.java
  │       │                       ├── ShortUuid.java
  │       │                       └── StatusException.java
  │       └── resources
  │           ├── application-dev.properties
  │           ├── application.properties
  │           ├── banner.txt
  │           ├── logback.xml
  │           └── templates
  │               └── registerNewUser.html
 </pre>

This file tree represents the directory and file structure of the application, including Java classes, configuration files, resources, and files generated during the project build.