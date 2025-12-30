```markdown
# To-Do App Springboot

A full **To-Do List Web Application** built with **Spring Boot** (backend) and pure **HTML, CSS, and JavaScript** (frontend).  
This project provides a simple login & register flow with **JWT authentication** and **CRUD APIs** to manage to-do tasks.

---

## 🚀 Features

✅ User Registration & Login (JWT Token)  
✅ Create, Read, Update, and Delete Todos  
✅ Mark Todos as Completed  
✅ Separate Frontend & Backend  
✅ RESTful API design  
✅ Authorization using Bearer Token

---

## 🛠 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Java, Spring Boot, Spring Data JPA |
| Database | MySQL (or any other relational DB) |
| Frontend | HTML, CSS, JavaScript |
| Authentication | JWT (JSON Web Tokens) |
| API Testing | Postman / Browser |

---

## 🗂 Project Structure

```

├── backend/                    # Spring Boot backend REST API
│   ├── src/
│   │   ├── main/java/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
├── TodoFrontend/              # Frontend for UI
│   ├── login.html
│   ├── register.html
│   ├── todos.html
│   ├── style.css
│   └── script.js
├── README.md

````

---

## 📌 Backend API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/auth/register` | Register new user |
| `POST` | `/auth/login` | Login user & return JWT token |

### Todos
> All todo routes require:  
`Authorization: Bearer <token>`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/todo` | Get all todos |
| `GET` | `/api/v1/todo/{id}` | Get todo by ID |
| `POST` | `/api/v1/todo/create` | Create new todo |
| `PUT` | `/api/v1/todo/{id}` | Update todo |
| `DELETE` | `/api/v1/todo/{id}` | Delete todo |

---

## 🏁 Getting Started

### 1️⃣ Clone the repository
```sh
git clone https://github.com/Mugilan2003/To-Do-App-Springboot.git
````

---

### 2️⃣ Backend Setup

#### 📌 Configure Database

Open:

```
backend/src/main/resources/application.properties
```

Update with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 3️⃣ Run Backend

Navigate to the backend directory:

```sh
cd backend
mvn clean install
mvn spring-boot:run
```

Backend will run at:

```
http://localhost:8080
```

---

### 4️⃣ Frontend Setup

Just open the `TodoFrontend/` files in your browser:

✔ `login.html`
✔ `register.html`
✔ `todos.html`

No server needed — all frontend is static.

---

## 🧪 How to Use

1. **Register** a new account
2. **Login** to get your JWT token
3. Store token in localStorage
4. Visit **todos.html**
5. Add / View / Update / Delete todos

---

## 🧠 Notes

📌 JWT must be sent in headers:

```
Authorization: Bearer <token>
```

📌 Completed tasks are shown with a strike-through in UI.

---

## 📌 Screenshots (Optional)

*Add screenshots of login, todos list, etc. here*

---

## 📫 Contributions

Contributions and improvements are welcome!
Feel free to open issues or submit pull requests.

---

## 📝 License

This project is open-source and available under the MIT License.
Feel free to use it and modify as you like.

