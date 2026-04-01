package org.example.craigslist.base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.example.craigslist.config.PlaywrightFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setup() {
        context = PlaywrightFactory.createContext();
        page = context.newPage();
    }

    @AfterMethod
    public void teardown() {
        if (context != null) {
            context.close();
        }
        PlaywrightFactory.close();
    }
}
