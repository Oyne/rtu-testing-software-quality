Feature: Admin User Management Functionality

  As an Admin
  I want to manage system users
  So that I can ensure that only authorized users have system access

  Background:
    Given the user logged in and opened admin tab

  @PositivePath
  Scenario: Successfully add a new system user
    When the user fills in all required fields with valid data
    And clicks the "Save" button
    Then a success message "Successfully Saved" is displayed
    And the new user should be searchable in the system

  @NegativePath
  Scenario Outline: Verify mandatory fields for adding a user
    When the user leaves the "<Field>" field empty
    And clicks the "Save" button
    Then a "Required" error message should appear for the "<Field>" field

    Examples:
      | Field         |
      | User Role     |
      | Employee Name |
      | Username      |
      | Password      |

  @PositivePath
  Scenario: Successfully delete an existing system user
    Given a user with username "temp_user_99" exists
    When the user searches for "temp_user_99"
    And the user deletes the record for "temp_user_99"
    And confirms the deletion popup
    Then a success message "Successfully Deleted" is displayed
    And searching for "temp_user_99" should return "No Records Found"

  @NegativePath
  Scenario Outline: Verify password validation
    Given the user navigates to the "Add User" screen
    When the user enters a password "<Password>"
    Then the system should display a validation error message "<ErrorMessage>"

    Examples:
      | Password  | ErrorMessage                               | Description             |
      | Ab1!      | Should have at least 8 characters          | Too short (4 chars)     |
      | abcdefg1! | Should have at least 1 upper case character| Missing Uppercase       |
      | ABCDEFG1! | Should have at least 1 lower case character| Missing Lowercase       |
      | Abcdefg!! | Should have at least 1 number              | Missing Number          |
      | Abcdefg12 | Should have at least 1 special character   | Missing Symbol          |