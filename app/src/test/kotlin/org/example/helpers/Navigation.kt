package org.example.helpers

import com.microsoft.playwright.Page

class Navigation {

    fun navigateToHousingPage(page: Page) {
        LoggerHelper.logger.info("Navigating to Housing page")
        page.navigate(
            UrlConstants.BASE_URL + UrlConstants.HOUSING_PAGE_PARAMETERS
        )
    }
}