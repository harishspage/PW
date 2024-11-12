public class LocatorTest {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://example.com");

        // Initialize LocatorManager and list all locators
        LocatorManager locatorManager = new LocatorManager(page);
        locatorManager.listAllLocators();

        // Example usage of a specific locator
        Locator someLocator = locatorManager.getLocator("element1"); // Adjust as needed
        if (someLocator != null && someLocator.isVisible()) {
            System.out.println("Found element: " + someLocator.textContent());
        }

        // Clean up
        browser.close();
        playwright.close();
    }
}
