Feature: Ecom app API validation

  @login
  Scenario Outline: Ecom app login validations with valid credentials
    Given user has "LOGINAPI" payload with "<username>" and "<password>"
    When user calls login api request with POST http method
    Then "message" in response body is "Login Successfully"

    Examples: 
      | username              | password   |
      | sanimondal7@gmail.com | Dexter@456 |

  #|sanimondal7@gmail.com|Dexter@456|
  @addproduct
  Scenario Outline: Ecom app create product validations
    Given user has "LOGINAPI" payload with "<username>" and "<password>"
    When user calls login api request with POST http method
    And user has "ADDPRODUCT" payload with "<productName>" "<productCategory>" "<productSubCategory>" "<productPrice>"  "<productDescription>" "<productFor>"  "<productImage>"
    When user calls the api request with POST http method
    Then "message" in response body is "Login Successfully"

    Examples: 
      | productName | productCategory | productSubCategory | productPrice | productDescription | productFor | productImage                           | username              | password   |
      | qwerty      | fashion         | shirts             |        11500 | Addias Originals   | women      | C:\\Users\\sanim\\Downloads\\shirt.jpg | sanimondal7@gmail.com | Dexter@456 |

  @createorder
  Scenario Outline: Ecom app create order validations
    Given user has "LOGINAPI" payload with "<username>" and "<password>"
    When user calls login api request with POST http method
    And user has add order "ADDORDER" payload with "<country>" and "<productOrderedId>"
    When user calls ADDORDER api request with POST http method

    Examples: 
      | country | productOrderedId         | username              | password   |
      | India   | 69c90e22f86ba51a653390c6 | sanimondal7@gmail.com | Dexter@456 |

  @vieworder
  Scenario Outline: Ecom app view order validations
    Given user has "LOGINAPI" payload with "<username>" and "<password>"
    When user calls login api request with POST http method
    And user has view order "VIEWORDER" payload with "<Orders>"
    When user calls VIEWORDER api request with GET http method

    Examples: 
      | Orders                   | username              | password   |
      | 69c92f18f86ba51a6533e98a | sanimondal7@gmail.com | Dexter@456 |
