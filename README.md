<!DOCTYPE html>
<html lang="en">

<body>

<h1>📰 tech-updates-app</h1>
<p>A centralized web platform for tech enthusiasts to stay updated on the latest technology news, blogs, and trends — featuring real-time aggregation, personalized features, and interactive options.</p>

<hr />

<h2>🎯 Project Vision</h2>
<p>This application provides a hub for tech enthusiasts to access up-to-date technology information. Built with <strong>Java Spring Boot</strong> for the backend and <strong>React.js</strong> for the frontend, it supports real-time content aggregation, personalization, and interactivity.</p>

<hr />

<h2>🛠 Tech Stack</h2>
<ul>
  <li><strong>Backend:</strong> Java Spring Boot</li>
  <li><strong>Frontend:</strong> React.js</li>
  <li><strong>Database:</strong> PostgreSQL or MongoDB</li>
  <li><strong>News Fetching:</strong> NewsAPI.org or RSS Feed Parsing</li>
  <li><strong>Authentication:</strong> Spring Security (planned)</li>
  <li><strong>Deployment:</strong> Firebase</li>
</ul>

<hr />

<h2>📁 Project Structure</h2>
<details>
  <summary>Click to view structure</summary>
  <pre><code>tech-updates-app/
├── backend/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── notmine/
│           │           └── techupdates/
│           │               ├── TechUpdatesApp.java
│           │               ├── controller/
│           │               ├── service/
│           │               ├── repository/
│           │               └── model/
│           └── resources/
│               └── application.properties
├── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── App.js
│   │   └── index.js
│   ├── public/
│   │   └── index.html
│   └── package.json
│
├── README.md
└── .gitignore
</code></pre>
</details>

<hr />

<h2>📅 Timeline & Milestones</h2>
<table>
  <thead>
    <tr>
      <th>Week</th>
      <th>Milestone</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>Week 1</td><td>✅ Setup & Documentation</td></tr>
    <tr><td>Week 2</td><td>🔐 User Authentication, 📰 Article Model, 🔄 Frontend Integration</td></tr>
    <tr><td>Week 3</td><td>📚 Bookmarks, 🔍 Search & Filter Features</td></tr>
    <tr><td>Week 4</td><td>🧪 Testing, 🚀 Deployment, 📄 Final Documentation, ✨ Polishing</td></tr>
  </tbody>
</table>

<hr />

<h2>✅ Features</h2>
<ul>
  <li>🔐 Spring Security-based User Authentication *(planned)*</li>
  <li>📰 Real-time tech news via NewsAPI or RSS feeds</li>
  <li>🔖 Bookmark articles for future reading</li>
  <li>🔍 Search & filter articles</li>
  <li>🎨 Clean and responsive React UI</li>
</ul>

<hr />

<h2>🧑‍💻 Local Setup</h2>

<h3>Backend (Spring Boot)</h3>
<pre><code>cd backend
./mvnw spring-boot:run</code></pre>

<h3>Frontend (React)</h3>
<pre><code>cd frontend
npm install
npm start</code></pre>

<hr />

<hr />

<h2>📁 Database Conection</h2>
<details>
  <summary>Click to view How to setup database</summary>
      <h3>1. Setup PostgreSQL locally (initially)</h3>
      <p>Install PostgreSQL locally or on a server.

Create a database for this project, e.g., tech_news_db.

Remember  PostgreSQL username and password (default user is usually postgres).</p>

<h3>2. Add the PostgreSQL Database . </h3>
<P>In  pom.xml file, include the PostgreSQL JDBC driver so that Spring Boot knows how to communicate with PostgreSQL:</P>

<pre><code><dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
</code></pre>

<h3>3. Configure Application Properties</h3>
<p>In src/main/resources/application.properties, add the database connection properties:</p>

<pre><code>spring.datasource.url=jdbc:postgresql://localhost:5432/tech_news_db
spring.datasource.username=postgres
spring.datasource.password=your_password_here
# JPA and Hibernate settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
</code></pre>
<p>spring.datasource.url specifies the JDBC URL pointing to your PostgreSQL database.

username and password must match your database credentials.

spring.jpa.hibernate.ddl-auto=update tells Hibernate to create or update tables automatically based on your entities.

spring.jpa.show-sql=true enables logging of SQL statements to the console (useful for debugging).</p>

<h3>4. Define JPA entites</h3>

<p>Model classes are annotated with JPA annotations like @Entity, @Table, @Id, and @GeneratedValue to map Java classes to database tables, for example:</p>

<pre><code>@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;
    private String passwordHash;

    // getters and setters...
}
</code></pre>

<h3>5. Create Repository Interfaces</h3>
<p>Repository interfaces extending JpaRepository allow CRUD operations without writing SQL:</p>

<pre><code>@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
</code></pre>

<h3>6. Use repository in controllers</h3>

<p>Inject your repository in controllers using @Autowired and use methods like save() to persist entities:</p>

<pre><code>@Autowired
private UserRepository userRepository;

@PostMapping("/api/users/register")
public User registerUser(@RequestBody User newUser) {
    return userRepository.save(newUser);
}
</pre></code>

<h3>7. Run and verify</h3>

<p>Start your PostgreSQL server.

Run your Spring Boot application.

Register a user or create records via your API.

Confirm records exist using pgAdmin or SQL queries.</p>

<h2>Summery</h2>

<p>By adding the PostgreSQL driver, configuring connection properties in application.properties, defining JPA entity classes and repositories, and using Spring Data JPA repositories in your application, the backend successfully connects and operates with the PostgreSQL database.</p>

</details>
<hr />

<h2>📦 Deployment</h2>
<p>Final deployment will be handled via <strong>Firebase Hosting</strong> after testing and polishing.</p>

<hr />

<h2>🙌 Contribution</h2>
<p>Feel free to fork, clone, and submit pull requests. Contributions are welcome!</p>

<hr />

<h2>👨‍🎓 Author</h2>
<p><strong>Prajwal Sutar</strong><br />
Computer Engineering Student | Backend & Cloud Enthusiast<br />
📍 Pune, India</p>

</body>
</html>
