@LoginFunctionality
Feature: Login Tests


  Background: Navigate to Home
    Given user navigates to 'Home' page

  @ValidCredentials
  Scenario Outline: Check the login functionality with valid credentials
    When user enters the <username> and <password>
    Then products label is displayed

    Examples:
      | username                  | password       |
      | "standard_user"           | "secret_sauce" |
      | "problem_user"            | "secret_sauce" |
      | "performance_glitch_user" | "secret_sauce" |

  @Negative @InvalidPassword
  Scenario Outline: Check the login functionality with invalid password
    When user enters the <username> and <password>
    Then error message is displayed <message>

    Examples:
      | username                  | password        | message                                                                     |
      | "performance_glitch_user" | ""              | "Epic sadface: Password is required"                                        |
      | "standard_user"           | ""              | "Epic sadface: Password is required"                                        |
      | "locked_out_user"         | ""              | "Epic sadface: Password is required"                                        |
      | "problem_user"            | ""              | "Epic sadface: Password is required"                                        |
      | "locked_out_user"         | "secret_sauce"  | "Epic sadface: Sorry, this user has been locked out."                       |
      | "standard_user"           | "secrets_sauce" | "Epic sadface: Username and password do not match any user in this service" |
      | "standard_user"           | " "             | "Epic sadface: Username and password do not match any user in this service" |
      | "locked_out_user"         | " "             | "Epic sadface: Username and password do not match any user in this service" |
      | "problem_user"            | "wsecret_sauce" | "Epic sadface: Username and password do not match any user in this service" |
      | "problem_user"            | " "             | "Epic sadface: Username and password do not match any user in this service" |
      | "performance_glitch_user" | "secret_sauced" | "Epic sadface: Username and password do not match any user in this service" |
      | "performance_glitch_user" | " "             | "Epic sadface: Username and password do not match any user in this service" |


  @InvalidUsername
  Scenario Outline: Check the login functionality with invalid username and valid password
    When user enters the <username> and <password>
    Then error message is displayed <message>

    Examples:
      | username     | password       | message                                                                     |
      | "wrong_user" | "secret_sauce" | "Epic sadface: Username and password do not match any user in this service" |
      | "12&3"       | "secret_sauce" | "Epic sadface: Username and password do not match any user in this service" |


  @InvalidCredentials
  Scenario Outline: Check the error message with invalid credentials
    When user enters the <username> and <password>
    Then error message is displayed <message>

    Examples:
      | username     | password        | message                                                                     |
      | ""           | ""              | "Epic sadface: Username is required"                                        |
      | " "          | ""              | "Epic sadface: Password is required"                                        |
      | ""           | "something"     | "Epic sadface: Username is required"                                        |
      | ""           | "secret_sauce"  | "Epic sadface: Username is required"                                        |
      | "something"  | ""              | "Epic sadface: Password is required"                                        |
      | " "          | "secret_sauce"  | "Epic sadface: Username and password do not match any user in this service" |
      | "something"  | "somethings"    | "Epic sadface: Username and password do not match any user in this service" |
      | "wrong_user" | "wrog_password" | "Epic sadface: Username and password do not match any user in this service" |
