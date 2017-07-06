Feature: As a user of remix, I can compile syntactically correct smart contracts

  Scenario: Load a simple contract from a file and compile it
    Given I navigate to the remix website
    And I delete the default tab
    And I create a new tab called "TestContract.sol"
    And I load into the tab the code from "TestContract2.txt"
    Then I create the contract with an argument of "200"
    And I verify running "getNumber" will return "200"
    Then I run "setNumber" with an argument of "100"
    Then I verify the "number" attribute has a value of "100"

  Scenario: Parallel contract load/compile
    Given I navigate to the remix website
    And I delete the default tab
    And I create a new tab called "NewTab.sol"
    And I load into the tab the code from "TestContract.txt"
    And I wait for the page and compiler to load
    Then I create the contract with an argument of ""
    And I run "getNumber" with an argument of ""