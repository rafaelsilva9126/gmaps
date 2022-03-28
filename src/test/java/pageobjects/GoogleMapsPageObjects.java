package pageobjects;

import org.springframework.core.env.ConfigurableEnvironment;

import constants.GoogleMapsProperties;
import framework.BasePage;
import framework.TestStatusContainer;

public class GoogleMapsPageObjects extends BasePage {

	private GoogleMapsProperties googleMapsPropertiesObj;
	private ConfigurableEnvironment googleMapsProperties;
	private TestStatusContainer testStatusContainer;

	public GoogleMapsPageObjects() {
		this.googleMapsPropertiesObj = new GoogleMapsProperties();
		this.googleMapsProperties = googleMapsPropertiesObj.loadProperties();
		this.testStatusContainer = new TestStatusContainer();
	}

	public TestStatusContainer getContainer() {
		return testStatusContainer;
	}

	public void acceptCookie() throws InterruptedException {

		getElementByXpath(googleMapsProperties.getProperty("search.id.acceptCookie")).click();
	}

	public ConfigurableEnvironment getgoogleMapsProperties() {
		return googleMapsProperties;
	}

	public void insertADestination(String place) {
		getElementByName((googleMapsProperties.getProperty("gmap.name.place"))).sendKeys(place);
		getElementById((googleMapsProperties.getProperty("gmap.id.searchIcon"))).click();
		testStatusContainer.addString("PARAM$place", place);
	}

	public String getPlaceResearched() {
		String placeResearched = getElementByXpath((googleMapsProperties.getProperty("gmap.xpath.placeResearched")))
				.getText();
		;
		return placeResearched;

	}

	public void clickOnDirectionPlace() {
		getElementByXpath((googleMapsProperties.getProperty("gmap.xpath.directionsBtn"))).click();

	}

	public String getArialTextTyped() {
		String arialTextTyped = getElementByXpath("//*[@id='directions-searchbox-1']/div/div/input")
				.getAttribute("aria-label");
		return arialTextTyped;
	}

}
