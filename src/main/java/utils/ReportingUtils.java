package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserContext;

import java.nio.file.Paths;

public class ReportingUtils {

    public static void captureScreenshot(Page page, String testName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("target/screenshots/" + testName + ".png"))
                .setFullPage(true));
    }

    public static void saveTrace(BrowserContext context, String testName) {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target/trace/" + testName + ".zip")));
    }
}