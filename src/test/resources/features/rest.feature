@rest
Feature: REST

  Scenario: User REST service example
    Given following user created
      | firstname     | lastname          | userstatus      |
      | cucumber-name | cucumber-lastname | cucumber status |
    And user "cucumber-name-1" is modified
    And user list size is "0"
    And user with surname "cucumber-lastname-1" firstName is "modified-name"
    And all users are deleted