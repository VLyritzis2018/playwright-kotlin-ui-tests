package org.example

import com.microsoft.playwright.Playwright

fun main() {
    Playwright.create().use { playwright ->
        val browser = playwright.chromium().launch(
            com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
        )

        val page = browser.newPage()
        page.navigate("https://madrid.craigslist.org/")

        Thread.sleep(5000)
        browser.close()
    }
}