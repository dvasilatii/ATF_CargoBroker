@UI @API @Run
Feature: Bid management

  Scenario Outline: Place a bid
    Given order list is retrieved
    And the response status code is 200
    And latest order was successfully retrieved
    When bid of amount <amount> currency <currency> is placed for latest order with comment <comment>
    And the response status code is 200



    Examples:
      | amount | currency | comment     |
      | 5400   | USD      | initial bid |