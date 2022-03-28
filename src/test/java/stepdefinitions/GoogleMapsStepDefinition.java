package stepdefinitions;

import org.junit.Assert;
import org.springframework.core.env.ConfigurableEnvironment;

import constants.DataProperties;
import constants.GoogleMapsProperties;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.GoogleMapsPageObjects;

public class GoogleMapsStepDefinition {

	private GoogleMapsPageObjects googleMapsPageObjects;
	private GoogleMapsProperties gMapsPropertiesObj;
	private ConfigurableEnvironment gMapsProperties;
	private DataProperties dataPropertiesObj;
	private ConfigurableEnvironment dataProperties;

	public GoogleMapsStepDefinition() {
		this.googleMapsPageObjects = new GoogleMapsPageObjects();
		this.gMapsPropertiesObj = new GoogleMapsProperties();
		this.gMapsProperties = gMapsPropertiesObj.loadProperties();
		this.dataPropertiesObj = new DataProperties();
		this.dataProperties = dataPropertiesObj.loadProperties();
	}

	@Given("I entered on Google Maps page")
	public void iEnteredOnGoogleMapsPage() {
		this.googleMapsPageObjects.getUrlDriver((gMapsProperties.getProperty("gmap.url.googleMapslUrl")));
	}

	@Given("I search {string} as a place")
	public void iSearchAsAPlace(String string) {
		this.googleMapsPageObjects.insertADestination(string);
	}

	@Then("The place searched is displayed as a headline text")
	public void thePlaceSearchedIsDisplayedAsAHeadlineText() {
		Assert.assertEquals(this.googleMapsPageObjects.getContainer().getString("PARAM$place"),
				this.googleMapsPageObjects.getPlaceResearched());
	}

	@When("I click on destination")
	public void iClickOnDestination() {
		this.googleMapsPageObjects.clickOnDirectionPlace();
	}

	@Then("The destination field is displayed with the placed searched")
	public void theDestinationFieldIsDisplayedWithThePlacedSearched() {
		Assert.assertTrue((this.googleMapsPageObjects.getArialTextTyped().contains("Dublin")));
			
	}

	@After
	public void closeDriver() {
		this.googleMapsPageObjects.quitDriver();
	}
}
