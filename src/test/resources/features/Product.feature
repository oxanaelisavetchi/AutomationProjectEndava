@ProductsFunctionality
Feature: Product Tests

  Background: Navigate to Home
    Given user navigates to 'Home' page
  @ValidCredentials
  Scenario Outline: Sort on Product page
    When users enters the <username> and <password>
    Then users enters on product page
    And users sort product by name
#   And user sort product by price



    Examples:
      | username                  | password       |
      | "standard_user"           | "secret_sauce" |
      | "problem_user"            | "secret_sauce" |
      | "performance_glitch_user" | "secret_sauce" |