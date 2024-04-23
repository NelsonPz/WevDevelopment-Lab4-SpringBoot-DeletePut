# Project Name

## Overview
This project is a Spring Boot application that serves as a RESTful API for managing user profiles and their postings. It provides endpoints for performing CRUD (Create, Read, Update, Delete) operations on profiles and postings.

## Features
- **Get Profiles:** Retrieve profiles by username or ID.
- **Get Profile Postings:** Retrieve postings associated with a specific profile.
- **Create Profile:** Add a new user profile.
- **Add Posting to Profile:** Add a new posting to a user's profile.
- **Delete Posting:** Remove a posting from a profile.
- **Delete All Postings by Profile:** Remove all postings associated with a profile.
- **Delete Profile:** Remove a user profile.
- **Update Posting:** Modify an existing posting within a profile.

## Technologies Used
- Java
- Spring Boot
- RESTful APIs
- Model-View-Controller (MVC) architecture

## How to Use
1. Clone the repository to your local machine.
2. Configure the project in your preferred IDE.
3. Run the application.
4. Access the API endpoints using tools like cURL, Postman, or a web browser.

## API Endpoints
- **GET /api/profiles:** Retrieve profiles.
- **GET /api/profiles/{profileId}:** Retrieve a profile by ID.
- **GET /api/profiles/{profileId}/postings:** Retrieve postings associated with a profile.
- **POST /api/profiles:** Create a new profile.
- **POST /api/profiles/{profileId}/postings:** Add a new posting to a profile.
- **POST /api/profiles/postings:** Add a new posting to a profile by username.
- **DELETE /api/profiles/{profileId}/postings/{postingId}:** Delete a posting from a profile.
- **DELETE /api/profiles/{profileId}/postings:** Delete all postings from a profile.
- **DELETE /api/profiles/{profileId}:** Delete a profile.
- **PUT /api/profiles/{profileId}/postings/{postingId}:** Update a posting within a profile.

