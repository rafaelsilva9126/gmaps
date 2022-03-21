package pageobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.junit.Assert;
import org.springframework.core.env.ConfigurableEnvironment;

import constants.PhpProperties;
import framework.BasePage;
import framework.TestStatusContainer;

public class PhpPageObjects extends BasePage {

	private PhpProperties phpPropertiesObj;
	private ConfigurableEnvironment phpProperties;
	private TestStatusContainer testStatusContainer;

	public PhpPageObjects() {
		this.phpPropertiesObj = new PhpProperties();
		this.phpProperties = phpPropertiesObj.loadProperties();
		this.testStatusContainer = new TestStatusContainer();
	}

	public void getStatusContainer(String name) {
		testStatusContainer.addString("PARAM$string", name);
	}

	public void acceptCookie() throws InterruptedException {

		getElementByXpath(phpProperties.getProperty("search.id.acceptCookie")).click();
	}

	public ConfigurableEnvironment getphpProperties() {
		return phpProperties;
	}

	public void typeUser(String string) {
		getElementByName((phpProperties.getProperty("php.name.emailBox"))).sendKeys(string);

	}

	public void typePassword(String string) {
		getElementByName((phpProperties.getProperty("php.name.passwordBox"))).sendKeys(string);
	}

	public void clickOnLgnBtn() {
		getElementByXpath((phpProperties.getProperty("php.xpath.lgnBtn"))).click();

	}

	public void validateInitialMessage() {
		getElementByLinkText((phpProperties.getProperty("php.linkText.myprofileMenu"))).click();
		String userFirstName = getElementByName("firstname").getAttribute("value");
		String nameDisplayedOnTop = getElementByXpath(
				(phpProperties.getProperty("php.xpath.nameDisplayedOnWelMessage"))).getText();
		Assert.assertTrue(nameDisplayedOnTop.contains(userFirstName));
		Assert.assertTrue(nameDisplayedOnTop.contains(nameDisplayedOnTop));

	}

	public void validateDateInformation() {
		Locale.setDefault(Locale.US);
		String date = getElementById((phpProperties.getProperty("php.id.idMsg"))).getText();
		String formatDate = date.substring(0, 15);
		System.out.println(formatDate);
		Date dateS = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy");
		String strDate = formatter.format(dateS);
		System.out.println(strDate);
		Assert.assertEquals(formatDate, strDate);
	}

	public void customersPage() {
		getElementByXpath((phpProperties.getProperty("php.xpath.accountsMenu"))).click();
		getElementByLinkText((phpProperties.getProperty("php.linkText.customersMenu"))).click();

	}

	public void addNewUser() {
		Random r = new java.util.Random();
		String userName = Long.toString(r.nextLong() & Long.MAX_VALUE, 36);
		String email = Long.toString(r.nextLong() & Long.MAX_VALUE, 36) + "@test.com";
		getElementByXpath((phpProperties.getProperty("php.xpath.newUserBtn"))).click();
		getElementByName((phpProperties.getProperty("php.name.firstNameBox"))).sendKeys(userName);
		getElementByName((phpProperties.getProperty("php.name.lastNameBox"))).sendKeys("Silva");
		;
		getElementByName((phpProperties.getProperty("php.name.emailBox"))).sendKeys(email);
		;
		getElementByName((phpProperties.getProperty("php.name.passwordBox"))).sendKeys("123456");
		getElementById((phpProperties.getProperty("php.id.countryBox"))).click();
		getElementByXpath((phpProperties.getProperty("php.xpath.searchBox"))).sendKeys("Brazil");
		getElementByXpath((phpProperties.getProperty("php.xpath.firstResult"))).click();
		getElementByXpath((phpProperties.getProperty("php.xpath.updateBtn"))).click();
		testStatusContainer.addString("PARAM$firstname", userName);
		testStatusContainer.addString("PARAM$email", email);
	}

	public void userCreationValidation() {
		String userNameColumn = getElementByXpath((phpProperties.getProperty("php.xpath.firstNameFirstRec")))
				.getText();
		String emailColumn = getElementByXpath((phpProperties.getProperty("php.xpath.emailFirstRec"))).getText();
		Assert.assertEquals(testStatusContainer.getString("PARAM$firstname"), userNameColumn);
		Assert.assertEquals(testStatusContainer.getString("PARAM$email"), emailColumn);
	}

	public void clickHelpBtn() {
		getElementByLinkText((phpProperties.getProperty("php.linkText.company"))).click();
		getElementByXpath((phpProperties.getProperty("php.xpath.helpMenu"))).click();

	}

	public void helpPage() {
		Assert.assertTrue(
				getElementByXpath((phpProperties.getProperty("php.xpath.helpheadermessage"))).isDisplayed());
		Assert.assertTrue(getElementByName((phpProperties.getProperty("php.name.emailBox"))).isEnabled());
		Assert.assertTrue(getElementByName((phpProperties.getProperty("php.name.nameSendPage"))).isEnabled());
		Assert.assertTrue(getElementByName((phpProperties.getProperty("php.name.messageBox"))).isEnabled());
		getElementById((phpProperties.getProperty("php.id.cookstop"))).click();
		Assert.assertTrue(getElementByXpath((phpProperties.getProperty("php.xpath.send"))).isDisplayed());
	}

}
