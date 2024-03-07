Feature: product price

  @priceVerification
  Scenario: Verify product prices are within a range of 1 to 10000
    Given set up connection to database
    And retrieve all product prices
    Then verify prices are between 1 and 10000