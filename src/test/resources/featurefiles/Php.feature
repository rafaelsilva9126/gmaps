Feature: As a QA Engineer, I want to interact in phptravels to simulate some importants behaviour


  Scenario: Login as agent
 	Given I entered on "agent" page
  And I access with "agent" profile
  Then the welcome message is displayed
  And the date is displayed correctly
  

  Scenario: Access to help page
 	Given I entered on "agent" page
  And I access with "agent" profile
  When I click on need help
  Then the help page is displayed
  

  Scenario: Adding a new user
  Given I entered on "admin" page
  And I access with "admin" profile
  When I access the menu "Customers"
  And I "create" a new user
  Then A new user is created 