package framework.utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.RequestOptions;
import com.microsoft.playwright.options.ScreenshotOptions;

import java.nio.file.Paths;
import java.util.Map;

public class PlaywrightUtils {

    private final Playwright playwright;
    private final Browser browser;
    private final Page page;
    private final APIRequestContext apiRequestContext;

    public PlaywrightUtils() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        apiRequestContext = playwright.request().newContext();
    }

    // Web Interaction Methods
    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void click(String selector) {
        page.locator(selector).click();
    }

    public void type(String selector, String text) {
        page.locator(selector).fill(text);
    }

    public String getText(String selector) {
        return page.locator(selector).textContent();
    }

    public boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    public void takeScreenshot(String path) {
        page.screenshot(new ScreenshotOptions().setPath(Paths.get(path)));
    }

    // API Testing Methods
    public APIResponse makeAPICall(String url, String method, Map<String, String> headers, String payload) {
        RequestOptions options = new RequestOptions()
                .setHeaders(headers)
                .setData(payload);

        return switch (method.toUpperCase()) {
            case "POST" -> apiRequestContext.post(url, options);
            case "GET" -> apiRequestContext.get(url, options);
            case "PUT" -> apiRequestContext.put(url, options);
            case "DELETE" -> apiRequestContext.delete(url, options);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        };
    }

    // Accessibility Testing (using Playwright-axe or similar)
    public void runAccessibilityCheck() {
        page.locator("body").evaluate("() => axe.run()"); // Ensure `axe-core` is loaded on the page
    }

    // Performance Testing Methods
    public void measurePerformance() {
        page.evaluate("() => performance.clearMarks()");
        page.evaluate("() => performance.mark('start')");

        // Add some actions to measure

        page.evaluate("() => performance.mark('end')");
        page.evaluate("() => performance.measure('pageLoad', 'start', 'end')");
        Object performanceEntries = page.evaluate("() => performance.getEntriesByType('measure')");
        System.out.println(performanceEntries.toString());
    }

    // Clean-up method
    public void close() {
        browser.close();
        playwright.close();
    }
}
