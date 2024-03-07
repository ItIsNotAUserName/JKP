Feature: Bank account test

  @BankTest
  Scenario Outline: Create many bank accounts
    Given Create bank account at "/api/myaccount/bankaccount" with data: "<type_of_pay>" and "<bank_account_name>" and "<description>" and <balance>
    When Get same bank by id and validate status code <200>
    Then Delete bank accounts by id

    Examples:
      | bank_account_name | description | type_of_pay | balance |
      | theworstbank22      | desc121     | CASH        | 110.00  |
#      | name 21           | desc2       | cash        | 220     |
#      | name 31           | desc3       | cash        | 333     |
#      | name 43           | desc4       | cash        | 444     |
#      | name 53           | desc5       | cash        | 505     |
#      | name 63           | desc6       | cash        | 606     |