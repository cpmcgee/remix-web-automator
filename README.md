# remix-web-automator
This is a test automation framework for Ethereum's Solidity web IDE. Obviously, traditional unit testing of smart contracts makes 1000% more sense in the real world, but this framework was created as a way for me to practice test framework design and try out new libraries, ideas, and features.

Visit remix.ethereum.org to see the application under test. Currently this framework allows you to load smart contracts into the editor from files on your computer, manipulate tabs, deploy smart contracts, run the methods on the smart contract, verify their output, verify the values of the contract's member variables, etc. 

This framework utilizes the following stack:
 - Java
 - Maven
 - Selenium WebDriver 
 - JUnit
 - Cucumber (and parallel execution plugin)
