Feature: Hello Output

  Scenario: Test sauce:performance custom command
    Given I am on the SauceDemo homepage
    When I execute sauce:performance on page load
    Then sauce:performance should assert performance is okay
    When I execute sauce:performance with on firstByte
    Then sauce:performance should assert firstByte is okay