package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class NaukriBoostAuto extends BaseTest {

    @Test
    public void loginProfileAndBoostProfile() {

        String username = System.getenv("USER_NAME");
        String password = System.getenv("USER_PAS");
        if (username == null || username.isBlank()) {
            throw new IllegalStateException("USER_NAME is not set. Check the workflow env block and the GitHub secret USER_NAME.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("USER_PAS is not set. Check the workflow env block and the GitHub secret USER_PAS.");
        }
        page.navigate("https://www.naukri.com/naukri360",
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));
        System.out.println("LOADED URL: " + page.url());
        System.out.println("LOADED TITLE: " + page.title());
        Locator loginLink = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Login").setExact(true));
        loginLink.waitFor(new Locator.WaitForOptions().setTimeout(60000));
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("target/surefire-reports/loaded.png")));
        loginLink.click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();

        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email ID / Username")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email ID / Username")).fill(username);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Refresh now")).nth(1).click();




    }

}
