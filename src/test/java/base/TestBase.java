package base;

import java.nio.file.Paths;
import com.microsoft.playwright.*;
import org.testng.ITestContext;
import org.testng.annotations.*;
import java.lang.reflect.Method;

public class TestBase {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setup(Method method, ITestContext testContext) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
        );

        // Create a plain context without trace directory
        context = browser.newContext();

        page = context.newPage();

        // Make page available to listeners
        testContext.setAttribute("page", page);
    }

    @AfterMethod
    public void teardown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
