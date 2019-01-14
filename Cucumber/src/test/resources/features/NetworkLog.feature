Feature: Performance Testing

  Scenario: NetworkLog should have mainjs
    Given I am on the SauceDemo homepage
    When I fetch networkLogs
    Then NetworkLog should contain mainjs
