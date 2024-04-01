@UI @API @Run
Feature: Bid management

  Scenario: Place a bid
    Given order list is retrieved
    And the response status code is 200
    And latest order was successfully retrieved
    When bid is placed with required details
      | currency | minAmount | maxAmount |
      | USD      | 5         | 150000    |
      | EUR      | 5         | 100000    |
      | RUB      | 10000     | 500000    |
    And the response status code is 200
    Then user log in
    And user navigates to 'orders' section
    And placed bid is added to the selected order