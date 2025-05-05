@ProductsFunctionality
Feature: Product Tests

  Background: Navigate to Home
    Given user navigates to 'Home' page

  @ValidCredentials
  Scenario Outline: Sort products on Product page by name and price
    When user enters the <username> and <password>
    And user sorts products by name in "<sort>" order
    And user sorts products by price in "<sort>" order

    Examples:
      | username                  | password       | sort |
      | "standard_user"           | "secret_sauce" | asc  |
      | "performance_glitch_user" | "secret_sauce" | desc |
