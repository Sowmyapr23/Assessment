package stepDefinitions;

import configurations.CommonMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;

/**
 * This class contains the definitions of the story line from feature file
 */
public class ValidateLoginAndProductDetails extends CommonMethods {

    /**
     * This method is used to launch the Web browser
     * @throws IOException
     */
    @Given("launch application site_url in browser")
    public void launchApplicationSite_urlInBrowser() throws IOException {
        launchBrowser();
    }

    /**
     * This method is used to click on a Web element
     * @param elementId
     * @throws IOException
     */
    @When("click on {string}")
    public void click_on(String elementId) throws IOException {
        clickOnElement(elementId);
    }

    /**
     * This method is used to select a text from the drop-down list
     * @param inputData
     * @param elementId
     * @throws IOException
     */
    @Then("select {string} in {string}")
    public void selectText(String inputData, String elementId) throws IOException {
        selectFromDropdown(inputData,elementId);
    }

    /**
     * This method is used to enter a text in the input field
     * @param inputData
     * @param elementId
     * @throws IOException
     */
    @Then("enter {string} in {string}")
    public void enterText(String inputData, String elementId) throws IOException {
        enterInTextBox(inputData,elementId);
    }

    /**
     * This method is used to compare expected value with the actual value
     * @param inputData
     * @param elementId
     * @throws IOException
     */
    @Then("verify {string} text is present on {string}")
    public void validateTexts(String inputData, String elementId) throws IOException {
        compareTexts(inputData,elementId);
    }

    /**
     * This method is used to compare a part of string against the other string
     * @param inputData
     * @param elementId
     * @throws IOException
     */
    @Then("validate {string} is displayed on {string} page")
    public void validateTextIsDisplayedOnLandingPage(String inputData, String elementId) throws IOException {
        compareTexts(inputData,elementId,2);
    }

    /**
     * This methos is used to close all the browser and the Web driver session
     * @throws IOException
     */
    @Then("close application")
    public void close_application() throws IOException {
        closeApp();
    }
}