# RentEase – CLI-Based Property Management System

## 📌 Project Overview
RentEase is a **Command-Line Interface (CLI) Property Management System** developed in **Java**, designed to streamline property management operations for different user roles. The system allows for account management, property transactions, maintenance tracking, and financial management.

## 🔹 Features
### **1. User Roles**
- **Admin**: Creates/deletes accounts (including other admins), views user information, and checks transaction history.
- **Property Dealer**: Adds/removes properties, views owned/unowned properties.
- **User**: Buys/sells properties, puts them on rent, submits maintenance requests, and manages related challans.
- **Maintenance Worker**: Views maintenance requests, chooses tasks, and generates automatic challans upon task completion.
- **Accountant**: Tracks and retrieves past and ongoing transactions.

### **2. Authentication System**
- Secure **sign-in/login** mechanism.
- Role-based access control.

### **3. Property Management**
- Add, remove, and update property listings.
- View available and owned properties.

### **4. Financial Management**
- Automated **challan generation** for property-related expenses.
- Transaction tracking and retrieval.

## 🛠️ Technologies Used
- **Programming Language:** Java
- **Data Storage:** Text files

## 🚀 How to Run the Project
1. Clone this repository:
   ```bash
   git clone https://github.com/MohsinN05/PF-Project.git
   ```
2. Navigate to the project directory:
   ```bash
   cd RentEase
   ```
3. Compile the Java files:
   ```bash
   javac Property.java
   ```
4. Run the application:
   ```bash
   java Property
   ```


