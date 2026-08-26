# Library Management System (Backend)

This application is built with Spring Boot to manage library operations such as book cataloging, user registration, book reservation, borrowing and fine records.  
The project demonstrates RESTful API design, database integration, and role-based authentication.

# Features
  - Manage books (add, update, delete, search)
  - User registration and authentication
  - Reservation, Borrowing and returning records
  - Role-based access control (Librarian, Regular user)
  - RESTful APIs with JSON responses
  - Database persistence with PostgreSQL and JPA

# Tech Stack
  - Java 21
  - Spring Boot
  - Spring Web
  - Spring Security (authentication & authorization)
  - Spring Data JPA (ORM)
  - PostgreSQL (database)
  - Maven (build tool)

# Project Structure
/* FOR A BETTER VIEW OF THE PROJECT STRUCTURE, CLICK ON Code INSTEAD OF Preview */
src\
    main\
        java\com\waldrich\libraryManagementSystem_25576\
                config\
                  -JwtFilter.java
                  -SecurityConfig.java
                controller\
                  -AuthController.java
                  -BookController.java
                  -BorrowingController.java
                  -FineController.java
                  -LocationController.java
                  -ReservationController.java
                  -StatsController.java
                  -UserController.java
                dto\
                  -AuthResponse.java
                  -BookDTO.java
                  -BorrowingDTO.java
                  -FineDTO.java
                  -LocationDTO.java
                  -LoginRequest.java
                  -LoginResponse.java
                  -OverviewStats.java
                  -ReservationDTO.java
                  -UserDTO.java
                  -UserDTOrecord.java
                  -UserStats.java
                enums\
                  -BookCategory.java
                  -BookStatus.java
                  -FineStatus.java
                  -LocationType.java
                  -ReservationStatus.java
                  -UserRole.java
                ERD\
                  -Blank diagram.jpeg
                exceptionsHandler\
                  -EmailAlreadyTakenException.java
                  -GlobalExceptionHandler.java
                  -UserNotFoundException.java
                model\
                  -Books.java
                  -Borrowings.java
                  -Fines.java
                  -Location.java
                  -Reservations.java
                  -UserPrincipal.java
                  -Users.java
                repository\
                  -BooksRepository.java
                  -BorrowingsRepository.java
                  -FinesRepository.java
                  -LocationRepository.java
                  -ReservationsRepository.java
                  -UsersRepository.java
                service\
                  -AuthService.java
                  -BooksService.java
                  -BorrowingsService.java
                  -EmailService.java
                  -FinesService.java
                  -JwtService.java
                  -LocationService.java
                  -MyUserDetailsService.java
                  -OtpService.java
                  -ReservationService.java
                  -StatsService.java
                  -UsersService.java
    resources\

# API Endpoints
  - POST /books/register     (to add a new book in the database. only by the librarian)
  - GET /books/all           (to list all the books)
  - GET /books/one/{bookId}   (serch a book by id)
  - GET /books/title          (search books by title)
  - GET /books/category       (search books by category)
  - PUT /books/update         (update a book. only by the librarian)
  - DELETE /books/delete/{id}  (delete a book. only by the librarian)

  - POST /auth/login
  - POST /auth/verify-otp
  - POST /auth/forgot-password
  - POST /auth/reset-password
  - POST /auth/refresh

  - POST /users/register
  - GET /users/all          (only the librarian)
  - GET /users/search       (only the librarian)
  - GET /users/{id}         (search a user by id)
  - PUT /users/update/{id}
  - DELETE /users/delete/{id}

  - POST /reservations/register
  - POST /reservations/all
  - GET /reservations/user
  - GET /reservations/search
  - GET /reservations/date/{reservationDate}
  - GET /reservations/status/{reservationStatus}
  - GET /reservations/one/{reservationId}
  - PUT /reservations/update/{reservationId}
  - DELETE /reservations/delete/{reservationId}
