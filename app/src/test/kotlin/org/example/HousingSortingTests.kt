package org.example

import org.example.helpers.Navigation
import org.example.pages.HousingPage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HousingSortingTests : BaseTest() {

    private val navigation = Navigation()
    private lateinit var housingPage: HousingPage

    @BeforeEach
    fun openHousingPage() {
        navigation.navigateToHousingPage(page)
        housingPage = HousingPage(page)
        housingPage.ensurePageLoaded()
    }

    @Test
    fun shouldShowDefaultSortingOptions() {
        housingPage.openSortMenu()
        org.junit.jupiter.api.Assertions.assertAll(
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isNewestOptionDisplayed(),
                    "Newest option should be displayed by default"
                )
            },
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isPriceAscendingOptionDisplayed(),
                    "Price ascending option should be displayed by default"
                )
            },
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isPriceDescendingOptionDisplayed(),
                    "Price descending option should be displayed by default"
                )
            }
        )
    }

    @Test
    fun shouldShowExtendedSortingOptionsAfterSearch() {
        housingPage.searchForHouseForSale()
        housingPage.openSortMenu()
        org.junit.jupiter.api.Assertions.assertAll(
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isNewestOptionDisplayed(),
                    "Newest option should be displayed after search"
                )
            },
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isPriceAscendingOptionDisplayed(),
                    "Price ascending option should be displayed after search"
                )
            },
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isPriceDescendingOptionDisplayed(),
                    "Price descending option should be displayed after search"
                )
            },
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isRelevanceOptionDisplayed(),
                    "Relevance option should be displayed after search"
                )
            },
            {
                org.junit.jupiter.api.Assertions.assertTrue(
                    housingPage.isUpcomingOptionDisplayed(),
                    "Upcoming option should be displayed after search"
                )
            }
        )
    }

    @Test
    fun shouldSortByPriceAscending() {
        housingPage.sortByPrice(org.example.models.PriceSort.ASCENDING)

        org.junit.jupiter.api.Assertions.assertTrue(
            housingPage.arePricesSorted(org.example.models.PriceSort.ASCENDING),
            "Prices should be sorted in ASCENDING order"
        )
    }

    @Test
    fun shouldSortByPriceDescending() {
        housingPage.sortByPrice(org.example.models.PriceSort.DESCENDING)

        org.junit.jupiter.api.Assertions.assertTrue(
            housingPage.arePricesSorted(org.example.models.PriceSort.DESCENDING),
            "Prices should be sorted in DESCENDING order"
        )
    }
}