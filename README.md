# 🎬 Netflix Backend - Video Streaming Platform

A robust backend system for a Netflix-like video streaming platform built with **Spring Boot microservices**, featuring content management, user subscriptions, video streaming, and intelligent recommendations using **OpenAI embeddings and pgvector**.

---

## 📌 Features

- ✅ **User Authentication & Authorization** (Keycloak)
- ✅ **Video Upload & Streaming**
- ✅ **Subscription Management**
- ✅ **Content Recommendation System** (OpenAI + pgvector)
- ✅ **Email-based Forgot Password with 2FA**
- ✅ **Content Categorization & Metadata Management**
- ✅ **Docker & Kubernetes Deployment**
- ✅ **Kafka for Asynchronous Communication**

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot (Microservices)
- **Database:** PostgreSQL with pgvector
- **Security:** Keycloak, JWT, 2FA
- **AI Recommendations:** OpenAI Embeddings API
- **Async Messaging:** Apache Kafka
- **Containerization:** Docker, Kubernetes
- **Others:** Feign Clients, Spring Cloud Gateway, Lombok

---

## 🧠 Recommendation System

The Recommendation Service uses OpenAI’s embeddings API to generate vector representations of content metadata (title, genre, description). These vectors are stored in PostgreSQL using the `pgvector` extension and compared using cosine similarity to recommend similar shows/movies.

---

## 🧪 Microservices Overview

| Service | Description |
|--------|-------------|
| **User Service** | Handles user registration, login, roles |
| **Video Service** | Uploads, streams, and categorizes videos |
| **Subscription Service** | Manages plans, payments, and access |
| **Recommendation Service** | Suggests similar content using embeddings |
| **Email Service** | Sends OTPs and password reset links |
| **Gateway Service** | API gateway for routing & authentication |
| **Kafka Event Bus** | Connects services asynchronously |

---

## 🚀 Getting Started

### Prerequisites

- Docker & Docker Compose
- Java 17+
- PostgreSQL with `pgvector` extension
- OpenAI API Key

### Run Locally

```bash
git clone https://github.com/yourusername/netflix-backend.git
cd netflix-backend
docker-compose up --build
