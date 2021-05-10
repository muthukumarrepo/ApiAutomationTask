  Feature: Validating User API
  I want to validate user respose data contains a given data
  
  Scenario Outline: Verify GET response data
    Given Valid user payload with "<name>" and "<job>"
    And User calls "registerUserAPI" endpoint with "post" http request
    When User calls "getUserAPI" endpoint with "get" http request
    Then Api call returns status code 200
    And verify respone contains user first_name as "Byron" and last_name as "Fields" 

    Examples: 
      | name     | job        |
      | Scofield | Automation |
