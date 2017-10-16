@web
Feature: Web

  Scenario: Search for cheese
    Given home page is opened
    And user searched for a "Blouse"
    Then Search Result page for "Blouse" is displayed
    And search result size is equal to "1"
    And user clicks on Add To Cart for "Blouse"