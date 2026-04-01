package tests;

import base.TestBase;
import pages.HousingPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;

import java.util.List;

public class HousingSortingTests extends TestBase {

    @Test(description = "Verify default sorting options on Housing page")
    public void verifyDefaultSortingOptions() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        List<String> options = housing.getSortOptions();

        Assert.assertTrue(options.contains(Constants.SORT_NEWEST), "Missing: newest");
        Assert.assertTrue(options.contains(Constants.SORT_OLDEST), "Missing: oldest");
        Assert.assertTrue(options.contains(Constants.SORT_PRICE_ASC), "Missing: price ascending");
        Assert.assertTrue(options.contains(Constants.SORT_PRICE_DESC), "Missing: price descending");
        Assert.assertTrue(options.contains(Constants.SORT_UPCOMING), "Missing: upcoming");
    }

    @Test(description = "Verify sorting options remain same after performing a search")
    public void verifySortingOptionsAfterSearch() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        housing.search("studio");

        List<String> options = housing.getSortOptions();

        Assert.assertTrue(options.contains(Constants.SORT_NEWEST));
        Assert.assertTrue(options.contains(Constants.SORT_OLDEST));
        Assert.assertTrue(options.contains(Constants.SORT_PRICE_ASC));
        Assert.assertTrue(options.contains(Constants.SORT_PRICE_DESC));
        Assert.assertTrue(options.contains(Constants.SORT_UPCOMING));
        Assert.assertTrue(options.contains(Constants.SORT_RELEVANT));
    }

    @Test(description = "Verify sorting by price ascending ($ → $$$)")
    public void verifyPriceAscendingSort() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        housing.selectSort(Constants.SORT_PRICE_ASC);

        List<Integer> prices = housing.getListingPrices();

        // Use HousingPage sorting logic
        Assert.assertTrue(housing.isSortedAscending(prices), "Prices are not sorted low to high");
    }

    @Test(description = "Verify sorting by price descending ($$$ → $)")
    public void verifyPriceDescendingSort() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        housing.selectSort(Constants.SORT_PRICE_DESC);

        List<Integer> prices = housing.getListingPrices();

        // Use HousingPage sorting logic
        Assert.assertTrue(housing.isSortedDescending(prices), "Prices are not sorted high to low");
    }

    @Test(description = "Verify sorting by newest")
    public void verifyNewestSort() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        housing.selectSort(Constants.SORT_NEWEST);

        List<String> dates = housing.getListingDates();

        Assert.assertTrue(housing.isSortedNewestFirst(dates), "Listings are not sorted newest first");
    }

    @Test(description = "Verify sorting by oldest")
    public void verifyOldestSort() {
        HousingPage housing = new HousingPage(page);
        housing.navigate();

        housing.selectSort(Constants.SORT_OLDEST);

        List<String> dates = housing.getListingDates();

        Assert.assertTrue(housing.isSortedOldestFirst(dates), "Listings are not sorted oldest first");
    }
}
