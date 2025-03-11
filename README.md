
---

## **📝 README for MongoDB**

# Spring Boot Member Management System (MongoDB)

## 📌 Overview
This is a **Spring Boot 3 + Java 21** project that provides a simple **Member Management System** with RESTful APIs.  
It allows users to:
- Add new members
- Retrieve members by ID
- List all members  
- Frontend is built with **React** for UI interactions.

## 🏗️ Tech Stack
- **Backend:** Spring Boot 3, Java 21, Spring Data MongoDB
- **Database:** MongoDB
- **Frontend:** React
- **Build Tool:** Maven

---

## 🚀 **Getting Started**

### **1️⃣ Prerequisites**
- **Java 21** (Ensure it's installed: `java -version`)
- **Maven 3+** (`mvn -version`)
- **Node.js 18+** (For React UI: `node -v`)
- **MongoDB** (Ensure it's installed and running)
- **Git** (For cloning the repo)

---

### 2️⃣ Clone the Repository

```sh
git clone https://github.com/sumitdeshinge/demomongo
```

### 3️⃣ MongoDB Setup

### **1. Start MongoDB Locally**
If MongoDB isn't running, start it:
```sh
mongod --dbpath /data/db
```

2. Verify MongoDB Connection
```sh
mongo
use kitchensink
```

### 4️⃣ Backend Setup
1. Configure MongoDB Connection in application.properties:
```sh
spring.data.mongodb.uri=mongodb://localhost:27017/kitchensink
```
2. Build & Run Spring Boot App
```sh
mvn clean install
mvn spring-boot:run
```
3. API Endpoints
```sh
List Members: GET http://localhost:8083/members
Get Member by ID: GET http://localhost:8083/members/{id}
Add Member: POST http://localhost:8083/members (JSON Body)
```

### 5️⃣ Frontend Setup
1. Navigate to frontend folder:
```sh
cd frontend
```
2. Install dependencies
```sh
npm install
```
3. Run React App:
```sh
npm start
```
4. Open Browser:
http://localhost:3000/ to interact with the system.
