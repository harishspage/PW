package framework.steps;

import framework.utils.PlaywrightUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class SampleSteps {

    private PlaywrightUtils utils;

    public SampleSteps() {
        utils = new PlaywrightUtils();
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        utils.navigateTo(url);
    }

    @When("I click on {string}")
    public void iClickOn(String locator) {
        utils.click(locator);
    }

    @When("I enter {string} into {string}")
    public void iEnterInto(String text, String locator) {
        utils.type(locator, text);
    }

    @Then("I should see {string} in {string}")
    public void iShouldSeeIn(String expectedText, String locator) {
        String actualText = utils.getText(locator);
        Assert.assertEquals(expectedText, actualText);
    }

    @Then("API response from {string} should have status {int}")
    public void apiResponseShouldHaveStatus(String url, int expectedStatus) {
        // Call PlaywrightUtils API method and assert status
    }

    // Other step definitions
}
