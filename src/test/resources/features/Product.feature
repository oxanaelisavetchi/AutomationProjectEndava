@ProductsFunctionality
Feature: Product Tests

  Background: Navigate to Home
    Given user navigates to 'Home' page

  @ValidCredentials
  Scenario Outline: Sort on Product page
    When user enters the <username> and <password>
    Then user enters on product page
    And user sort asc product by name
    And user sort desc product by name
    And user sort asc product by price
    And user sort desc product by price

    Examples:
      | username                  | password       |
      | "standard_user"           | "secret_sauce" |
      | "performance_glitch_user" | "secret_sauce" |