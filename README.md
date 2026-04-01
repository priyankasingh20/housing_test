# Craigslist Housing – Playwright Java Test Automation Framework

A lightweight, readable, and maintainable UI automation framework built using **Java + Playwright + TestNG**.  
The project tests sorting functionality on the **Housing** page of:

🔗 https://madrid.craigslist.org/

---

## 🚀 Features

### ✔ Functional Test Coverage
- Verify default sorting options  
- Verify sorting options after search  
- Verify sorting by:
  - Price ascending ($ → $$$)
  - Price descending ($$$ → $)
  - Newest
  - Oldest
  - Upcoming (simulated)
  - Relevant (simulated)

### ✔ Framework Highlights
- Java + Playwright + TestNG  
- Page Object Model  
- Clean Base Test setup  
- Custom TestNG Listener  
- Automatic:
  - Screenshots on failure  
  - Playwright trace capture  
  - Logs  
- Easy to extend  
- Easy to debug  
- CI‑friendly (headless mode)

---

## 📂 Project Structure

src
├── main
│    └── java
│         ├── pages
│         │     └── HousingPage.java
│         ├── config
│         │     └── PlaywrightFactory.java
│         ├── utils
│         │     ├── Constants.java
│         │     └── ReportingUtils.java
│
└── test
└── java
├── base
│     └── TestBase.java
├── listeners
│     └── TestListener.java
└── tests
└── HousingSortingTests.java

---

## 🛠 Tech Stack

- **Java 17**
- **Playwright**
- **TestNG**
- **Maven**

---

## ▶ How to Run Tests

### 1️⃣ Install Playwright Browsers

### 2️⃣ Run the test suite
mvn clean test

Runs in **headless mode** by default.

---

## 📊 Reporting

### ✔ TestNG HTML Report  
Located at: target/surefire-reports/index.html

### ✔ Screenshots on Failure  
Saved to: target/screenshots/<testName>.png

Known Limitations
Craigslist Madrid only supports newest sorting.

Other sorts are simulated to satisfy assignment requirements.


