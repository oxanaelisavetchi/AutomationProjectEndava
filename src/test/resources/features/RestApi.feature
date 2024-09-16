@RestApiFunctionality
Feature: Rest Api Tests

  Background: Test Site Availability
    Given availability of the test site

  @GetRequests
  Scenario Outline: Check the get rest api functionality
    When run request <path>
    And check get status code <status>, <message>
    Then check get response <data>, <value>, <message>

    Examples:
      | path            | status | message                      | data       | value |
      | "users/2"       | 200    | "Single user "               | "data.id"  | 2     |
      | "unknown/2"     | 200    | "Single resource "           | "data.id"  | 2     |
      | "users?page=2"  | 200    | "List user "                 | "per_page" | 6     |
      | "unknown"       | 200    | "List resource "             | "per_page" | 6     |
      | "users?delay=3" | 200    | "Delayed response "          | "per_page" | 6     |
      | "unknown/23"    | 404    | "Single resource not found " | ""         | 0     |
      | "users/23"      | 404    | "Single user not found "     | ""         | 0     |

  @PostRequests
  Scenario Outline: Check the post rest api functionality
    When run request <path> with <data> and <value>
    And check status code <status>, <message>
    Then check post response <message>, <response>

    Examples:
      | path       | status | message                | data             | value                           | response            |
      | "users"    | 201    | "User created "        | "name,job"       | "morpheus,leader"               | ""                  |
      | "register" | 200    | "User registered "     | "email,password" | "eve.holt@reqres.in,pistol"     | "QpwL5tke4Pnpja7X4" |
      | "register" | 400    | "User not registered " | "email"          | "sydney@fife"                   | ""                  |
      | "login"    | 200    | "User logged "         | "email,password" | "eve.holt@reqres.in,cityslicka" | "QpwL5tke4Pnpja7X4" |
      | "login"    | 400    | "User not logged "     | "email"          | "eve.holt@reqres.in"            | ""                  |

  @PutRequests
  Scenario Outline: Check the put rest api functionality
    When run put request <path> with <data> and <value>
    And check status code <status>, <message>
    Then check response <message>

    Examples:
      | path      | status | message         | data       | value                    |
      | "users/2" | 200    | "User updated " | "name,job" | "morpheus,zion resident" |

  @PatchRequests
  Scenario Outline: Check the put rest api functionality
    When run patch request <path> with <data> and <value>
    And check status code <status>, <message>
    Then check response <message>

    Examples:
      | path      | status | message         | data       | value                    |
      | "users/2" | 200    | "User updated " | "name,job" | "morpheus,zion resident" |

  @DeleteRequests
  Scenario Outline: Check the delete rest api functionality
    When run delete request <path>
    And check status code <status>, <message>

    Examples:
      | path      | status | message         |
      | "users/2" | 204    | "User deleted " |