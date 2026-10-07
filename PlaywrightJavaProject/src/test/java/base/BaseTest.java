package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless));

//        context = browser.newContext(new Browser.NewContextOptions()
//                .setViewportSize(1920, 1080)
//                .setLocale("en-IN")
//                .setTimezoneId("Asia/Kolkata")
//                .setRecordVideoDir(Paths.get("target/surefire-reports/videos/")));
        BrowserContext context =browser.newContext(new Browser.NewContextOptions()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebkit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"));

        page = context.newPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // 1. Capture the screenshot FIRST, while the page is still open
        if (result.getStatus() == ITestResult.FAILURE && page != null) {
            try {
                System.out.println("FAILED PAGE URL: " + page.url());
                System.out.println("FAILED PAGE TITLE: " + page.title());
                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("target/surefire-reports/failure.png"))
                        .setFullPage(true));
            } catch (Exception e) {
                System.out.println("Could not capture screenshot: " + e.getMessage());
            }
        }

        // 2. Then close everything
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
