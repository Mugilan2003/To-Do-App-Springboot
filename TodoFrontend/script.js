// Shared script for login, register, and todos pages
const SERVER_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

// Login page logic
function login() {
    
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(`${SERVER_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    })
    .then(async response => {
        if (!response.ok) {
            const text = await response.text();
            throw new Error(text || "Login failed");
        }
        return response.json();
    })
    .then(data => {
        localStorage.setItem("token", data.token);
        window.location.href = "todos.html";
    })
    .catch(err => alert(err.message));
}



// Register page logic
function register() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(`${SERVER_URL}/auth/register`, {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({ email, password })
    })
        .then(response => {
            if (response.ok) {
                alert("Registration successfull. Please Login");
                window.location.href = "login.html";
            }
            else {
                return response.json().then(data => {
                    throw new Error(data.message || "Registration Failed");
                })
            }
        })
        .catch(error => {
            alert(error.message);
        })
}

// Todos page logic
function createTodoCard(todo) {
    const card = document.createElement("div");
    card.className = "todo-card";

    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.checked = todo.isCompleted;

    const span = document.createElement("span");
    span.textContent = todo.title;

    span.style.textDecoration = todo.isCompleted ? "line-through" : "none";
    span.style.color = todo.isCompleted ? "#aaa" : "#000";

checkbox.addEventListener("change", function () {
    
    updateTodoStatus({
        id: todo.id,
        title: todo.title,
        description: todo.description,
        isCompleted: checkbox.checked
    });
});

    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "x";
    deleteBtn.onclick = ()=>deleteTodo(todo.id);

    card.appendChild(checkbox);
    card.appendChild(span);
    card.appendChild(deleteBtn);

    return card;
}

function loadTodos() {
    if(!token){
        alert("Please Login First...!");
        window.location.href="login.html";
        return;
    }
    fetch(`${SERVER_URL}/api/v1/todo`, {
        method: "GET",
        headers: {Authorization : `Bearer ${token}`}
        // ,body:JSON.stringify({title: todoText, isCompleted: false})
    })
    .then(response => {
        if (!response.ok) {
             throw new Error(data.message || "Failed to update todo");
            } 
            return response.json();
        })
        .then((todos)=>{
            const todoList=document.getElementById("todo-list");
            todoList.innerHTML="";
            if(!todos || todos.length===0){
                todoList.innerHTML='<p id="empty-message">No Todos yet. Add one below!</p>';
            }
            else{
                todos.forEach((todo)=>{
                    todoList.appendChild(createTodoCard(todo));
                });
            }
        })
        .catch(() => {
            document.getElementById("todo-list")
                    .innerHTML='<p style="color:red">Failed to load Todos</p>';
        })
}

function addTodo() {
    const input=document.getElementById("new-todo");
    const todoText=input.value.trim();
    if (!todoText) {
        alert("Please enter a todo title");
        return;
    }
fetch(`${SERVER_URL}/api/v1/todo/create`, {
        method: "POST",
        headers: {"Content-type": "application/json",
            Authorization : `Bearer ${token}`},
        body:JSON.stringify({title: todoText, description: "todo", isCompleted: false})
    })
    .then(response => {
        if (!response.ok) {
             throw new Error(data.message || "Failed to update todo");
            } 
            return response.json();
        })
        .then(()=>{
            input.value="";
            loadTodos();
        })
        .catch(error => {
            alert(error.text);
        })  
}

function updateTodoStatus(todo) {
    fetch(`${SERVER_URL}/api/v1/todo/${todo.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        },
            body: JSON.stringify(todo)
    })
    .then(async response => {
        if (!response.ok) {
            const text = await response.text();
            throw new Error(text || "Failed to update todo");
        }
        return response.json();
    })
    .then(() => loadTodos())
    .catch(error => alert(error.message));
}


function deleteTodo(id) {
    fetch(`${SERVER_URL}/api/v1/todo/${id}`, {
        method: "DELETE",
        headers: {Authorization : `Bearer ${token}`}
    })
    .then(response => {
        if (!response.ok) {
             throw new Error(data.message || "Failed to delete todo");
            } 
            return response.text();
        })
        .then(()=>loadTodos())
        .catch(error => {
            alert(error.text);
        })
}

document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("todo-list")) {
        loadTodos();
    }
});
