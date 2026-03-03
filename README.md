# CDRB_01 – Child Development Record Book

An Android mobile application for digitising and managing child health records. The app provides healthcare workers and parents with a secure, centralised repository for a child's medical history, vaccinations, nutritional supplements, and screening results.

---

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Firebase Setup](#firebase-setup)
- [Build & Run](#build--run)
- [App Navigation Flow](#app-navigation-flow)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- 🔐 **Secure Authentication** – Email/password login via Firebase Authentication
- 👶 **Baby Record Lookup** – Search records by unique Baby ID
- 💉 **Vaccination Card** – Full vaccination history with dates and doses
- 🏥 **Medical Profile** – Birth details, blood group, APGAR score, and health concerns
- 🔬 **Laboratory Card** – Lab test results with doctor notes
- 👁️ **Eye & Ear Card** – Eye and ear screening results
- 🦷 **Dental Card** – Dental health records *(coming soon)*
- 🌾 **Triposha Card** – Nutritional supplement (Triposha) distribution history
- 🏨 **Clinic Card** – Clinic visit and checkup records
- ☁️ **Real-time Cloud Sync** – All data stored and synced via Firebase Realtime Database
- 📊 **Dynamic Table Views** – Records rendered programmatically from live Firebase data

---

## Technology Stack

| Category          | Technology                                      |
|-------------------|-------------------------------------------------|
| Language          | Java                                            |
| Platform          | Android (minSdk 24 · targetSdk 34)              |
| Build System      | Gradle with Kotlin DSL                          |
| Authentication    | Firebase Authentication (Email/Password)        |
| Database          | Firebase Realtime Database                      |
| UI                | AndroidX AppCompat + Material Design Components |
| Testing           | JUnit 4 · Espresso                              |

---

## Project Structure

```
CDRB_01/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/cdrb_01/
│   │   │   │   ├── MainActivity.java          # Splash screen
│   │   │   │   ├── Login_Page.java            # Firebase authentication
│   │   │   │   ├── Baby_Id.java               # Baby ID input
│   │   │   │   ├── Home_Page.java             # Main dashboard
│   │   │   │   ├── Medical_info.java          # Medical profile
│   │   │   │   ├── Vaccination_Card.java      # Vaccination records
│   │   │   │   ├── Clinic_Card.java           # Clinic visit records
│   │   │   │   ├── Triposha_Card.java         # Nutrition records
│   │   │   │   ├── Laboratory_Card.java       # Lab test results
│   │   │   │   ├── Eye_Card.java              # Eye & ear screening
│   │   │   │   ├── Dental_Card.java           # Dental records (WIP)
│   │   │   │   └── classes/
│   │   │   │       └── VaccinationRecord.java # Data model
│   │   │   ├── res/                           # Layouts, drawables, values
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                              # Unit tests
│   │   └── androidTest/                       # Instrumented tests
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradlew / gradlew.bat
```

---

## Prerequisites

- [Android Studio](https://developer.android.com/studio) (Hedgehog or later recommended)
- JDK 8 or higher
- A Firebase project with **Realtime Database** and **Authentication** enabled
- An Android device or emulator running **Android 7.0 (API 24)** or higher

---

## Firebase Setup

1. Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project (or use an existing one).
2. Register an Android app with the package name `com.example.cdrb_01`.
3. Download the generated `google-services.json` file and place it in the `app/` directory.
4. Enable **Email/Password** sign-in under **Authentication → Sign-in method**.
5. Create a **Realtime Database** and structure data as follows:

```
Main/
  └── {babyId}/
        ├── MedicalInfo/
        ├── Vaccinations/
        ├── Clinic/
        ├── Laboratory/
        ├── Eye/
        ├── Dental/
        └── Triposha/
```

6. Set appropriate database security rules for your environment.

---

## Build & Run

```bash
# Clone the repository
git clone https://github.com/yasiruchamuditha/CDRB_01.git
cd CDRB_01

# Build a debug APK
./gradlew assembleDebug          # macOS / Linux
gradlew.bat assembleDebug        # Windows

# Install on a connected device / running emulator
./gradlew installDebug
```

Alternatively, open the project in **Android Studio** and click **Run ▶**.

---

## App Navigation Flow

```
Splash Screen (MainActivity)
        │ (5 sec delay)
        ▼
  Login Page
        │ (successful Firebase auth)
        ▼
  Baby ID Input
        │ (enter Baby ID)
        ▼
  Home Dashboard
        ├── Medical Profile
        ├── Vaccination Card
        ├── Clinic Card
        ├── Triposha Card
        ├── Laboratory Card
        ├── Eye & Ear Card
        └── Dental Card (coming soon)
```

---

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add your feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
