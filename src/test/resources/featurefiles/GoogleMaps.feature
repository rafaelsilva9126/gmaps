Feature: As a QA Engineer, I want to verify if the city researched is correctly displayed.

	Background:
	Given I entered on Google Maps page

  Scenario: Login as agent
 	Given I search "Dublin" as a place
  Then The place searched is displayed as a headline text
  When I click on destination
  Then The destination field is displayed with the placed searched 
  
