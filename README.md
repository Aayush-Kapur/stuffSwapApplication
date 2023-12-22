# StudentStuffSwapApplication

## Overview

Welcome to the StudentStuffSwapApplication! This Java-SpringBoot project is designed to facilitate the exchange of student-related items among users within a secure and organized platform. The application leverages the Spring Security framework to ensure the authentication of users and follows the MVC architecture, utilizing relevant DTOs (Data Transfer Objects) for input and output.

## Key Components

### 1. Controllers

#### Category Class
- **addCategories**: Add multiple categories at once.
- **addCategory**: Add a single category.
- **deleteCategory**: Delete a category.
- **getCategories**: Retrieve a list of all categories.

#### Listing Class
- **getListingsUnderCategory**: Get listings under a specific category.
- **getListingsByCurrentUser**: Get listings posted by the current user.
- **getAllListingsByUser**: Get all listings of a specific user.
- **getAllListings**: Get all listings irrespective of category.
- **addListing**: Add a new listing.
- **updateListing**: Update an existing listing.

#### Registration Class
- **temporaryRegister**: Register a user, sending a temporary OTP for email verification.
- **verifyEmail**: Verify the user's email using the received OTP.
- **login**: Log in to the application.

#### User Class
- **getAllUsers**: Retrieve a list of all users.
- **getUser**: Get details of a specific user.
- **updateUser**: Update user information.

### 2. Workflow

1. **Registration and Verification**: Users register by providing necessary details. Upon registration, a temporary OTP is sent to the user's email for verification. Once the email is verified, the user account is created.

2. **Listing Management**: Authenticated users can post listings to sell or buy items. Listings can be filtered based on categories, allowing users to find relevant items easily.

3. **Viewing and Interacting with Listings**: Users can view listings posted by other users. Interactions such as updating, deleting, and contacting the seller are facilitated through the provided APIs.

4. **Authentication**: All active users are required to be authenticated, ensuring a secure environment for the exchange of student-related items.

## Testing

The APIs of the StudentStuffSwapApplication have been thoroughly tested using Postman to guarantee their functionality and reliability.

Feel free to explore and contribute to the StudentStuffSwapApplication! If you encounter any issues or have suggestions for improvement, please open an issue in the repository.

Happy swapping!
