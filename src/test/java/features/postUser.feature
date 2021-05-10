Feature: Validating User API
  I want to validate user register with providing invalid data
	
	@InvaliduserRegister
  Scenario Outline: Verify if error message is thrown when invalid payload is provided
    Given Invalid user payload with "<email>"
    When User calls "registerUserAPI" endpoint with "post" http request
    Then Api call returns status code 400
    And "error" in response body is "Missing password"

    Examples: 
      | email       |
      | sydney@fife |
