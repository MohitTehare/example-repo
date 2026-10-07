package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

public class BaseTest {
   protected Playwright playwright;
   protected Browser browser;
   protected Page page;

   @BeforeMethod
    public void setUp() {
      playwright = Playwright.create();
      browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(5000));
      page = browser.newPage();
    }


    @AfterMethod
    public  void tearDown() {
   if (browser != null) browser.close();
   if (playwright != null) playwright.close();
    }

    @AfterMethod(alwaysRun = true)
    public void captureOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && page != null) {
            System.out.println("FAILED PAGE URL: " + page.url());
            System.out.println("FAILED PAGE TITLE: " + page.title());
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("target/surefire-reports/failure.png"))
                    .setFullPage(true));
        }
    }
}
