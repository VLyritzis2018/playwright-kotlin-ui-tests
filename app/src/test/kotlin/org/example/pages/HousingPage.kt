package org.example.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import org.example.helpers.ConstStrings
import org.example.helpers.LoggerHelper
import org.example.models.PriceSort

class HousingPage(private val page: Page) {

    private val searchInput = page.locator("input[placeholder='${ConstStrings.SEARCH_HOUSING_PLACEHOLDER}']")
    private val searchBtn = page.locator("button[type='submit']")
    private val sortDropdown = page.locator("div.cl-search-sort-mode.bd-combo-box")
    private val priceElements = page.locator(".priceinfo")
    private val newestOption = page.locator("button.cl-search-sort-mode-newest:not(.bd-list-box-focused-item)")
    private val priceAscOption = page.locator("button.cl-search-sort-mode-price-asc")
    private val priceDescOption = page.locator("button.cl-search-sort-mode-price-desc")
    private val relevanceOption = page.locator("button.cl-search-sort-mode-relevance:not(.bd-list-box-focused-item)")
    private val upcomingOption = page.locator("button.cl-search-sort-mode-upcoming")

    fun ensurePageLoaded() {
        LoggerHelper.logger.info("Checking if Housing page is loaded")
            searchInput.waitFor(Locator.WaitForOptions()
            .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE))
        LoggerHelper.logger.info("Housing page loaded successfully")
    }

    fun openSortMenu() {
        LoggerHelper.logger.info("Opening sort dropdown")
        sortDropdown.click()
        page.locator("div.items").waitFor()
        LoggerHelper.logger.info("Sort menu is visible")
    }

    fun sortByPrice(order: PriceSort) {
        openSortMenu()
        when (order) {
            PriceSort.ASCENDING -> {
                LoggerHelper.logger.info("Selecting ASCENDING sort option")
                page.locator("text='${ConstStrings.SORT_PRICE_ASC}'").click()
            }
            PriceSort.DESCENDING -> {
                LoggerHelper.logger.info("Selecting DESCENDING sort option")
                page.locator("text='${ConstStrings.SORT_PRICE_DESC}'").click()
            }
        }
        LoggerHelper.logger.info("Sorting action completed: $order")
    }

    fun arePricesSorted(order: PriceSort): Boolean {
        LoggerHelper.logger.info("Validating price sorting: $order")
        val prices = priceElements.allTextContents()
            .map { it.replace(Regex("[^\\d]"), "").toInt() }
        LoggerHelper.logger.info("Extracted prices: $prices")
        val result = when (order) {
            PriceSort.ASCENDING ->
                prices.zipWithNext().all { (a, b) -> a <= b }
            PriceSort.DESCENDING ->
                prices.zipWithNext().all { (a, b) -> a >= b }
        }
        LoggerHelper.logger.info("Sorting validation result ($order): $result")
        return result
    }

    fun isNewestOptionDisplayed(): Boolean {
        val result = newestOption.isVisible
        LoggerHelper.logger.info("Is 'newest' displayed: $result")
        return result
    }

    fun isPriceAscendingOptionDisplayed(): Boolean {
        val result = priceAscOption.isVisible
        LoggerHelper.logger.info("Is ascending option displayed: $result")
        return result
    }

    fun isPriceDescendingOptionDisplayed(): Boolean {
        val result = priceDescOption.isVisible
        LoggerHelper.logger.info("Is descending option displayed: $result")
        return result
    }

    fun isRelevanceOptionDisplayed(): Boolean {
        val result = relevanceOption.isVisible
        LoggerHelper.logger.info("Is 'relevance' displayed: $result")
        return result
    }

    fun isUpcomingOptionDisplayed(): Boolean {
        val result = upcomingOption.isVisible
        LoggerHelper.logger.info("Is 'upcoming' displayed: $result")
        return result
    }

    fun searchForHouseForSale() {
        LoggerHelper.logger.info("Searching for ${ConstStrings.SEARCH_HOUSE_INPUT}")
        searchInput.fill(ConstStrings.SEARCH_HOUSE_INPUT)
        searchInput.press("Enter")
        page.locator("span.label:has-text('${ConstStrings.SEARCH_HOUSE_INPUT}')").waitFor()
        LoggerHelper.logger.info("Search completed and page stabilized")
    }
}