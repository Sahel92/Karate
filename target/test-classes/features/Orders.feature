@Regression
Feature: Orders Tek RetailAPI testing

  Background: getting authorization for using the api
    Given url appUrl
    * def tokenRetail = callonce read("getToken.feature")
    * def token = tokenRetail.response.accessToken
    * header x-access-token = token

  @getAllOders
  Scenario: Get all orders of buyer API Test
    * path "/order"
    * method get
    * status 200

  @postOrder
  Scenario: create a new order API Test
    * path "/order"
    * request
      """
      {
      "addressId": 6774,
      "paymentId": 6091,
      "products": [
      {
        "productId": 1,
        "quantity": 3
      }
      ]
      }
      """
    * method post
    * status 200
    * print response

  @cancelOrder
  Scenario: cancel order API test
    * path "/order/cancel/"+5085
    * param id = 5085
    * method post
    * status 200
    * print response

  @orderAgain
  Scenario: buy an order again Test
    * path "/order/buy-again/"+5085
    * param id = 5085
    * request
      """
      {"addressId": 6744,
      "paymentId": 6091
      
      }
      """
    * method post
    * status 200
    * print response

  @returnOrder
  Scenario: return order API test
    * def data = Java.type("test.DataGenerator")
    * def reason = data.getDropReason()
    * def dropOff = data.getDropOff()
    * path "/order/return/"+5086
    * param id = 5086
    * request
      """
      {
      "reason": "#(reason)",
      "dropOff": "#(dropOff)",
      "productIds": [
       1
      ]
      }
      """
    * method post
 		* status 200
    * print response
    
    
