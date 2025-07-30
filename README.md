# 🧠 Mental Health Journal & Recommendation System

A secure full-stack mental wellness platform that enables users to log moods, maintain journal entries, and receive personalized content such as motivational videos and self-help resources. Built with **Spring Boot**, **PostgreSQL**, and a **React frontend**, the system includes a layered admin model with **root-level approval** and a custom mail service integrated via **IPC**.

---

## 🚀 Features

### 🧘‍♀️ User Features
- Register and securely log in
- Track daily moods and write journal entries
- Get personalized content recommendations based on mood
- View history of journal entries and received content

### 🛠️ Admin Features
- Manage user-submitted content (approve/reject)
- View and manage all users and their content
- Add motivational YouTube videos with mood tags

### 🔐 Root User Features
- Preloaded root account for secure access
- Approve or reject admin registration requests
- System-wide oversight and control

---

## 🧰 Tech Stack

| Layer       | Technology                           |
|-------------|--------------------------------------|
| Backend     | Spring Boot, Spring Security, JWT    |
| Frontend    | React.js (REST API integration)      |
| Database    | PostgreSQL                           |
| Mail Service| Custom IPC-based mail sender         |
| Auth        | Spring Security + JWT                |

---

## 📦 Setup Instructions

### Prerequisites
- Java 17+
- Maven
- PostgreSQL
- React.js (for frontend)
- IPC-enabled mail service (custom or mock)

---

### 🧑‍💻 Backend Setup

```bash
git clone https://github.com/GameCorpGG/Mental-Health-Journal-Recommendation-System.git
cd Mental-Health-Journal-Recommendation-System/MhjrsApplication
mvn clean install
mvn spring-boot:run
```

```bash
### 🌐 Frontend Setup

cd frontend
npm install
npm start
```

## 🔐 Root User Bootstrapping
When the system starts for the first time, it automatically creates a secure root user for platform governance.
- Email: rachit181004@gmail.com
- Password: rootpassword@123 (stored in hashed form)


## 📡 API Overview
| Endpoint                     | Method | Access     | Description                           |
| ---------------------------  | ------ | ---------- | ------------------------------------- |
| `/auth/register`             | POST   | Public     | Register new users                    |
| `/auth/login`                | POST   | Public     | Authenticate users                    |
| `/admin/user/all_users`      | GET    | ADMIN      | Get Details of all the users          |
| `/admin/user/find`           | GET    | ADMIN      | Find one particular user              |
| `/admin/user/delete`         | DELETE | ADMIN      | Delete a particular user              |
| `/admin/content`             | GET    | ADMIN      | Fetch all content                     |
| `/admin/content/add`         | POST   | ADMIN      | Add YouTube videos with mood tags     |
| `/admin/content/delete`      | DELETE | ADMIN      | Delete Content from the database      |
| `/admin/content/approve`     | POST   | ADMIN      | Approve Content                       |
| `/admin/content/reject`      | POST   | ADMIN      | Reject Content                        |
| `/journal/create`            | POST   | USER       | Create a Journal Entry                |
| `/journal/history`           | GET    | USER       | View Previous Journal Entries         |
| `/mood/log`                  | POST   | USER       | Enter your mood log                   |
| `/recommendations/recommend` | GET    | USER       | View your recommendations             |

## ✉️ Custom Mail Service
This project uses an Inter-Process Communication (IPC) based mail service to send notifications (e.g., approval updates, registration success). The backend sends email messages through an IPC channel to a separate mail handler process.



