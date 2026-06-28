# 📧 Smart Bulk Email Automation System

A production-inspired email automation system built with **Java, Spring Boot, MySQL, and Brevo SMTP** that sends personalized emails while preventing duplicate deliveries.

Unlike a simple bulk mail sender, this application maintains a database of previously contacted recipients to ensure the same email address is never contacted twice.

---

# 🚀 Features

- Send emails to multiple recipients in a single request
- Personalized email content using recipient email
- Resume attachment support
- Duplicate email detection using MySQL
- Skip previously contacted recipients
- Automatic recipient persistence
- Detailed logging using SLF4J
- RESTful API
- SMTP integration using Brevo
- Exception handling during email delivery
- Clean layered architecture

---

# 🏗 Architecture

```
                Client

                  │

                  ▼

        EmailController (REST API)

                  │

                  ▼

           EmailService

                  │

      Check Recipient Database

          ┌───────────────┐
          │               │
          ▼               ▼

   Email Exists      Email Doesn't Exist

          │               │

      Skip Email     Send via Brevo SMTP

                          │

                          ▼

                Save Recipient Record

                          │

                          ▼

                     Return Summary
```

---

# 📂 Project Structure

```
src
│
├── controller
│     └── EmailController
│
├── dto
│     └── EmailDto
│
├── entity
│     └── Recipient
│
├── repository
│     └── RecipientRepository
│
├── service
│     └── EmailService
│
└── resources
      └── application.properties
```

---

# 🛠 Tech Stack

| Technology | Purpose |
|------------|----------|
| Java 21 | Backend |
| Spring Boot | REST API |
| Spring Mail | Email Sending |
| Spring Data JPA | Database Access |
| MySQL | Recipient Storage |
| Brevo SMTP | Email Delivery |
| Maven | Build Tool |
| Lombok | Boilerplate Reduction |
| SLF4J | Logging |

---

# 🔄 Email Processing Flow

```
Receive Email List

        │

        ▼

For Each Email

        │

        ▼

Check Database

        │

 ┌──────┴──────────┐

 │                 │

Exists         Doesn't Exist

 │                 │

Skip         Send Email

                    │

                    ▼

          Email Sent Successfully

                    │

                    ▼

          Save Recipient Record
```

---

# 📬 API Endpoints

## Send Default Email

```
POST /email/send
```

Request

```json
[
    "john@gmail.com",
    "alice@gmail.com",
    "hr@company.com"
]
```

---

## Send Custom Email

```
POST /email/send1
```

Request

```json
{
    "recipients":[
        "john@gmail.com",
        "alice@gmail.com"
    ],
    "subject":"Software Engineer Application",
    "body":"Hello {name}, I hope you are doing well."
}
```

---

# 🗄 Database Schema

Recipient

| Column | Type |
|----------|--------|
| id | BIGINT |
| email | VARCHAR (Unique) |
| name | VARCHAR |

---

# 📎 Attachments

Supports sending PDF attachments such as:

- Resume
- Portfolio
- Cover Letter

---

# 📝 Personalization

The application automatically extracts a friendly name from the email.

Example

```
john.doe@gmail.com

↓

John Doe
```

Then

```
Hello {name}
```

becomes

```
Hello John Doe
```

---

# 🔐 Duplicate Protection

Before sending an email, the application checks the database.

If the recipient already exists

```
Skip Email
```

Otherwise

```
Send Email

↓

Save Recipient
```

This prevents accidental duplicate emails during future campaigns.

---

# 📈 Logging

Every email operation is logged.

Example

```
INFO  Email successfully sent to hr@company.com

INFO  Saving recipient

INFO  Recipient already exists. Skipping.

ERROR Failed to send email
```

---

# ⚙ Configuration

Configure SMTP inside

```
application.properties
```

Example

```properties
spring.mail.host=smtp-relay.brevo.com
spring.mail.port=587
spring.mail.username=YOUR_SMTP_LOGIN
spring.mail.password=YOUR_SMTP_PASSWORD
```

---

# 📌 Future Improvements

- CSV Upload Support
- Excel Upload
- Retry Mechanism
- Email Status Dashboard
- Email Scheduling
- Campaign Management
- RabbitMQ Integration
- Kafka Integration
- Open Tracking
- Click Tracking
- Webhook Support
- Docker Deployment
- Admin Dashboard
- Authentication & Authorization

---

# 🎯 Why This Project?

This project demonstrates practical backend engineering concepts beyond basic email sending.

It showcases:

- REST API Design
- Spring Boot Architecture
- Database Integration
- SMTP Integration
- Duplicate Detection
- Batch Processing
- Logging
- Exception Handling
- Clean Code Practices
- Real-world Automation Workflow

---

# 👨‍💻 Author

**Deepesh Agrawal**

Software Engineer

- Java
- Spring Boot
- REST APIs
- MySQL
- PostgreSQL
- Git
- Jenkins
- Backend Development
