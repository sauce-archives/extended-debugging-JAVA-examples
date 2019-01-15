Feature: Performance Testing

  Scenario: PageLoadTime should less than 5s
    Given I am on the SauceDemo homepage
    When I fetch metrics
    Then PageLoadTime should be less than 5s
