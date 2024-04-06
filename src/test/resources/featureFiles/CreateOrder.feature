@CurrentTest @UI @Run
Feature: Place the order procedure

  Scenario Outline: Order is created
    Given user log in
    And user navigates to 'orders' section
    And "create" new order button is clicked
    When order params fields are filled
    And existing client <clientName> is specified for department <department>
    Then user submits order
    And 'Order successfully created' message is displayed on Orders page

    Examples:
      | clientName          | department |
      | Company (VAT: 0001) | LTL export |