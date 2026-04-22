# Customer Management System
## Full-Stack: Spring Boot (Backend) + Angular (Frontend)

---

## FOLDER STRUCTURE

```
CustomerManagementSystem/
├── backend/        ← Spring Boot (Java, runs on port 8080)
└── frontend/       ← Angular (runs on port 4200)
```

---

## PREREQUISITES

Make sure you have these installed:
- Java 21
- Maven (or use the included `mvnw`)
- Node.js (v18+)
- Angular CLI → `npm install -g @angular/cli`
- MySQL running locally on port 3306

---

## STEP 1 — Setup MySQL Database

Open MySQL Workbench or your MySQL terminal and run:

```sql
CREATE DATABASE IF NOT EXISTS customers;
```

The backend already has:
- Username: `root`
- Password: `SDcard!@123`
- DB name: `customers`

If your MySQL password is different, edit:
`backend/src/main/resources/application.properties`

---

## STEP 2 — Run the Backend (Terminal 1)

Open a terminal in VS Code, navigate to the backend folder:

```bash
cd CustomerManagementSystem/backend
```

Then run:

```bash
# On Mac/Linux:
./mvnw spring-boot:run

# On Windows:
mvnw.cmd spring-boot:run
```

✅ Backend is ready when you see:
`Started ApimitApplication in X seconds`

It runs at: http://localhost:8080

---

## STEP 3 — Run the Frontend (Terminal 2)

Open a **second terminal** in VS Code (click the + icon in the terminal panel).

```bash
cd CustomerManagementSystem/frontend
npm install
ng serve
```

✅ Frontend is ready when you see:
`Application bundle generation complete.`

Open in browser: http://localhost:4200

---

## CORS — Already Configured ✅

The file `backend/src/main/java/com/example/demo/CorsConfig.java`
already allows requests from `http://localhost:4200`.
No extra setup needed.

---

## API ENDPOINTS REFERENCE

| Module               | Base URL                        |
|----------------------|---------------------------------|
| Customer Details     | GET/POST/PUT/DELETE /customer-details      |
| Customer Name        | GET/POST/PUT/DELETE /customer-name         |
| Customer Address     | GET/POST/PUT/DELETE /customer-address      |
| Contact Details      | GET/POST/PUT/DELETE /customer-contact-details |
| Identification       | GET/POST/PUT/DELETE /customer-identification  |
| Classification       | GET/POST/PUT/DELETE /customer-classification  |
| Proof of Identity    | GET/POST/PUT/DELETE /customer-proof-of-identity |

---

## TROUBLESHOOTING

**`ng` not found:**
```bash
npm install -g @angular/cli
```

**Port 8080 already in use:**
Change `server.port=8080` in `application.properties` to another port (e.g. 8081),
then update `api.service.ts` → `private base = 'http://localhost:8081';`

**MySQL connection refused:**
Make sure MySQL service is running. On Windows, start it from Services or MySQL Workbench.

**Data not loading in UI:**
Make sure BOTH backend and frontend are running at the same time.
