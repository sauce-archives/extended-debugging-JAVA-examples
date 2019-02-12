Feature: Hello Output

  Scenario: Test sauce:performance custom command
    Given I am on the SauceDemo homepage
    When I execute sauce:performance with test name
    Then sauce:performance should assert performance is okay
