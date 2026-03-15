# Frying Pan

A proof-of-concept Android app demonstrating a path traversal vulnerability in Sieve, 
a deliberately vulnerable password manager by ReversecLabs. Exploits an exposed 
Content Provider to extract and exfiltrate stored credentials to a remote database. 
(For educational purposes only)

---

## Requirements

Make sure you have the following requirements before setting up Frying Pan:

- **Android Studio**
- **Android Emulator** running Android 13 or above with Google Play
- **Sieve APK** installed and set up on the emulator with at least 
  one password entry and one backup file created
- **Firebase Account** - [https://firebase.google.com](https://firebase.google.com)
- **Git** installed on your machine

---

## Setup

### 1. Clone the Repository

Open a terminal and run:
```
git clone https://github.com/Looonel/Frying-Pan.git
```

Then open Android Studio, select the cloned project's folder, and press OK

---

### 2. Firebase & Firestore Setup

**Step 1** - Go to [https://console.firebase.google.com](https://console.firebase.google.com) 
and create a new project.

**Step 2** - Inside your new project, add an Android app with the 
following package name:
```
com.example.sievepoc_app
```

**Step 3** - Download the google-services.json file (you can find it in the settings of your new project) 
and place it inside the app/ directory of the project

**Step 4** - Go to Firestore Database in the Firebase Console 
and create a database. Then create a new collection called "Credentials"

**Step 5** - Set your Firestore rules to allow reading and writing to anyone (for testing)

---

### 3. Configure the Backup File Name

Frying Pan requires the exact name of the Sieve backup file to 
execute the attack. To find it, open the Sieve application, go
to settings, and click restore file from SD card.

You should see a file following this naming format:
```
Backup (YYYY-MM-DD HH-MM-SS.mmm).xml
```

Once you have the filename, open MainActivity.java and update 
the fileName variable with the file in your Sieve application

---

### 4. Build and Run

**Step 1** - Make sure your emulator is running with Sieve installed 
and opened at least once.

**Step 2** - In Android Studio, click the run button.

**Step 3** - Upon launch, Frying Pan will automatically read the 
backup file and exfiltrate its contents to your Firestore database.

**Step 4** - Check your Firestore database under the 
"Credentials" collection to see the stolen data.

---

## Limitations

- Requires prior knowledge of the backup filename
- Only works on Android 13 and above
- Path traversal is only limited to files accessible within the Sieve application
