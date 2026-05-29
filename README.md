# 🛡️ Jakarta EE Authentication System

A professional, production-grade authentication template built with **Jakarta EE 10**, **JPA (EclipseLink)**, and **MySQL**. This project demonstrates a robust implementation of the MVC pattern with a clean layered architecture, featuring secure password hashing and session management.

---

## ⚡ Quick Preview

| Page | Description | Business Flow |
| :--- | :--- | :--- |
| **Login** | Modern, clean sign-in interface | Validates credentials against hashed passwords in DB. |
| **Register** | Comprehensive sign-up page | Hashes passwords using BCrypt and ensures unique usernames. |
| **Home** | Secure dashboard | Protected area accessible only after successful authentication. |

> [!TIP]
> This project uses **Inter** typography and a customized CSS design system for a premium look and feel without external UI frameworks.

---

## 🛠️ Tech Stack

### Backend & Core
- **Framework:** Jakarta EE 10 (Servlet 6.0, JSP 3.0)
- **Persistence (ORM):** Jakarta Persistence (JPA) 3.2 with **EclipseLink 5.0**
- **Security:** **jBCrypt 0.4** (Strong hashing with salt)
- **JDK Support:** Optimized for Java 14+

### Database & Build
- **Database:** MySQL 8.0+
- **Build Tool:** Apache Maven
- **Driver:** MySQL Connector/J 8.0.32

### Frontend
- **Engine:** JSP (Jakarta Server Pages)
- **Styling:** Custom Vanilla CSS (Modern Design System)
- **Assets:** Google Fonts (Inter)

---

## ✨ Features

- [x] **Secure Authentication:** Implementation of login functionality with session persistence.
- [x] **Password Hashing:** Industry-standard **BCrypt** hashing for storing user credentials.
- [x] **User Registration:** Robust sign-up flow with duplicate username detection.
- [x] **Layered Architecture:** Clear separation of concerns (Controller, DAL, Entity).
- [x] **Database Auto-Sync:** JPA `schema-generation` configured to create tables automatically.
- [x] **Session & Cookie Management:** Validates users and handles "Remember Me" logic via cookies.
- [x] **Modern UI/UX:** Responsive layouts with elegant alert notifications and form validation.

---

## 📂 Project Structure

```text
jakarta-ee-authentication
├── src/main/java/com/fpt
│   ├── controller           # Web Layer: Servlets handling HTTP requests
│   │   └── AuthController.java
│   ├── dal                  # Data Access Layer: JPA-based database operations
│   │   └── AccountDal.java
│   └── entity               # Model Layer: JPA Database Entities
│       └── Account.java
├── src/main/resources/META-INF
│   └── persistence.xml      # JPA Configuration (Database connection & ORM)
├── src/main/webapp
│   ├── css                  # UI Styling (style.css)
│   ├── WEB-INF              # Web deployment descriptors
│   ├── login.jsp            # Sign-in UI
│   ├── register.jsp         # Sign-up UI
│   └── home.jsp             # Secure dashboard UI
└── pom.xml                  # Maven Dependencies & Build Configuration
```

---

## 🏛️ Architecture

The project follows a **Layered MVC Architecture**:

1.  **View (Webapp/JSP):** Responsible for rendering the UI and capturing user input.
2.  **Controller (Servlets):** Acts as the orchestrator. Validates requests, interacts with the DAL, and determines the next view.
3.  **DAL (Data Access Layer):** Uses `EntityManager` (JPA) to perform CRUD operations on the database.
4.  **Entity (JPA Models):** Maps Java classes directly to MySQL tables using annotations.

---

## ⚙️ Installation & Setup

### Prerequisites
- JDK 14 or higher
- Apache Maven 3.x
- MySQL Server (Local or Docker)
- A Jakarta EE compatible server (e.g., Payara, GlassFish, or Tomcat 10+)

### 1. Database Configuration
Create a database named `jakarta_ee_authentication` in MySQL:
```sql
CREATE DATABASE jakarta_ee_authentication;
```

### 2. Configure Credentials
Update `src/main/resources/META-INF/persistence.xml` with your database credentials:
```xml
<property name="jakarta.persistence.jdbc.user" value="your_username"/>
<property name="jakarta.persistence.jdbc.password" value="your_password"/>
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/jakarta_ee_authentication"/>
```

### 3. Build & Run
Run the following Maven command to build the project:
```bash
mvn clean package
```
Deploy the generated `.war` file from the `target/` directory to your application server.

---

## 🔑 Environment Variables & Security

| Property | Default Value | Description |
| :--- | :--- | :--- |
| `jdbc.url` | `jdbc:mysql://localhost:3306/jakarta_ee_authentication` | Database Connection URL |
| `jdbc.user` | `root` | Database Username |
| `jdbc.password` | `112233` | Database Password |
| `jbcrypt.salt` | Managed by Library | Salt used for password hashing |

> [!WARNING]
> **Production Security Note:** Currently, the `AuthController` stores raw usernames and passwords in cookies for demo purposes. In a production environment, use **JWT (JSON Web Tokens)** or **Secure Session Tokens** instead.

---

## 📑 API Summary

The application uses a unified controller pattern for handling actions:

| Method | Endpoint | Action | Description |
| :--- | :--- | :--- | :--- |
| `POST/GET` | `/controller` | `Login` | Sign in with username and password |
| `POST/GET` | `/controller` | `Register` | Create a new user account |
| `POST/GET` | `/controller` | `Logout` | Invalidate session and clear cookies |

---

## 🚀 Future Improvements

- [ ] **JWT Integration:** Implement stateless authentication.
- [ ] **RBAC (Role-Based Access Control):** Fully implement the `role` field in `Account`.
- [ ] **Input Sanitization:** Add ESAPI or similar for XSS protection.
- [ ] **Password Reset:** Email-based recovery flow.
- [ ] **UI Refresh:** Migration to TailwindCSS or Bootstrap 5 for advanced responsiveness.

---

## 👨‍💻 Author

**Senior Developer @ FPT**
- GitHub: [@maaitlunghau](https://github.com/maaitlunghau)
- Purpose: Self-study and foundation for Jakarta EE projects.

---

## ⚖️ License

This project is licensed under the **MIT License** - feel free to use it for your own learning or projects!
