public class TestExample {

    public static void main(String[] args) {
        PlaywrightUtils utils = new PlaywrightUtils();
        utils.navigateTo("https://example.com");

        // Create locators using LocatorManager
        LocatorManager locatorManager = new LocatorManager(utils.getPage());

        // Add locators
        locatorManager.addCssLocator("loginButton", "#login-button");
        locatorManager.addXpathLocator("usernameField", "//input[@name='username']");
        locatorManager.addTextLocator("welcomeText", "Welcome");

        // List all locators
        locatorManager.listAllLocators();

        // Clean up
        utils.close();
    }
}
