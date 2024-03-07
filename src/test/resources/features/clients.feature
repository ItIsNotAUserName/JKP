Feature: user should be able to create a client
    @db1
    Scenario: verify client
      Given new client is created
      Then verify client exists in database
      And delete client in database
      Then verify client does not exist using API