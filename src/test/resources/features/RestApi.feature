@RestApiFunctionality
Feature: Rest Api Tests

  Background: Test Site Availability
    Given the API is reachable at base URL

  @GetRequests
  Scenario Outline: Check GET API response
    When run request "<path>"
    And check get status code <status>, <message>
    Then check get response <data>, <value>, <message>

    Examples:
      | path        | status | message            | data       | value |
      | USERS_ID    | 200    | "Single user "     | "data.id"  | 2     |
      | UNKNOWN_2   | 200    | "Single resource " | "data.id"  | 2     |
      | USERS_PAGE  | 200    | "List user "       | "per_page" | 6     |

  @PostRequests
  Scenario Outline: Check the post rest api functionality
    When run request "<path>" with <data> and <value>
    And check status code <status>, <message>
    Then check post response <message>, <response>

    Examples:
      | path       | status | message                | data             | value                           | response            |
      | USERS    | 201    | "User created "        | "name,job"       | "morpheus,leader"               | ""                  |
      | REGISTER | 200    | "User registered "     | "email,password" | "eve.holt@reqres.in,pistol"     | "QpwL5tke4Pnpja7X4" |
      | REGISTER | 400    | "User not registered " | "email"          | "sydney@fife"                   | ""                  |
      | LOGIN   | 200    | "User logged "         | "email,password" | "eve.holt@reqres.in,cityslicka" | "QpwL5tke4Pnpja7X4" |
      | LOGIN  | 400    | "User not logged "     | "email"          | "eve.holt@reqres.in"            | ""                  |

  @PutRequests
  Scenario Outline: Check the put rest api functionality
    When run put request "<path>" with <data> and <value>
    And check status code <status>, <message>
    Then check response <message>

    Examples:
      | path      | status | message         | data       | value                    |
      | USERS_ID | 200    | "User updated " | "name,job" | "morpheus,zion resident" |

  @PatchRequests
  Scenario Outline: Check the put rest api functionality
    When run patch request "<path>" with <data> and <value>
    And check status code <status>, <message>
    Then check response <message>

    Examples:
      | path      | status | message         | data       | value                    |
      | USERS_ID | 200    | "User updated " | "name,job" | "morpheus,zion resident" |

  @DeleteRequests
  Scenario Outline: Check the delete rest api functionality
    When run delete request "<path>"
    And check status code <status>, <message>

    Examples:
      | path      | status | message         |
      | USERS_ID | 204    | "User deleted " |

  @APITest
  Scenario: Create a user with data table
    When I create a user with the following details:
      | email    | eve.holt@reqres.in |
      | password | pistol             |
    Then the response should contain token
