# AGENTS.md

## Project Overview

This repository is a starter application for building REST APIs using the Spring Framework and MongoDB. It provides a basic structure with controllers for greetings, system status, and user management, integrated with MongoDB for data persistence. The project is based on Spring Boot 1.5.8, Java 8, and uses Maven for build management.

Key technologies:

- Java 8
- Spring Boot (Web, Data MongoDB)
- MongoDB
- Maven
- Tomcat (for deployment)

## Setup Commands

- **Install dependencies**: `mvn clean install`
- **Environment setup**: Ensure MongoDB is running locally on default port 27017, or configure via environment variables (SPRING_MONGO_HOST, SPRING_MONGO_PORT, etc.)
- **Database**: MongoDB database named 'spring-rest-api' (configurable)

## Development Workflow

- **Start development server**: `mvn spring-boot:run`
- **Hot reload**: Spring Boot DevTools is not included, but code changes will restart the app automatically in development mode
- **Environment variables**: Set SPRING_MONGO_HOST, SPRING_MONGO_PORT, SPRING_MONGO_DATABASE as needed for MongoDB connection

## Testing Instructions

- **Run all tests**: `mvn test`
- **Test framework**: Uses Spring Boot Test with JUnit and JSON Path for assertions
- **Coverage**: No specific coverage tool configured; run tests to verify functionality

## Code Style

- **Language conventions**: Standard Java naming conventions (camelCase for methods/variables, PascalCase for classes)
- **Linting**: No specific linter configured; follow Java best practices
- **Formatting**: Use standard Java indentation and formatting
- **Imports**: Organize imports alphabetically

## Build and Deployment

- **Build**: `mvn clean package` (produces WAR file)
- **Deployment to Tomcat**: Copy the WAR to Tomcat's webapps directory or use standalone Tomcat with CATALINA_OPTS for MongoDB config
- **Deployment to OpenShift**: Use the 'openshift' Maven profile (`mvn clean package -P openshift`), set environment variables (SPRING*PROFILES=openshift, SPRING_MONGO*\* variables)

## Agent Guidance

This project currently has prompt files in `.github/prompts/` to guide Copilot:

- `understand-repo-structure.prompt.md`: Helps Copilot understand the repository structure and purposes
- `agent.prompt.md`: Instructions for creating agent files and AGENTS.md

No custom agent files (`.github/agents/*.agent.md`) are configured yet. Agents can be created for specific tasks like code generation, refactoring, or testing by following the `agent.prompt.md` guidelines.

## Pull Request Guidelines

- **Title format**: Descriptive summary of changes
- **Required checks**: Ensure tests pass (`mvn test`), code compiles (`mvn compile`)
- **Commit conventions**: Use clear, imperative commit messages

## Additional Notes

- **MongoDB setup**: For local development, start MongoDB service. For production, use externalized configuration.
- **Troubleshooting**: Check MongoDB connection in logs; ensure correct environment variables.
- **Performance notes**: Basic setup; optimize MongoDB queries and Spring configurations as needed.
- **Monorepo**: This is a single Maven project, not a monorepo.
