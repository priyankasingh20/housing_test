package org.example.craigslist.pages;

import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;

public class HousingPage {

    private final Page page;

    private static final String SORT_DROPDOWN = "select[name='sort']";
    private static final String SORT_OPTIONS = "select[name='sort'] option";
    private static final String PRICE_ELEMENTS = ".result-price";
    private static final String SEARCH_INPUT = "input[name='query']";

    public HousingPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://madrid.craigslist.org/search/hhh");
        page.waitForLoadState();
    }

    public void search(String query) {
        page.fill(SEARCH_INPUT, query);
        page.keyboard().press("Enter");
        page.waitForLoadState();
    }

    public void selectSort(String value) {
        page.selectOption(SORT_DROPDOWN, value);
        page.waitForLoadState();
    }

    public List<String> getSortOptions() {
        return page.locator(SORT_OPTIONS).allInnerTexts()
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<Integer> getAllPrices() {
        return page.locator(PRICE_ELEMENTS)
                .allInnerTexts()
                .stream()
                .map(t -> t.replace("€", "").replace(",", "").trim())
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
