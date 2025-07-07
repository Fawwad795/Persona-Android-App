# Persona - NUST Student Portal App

A comprehensive Android application designed for NUST (National University of Sciences and Technology) students to manage their academic and campus life efficiently. This project was developed as part of Object Oriented Programming and Database Management System courses in the second semester.

## 📝 Project Description

Persona is a modern student portal application that showcases the idea to provide NUST students with a centralized platform to access various campus services and manage their academic information. The app features a simple , clean, intuitive interface that allows students to authenticate using their Qalam ID and access multiple university services (not implemented fully) from a single platform.

The application implements object-oriented programming principles with a clear separation of concerns, utilizing inheritance, encapsulation, and abstraction. It integrates with Firebase for real-time data management and follows Android development best practices.

## ✨ Features

### Authentication System

- **Secure Login**: Students can log in using their NUST Qalam ID and password
- **User Registration**: New student signup functionality
- **Session Management**: Secure user session handling

### Student Services Dashboard (Static)

- **Student Profile Management**: View and manage personal academic information
- **Attendance Tracking**: Monitor monthly attendance percentage with visual progress indicators
- **E-Pass System**: Digital pass management for campus access
- **Shuttle Service**: Campus shuttle tracking and booking
- **Menu Services**: Campus dining menu and food services
- **Announcements**: University-wide announcements and notifications
- **Complaints System**: Submit and track campus-related complaints
- **Requests Management**: Submit various administrative requests
- **Dues Management**: Track and manage financial dues

## 📸 Screenshots

### Mobile App Interface

<div align="center">
  <img src="screenshots/WhatsApp Image 2025-07-07 at 16.58.46_711faf68.jpg" width="250" alt="Sign up Screen"/>
  <img src="screenshots/WhatsApp Image 2025-07-07 at 16.58.46_99debe6b.jpg" width="250" alt="Login Screen"/>
  <img src="screenshots/WhatsApp Image 2025-07-07 at 16.58.46_8ae5b5db.jpg" width="250" alt="Dashboard"/>
  
</div>

### Firebase Implementation

<div align="center">
  <img src="screenshots/Screenshot 2025-07-07 170253.png" width="600" alt="Live Student Records"/>
</div>

## 🛠️ Technologies Used

### Frontend

- **Java**: Primary programming language for Android development
- **Kotlin**: Used for Gradle build configuration
- **Android SDK**: Native Android development framework
- **XML**: Layout design and UI components

### Backend & Database

- **Firebase Realtime Database**: Cloud-based NoSQL database for real-time data synchronization

## 🎓 Core OOP and DBMS Concepts Covered

### Object Oriented Programming (OOP) Concepts

#### 1. **Inheritance**

- **Abstract Base Class**: `Student.java` serves as an abstract parent class
- **Concrete Subclasses**: `UndergradStudent.java` and `PostgradStudent.java` extend the Student class
- **Method Inheritance**: Common properties and methods inherited from parent class

#### 2. **Encapsulation**

- **Private Data Members**: All student attributes are private with public getter/setter methods
- **Data Hiding**: Internal implementation details are hidden from external classes
- **Access Modifiers**: Proper use of private, protected, and public access modifiers

#### 3. **Abstraction**

- **Abstract Classes**: Student class provides abstract representation of student entities
- **Interface Implementation**: Activities implement OnClickListener and other interfaces
- **Database Abstraction**: DatabaseManager class abstracts Firebase operations

#### 4. **Polymorphism**

- **Method Overriding**: Subclasses can override parent class methods
- **Interface Implementation**: Multiple classes implementing the same interface differently
- **Dynamic Method Dispatch**: Runtime method resolution based on object type

### Database Management System (DBMS) Concepts

#### 1. **Database Design**

- **Entity Modeling**: Student entities with proper attributes
- **Relationship Management**: Handling relationships between different data entities

#### 2. **CRUD Operations**

- **Create**: Adding new student records and user data
- **Read**: Retrieving student information and displaying in UI
- **Update**: Modifying existing student records
- **Delete**: Removing outdated or unnecessary data

#### 3. **Data Security**

- **Authentication**: Secure user authentication before database access
- **Authorization**: Role-based access to different data sections
- **Data Validation**: Input validation before database operations

## 🚀 Installation Instructions

### Prerequisites

- **Android Studio**: Latest version (Arctic Fox or newer)
- **Java Development Kit (JDK)**: Version 8 or higher
- **Android SDK**: API level 24 or higher
- **Git**: For cloning the repository
- **Firebase Account**: For database configuration

### Step-by-Step Setup

#### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Persona_App.git
cd Persona_App
```

#### 2. Open Project in Android Studio

- Launch Android Studio
- Select "Open an existing Android Studio project"
- Navigate to the cloned `Persona_App` directory
- Click "OK" to open the project

#### 3. Firebase Configuration

- Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
- Add an Android app to your Firebase project
- Package name: `com.nust.personaapp`
- Download the `google-services.json` file
- Place the file in the `app/` directory (replace existing one if needed)

#### 4. Firebase Database Setup

- In Firebase Console, go to "Realtime Database"
- Create a database in test mode
- Set up the following database structure:

```json
{
  "Student_Records": {
    "student_id": {
      "firstName": "string",
      "lastName": "string",
      "qalamId": "string",
      "email": "string",
      "department": "string",
      "school": "string",
      "degree": "string",
      "semester": "string",
      "percentMonthlyAttendance": "number"
    }
  }
}
```

#### 5. Sync and Build Project

- Click "Sync Now" when prompted by Android Studio
- Wait for Gradle sync to complete
- Build the project: `Build → Make Project`

#### 6. Configure Device/Emulator

- **Physical Device**: Enable Developer Options and USB Debugging
- **Emulator**: Create an AVD with API level 24+ using AVD Manager

#### 7. Run the Application

- Connect your device or start the emulator
- Click the "Run" button or press `Shift + F10`
- Select your target device
- Wait for the app to install and launch

---

_This project demonstrates practical implementation of OOP principles and DBMS concepts in a real-world Android application, showcasing modern mobile development practices and clean code architecture._
