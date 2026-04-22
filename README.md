# Playwright Kotlin UI Test Framework

## Overview
This project contains automated UI tests built with:

- Kotlin
- JUnit 5
- Microsoft Playwright
- Gradle

The framework validates sorting and search functionality for the Housing page of https://madrid.craigslist.org/ 

## Project Structure

```text
playwright/
│
├── README.md
├── gradlew
├── settings.gradle.kts
│
└── app/
    ├── build.gradle.kts
    └── src/
        ├── main/kotlin/org/example/
        │   └── App.kt
        │
        └── test/kotlin/org/example/
            ├── BaseTest.kt
            ├── HousingSortingTests.kt
            │
            ├── pages/
            │   └── HousingPage.kt
            │
            ├── models/
            │   └── PriceSort.kt
            │
            └── helpers/
                ├── Navigation.kt
                ├── UrlConstants.kt
                ├── ConstStrings.kt
                └── LoggerHelper.kt
```
## Test Report Location
After executing the automated tests, the HTML execution report can be found at: app/build/reports/tests/test/index.html

Open this file in any browser to review:
- Passed tests
- Failed tests
- Error details
- Stack traces
- Execution duration
- Test summary

## Test Scenarios
TC-001 — Verify Default Sorting Options Are Available
Objective: Validate that the Housing page displays the default sorting options when the sort dropdown menu is opened.

Preconditions
 - Application is accessible
 - User is on the Housing page
 - Browser launches successfully

Test Steps
1. Launch the browser.
2. Navigate to the Housing page.
3. Open the sorting dropdown menu.
4. Verify that the newest option is displayed.
5. Verify that the price ascending (£ → £££) option is displayed.
6. Verify that the price descending (£££ → £) option is displayed.

Expected Result: The sorting dropdown displays all default options:
 - newest
 - £ → £££
 - £££ → £

Console Execution: .\gradlew test --tests "org.example.HousingSortingTests.shouldShowDefaultSortingOptions"
-----------------------------------------------------------------------------------------------------------

TC-002 — Verify Extended Sorting Options After Search
Objective: Validate that additional sorting options are displayed after performing a housing search.

Preconditions:
 - Application is accessible
 - User is on the Housing page
 - Browser launches successfully

Test Steps
1. Launch the browser.
2. Navigate to the Housing page.
3. Enter House for sale in the search field and execute the search
4. Open the sorting dropdown menu.
5. Verify that the relevance option is displayed.
6. Verify that the upcoming option is displayed.
7. Verify that the newest option is displayed.
8. Verify that the price ascending (£ → £££) option is displayed.
9. Verify that the price descending (£££ → £) option is displayed.

Expected Result: The sorting dropdown displays all extended options after search:
- relevance
- upcoming
- newest
- £ → £££
- £££ → £

Console Execution: .\gradlew test --tests "org.example.HousingSortingTests.shouldShowExtendedSortingOptionsAfterSearch"
-----------------------------------------------------------------------------------------------------------------------

TC-003 — Verify Results Are Sorted by Price in Ascending Order
Objective: Validate that housing search results are sorted from lowest price to highest price when the ascending price option is selected.

Preconditions:
- Application is accessible
- User is on the Housing page
- Browser launches successfully

Test Steps
1. Launch the browser.
2. Navigate to the Housing page.
3. Open the sorting dropdown menu.
4. Select the price ascending (£ → £££) option.
5. Verify that each price is less than or equal to the next price in the list.

Expected Result: Search results are displayed in ascending price order, from lowest to highest.

Console Execution: .\gradlew test --tests "org.example.HousingSortingTests.shouldSortByPriceAscending"
------------------------------------------------------------------------------------------------------

TC-004 — Verify Results Are Sorted by Price in Descending Order
Objective: Validate that housing search results are sorted from highest price to lowest price when the descending price option is selected.

Preconditions:
- Application is accessible
- User is on the Housing page
- Browser launches successfully

Test Steps
1. Launch the browser.
2. Navigate to the Housing page.
3. Open the sorting dropdown menu.
4. Select the price descending (£££ → £) option.
5. Verify that each price is higher than or equal to the next price in the list.

Expected Result: Search results are displayed in descending price order, from highest to lowest.

Console Execution: .\gradlew test --tests "org.example.HousingSortingTests.shouldSortByPriceAscending"
------------------------------------------------------------------------------------------------------