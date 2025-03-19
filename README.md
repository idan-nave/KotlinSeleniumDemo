# SeleniumDemo

This is a demo project for Selenium automated testing using Java JDK 24, Kotlin, and Maven. The tests are written using Selenium WebDriver and target the Swag Labs demo site ([https://www.saucedemo.com/](https://www.saucedemo.com/)). The project is built with Spring Boot and designed to demonstrate a modular testing framework.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Project Structure](#project-structure)
- [Usage](#usage)
  - [Running Tests in Headless Mode](#running-tests-in-headless-mode)
  - [Running Tests with Browser UI](#running-tests-with-browser-ui)
- [Configuration](#configuration)
- [Technologies Used](#technologies-used)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Overview

The SeleniumDemo project is designed to:
- Validate the login functionality on Swag Labs.
- Navigate through the product pages and add items to the cart.
- Perform an end-to-end checkout process.

## Features

- **Login Testing:** Verifies successful login using valid credentials.
- **Product Navigation:** Ensures products can be added to the cart.
- **Checkout Process:** Validates the complete checkout flow.
- **Headless Mode Toggle:** Switch between headless execution and visible browser UI by setting a system property.

## Requirements

- **Java JDK 24** – Ensure that JDK version 24 is installed.
- **Maven** – Project management and build tool.
- **Chromium Browser** – Tested with Chromium v134.0.6998.35 (Snap version).
- **Kotlin** – Used for writing test cases.

## Project Structure

```
/seleniumDemo
├── pom.xml                # Maven configuration file
├── README.md              # Project documentation
├── cline_docs/            # Additional project documentation
└── src/
    ├── main/
    │   └── kotlin/
    │       └── com/example/seleniumDemo/
    │           └── SeleniumDemoApplication.kt   # Main application file
    └── test/
        └── kotlin/
            └── com/example/seleniumDemo/
                ├── BaseTest.kt      # Base test setup with headless toggle
                ├── LoginTest.kt     # Tests for login functionality
                ├── ProductsTest.kt  # Tests for product navigation and cart operations
                ├── CheckoutTest.kt  # Tests for the checkout process
                └── SeleniumDemoTest.kt # Placeholder file
```

## Usage

### Running Tests in Headless Mode

By default, tests run in headless mode. To run tests with headless mode enabled, execute:

```bash
mvn test
```

### Running Tests with Browser UI

To view the browser UI during test execution, set the system property `headless` to `false`:

```bash
mvn test -Dheadless=false
```

## Configuration

The headless mode toggle is configured in `BaseTest.kt`:

```kotlin
protected val isHeadless: Boolean = System.getProperty("headless", "true").toBoolean()
```

Set the system property `headless` to override the default behavior.

## Technologies Used

- **Java JDK 24**: Latest Java development kit.
- **Selenium WebDriver**: Automation testing framework.
- **Kotlin**: Language for writing tests.
- **Maven**: Build and project management.
- **Spring Boot**: Provides dependency management and core application framework.

## Troubleshooting

- **CDP Version Warning:** You might see a warning regarding CDP version mismatch. This warning does not affect test execution. To suppress or address it, consider updating to a compatible Selenium version in the future.
- **Dependency Issues:** Ensure that all required dependencies are properly installed via Maven.

## License

Specify your project license here.

---

For any further questions or issues, please refer to the project documentation in the `cline_docs` folder.

Happy Testing!
