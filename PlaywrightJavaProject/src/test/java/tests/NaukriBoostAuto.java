package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

public class NaukriBoostAuto extends BaseTest {

    @Test
    public void loginProfileAndBoostProfile() {

        String username = System.getenv("USER_NAME");
        String password = System.getenv("USER_PAS");


        page.navigate("https://www.naukri.com/naukri360",
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
        System.out.println("LOADED URL: " + page.url());
        System.out.println("LOADED TITLE: " + page.title());
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();;
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email ID / Username")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email ID / Username")).fill(username);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Refresh now")).nth(1).click();


    }

}
