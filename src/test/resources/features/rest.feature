@rest
Feature: REST

  Scenario: User REST service example
    Given all users are deleted
    When following user created
      | firstname     | lastname          | userstatus      |
      | cucumber-name | cucumber-lastname | cucumber status |
    Then user list size is "1"
    When user "cucumber-name-1" is modified
    Then user with surname "cucumber-lastname-1" firstName is "Modified cucumber user"