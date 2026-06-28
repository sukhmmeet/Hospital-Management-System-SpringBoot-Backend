# 🏥 Hospital Management System Backend

A secure and scalable **Hospital Management System Backend** built with **Spring Boot**.

This project implements real-world backend practices including **JWT Authentication, OAuth2 Login, Role Based Access Control, DTO architecture, layered design, and relational database management**.

---

## 🚀 Features

### 🔐 Authentication & Security

- JWT based authentication
- Secure signup and login
- OAuth2 login integration
- Spring Security implementation
- Role Based Access Control (RBAC)
- BCrypt password encryption
- Stateless authentication
- Protected REST APIs

---

## 👥 User & Role Management

The system uses a centralized `User` entity for authentication and authorization.

Supported roles:

```
ADMIN
DOCTOR
PATIENT
```

User relationship:

```
User
 |
 +---- Patient
 |
 +---- Doctor
```

User ID is used as the main identity reference across the system.

---

# 🏥 Modules

## 👨‍⚕️ Doctor Management

Admin can:

- Register doctors
- Assign doctor roles
- Manage doctor profiles
- Assign departments
- Manage appointments

Doctors can:

- View appointments
- Update appointment status
- Manage their profile

---

## 🧑 Patient Management

Patients can:

- Create profile
- Update profile
- View appointments
- Cancel appointments

---

## 📅 Appointment Management

Appointment lifecycle:

```
REQUESTED
     |
     v
CONFIRMED
     |
     v
COMPLETED


REQUESTED / CONFIRMED
     |
     v
CANCELLED
```

Features:

- Create appointments
- Assign doctors
- Confirm appointments
- Complete appointments
- Cancel appointments

---

## 🏢 Department Management

Admin can:

- Create departments
- Update departments
- Delete departments
- Add doctors to departments
- Remove doctors
- Assign department head

---

# 🔗 Database Relationships

Implemented using JPA / Hibernate:

```
User
 |
 | OneToOne
 |
Doctor


User
 |
 | OneToOne
 |
Patient


Doctor
 |
 | OneToMany
 |
Appointment


Department
 |
 | ManyToMany
 |
Doctor
```

---

# 🏗️ Architecture

The project follows layered architecture:

```
Controller
      |
      v
Service
      |
      v
Repository
      |
      v
Database
```

---

# 📦 DTO Based Design

Entities are not exposed directly.

Flow:

```
Request DTO

      |
      v

Controller

      |
      v

Service

      |
      v

Entity

      |
      v

Response DTO
```

Benefits:

- Secure API responses
- Clean separation
- Easy maintenance
- Loose coupling

---

# 📂 Project Structure

```
src/main/java/com/dhaliwal/hospitalManagement


├── controller
│   ├── AdminController
│   ├── DoctorController
│   ├── PatientController
│   ├── AppointmentController
│   ├── DepartmentController
│   └── PublicController
│
├── service
│
├── repository
│
├── entity
│
├── dto
│
├── mapper
│
└── security
    │
    ├── auth
    │   ├── controller
    │   │   └── AuthController
    │   │
    │   ├── service
    │   │   └── AuthService
    │   │
    │   └── dto
    │       ├── LoginRequestDto
    │       ├── LoginResponseDto
    │       ├── SignupRequestDto
    │       └── SignupResponseDto
    │
    ├── config
    │   ├── WebSecurityConfig
    │   └── ApplicationConfig
    │
    ├── jwt
    │   ├── JwtAuthFilter
    │   └── AuthUtil
    │
    ├── oauth2
    │   └── OAuth2SuccessHandler
    │
    └── userdetails
        └── CustomUserDetailService
```

---

# 🔑 Security Flow

## Login Flow

```
User
 |
 v
Auth Controller
 |
 v
Authentication Manager
 |
 v
JWT Generated
 |
 v
Access APIs
```

## Request Flow

```
Client

 |
 | Bearer Token

 v

JwtAuthFilter

 |
 v

SecurityContext

 |
 v

Controller
```

---

# 🛡️ API Security

Public:

```
/auth/**
/public/**
```

Admin:

```
/admin/**
```

Doctor:

```
/doctor/**
```

Patient:

```
/patient/**
```

---

# 🛠️ Tech Stack

## Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate


## Authentication

- JWT
- OAuth2
- BCrypt


## Database

- PostgreSQL / MySQL


## Build Tool

- Gradle

---

# ⚙️ Setup

Clone repository:

```bash
git clone https://github.com/sukhmmeet/Hospital-Management-System-SpringBoot-Backend.git
```

Configure database:

```
src/main/resources/application.yml
```

Run:

```bash
./gradlew bootRun
```

---

# 🔐 Environment Variables

Required:

```
DATABASE_URL

DATABASE_USERNAME

DATABASE_PASSWORD

JWT_SECRET

GOOGLE_CLIENT_ID

GOOGLE_CLIENT_SECRET
```

---

# 👨‍💻 Author

Sukhmmeet

GitHub:
https://github.com/sukhmmeet


⭐ Give a star if you like this project.
