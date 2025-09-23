Feature: User API Testing
  Scenario: Verify user details
    Given I send a GET request to "/users/2"
    Then the response status code should be 200
