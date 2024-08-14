@RestApiFunctionality
Feature: Rest Api Tests

  Background: Test Site Availability
    Given availability of the test site

  @GetRequests
  Scenario Outline: Check the get rest api functionality
    When run request <url>
    When get status code <status>, <message>
    Then get response <data>, <value>, <message>

    Examples:
      | url                 | status | message                      | data       | value |
      | "api/users/2"       | 200    | "Single user "               | "data.id"  | 2     |
      | "api/unknown/2"     | 200    | "Single resource "           | "data.id"  | 2     |
      | "api/users?page=2"  | 200    | "List user "                 | "per_page" | 6     |
      | "api/unknown"       | 200    | "List resource "             | "per_page" | 6     |
      | "api/users?delay=3" | 200    | "Delayed response "          | "per_page" | 6     |
      | "api/unknown/23"    | 404    | "Single resource not found " | ""         | 0     |
      | "api/users/23"      | 404    | "Single user not found "     | ""         | 0     |

  @PostRequests
  Scenario Outline: Check the post rest api functionality
    When run request <url> with <data> and <value>
    When status code <status>, <message>
    Then post response <message>, <response>

    Examples:
      | url                              | status | message                | data             | value                           | response            |
      | "https://reqres.in/api/users"    | 201    | "User created "        | "name,job"       | "morpheus,leader"               | ""                  |
      | "https://reqres.in/api/register" | 200    | "User registered "     | "email,password" | "eve.holt@reqres.in,pistol"     | "QpwL5tke4Pnpja7X4" |
      | "https://reqres.in/api/register" | 400    | "User not registered " | "email"          | "sydney@fife"                   | ""                  |
      | "https://reqres.in/api/login"    | 200    | "User logged "         | "email,password" | "eve.holt@reqres.in,cityslicka" | "QpwL5tke4Pnpja7X4" |
      | "https://reqres.in/api/login"    | 400    | "User not logged "     | "email"          | "eve.holt@reqres.in"            | ""                  |

  @PutRequests
  Scenario Outline: Check the put rest api functionality
    When run put request <url> with <data> and <value>
    When status code <status>, <message>
    Then response <message>

    Examples:
      | url                             | status | message         | data       | value                    |
      | "https://reqres.in/api/users/2" | 200    | "User updated " | "name,job" | "morpheus,zion resident" |


  @PatchRequests
  Scenario Outline: Check the put rest api functionality
    When run patch request <url> with <data> and <value>
    When status code <status>, <message>
    Then response <message>

    Examples:
      | url                             | status | message         | data       | value                    |
      | "https://reqres.in/api/users/2" | 200    | "User updated " | "name,job" | "morpheus,zion resident" |


  @DeleteRequests
  Scenario Outline: testing delete requests
    When delete functionality <url>
    When status code <status>, <message>

    Examples:
      | url                             | status | message         |
      | "https://reqres.in/api/users/2" | 204    | "User deleted " |