@UI @DB @Run
Feature: Client management

  Scenario: Client is created
    Given user log in
    And user navigates to 'clients' section
    And "create" new client button is clicked
    When user fills client details
    Then user submits new client
    And 'Client successfully created' message is displayed on Clients page
    And created client exists in database