Feature: Performance Testing

  Scenario: Verify TimingLog
    Given I am on the SauceDemo homepage
    When I fetch timingLog
    Then TimingLog should contain domLoading time
