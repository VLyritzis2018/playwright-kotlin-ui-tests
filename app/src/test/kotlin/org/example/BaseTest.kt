package org.example

import com.microsoft.playwright.Browser
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import org.example.helpers.LoggerHelper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest {

    protected lateinit var playwright: Playwright
    protected lateinit var browser: Browser
    protected lateinit var page: Page

    @BeforeEach
    fun initializeBrowser() {
        LoggerHelper.logger.info("Initializing Playwright browser")
        playwright = Playwright.create()
        browser = playwright.chromium().launch(
            com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
        )
        page = browser.newPage()
        LoggerHelper.logger.info("Browser initialized successfully")
    }

    @AfterEach
    fun closeBrowser() {
        LoggerHelper.logger.info("Closing browser")
        browser.close()
        playwright.close()
        LoggerHelper.logger.info("Browser closed")
    }
}