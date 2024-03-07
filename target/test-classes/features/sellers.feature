Feature: Test sellers
  @seller
  Scenario: Get all sellers and validate phone numbers
    Given get all sellers with api path "/api/myaccount/sellers" and parameters: isArchived "false", page 1, size 10
    Then user get all phone numbers and validate is not null

  @seller2
  Scenario: Get list of all sellers, print names and validate they are not null
    Given get all sellers with api path "/api/myaccount/sellers/all"
    Then print seller names and validate it is not null

    Scenario: Create