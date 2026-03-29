Feature: Login Modal UI Elements

  As a user
  I want to see all elements of the Login modal
  So that I can understand how to log in

  Scenario: Verify Login modal contents
    Given the user opens the Login modal
    Then the Login modal title should be "Вхід"
    And the Phone or email input placeholder should be "Телефон або e-mail"
    And the Password input placeholder should be "Пароль"
    And the show|hide password button should be clickable
    And the Remember Me checkbox should be clickable
    And the Remember Me checkbox label should be "Запамʼятати мене"
    And the Forgot Password link should be clickable
    And the Forgot Password link text should be "Забули пароль?"
    And the Login button should be clickable
    And the Login button text should be "Увійти"
    And all social login buttons should be clickable
    When the user closes the login modal
    Then the Login modal should be closed
