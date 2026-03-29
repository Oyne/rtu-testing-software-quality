Feature: Test Failure report

  As a tester
  I want to see failed test step screenshot
  So that I can understand what is wrong

  Scenario: Verify failed test
    Given the user opens the Login modal
    Then the Login modal title should be "ERROR"