# awesome-music-studio
A simple project of booking a music studio for demonstration purposes

# Awesome Music Booking System

This is a simple Spring Boot-based booking system for managing rehearsal room reservations. Users can create bookings without authentication, and an admin can approve or reject requests.

## Features
- REST API for booking management
- Supports booking slots: Morning, Afternoon, Evening
- Admin can approve or reject bookings
- Uses H2 in-memory database for easy testing

## Technologies Used
- Java 17
- Spring Boot 3.2
- Spring Data JPA
- H2 Database
- Lombok

## Installation and Setup
### Clone the repository
```sh
git clone https://github.com/your-repo/awesome-music-booking.git
cd awesome-music-booking
```

### Build the project
```sh
mvn clean install
```

### Run the application
```sh
mvn spring-boot:run
```
The app will start on `http://localhost:8080`

## API Endpoints
### 1. Create a Booking
**POST** `http://localhost:8080/api/v1/bookings`
```json
{
  "customerName": "John Doe",
  "email": "johndoe@example.com",
  "slot": {
    "id": 1
  }
}
```

### 2. Get the Booking by code
**GET** `http://localhost:8080/api/v1/bookings/{code}/status`


### 3. Get Pending Bookings (Manager)
**GET** `http://localhost:8080/api/v1/manager/bookings`

### 4. Approve Booking
**PATCH** `http://localhost:8080/api/v1/manager/bookings/{code}/approve`

### 5. Reject Booking
**PATCH** `http://localhost:8080/api/v1/manager/bookings/{code}/reject`

## Database Access
H2 database console available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:ams`
- Username: `root`
- Password: `root`

## Contributing
Feel free to fork this repository and submit pull requests.

## License
This project is licensed under the MIT License.

