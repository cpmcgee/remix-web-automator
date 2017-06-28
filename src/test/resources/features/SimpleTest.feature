Feature: As a user of remix, I can compile syntactically correct smart contracts

  Scenario: Load a simple contract from a file and compile it
    Given I navigate to the remix website
    And I wait for the page and compiler to load
    And I delete the default tab
    And I create a new tab called "TestContract.sol"
    And I load into the tab the code from "TestContract.txt"
    Then I verify i can compile the contract without errors
