package stepdefinitions;

import org.springframework.core.env.ConfigurableEnvironment;

import constants.DataProperties;
import constants.PhpProperties;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.PhpPageObjects;

public class PhpStepDefinition {

	private PhpPageObjects phpPageObjects;
	private PhpProperties phpPropertiesObj;
	private ConfigurableEnvironment phpProperties;
	private DataProperties dataPropertiesObj;
	private ConfigurableEnvironment dataProperties;

	public PhpStepDefinition() {
		this.phpPageObjects = new PhpPageObjects();
		this.phpPropertiesObj = new PhpProperties();
		this.phpProperties = phpPropertiesObj.loadProperties();
		this.dataPropertiesObj = new DataProperties();
		this.dataProperties = dataPropertiesObj.loadProperties();
	}

	@Given("I entered on {string} page")
	public void iEnteredOnPage(String page) throws InterruptedException {
		switch (page) {
		case "agent":
			this.phpPageObjects.getUrlDriver((phpProperties.getProperty("php.url.phpAgentTravelUrl")));
			break;
		case "admin":
			this.phpPageObjects.getUrlDriver((phpProperties.getProperty("php.url.phpAdmTravelUrl")));
			break;
		default:
			break;
		}
	}

	@Then("the date is displayed correctly")
	public void theDateIsDisplayedCorrectly() {
		this.phpPageObjects.validateDateInformation();
	}

	@Given("I type a name {string}")
	public void iTypeAName(String User) {
		this.phpPageObjects.typeUser(User);
	}

	@Given("I type a password {string}")
	public void iTypeAPassword(String password) {
		this.phpPageObjects.typePassword(password);
		this.phpPageObjects.clickOnLgnBtn();
	}

	@Then("the welcome message is displayed")
	public void theWelcomeMessageIsDisplayed() {
		this.phpPageObjects.validateInitialMessage();
	}

	@Given("I access with {string} profile")
	public void iAccessWithProfile(String profile) {
		switch (profile) {
		case "agent":
			this.phpPageObjects.typeUser((dataProperties.getProperty("data.useragent")));
			this.phpPageObjects.typePassword((dataProperties.getProperty("data.passwordagent")));
			this.phpPageObjects.clickOnLgnBtn();
			break;
		case "admin":
			this.phpPageObjects.typeUser((dataProperties.getProperty("data.useradm")));
			this.phpPageObjects.typePassword((dataProperties.getProperty("data.uderadmpassword")));
			this.phpPageObjects.clickOnLgnBtn();
			break;

		default:
			break;
		}
	}

	@When("I access the menu {string}")
	public void iAccessTheMenu(String menu) {
		switch (menu) {
		case "Customers":
			this.phpPageObjects.customersPage();

			break;

		default:
			break;
		}
	}

	@When("I {string} a new user")
	public void iANewUser(String crud) {
		switch (crud) {
		case "create":
			this.phpPageObjects.addNewUser();

			break;

		default:
			break;
		}
	}

	@Then("A new user is created")
	public void aNewUserIsCreated() {
		this.phpPageObjects.userCreationValidation();
	}

	@When("I click on need help")
	public void iClickOnNeedHelp() {
		this.phpPageObjects.clickHelpBtn();
	}

	@Then("the help page is displayed")
	public void theHelpPageIsDisplayed() {
		this.phpPageObjects.helpPage();
	}

	@After
	public void closeDriver() {
		this.phpPageObjects.quitDriver();
	}
}
