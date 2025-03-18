# Recommendation based on user data

This is a Java web application built using **Eclipse**, **Servlets**, **JSP**, and **MySQL**. The system allows managing user profiles, financial data, device usage, social media activity, and lifestyle data. It also provides product recommendations based on user data.

## Features
- **User Management**: Create, update, delete, and view user information.
- **Device Tracking**: Store user device preferences and internet usage.
- **Social Media Analytics**: Track social media engagement and fatigue levels.
- **Entertainment Usage**: Monitor video, gaming, and music consumption.
- **Lifestyle Tracking**: Record sleep, reading, and physical activity data.
- **Product Recommendations**: Provide personalized recommendations based on user data.

## Technologies Used
- **Backend**: Java, Servlets, JSP
- **Frontend**: HTML, CSS, JSTL
- **Database**: MySQL
- **Tools**: Eclipse, Apache Tomcat, JDBC
- **Version Control**: Git & GitHub

## Installation & Setup

### Prerequisites
- **Java 8+**
- **Apache Tomcat 9+**
- **MySQL Database**
- **Eclipse IDE (with EE support)**

### Step 1: Clone the Repository
```sh
git clone https://github.com/yourusername/your-repo.git
cd your-repo
```

### Step 2: Set Up the Database
1. Create a MySQL database:
   ```sql
   CREATE DATABASE name_you_want;
   ```
2. Import the schema:
   Import the schema.sql file into your database.
   
   
3. Update `ConnectionManager.java` with your database credentials.

### Step 3: Configure Tomcat in Eclipse
1. Open Eclipse and import the project.
2. Right-click the project → **Properties** → **Targeted Runtimes** → Select **Apache Tomcat**.
3. Ensure set up impl and spec 1.2.5 jar files.
4. Ensure `web.xml` is properly configured.
5. Start Tomcat and deploy the application.

### Step 4: Run the Application
1. Access the application at:
   ```
   http://localhost:8080/Project
   ```
2. Navigate to different pages for user management, analytics, and recommendations.

## API Endpoints
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/users` | `GET` | View all users |
| `/users` | `POST` | Add a new user |
| `/devices` | `GET` | View user devices |
| `/socialmedia` | `GET` | View social media usage |
| `/entertainment` | `GET` | View entertainment data |
| `/lifestyle` | `GET` | View lifestyle habits |
| `/recommendations` | `GET` | Get product recommendations |

## Contributing
1. Fork the repository.
2. Create a new branch (`feature-branch`).
3. Commit your changes (`git commit -m "Add new feature"`).
4. Push to the branch (`git push origin feature-branch`).
5. Submit a pull request.

## License
This project is licensed under the **MIT License**.
