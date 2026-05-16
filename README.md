#  Gestion des Produits — Jakarta EE & Hibernate

> A full-stack web application for product management built with Jakarta EE Servlets, JSP, and Hibernate ORM — featuring authentication, role-based access control, and a clean responsive UI.

---

##  Preview

| Login Page | Admin Dashboard | User View |
|---|---|---|
| Authentication with register | Full CRUD on products | Read-only product list + search |

---

##  Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Jakarta EE — Servlets & JSP |
| **ORM** | Hibernate Framework |
| **Database** | MySQL (via XAMPP) |
| **Server** | Apache Tomcat 10 |
| **Build Tool** | Maven |
| **IDE** | Eclipse IDE |

---

##  Features

-  **Authentication** — Login & Register with session management
-  **Role-based Access Control** — Admin and User roles
-  **Product CRUD** — Create, Read, Update, Delete (Admin only)
-  **Product Consultation** — Read-only view for regular users
-  **Live Search** — Search products by name instantly
-  **Hibernate Persistence** — ORM for both `User` and `Produit` entities
-  **Responsive UI** — Clean design with custom CSS color palette

---

##  Project Structure

```
tp11-web/
├── src/main/java/
│   ├── Controlleur/
│   │   ├── HelloServlet.java       # Login logic
│   │   ├── RegisterServlet.java    # Registration logic
│   │   ├── AddServlet.java         # Add product
│   │   ├── EditServlet.java        # Edit product
│   │   ├── DeleteServlet.java      # Delete product
│   │   └── LogoutServlet.java      # Session invalidation
│   ├── DAO/
│   │   ├── IProduitDao.java        # Product DAO interface
│   │   ├── ProduitDAOImpH.java     # Hibernate implementation
│   │   ├── ProduitDaoImpl.java     # JDBC implementation (fallback)
│   │   ├── DAOFactory.java         # DAO Factory pattern
│   │   ├── userDAO.java            # User DAO (Hibernate)
│   │   └── DBConnection.java       # JDBC connection
│   ├── Model/
│   │   ├── produits.java           # Product entity
│   │   └── User.java               # User entity
│   └── Util/
│       └── HibernateUtil.java      # SessionFactory singleton
├── src/main/resources/
│   └── hibernate.cfg.xml           # Hibernate configuration
├── src/main/webapp/
│   ├── Hello.jsp                   # Login page
│   ├── register.jsp                # Register page
│   ├── home.jsp                    # Main dashboard
│   ├── ajout.jsp                   # Add product form
│   ├── edit.jsp                    # Edit product form
│   ├── Error.jsp                   # Error page
│   ├── index.jsp                   # Entry point
│   └── style.css                   # Global stylesheet
└── pom.xml
```

---

##  User Roles

| Role | Login | Password | Permissions |
|---|---|---|---|
| **Admin** | `admin` | `1234` | View + Add + Edit + Delete + Stats |
| **Admin** | `nour` | `nour` | View + Add + Edit + Delete + Stats |
| **Admin** | `manager` | `mgr2024` | View + Add + Edit + Delete + Stats |
| **User** | `alice` | `alice123` | View + Search only |


---

##  Setup & Installation

### Prerequisites
- Java 17+
- Apache Tomcat 10
- MySQL (XAMPP recommended)
- Eclipse IDE (or any Maven-compatible IDE)
- Maven 3.x

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/your-username/tp11-web.git
cd tp11-web
```

**2. Create the database**

Open phpMyAdmin and create a database named `jee`, then run:
```bash
SQL_setup.sql
```
This will:
- Create the `role` column in the `user` table
- Insert default admin and user accounts
- Insert sample products

**3. Configure Hibernate**

Edit `src/main/resources/hibernate.cfg.xml` if needed:
```xml
<property name="hibernate.connection.url">
    jdbc:mysql://localhost:3306/jee?useSSL=false&amp;serverTimezone=UTC
</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password"></property>
```

**4. Build & Deploy**
```bash
mvn clean package
```
Deploy the generated `.war` file to Apache Tomcat 10, or use Eclipse → Run on Server.

**5. Access the app**
```
http://localhost:8080/tp11-web
```

---

##  Database Schema

### Table `user`
```sql
CREATE TABLE user (
    login    VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    role     VARCHAR(20) DEFAULT 'user'
);
```

### Table `produits`
```sql
CREATE TABLE produits (
    ID_PRODUIT  BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOM_PRODUIT VARCHAR(100) NOT NULL,
    PRIX        DOUBLE NOT NULL
);
```



##  Architecture

```
Browser
   │
   ▼
JSP (View)
   │
   ▼
Servlet (Controller)
   │
   ▼
DAO Interface (IProduitDao / userDAO)
   │
   ▼
Hibernate Implementation (ProduitDAOImpH)
   │
   ▼
MySQL Database (jee)
```

---



This project was developed as part of a **Jakarta EE university course**.  
Feel free to use it as a reference for learning purposes.

---

> Made with  Java &  Hibernate
