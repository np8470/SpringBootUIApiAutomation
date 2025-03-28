Spring Boot + Maven + Java + Selenium + TestNG Web UI & RestAssured API Automation Framework

🚀 Overview

This repository contains a Hybrid Automation Framework for Web UI & API testing using Spring Boot, Selenium, TestNG, RestAssured, and Maven. The framework supports cross-browser testing, parallel execution, API automation, database testing, reporting, and CI/CD integration.

📌 Features

✅ Web UI Automation

Selenium WebDriver with Page Object Model (POM) & Page Factory

Parallel Execution & Cross-Browser Testing

Explicit & Fluent Waits for Synchronization

Data-Driven Testing using Excel/JSON

Docker Support for ReportPortal

Logging & Debugging using Log4j

✅ API Automation (RestAssured)

GET, POST, PUT, DELETE API Tests

Dynamic Utility Methods for API Requests

Custom Exception Handling for API Failures

JWT & Basic Authentication

Security Testing with OWASP ZAP

✅ Reporting & CI/CD

Advanced Reporting: ReportPortal Docker base container

Retry Mechanism for Flaky Tests

🛠️ Installation & Setup

🔹 Prerequisites

Install Java 17+

Install Maven 3+

Install Node.js (for Selenium Grid, if needed)

Install Docker (for Selenium Grid & API Mocking)

Install Git & Clone Repository

git clone "https://github.com/np8470/SpringBootUIApiAutomation.git"
cd your-project-folder

🔹 Configure Dependencies
mvn clean install

🚀 Running Tests

🔹 Run All Tests
mvn clean test
🔹 Run Specific Test Suite
mvn test -DsuiteXmlFile=testng.xml
🔹 Run Tests with Maven Profiles
mvn test -Pchrome
mvn test -Pfirefox

📊 Reports & Logs

✅ Generate Report in ReportPortal Docker Container


🔄 CI/CD Integration

🔹 Run Tests in Jenkins

Configure Maven in Jenkins

Set Git Repository URL

Add Build Step: mvn clean test

🚀 Future Enhancements

✅ BDD Support (Cucumber)
✅ Mobile Testing (Appium)
✅ Cloud Testing (BrowserStack, SauceLabs)
✅ Performance Testing (JMeter)
✅ Integration with Jenkins & GitHub Actions
✅ Security Testing with OWASP ZAP

Test Management Integration: JIRA, TestRail

📝 Contributors

Your Name - [Niraj Patel](https://github.com/np8470)

Feel free to contribute! 😊
