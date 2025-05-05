@LoginFunctionality
Feature: Login Tests


  Background: Navigate to Home
    Given user navigates to 'Home' page

  @ValidCredentials
  Scenario Outline: Check the login functionality with valid credentials
    When user enters the <username> and <password>
    Then user enters on product page

    Examples:
      | username                  | password       |
      | "standard_user"           | "secret_sauce" |
      | "problem_user"            | "secret_sauce" |
      | "performance_glitch_user" | "secret_sauce" |

  @Negative @InvalidPassword
  Scenario Outline: Check the login functionality with invalid password
    When user enters the <username> and <password>
    Then user receives message "<message>"

    Examples:
      | username                  | password        | message             |
      | "performance_glitch_user" | ""              | PASSWORD_REQUIRED   |
      | "standard_user"           | ""              | PASSWORD_REQUIRED   |
      | "locked_out_user"         | ""              | PASSWORD_REQUIRED   |
      | "problem_user"            | ""              | PASSWORD_REQUIRED   |
      | "locked_out_user"         | "secret_sauce"  | LOCKED_OUT_USER     |
      | "standard_user"           | "secrets_sauce" | INVALID_CREDENTIALS |
      | "standard_user"           | " "             | INVALID_CREDENTIALS |
      | "locked_out_user"         | " "             | INVALID_CREDENTIALS |
      | "problem_user"            | "wsecret_sauce" | INVALID_CREDENTIALS |
      | "problem_user"            | " "             | INVALID_CREDENTIALS |
      | "performance_glitch_user" | "secret_sauced" | INVALID_CREDENTIALS |
      | "performance_glitch_user" | " "             | INVALID_CREDENTIALS |


  @InvalidUsername
  Scenario Outline: Check the login functionality with invalid username and valid password
    When user enters the <username> and <password>
    Then user receives message "<message>"

    Examples:
      | username     | password       | message            |
      | "wrong_user" | "secret_sauce" | INVALID_CREDENTIALS|
      | "12&3"       | "secret_sauce" | INVALID_CREDENTIALS|


  @InvalidCredentials
  Scenario Outline: Check the error message with invalid credentials
    When user enters the <username> and <password>
    Then user receives message "<message>"

    Examples:
      | username     | password        | message               |
      | ""           | ""              | USERNAME_REQUIRED     |
      | " "          | ""              | PASSWORD_REQUIRED     |
      | ""           | "something"     | USERNAME_REQUIRED     |
      | ""           | "secret_sauce"  | USERNAME_REQUIRED     |
      | "something"  | ""              | PASSWORD_REQUIRED     |
      | " "          | "secret_sauce"  | INVALID_CREDENTIALS   |
      | "something"  | "somethings"    | INVALID_CREDENTIALS   |
      | "wrong_user" | "wrong_password"| INVALID_CREDENTIALS   |

