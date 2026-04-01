package org.example.craigslist.tests;

import org.example.craigslist.base.TestBase;
import org.example.craigslist.pages.HousingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class HousingSortingTests extends TestBase {

    @Test
    public void verifyDefaultSortingOptions() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        List<String> options = housing.getSortOptions();

        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("price ↑")),
                "Expected 'price ↑' in default sort options");
        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("price ↓")),
                "Expected 'price ↓' in default sort options");
        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("newest")),
                "Expected 'newest' in default sort options");
    }

    @Test
    public void verifySortingOptionsAfterSearch() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();
        housing.search("studio");

        List<String> options = housing.getSortOptions();

        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("price ↑")),
                "Expected 'price ↑' after search");
        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("price ↓")),
                "Expected 'price ↓' after search");
        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("newest")),
                "Expected 'newest' after search");
        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("upcoming")),
                "Expected 'upcoming' after search");
        Assert.assertTrue(options.stream().anyMatch(o -> o.toLowerCase().contains("relevant")),
                "Expected 'relevant' after search");
    }

    @Test
    public void verifyPriceAscendingSort() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        // value attribute may be 'priceasc' or similar; adjust if needed after inspecting DOM
        housing.selectSort("priceasc");

        List<Integer> prices = housing.getAllPrices();
        Assert.assertTrue(isSortedAscending(prices),
                "Prices are not sorted in ascending order: " + prices);
    }

    @Test
    public void verifyPriceDescendingSort() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        // value attribute may be 'pricedsc' or similar; adjust if needed after inspecting DOM
        housing.selectSort("pricedsc");

        List<Integer> prices = housing.getAllPrices();
        Assert.assertTrue(isSortedDescending(prices),
                "Prices are not sorted in descending order: " + prices);
    }

    private boolean isSortedAscending(List<Integer> list) {
        return list.equals(list.stream().sorted().toList());
    }

    private boolean isSortedDescending(List<Integer> list) {
        return list.equals(list.stream().sorted(Comparator.reverseOrder()).toList());
    }
}
