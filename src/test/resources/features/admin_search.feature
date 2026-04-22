  Feature: Admin Search Functionality

    As an Admin
    I want to filter the system users list by various criteria
    So that I can find specific accounts without scrolling through the whole list

    Background:
      Given the user logged in and opened admin tab

    @SadPath
    Scenario: Search user by partial username
      When the user searches users by part of username "Admi"
      Then No Records Found

    @HappyPath
    Scenario: Search user by full username
      When the user attempts to search with full username "Admin"
      Then user with username "Admin" is shown

    @HappyPath
    Scenario: Search user by user role
      When the user attempts to search by "ESS" user role
      Then users with only "ESS" user role are visible
      And correct number of records is shown

    @SadPath
    Scenario: Search user by non-existing user employee name
      When the user attempts to search by a non-existing employee name "Unknown"
      Then employee name field validation is visible

    @HappyPath
    Scenario: Search user by full employee name
      Given the user captures the employee name of the first record in the table
      And the user searches by the captured employee name
      Then the table should only show record for that captured name

    @HappyPath
    Scenario: Search user by status
      When the user attempts to search by "Disabled" status
      Then  users with only "Disabled" status are visible
      And correct number of records is shown

    @HappyPath
    Scenario Outline: Search user with specific combinations
      When the user filters by "<Username>", "<UserRole>" and "<Status>"
      Then the results should match the filtered criteria of "<Username>", "<UserRole>" and "<Status>"

      Examples:
        | Username | UserRole | Status   |
        | Admin    | Admin    | Enabled  |
        |          | ESS      | Disabled |

    @HappyPath
    Scenario: Reset search filters to view all records
      When the user attempts to search by "ESS" user role
      Then users with only "ESS" user role are visible
      When the user resets the search filters
      Then the search fields should be cleared
      And all system user records should be displayed