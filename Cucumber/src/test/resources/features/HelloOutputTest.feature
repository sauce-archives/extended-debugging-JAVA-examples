Feature: Hello Output

  Scenario: Verify Hello output
    Given I am on the SauceDemo homepage
    When I execute sauce:hello with test name
    Then Hello should return test name
