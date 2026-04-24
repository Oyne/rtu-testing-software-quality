Feature: Login Functionality

  As a user
  I want to be able to login with correct credentials
  So that I can access my dashboard

  Background:
    Given the user opens login page

  @HappyPath
  Scenario: Correct credentials provided
    When the user logs in with valid credentials
    Then dashboard page opened

  @SadPath
  Scenario: No credentials provided
    When the user attempts to log in without credentials
    Then username validation message "Required" appears
    And password validation message "Required" appears
    And current page is still login page

  @SadPath
  Scenario: Only username provided
    When the user attempts to log in with only a username
    Then password validation message "Required" appears
    And current page is still login page

  @SadPath
  Scenario: Only password provided
    When the user attempts to log in with only a password
    Then username validation message "Required" appears
    And current page is still login page

  @SadPath
  Scenario: Wrong credentials provided
    When the user attempts to log in with invalid credentials
    Then "Invalid credentials" error is visible
    And current page is still login page