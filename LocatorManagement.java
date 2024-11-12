package framework.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.Map;

public class LocatorManager {

    private final Page page;
    private final Map<String, Locator> locators;

    public LocatorManager(Page page) {
        this.page = page;
        this.locators = new HashMap<>();
    }

    // Methods to create locators using various strategies
    public void addCssLocator(String name, String cssSelector) {
        locators.put(name, page.locator(cssSelector));
    }

    public void addXpathLocator(String name, String xpath) {
        locators.put(name, page.locator(xpath));
    }

    public void addTextLocator(String name, String text) {
        locators.put(name, page.getByText(text));
    }

    public void addAltTextLocator(String name, String altText) {
        locators.put(name, page.getByAltText(altText));
    }

    public void addPlaceholderLocator(String name, String placeholder) {
        locators.put(name, page.getByPlaceholder(placeholder));
    }

    public void addRoleLocator(String name, String role, Map<String, String> attributes) {
        locators.put(name, page.getByRole(role, new Page.GetByRoleOptions().setAttributes(attributes)));
    }

    public void addLabelLocator(String name, String label) {
        locators.put(name, page.getByLabel(label));
    }

    public Locator getLocator(String name) {
        return locators.get(name);
    }

    // Method to list all locators added with their type
    public void listAllLocators() {
        locators.forEach((name, locator) -> {
            System.out.println("Locator name: " + name + ", Locator type: " + getLocatorType(locator));
        });
    }

    // Helper method to determine the locator type
    private String getLocatorType(Locator locator) {
        // This is a placeholder; you can add logic to differentiate locator types based on how they were added
        return locator.toString();
    }
}
