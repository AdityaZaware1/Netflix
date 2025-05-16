# 🎬 Netflix Clone Backend

A backend system for a Netflix-like video streaming platform built using **Spring Boot microservices** architecture.  
This project incorporates **JWT-based authentication**, **Razorpay payment integration**, and **Apache Kafka** for event-driven communication.  
All microservices are **containerized using Docker** and **orchestrated with Kubernetes** to ensure scalability, reliability, and easy deployment.

---

## 🧱 Microservices Overview

| Service Name           | Description                                                         |
|------------------------|---------------------------------------------------------------------|
| **User Service**       | Manages user profiles, roles, and user data                         |
| **Auth Service**       | Handles authentication, JWT token generation, and security          |
| **Subscription Service** | Manages user subscriptions and payment processing via Razorpay    |
| **Stream Service**     | Handles video streaming logic and content management                |
| **History Service**    | Tracks user watch history and viewing preferences                   |
| **API Gateway**        | Routes requests to the appropriate microservices                    |
| **Server**             | Main backend orchestration or entry point for the platform          |

---

## ⚙️ Key Technologies

- **Spring Boot** – Framework for building microservices
- **JWT** – JSON Web Tokens for secure authentication and authorization
- **Razorpay** – Payment gateway for subscription handling
- **Apache Kafka** – Event-driven communication between services
- **Docker** – Containerization of all microservices
- **Kubernetes** – Container orchestration and deployment management
- **MySQL** – Persistent relational database storage

---

## 🚀 Features

- Secure and scalable user authentication with JWT
- Subscription management with seamless Razorpay payment integration
- Reliable event-driven architecture with Kafka for decoupled service communication
- Video streaming management supporting personalized user experience
- Watch history tracking for content recommendations and progress
- Fully containerized microservices for easy deployment and scaling using Docker and Kubernetes

---

## 🛠️ Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- Docker
- Kubernetes cluster (Minikube, Docker Desktop, or cloud-managed Kubernetes)
- Kafka cluster (can be run locally using Docker)
- Razorpay account and API keys

---

### Clone the Repository and start the container

```bash
git clone https://github.com/yourusername/netflix-backend.git
cd netflix-backend
docker compose build -t

