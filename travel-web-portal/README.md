# Travel Web Portal

A dynamic travel booking web application built with Java, Spring Boot, MySQL, HTML, CSS, and JavaScript.

## Features

- User registration and login (passwords hashed with BCrypt)
- Browse and search travel packages by destination
- Book a package: choose travel date and number of travelers
- Simulated payment flow and booking confirmation
- "My bookings" page for logged-in users
- Admin panel to manage packages, view all bookings, and view registered users

## Tech stack

- **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Hibernate
- **Frontend:** Thymeleaf templates, HTML5, CSS3
- **Database:** MySQL
- **Build tool:** Maven

## Project structure

```
src/main/java/com/soundhar/travelportal/
  ├── controller/   -> handles HTTP requests (Auth, Package, Booking, Admin, Home)
  ├── model/        -> JPA entities (User, TravelPackage, Booking)
  ├── repository/   -> Spring Data JPA repositories
  └── config/       -> app configuration (password encoder)

src/main/resources/
  ├── templates/    -> Thymeleaf HTML pages
  ├── static/css/   -> stylesheet
  └── db/           -> sample data SQL script
```

## How to run locally

1. Install **Java 17+**, **Maven**, and **MySQL**.
2. Create the database:
   ```sql
   CREATE DATABASE travel_portal;
   ```
3. Open `src/main/resources/application.properties` and set your MySQL username/password.
4. Run the app:
   ```bash
   mvn spring-boot:run
   ```
5. Visit `http://localhost:8080` in your browser.
6. (Optional) Load sample packages by running `src/main/resources/db/seed-data.sql` against your database after the app has started once (so the tables exist).
7. To access the admin panel, register a normal account, then in MySQL run:
   ```sql
   UPDATE users SET role = 'ROLE_ADMIN' WHERE email = 'your-email@example.com';
   ```
   Log out and log back in — you'll be redirected to `/admin/dashboard`.

## Possible next steps

- Integrate a real payment gateway (Razorpay/Stripe) instead of the simulated payment form
- Add image upload for packages instead of external URLs
- Add pagination for the packages list
- Write unit tests for the service/repository layer

## Author

Built by Soundhar as part of full-stack Java developer training and portfolio work.
