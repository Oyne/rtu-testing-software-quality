Feature: Admin User Management Functionality

  As an Admin
  I want to manage users leaves
  So that I can ensure that only correct users have leaves

  Background:
    Given the user logged in and opened leave tab after capturing the employee name of the first record in the table of admin tab

  @HappyPath
  Scenario: Successfully add a leave to system user
    When the user attempts to add a leave to captured employee
    Then created leave should be visible in leave list

  @HappyPath
  Scenario: Successfully remove a leave of system user
    Given the leave for captured employee exists
    When the user attempts deleting existing leave
    Then deleted leave should be not visible in leave list

  @SadPath
  Scenario: Verify mandatory fields for adding a user
    Given the users open assign leave page
    When the user attempts to save new leave without required data
    Then validation messages appear after each field of assign leave tab