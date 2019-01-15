Feature: Performance Testing

  Scenario: Is performance log exists
    Given I am on the SauceDemo homepage
    When I fetch performanceLog
    Then All the metrics should exists
