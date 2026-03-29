Feature: Login page

  As a user
  I want to be able to login with correct credentials

  Scenario: Correct credentials provided
    Given the user opens login page
    When the user enters username "Admin"
    And the user enters password "admin123"
    And the user presses login button
    Then dashboard page opened