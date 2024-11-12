package framework.utils;

import com.microsoft.playwright.*;
import java.util.List;

public class PlaywrightUtils {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    public PlaywrightUtils() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void click(String locator) {
        page.locator(locator).click();
    }

    public void type(String locator, String text) {
        page.locator(locator).fill(text);
    }

    public String getText(String locator) {
        return page.locator(locator).textContent();
    }

    public void closeBrowser() {
        browser.close();
        playwright.close();
    }

    // Add methods for API, accessibility, and performance testing
    public APIResponse makeAPICall(String url, String method, String payload) {
        // Code to make API call using Playwright's APIRequest
    }

    public void runAccessibilityCheck() {
        // Integrate accessibility checks using playwright-axe
    }

    public void measurePerformance(String url) {
        // Performance checks can be done here
    }
}
