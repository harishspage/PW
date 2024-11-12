package framework.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocatorManager {

    private final Page page;
    private final Map<String, Locator> locators;

    public LocatorManager(Page page) {
        this.page = page;
        this.locators = new HashMap<>();
        generateLocatorsFromDOM();
    }

    // Method to read the DOM and generate locators dynamically
    private void generateLocatorsFromDOM() {
        // Enhanced JavaScript snippet to reliably capture element attributes
        List<Map<String, String>> elements = page.evaluate("() => {\n" +
                "  const elements = [];\n" +
                "  document.querySelectorAll('*').forEach(el => {\n" +
                "    const elementData = {};\n" +
                "    if (el.id) elementData['css'] = `#${el.id}`;\n" +
                "    if (el.className && el.className.trim()) elementData['css'] = `.${el.className.trim().replace(/\\s+/g, '.')}`;\n" +
                "    if (el.tagName) elementData['tag'] = el.tagName.toLowerCase();\n" +
                "    if (el.textContent && el.textContent.trim().length < 30) elementData['text'] = el.textContent.trim();\n" +
                "    if (el.getAttribute('role')) elementData['role'] = el.getAttribute('role');\n" +
                "    if (el.getAttribute('placeholder')) elementData['placeholder'] = el.getAttribute('placeholder');\n" +
                "    if (el.getAttribute('alt')) elementData['alt'] = el.getAttribute('alt');\n" +
                "    if (Object.keys(elementData).length > 0) elements.push(elementData);\n" +
                "  });\n" +
                "  return elements;\n" +
                "}");

        // Populate locators map with identified elements
        int counter = 1;
        for (Map<String, String> element : elements) {
            String name = "element" + counter++;
            addLocatorFromAttributes(name, element);
        }
    }

    // Method to add locator based on available attributes
    private void addLocatorFromAttributes(String name, Map<String, String> attributes) {
        if (attributes.containsKey("css")) {
            locators.put(name, page.locator(attributes.get("css")));
        } else if (attributes.containsKey("text")) {
            locators.put(name, page.getByText(attributes.get("text")));
        } else if (attributes.containsKey("role")) {
            locators.put(name, page.getByRole(attributes.get("role")));
        } else if (attributes.containsKey("placeholder")) {
            locators.put(name, page.getByPlaceholder(attributes.get("placeholder")));
        } else if (attributes.containsKey("alt")) {
            locators.put(name, page.getByAltText(attributes.get("alt")));
        } else if (attributes.containsKey("tag")) {
            locators.put(name, page.locator(attributes.get("tag")));
        }
    }

    // Method to list all locators with their types
    public void listAllLocators() {
        locators.forEach((name, locator) -> {
            System.out.println("Locator name: " + name + ", Locator: " + locator.toString());
        });
    }

    // Method to get a specific locator by name
    public Locator getLocator(String name) {
        return locators.get(name);
    }
}
