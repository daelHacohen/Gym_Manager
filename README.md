# Gym Manager

## Project Description
**Gym Manager** is a gym management system designed to handle:
- Clients
- Instructors
- Different types of training sessions (Pilates, Ninja, Thai Boxing, and more)
- Class registration and exception handling

The system is written in **Java**, utilizing **Object-Oriented Programming (OOP)** principles and design patterns such as **Factory** and **Observer**.

---

## Key Features
- **Client Management**:
  - Register new clients
  - Validate client age (`InvalidAgeException`)
  - Prevent duplicate registrations (`DuplicateClientException`)
- **Instructor Management**:
  - Verify instructor qualifications (`InstructorNotQualifiedException`)
- **Session Management**:
  - Support for different types of training sessions (Pilates, Ninja, Thai Boxing)
  - Factory pattern for dynamic session creation
- **Design Patterns**:
  - `Observer Pattern`: For client subscription to sessions
  - `Factory Pattern`: For dynamic creation of session objects

---

## Technologies Used
- **Java** - Primary programming language
- **Object-Oriented Programming (OOP)** - Using encapsulation, inheritance, and polymorphism
- **Design Patterns**:
  - Factory Pattern
  - Observer Pattern

---

## System Requirements
- **JDK 8** or later
- **IDE** supporting Java (IntelliJ, Eclipse, or similar)

---

## How to Run the Project
1. Ensure JDK is installed on your system.
2. Import the project into your IDE:
   - Open the project from the `Gym_Manager-main` folder
   - Make sure all dependencies and files are properly loaded
3. Run `Main.java`.

---

## Testing and Validation
- **auto_check.py** - Script for automated testing of functionalities
- **output.txt** - Sample output for verifying results

---

## Contribution
- Feel free to **Fork**, edit, and submit a **Pull Request**.
- Maintain clean and readable code.
- Add comments (documentation) where necessary.

---

## Credits
- Developed as part of an academic **OOP assignment**.
- Special thanks to the instructor and the academic institution.


