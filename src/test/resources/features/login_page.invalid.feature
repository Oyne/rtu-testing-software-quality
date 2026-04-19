Feature: Login page

  As a user
  I want to understand when I make mistakes during the login process
  So that I know what needs to be corrected to log in successfully

  Scenario: No credentials provided
    Given the user opens login page
    When the user attempts to log in without credentials
    Then username validation message "Required" appears
    And password validation message "Required" appears
    And current page is still login page

  Scenario: Only username provided
    Given the user opens login page
    When the user attempts to log in with only a username
    Then password validation message "Required" appears
    And current page is still login page

  Scenario: Only password provided
    Given the user opens login page
    When the user attempts to log in with only a password
    Then username validation message "Required" appears
    And current page is still login page

  Scenario: Wrong credentials provided
    Given the user opens login page
    When the user attempts to log in with invalid credentials
    Then "Invalid credentials" error is visible
    And current page is still login page