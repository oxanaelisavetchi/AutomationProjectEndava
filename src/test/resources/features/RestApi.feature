
@RestApiFunctionality
Feature: Rest Api Tests

  Background: Ensure API is available
    Given the API is accessible via the configured base URL

  @GetRequests
  Scenario Outline: Validate GET endpoint responses
    When a GET request is sent to the endpoint "<path>"
    Then the response of GET status code should be <status> with message <message>
    And the response should contain "<data>" with value <value>

    Examples:
      | path       | status | message            | data       | value |
      | USERS_ID   | 200    | Single user     | data.id    | 2     |
      | UNKNOWN_2  | 200    | Single resource  | data.id    | 2     |
      | USERS_PAGE | 200    | List user       | per_page   | 6     |

  @PositivePostRequests
  Scenario Outline: Successful user operations via POST
    When a POST request is sent to the endpoint "<path>" with data "<data>" and value "<value>"
    Then the response of POST status code should be <status> with message "<message>"
    And the response should contain token "<response>"

    Examples:
      | path     | data           | value                         | status | message         | response            |
      | REGISTER | email,password | eve.holt@reqres.in,pistol     | 200    | User registered | QpwL5tke4Pnpja7X4    |
      | LOGIN    | email,password | eve.holt@reqres.in,cityslicka | 200    | User logged     | QpwL5tke4Pnpja7X4    |

  @PutRequests
Scenario: Update user data using PUT
    When the PUT request is sent "USERS_ID" with data "name,job" and value "morpheus,zion resident"
    Then the response status code should be 200, with message "User updated"
    And the response should contain field "updatedAt"

  @PatchRequests
  Scenario: Update user data using PATCH
    When the PATCH request is sent to "USERS_ID" with data "name,job" and value "morpheus,zion resident"
    Then the response status code should be 200, with message "User updated"
    And the response should contain field "updatedAt"

  @DeleteRequests
  Scenario: Delete user by ID
    When I delete a user at "USERS_ID"
    Then the response status code should be 204, with message "User deleted"

  @APITest
  Scenario: Register user with data table
    When I create a user with the following details:
      | email    | eve.holt@reqres.in |
      | password | pistol             |
    Then the response should contain token

  @NegativePostRequests
  Scenario Outline: Unsuccessful POST operations due to missing or invalid data
    When a POST request is sent to the endpoint "<path>" with data "<data>" and value "<value>"
    Then the response of POST status code should be <status> with message "<message>"

    Examples:
      | path     | data    | value             | status | message              |
      | REGISTER | email   | sydney@fife       | 400    | User not registered  |
      | LOGIN    | email   | eve.holt@reqres.in| 400    | User not logged      |
