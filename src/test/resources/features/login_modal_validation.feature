Feature: Login Modal validation

  As a user
  I want to understand when I make mistakes during the login process
  So that I know what needs to be corrected to log in successfully

  Scenario: No credentials provided
    Given the user opens the Login modal
    When the user clicks the Login button
    Then the validation message should be "Вкажіть телефон або email"
    And the Phone or email input should be highlighted
    And the Password input should be highlighted
    And the user closes the login modal

  Scenario: Only email provided
    Given the user opens the Login modal
    When the user enters email "test@gmail.com"
    And the user clicks the Login button
    Then the validation message should be "Невірні дані входу"
    And the Phone or email input should be highlighted
    And the Password input should be highlighted
    And the user closes the login modal

  Scenario: Only password provided
    Given the user opens the Login modal
    When the user enters password "test"
    And the user clicks the Login button
    Then the validation message should be "Вкажіть телефон або email"
    And the Phone or email input should be highlighted
    And the Password input should be highlighted
    And the user closes the login modal

  Scenario: Wrong credentials provided
    Given the user opens the Login modal
    When the user enters email "test@gmail.com"
    And the user enters password "wrongpass"
    And the user clicks the Login button
    Then the validation message should be "Невірні дані входу"
    And the Phone or email input should be highlighted
    And the Password input should be highlighted
    And the user closes the login modal