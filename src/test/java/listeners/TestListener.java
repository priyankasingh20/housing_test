package listeners;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Page page = (Page) result.getTestContext().getAttribute("page");
        if (page != null) {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("target/screenshots/" + result.getName() + ".png"))
                    .setFullPage(true));
        }
    }
}
