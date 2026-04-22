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
  Scenario: Successfully delete an existing system user
    When the user attempts to delete first record of non admin user
    Then deleted user should not be displayed

  @HappyPath
  Scenario: Successfully add a new system user
    Given the user captures the employee name of the first record in the table
    And the users open add user page
    When the user attempts creating new user with required valid data
    Then the new user should be searchable in the system

  @SadPath
  Scenario Outline: Verify mandatory fields for adding a user
    Given the users open add user page
    When the user leaves the "<Field>" field empty
    And clicks the "Save" button
    Then a "Required" error message should appear for the "<Field>" field

    Examples:
      | Field         |
      | User Role     |
      | Employee Name |
      | Username      |
      | Password      |

  @SadPath
  Scenario Outline: Verify password validation
    Given the user navigates to the "Add User" screen
    When the user enters a password "<Password>"
    Then the system should display a validation error message "<ErrorMessage>"

    Examples:
      | Password  | ErrorMessage                                | Description         |
      | Ab1!      | Should have at least 8 characters           | Too short (4 chars) |
      | abcdefg1! | Should have at least 1 upper case character | Missing Uppercase   |
      | ABCDEFG1! | Should have at least 1 lower case character | Missing Lowercase   |
      | Abcdefg!! | Should have at least 1 number               | Missing Number      |
      | Abcdefg12 | Should have at least 1 special character    | Missing Symbol      |