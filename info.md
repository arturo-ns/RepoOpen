# Lunaris Partner Management API

## What is this?

This is a REST API for managing partners in the Lunaris ecosystem. It allows you to create and manage partner information through simple HTTP requests.

## What can it do?

- **Create Partners**: Add new partners to the system with their contact information, company details, and location data.

## Key Features

- **Partner Registration**: Register partners with complete information including representative name, contact details, and company information.
- **Business Rules**: The system ensures data integrity by preventing duplicate partners and validating email addresses and phone numbers.
- **Automatic ID Generation**: Each partner gets a unique identifier automatically generated when created.
- **Audit Trail**: The system tracks when partners are created and last updated.

## How to use it?

Send a POST request to `/api/v1/partners` with partner information in JSON format. The API will validate the data and create the partner if everything is correct.

## Technical Details

- Built with Spring Boot
- Uses MySQL database for data storage
- Follows Domain-Driven Design (DDD) architecture
- Includes comprehensive error handling
- API documentation available via Swagger/OpenAPI

## What information is required?

When creating a partner, you need to provide:
- Representative's first and last name
- Contact phone (international format)
- Contact email
- Company name
- City and country
- State or province (optional)

The system will automatically generate a unique partner ID for each new partner.

## Developer Information

**Developer**: [Your Name]
**Email**: [your.email@example.com]
**GitHub**: [your-github-username]
**LinkedIn**: [your-linkedin-profile]
