@Regression
Feature: payments API testing

  Background: getting authorization for using the api
    Given url appUrl
    * def tokenRetail = callonce read("getToken.feature")
    * def token = tokenRetail.response.accessToken
    * header x-access-token = token

  @getPayment
  Scenario: get Payment methods API test
    * path "/payment"
    * method get
    * status 200
    * karate.write(response,'payment.json')

  @createPayment
  Scenario: post payment card Tek Retail API test
    * def data = Java.type("test.DataGenerator")
    * def cardNumber = data.getCardNumber()
    * def nameOnCard = data.getNameOnCard()
    * def expirationMonth = data.getExpMonth()
    * def expirationYear = data.getExpYear()
    * def securityCode = data.getCCV()
    * def requestBody = read('cardInfo.json')
    * path "/payment"
    * request requestBody
    * method post
    * status 200

  @updatePayment
  Scenario: update payment card Tek Retail API Test
    * def paymentId = read('file:./target/payment.json')
    * def data = Java.type("test.DataGenerator")
    * def cardNumber = data.getCardNumber()
    * def nameOnCard = data.getNameOnCard()
    * def expirationMonth = data.getExpMonth()
    * def expirationYear = data.getExpYear()
    * def securityCode = data.getCCV()
    * def id = paymentId[0].id
    * path "/payment/"+id
    * param id = id
    * def requestBody = read('cardInfo.json')
    * request requestBody  
    * method put
    * status 200

  @deletePayment
  Scenario: delete payment Tek Retail API Test
    * def paymentId = read('file:./target/payment.json')
    * def id = paymentId[0].id
    * path "payment/"+id
    * param id = id
    * method delete
    * status 200
