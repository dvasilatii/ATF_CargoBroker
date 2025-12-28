# ATF Cargo Broker – QA Automation Framework

Automation testing framework for a Cargo Broker web application.

This project demonstrates an approach to automated testing of web applications, covering UI, API, and database validation. The framework is built using Java and integrates Selenium, TestNG, Cucumber, RestAssured, and JDBC.

---

## Project Overview

This framework was developed as part of transitioning from manual testing to automated testing.

The goal of the project is to provide a structured automation solution that:

* Covers core business workflows
* Supports maintainable and scalable test development
* Allows integration with CI/CD pipelines

---

## Key Features

* UI automation using Selenium WebDriver
* Page Object Model (POM) implementation
* API testing using RestAssured
* Database validation using JDBC and SQL queries
* Behavior-Driven Development (BDD) with Cucumber
* Data-driven test execution using JSON test data
* Modular test architecture
* CI integration support (Jenkins / GitHub Webhooks)

---

## Technology Stack

| Layer          | Technology               |
| -------------- | ------------------------ |
| Language       | Java                     |
| UI Automation  | Selenium WebDriver       |
| Test Framework | TestNG, Cucumber         |
| API Testing    | RestAssured              |
| Build Tool     | Maven                    |
| Reporting      | Cucumber Reports         |
| Database       | JDBC / SQL               |
| CI/CD          | Jenkins, GitHub Webhooks |

---

## Project Structure

The framework follows standard automation architecture principles.

Main components include:

* `src/test/resources/featureFiles`
  Contains Cucumber feature files describing test scenarios.

* `src/main/java/org/cargobroker/pageObjects`
  Page Object classes representing UI pages and actions.

* `src/main/java/org/cargobroker/utils`
  Utility classes and reusable helper methods.

Configuration values and test data are externalized to simplify maintenance and allow flexible test execution.

---

## Framework Architecture

The framework follows a layered structure:

```
Feature Files (BDD)
        ↓
Step Definitions
        ↓
Page Objects
        ↓
Utilities / Helpers
        ↓
Automation Layers
(Selenium / API / Database)
```

This structure helps isolate responsibilities between test definitions, page interaction logic, and supporting utilities.

---

## Example Test Scenario

Example of a BDD scenario written using Cucumber:

```gherkin
Feature: Login functionality

Scenario: Successful login with valid credentials
  Given user is on the login page
  When user enters valid username and password
  Then user should be redirected to the dashboard
```

---

## Running Tests

### Prerequisites

Ensure the following tools are installed:

* Java 11 or higher
* Maven 3.6 or higher
* Git

---

### Clone the Repository

```bash
git clone https://github.com/dvasilatii/ATF_CargoBroker.git
cd ATF_CargoBroker
```

---

### Execute Tests

Run the test suite using Maven:

```bash
mvn clean test
```

Tests are executed through the Cucumber runner and can be filtered using tags.

---

## Test Reports

After execution, reports are generated in:

```
target/cucumber-reports
```

These reports contain detailed information about executed scenarios, including step results and execution time.

---
