@Regression
Feature: products  Tek Retail API testing

  Background: getting authorization for using the api
    Given url appUrl
    * def tokenRetail = callonce read("getToken.feature")
    * def token = tokenRetail.response.accessToken
    * header x-access-token = token

    @getProducts
    Scenario: get all products
    * path "/product"
    * method get
    * status 200
  