                WELCOME TO MY BANK PROJECT MADE BY HARSHAD MADHAV DHUPPE
                    ROLL NO : 3145, PRN NUMBER : 23025331844046
                CSMSS CHH. SHAHU COLLEGE OF ENGINEERING SAMBHAJINAGAR

# 🏦 Bank Management System (Core Java + MySQL)

This is a Java-based **Bank Management Project** built using **Core Java (OOP + JDBC + MySQL)**.  
The project performs essential banking operations and stores data securely in a MySQL database.

---

## 🚀 Features
✔ Create New Bank Account  
✔ Login to Account  
✔ Deposit Amount  
✔ Withdraw Amount  
✔ Check Account Balance  
✔ Transaction Security  
✔ Stores all data in MySQL database using JDBC  

---

## 🛠️ Technologies Used
| Component | Technology |
|----------|-------------|
| Programming Language | Java (Core Java + OOP) |
| Database | MySQL |
| Connectivity | JDBC |
| IDE | Eclipse |

---

Bank-Project/
│
├─ src/pk/
│ ├─ BankSystem.java
│ ├─ DatabaseConnection.java
│
├─ README.md ← (this file)





---

## 🔧 MySQL Database Configuration
Create a database and table before running the project:

```sql
CREATE DATABASE bankdb;
USE bankdb;

CREATE TABLE accounts (
    acc_no INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    password VARCHAR(50),
    balance DOUBLE
);



String url = "jdbc:mysql://localhost:3306/bankdb";
String username = "root";
String password = "#########";
