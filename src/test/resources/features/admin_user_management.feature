Feature: Admin User Management Functionality

  As an Admin
  I want to manage system users
  So that I can ensure that only authorized users have system access

  Background:
    Given the user logged in and opened admin tab

  @HappyPath
  Scenario: Return to admin page from add user page
    Given the users open add user page
    When the user clicks cancel
    Then admin page opened

  @HappyPath
  Scenario: Successfully add a new system user
    Given the user captures the employee name of the first record in the table
    And the users open add user page
    When the user attempts creating new user with required valid data
    Then the new user should be searchable in the system

  @HappyPath
  Scenario: Successfully delete an existing system user
    When the user attempts to delete first record of non admin user
    Then deleted user should not be displayed

  @SadPath
  Scenario: Verify mandatory fields for adding a user
    Given the users open add user page
    When the user attempts to save new user without required data
    Then validation messages appear after each field of new user tab

  @SadPath
  Scenario Outline: Verify password validation
    Given the users open add user page
    When the user enters a password "<Password>"
    Then the system should display a validation error message under password field "<ValidationMessage>"

    Examples:
      | Password  | ValidationMessage                                      |
      | a         | Should have at least 7 characters                      |
      | qwertyu   | Your password must contain minimum 1 number            |
      | 1234567   | Your password must contain minimum 1 lower-case letter |
      | qwertyu1  |                                                        |
      | 1234567a  |                                                        |