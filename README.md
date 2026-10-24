# Amazon Egypt – UI Test Automation Framework

<p align="center">
  <b>Java · Selenium WebDriver · TestNG · Maven · POM · Allure · Log4j2</b>
</p>

A browser-based UI automation project for validating selected shopping workflows on **Amazon Egypt**. The framework uses Java and Selenium WebDriver, organizes page interactions with the **Page Object Model (POM)**, executes tests through TestNG and Maven, and supports execution logging with Log4j2 and test reporting with Allure.

The project focuses on practical e-commerce scenarios such as searching for products, applying filters, sorting results, checking product details, validating cart behavior, and testing invalid search input.

> **Application under test:** Amazon Egypt (`amazon.eg`)  
> **Project type:** UI Test Automation  
> **Test runner:** TestNG  
> **Build tool:** Maven

---

## Table of Contents

- [Project Overview](#project-overview)
- [Objectives](#objectives)
- [Technology Stack](#technology-stack)
- [Application Under Test](#application-under-test)
- [Features and Test Coverage](#features-and-test-coverage)
- [Demo Video](#demo-video)
- [Framework Architecture](#framework-architecture)
- [Page Object Model](#page-object-model)
- [Test Suite](#test-suite)
- [Assertions and Validation Strategy](#assertions-and-validation-strategy)
- [Logging and Reporting](#logging-and-reporting)
- [Project Configuration](#project-configuration)
- [Prerequisites](#prerequisites)
- [Installation and Setup](#installation-and-setup)
- [How to Run](#how-to-run)
- [Generate the Allure Report](#generate-the-allure-report)
- [Troubleshooting](#troubleshooting)
- [Known Limitations](#known-limitations)
- [Potential Enhancements](#potential-enhancements)
- [Author](#author)
- [Disclaimer](#disclaimer)

---

## Project Overview

This repository contains an automated UI testing framework built to exercise selected Amazon Egypt shopping flows through a real browser. The tests simulate user actions and use assertions to check the application’s observable behavior.

The project is structured around reusable page classes so that test scenarios can focus on **what is being tested**, while page objects handle **how the browser interacts with the page**. Shared browser utilities are centralized in `BasePage`, while separate TestNG classes organize product, cart, and invalid-search scenarios.

The website is dynamic and changes over time. Product prices, availability, result ordering, page layout, and the presence of product-specific sections can vary by item, location, and session. Tests should therefore validate requirements carefully and avoid relying on assumptions that are not guaranteed by the page.

## Objectives

- Build practical experience with Java-based UI test automation.
- Automate key e-commerce user journeys using Selenium WebDriver.
- Apply the Page Object Model to improve reuse and maintainability.
- Use TestNG to organize and execute test scenarios.
- Use Maven to manage dependencies and run the test suite.
- Verify expected behavior with meaningful assertions.
- Use explicit waits to handle dynamic page elements.
- Record execution details with Log4j2.
- Produce readable test results through Allure reporting.
- Practice both positive and negative testing.

## Technology Stack

| Technology / Tool | Role in the Project |
|---|---|
| Java 26 | Main programming language configured in `pom.xml` |
| Selenium WebDriver 4.48.0 | Browser automation |
| TestNG 7.12.0 | Test execution, annotations, and assertions |
| Maven | Dependency management and build/test execution |
| Page Object Model (POM) | Separates page interactions from test scenarios |
| Allure TestNG 2.35.5 | Test reporting integration |
| Log4j2 2.26.1 | Execution logging |
| Gson 2.14.0 | JSON handling support, where used |
| OpenCSV 5.12.0 | CSV data handling support, where used |
| Apache POI 5.5.1 | Excel file handling support, where used |
| IntelliJ IDEA | Development environment |
| Google Chrome | Browser used for UI testing |

Dependency versions above reflect the versions declared in the repository's Maven configuration. Browser and driver compatibility still depend on the local environment.

## Application Under Test

**Amazon Egypt** is the target e-commerce website. The automation focuses on user-facing shopping flows rather than internal services or APIs.

The website is a live third-party application, so automated results may be affected by product availability, regional content, dynamic prices, cookies, sign-in prompts, CAPTCHA, rate limiting, or UI changes.

## Features and Test Coverage

The following sections describe the main workflows covered or targeted by the project. Exact execution coverage is defined by the test methods currently present in the repository and in `testng.xml`.

### 1. Product Search

- Enter a search keyword in the search field.
- Submit the search.
- Inspect the resulting product listing.
- Continue with product filtering, sorting, or navigation as required by the scenario.

Example search terms used in the demo include monitors, wireless mice, games, and keyboards.

### 2. Product Filtering

- Apply the Free Shipping filter where it is available.
- Set a maximum price using the price control provided by the results page.
- Validate the resulting product list against the intended scenario.
- Use price boundaries that match the test requirement.

Because Amazon's price controls and filter options may vary by category or region, the automation should verify that the filter is actually applied rather than assuming that clicking a control guarantees the expected result.

### 3. Product Sorting

- Select a supported sort option, such as **Price: Low to High** or **Price: High to Low**.
- Verify the selected sorting option where implemented.
- When validating the actual ordering, compare numeric product prices while accounting for products with unavailable, discounted, or non-standard price displays.

### 4. Product Details Verification

- Navigate from a product listing to a product details page.
- Check relevant product information.
- Verify the visibility of required sections, such as the product rating section, when the selected product page is expected to contain them.

Product pages do not always share an identical layout. A robust test should distinguish between an incorrect locator and a section that is legitimately absent for a particular product.

### 5. Shopping Cart

- Add selected products to the cart.
- Navigate to the cart and inspect its contents.
- Verify the presence of expected products and quantities where covered by the test.
- Validate relevant price or budget requirements.
- Check cart totals or subtotals against the test's expected range where implemented.

### 6. Invalid Search – Negative Testing

An example invalid search term is:

```text
###xyz123!!!
```

The negative scenario is intended to check that:

- The search request can be submitted.
- Relevant products are not displayed when no relevant match is expected.
- The application responds without an unexpected test or browser failure.
- Important page elements remain usable after the search.

The expected outcome should reflect the actual requirement and website behavior; an empty result list should not be assumed unless that is what the test is designed to verify.

### Scenario Summary

| Area | Main validation goal |
|---|---|
| Search | Search workflow and results behavior |
| Filters | Shipping and price filter behavior |
| Sorting | Selected sort option and product price order where validated |
| Product details | Visibility and content of required product information |
| Cart | Selected products, quantities, and relevant price calculations |
| Negative search | Appropriate handling of invalid search input |

---

## Demo Video

A recorded demonstration of the project is available in the repository:



https://github.com/user-attachments/assets/435448e3-bf17-431d-bffd-27c60543b689


The demo shows examples of browser-based product searches and navigation, product listing interactions, and automation execution from the development environment.

---

## Framework Architecture

The project uses a Page Object Model structure with shared browser utilities and separate test classes.

### Main Components

| Component | Responsibility |
|---|---|
| `BasePage` | Shared Selenium utilities, element finding, waits, and reusable browser interactions |
| `HomePage` | Homepage actions, including entering a search term and submitting a search |
| `ProductPage` | Product listing actions, filters, sorting, navigation to product pages, and product detail checks |
| `CartPage` | Cart interactions and cart-related validations |
| `ProductTest` | Product browsing, filtering, and product-related test scenarios |
| `CartTest` | Shopping cart test scenarios |
| `InvalidCaseTest` | Negative search and invalid-input test scenarios |
| `BaseTest` | Shared test setup/teardown and browser lifecycle, where configured |
| `testng.xml` | TestNG suite definition |
| `pom.xml` | Dependencies, Java/compiler settings, and Maven plugin configuration |

### Typical Test Flow

1. TestNG starts the selected test method.
2. The test setup initializes the browser according to the framework configuration.
3. The browser navigates to the target website.
4. The test calls reusable methods on the relevant page object.
5. Selenium waits for the required UI condition before interacting with dynamic elements.
6. Assertions compare the actual result with the expected behavior.
7. Logs and test result files help explain the execution outcome.
8. Test teardown closes the browser when configured to do so.

---

## Page Object Model

The **Page Object Model (POM)** is used to organize page-specific behavior and reduce repeated Selenium code in the test classes.

### `BasePage`

The shared base page is intended to centralize common operations, such as locating elements and applying reusable wait behavior. This keeps page classes consistent and reduces duplication.

### `HomePage`

The homepage object handles actions related to starting a shopping flow, such as entering a search keyword and submitting the search.

### `ProductPage`

The product page object handles product listing and product details interactions. Depending on the test scenario, this includes filters, price selection, sorting, opening a product, and checking relevant product information.

### `CartPage`

The cart page object provides reusable cart interactions and methods used to validate cart contents or price information.

### Why POM?

- Keeps test scenarios readable and focused on requirements.
- Centralizes page interactions and locator maintenance.
- Encourages reuse of common actions.
- Reduces duplicated browser interaction code.
- Makes UI maintenance easier when the website changes.

---

## Test Suite

The repository's `testng.xml` defines the suite named `AmazonTestSuite` and includes these test classes:

```xml
<class name="productTest.ProductTest"/>
<class name="theCartTest.CartTest"/>
<class name="invalidTest.InvalidCaseTest"/>
```

This allows the configured suite to execute the product, cart, and invalid-search tests together.

If test classes are renamed or moved to another package, update `testng.xml` accordingly.

## Assertions and Validation Strategy

Assertions are what establish whether the test's expected behavior was actually verified. Completing browser actions without an assertion is not sufficient evidence that the scenario passed correctly.

Examples of useful checks include:

- Confirming that the expected results page or message is displayed.
- Checking whether the selected filter or sorting option is active.
- Verifying product price order when the requirement specifically tests sorting.
- Checking that required product details are visible.
- Confirming that expected products and quantities appear in the cart.
- Comparing a cart subtotal or total against the scenario's expected price range.
- Confirming that an invalid search produces the expected negative outcome.

Assertions should use the requirement's expected result and should not depend on unstable assumptions about live product content.

---

## Logging and Reporting

### Log4j2

Log4j2 is included in the Maven dependencies for logging. Useful log entries describe the major steps and the state of execution, for example:

- Test started or completed.
- Search submitted.
- Filter or sort option selected.
- Product page opened.
- Cart action performed.
- Validation started or failed.

Logs should provide diagnostic context without recording passwords, session tokens, or other sensitive information.

### Allure

The project includes the Allure TestNG adapter. After tests run, Allure result files can be used to generate an HTML report, provided the reporting integration is configured and result files are created.

Typical generated directories:

```text
allure-results/
allure-report/
```

- `allure-results/` contains raw execution result files.
- `allure-report/` contains the generated HTML report.

These are generated artifacts, so they do not necessarily need to be committed to the repository.

---

## Project Configuration

The Maven `pom.xml` defines the project's dependencies and build configuration. It also configures the Maven Surefire plugin to use `testng.xml` as the test suite file.

The project includes dependencies for Selenium, TestNG, Allure, Log4j2, Gson, OpenCSV, Apache POI, and Commons CSV. A dependency being present does not necessarily mean it is used by every test; usage depends on the source code.

---

## Prerequisites

Install the following before running the project:

1. **Java JDK 26** or the Java version required by the current `pom.xml`.
2. **Apache Maven** installed and available on the system `PATH`.
3. **Google Chrome** installed.
4. **Allure Commandline** if you want to generate and open the HTML report from the terminal.
5. IntelliJ IDEA or another Java IDE (optional).

Verify the Java and Maven installations in PowerShell or a terminal:

```bash
java -version
mvn -version
```

If you plan to generate an Allure report, also verify:

```bash
allure --version
```

If `mvn` or `allure` is not recognized, install the relevant tool and configure its executable directory in `PATH`, then reopen the terminal.

---

## Installation and Setup

### 1. Clone the repository

```bash
git clone https://github.com/AmrMohamed222/Amazon-Automation-Selenuim.git
cd Amazon-Automation-Selenuim
```

### 2. Open the project

Open the project folder in IntelliJ IDEA and allow Maven to import `pom.xml` and download the required dependencies.

### 3. Check the configuration

Before running the tests, confirm that:

- Java is installed and compatible with the project's Maven configuration.
- Maven works from the terminal.
- Chrome is installed.
- The WebDriver setup can launch the installed browser.
- `testng.xml` points to the intended test classes.
- The Log4j2 configuration file is present if the source code expects one.
- Allure result generation is configured if you intend to create an Allure report.

---

## How to Run

Run commands from the project root directory, where `pom.xml` is located.

### Option 1: Run the full configured test suite with Maven

```bash
mvn clean test
```

This cleans previous build output and runs the tests selected by the Maven Surefire/TestNG configuration.

### Option 2: Build and run tests

```bash
mvn clean install
```

This cleans the previous build, compiles the project, executes the configured tests, and packages the project if the build configuration requires it.

### Option 3: Run from IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Locate `testng.xml` in the project root.
3. Right-click `testng.xml`.
4. Select **Run**.
5. Review the TestNG output and test results.

You can also run a single test class from the IDE when debugging a specific scenario.

---

## Generate the Allure Report

Run the tests first so the `allure-results` directory contains fresh result files.

### 1. Generate the report

```bash
allure generate allure-results --clean -o allure-report
```

### 2. Open the report

```bash
allure open allure-report
```

### Full command sequence

```bash
mvn clean test
allure generate allure-results --clean -o allure-report
allure open allure-report
```

### If the report is empty or missing

- Confirm that the test execution completed and generated `allure-results`.
- Confirm that the Allure TestNG dependency and reporting integration are active.
- Confirm that the Allure CLI is installed and available in `PATH`.
- Rerun the tests and regenerate the report to avoid opening stale results.

---

## Troubleshooting

### `mvn` is not recognized

**Cause:** Maven is not installed or its `bin` directory is not configured in `PATH`.

**Fix:** Install Maven, update `PATH`, open a new terminal, and run `mvn -version` again.

### `TimeoutException`

**Possible causes:**
- The locator does not match the current DOM.
- The element is not present on the selected product page.
- The element exists but is not visible.
- The page has not reached the expected state.
- The test waits for the wrong condition.

**Recommended investigation:**
1. Inspect the current DOM in browser developer tools.
2. Confirm that the locator matches the intended element.
3. Check whether that element should exist for the selected product.
4. Use the appropriate wait condition, such as presence, visibility, or clickability.
5. Scroll the element into view when necessary.
6. Do not treat a longer timeout as the only fix when the locator or expectation is incorrect.

### `StaleElementReferenceException`

**Cause:** The page's DOM changed after Selenium located an element, for example after a search, filter, sort, or navigation operation.

**Recommended fix:** Locate the element again after the update and wait for the expected UI state before interacting with it. Avoid reusing a `WebElement` reference captured before the page changed.

### Browser or driver startup failure

**Possible causes:** Browser/driver incompatibility, an invalid driver configuration, or an environment issue.

**Recommended fix:** Check the complete exception, verify the installed Chrome version, and review the project's WebDriver initialization. Selenium Manager may assist with driver management when supported by the Selenium setup and environment.

### Test passes without validating the expected behavior

**Cause:** The scenario performs actions but does not assert the required outcome.

**Fix:** Add a meaningful assertion that compares the actual UI state with the expected result in the test requirement.

---

## Known Limitations

- The project automates selected UI workflows, not the complete Amazon platform.
- Amazon is a live website, so product availability, prices, search results, and page structure can change.
- CAPTCHA, sign-in prompts, cookies, regional differences, or temporary website behavior may interrupt execution.
- Some product details or rating sections may not exist on every product page.
- Test reliability depends on correct locators, appropriate waits, stable test data, and meaningful assertions.
- The repository's current level of CI integration, parallel execution, screenshot capture, and retry handling depends on what is implemented in the source code.

Please use the automation responsibly and follow the website's terms and applicable policies. Do not use automated tests to overload the website or bypass access controls.

## Potential Enhancements

Potential next steps for the project include:

- Expand test coverage for additional positive and negative scenarios.
- Strengthen numeric validation of price sorting and price filters.
- Add screenshots and useful diagnostic attachments when a test fails.
- Improve handling of dynamic page elements and changing result lists.
- Organize reusable test data for easier scenario maintenance.
- Separate smoke and regression test suites.
- Integrate automated execution into a CI pipeline.
- Improve traceability between requirements and test cases.

These are possible enhancements, not claims that these capabilities are already implemented.

---

## Author

**Amr Mohamed**  
Software Tester | Java | Selenium WebDriver | TestNG

- **GitHub Repository:** [Amazon-Automation-Selenuim](https://github.com/AmrMohamed222/Amazon-Automation-Selenuim)
- **LinkedIn:** [Amr Mohamed](https://www.linkedin.com/in/amr-mohamed-46b303215)

---

## Disclaimer

This repository is an educational software testing project and is not affiliated with, endorsed by, or sponsored by Amazon. Amazon and its associated marks belong to their respective owners.
