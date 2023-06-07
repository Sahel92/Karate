@Regression
Feature: Retail Address Api Features

  Background: getting authorization for using the api
    Given url appUrl
    * def tokenRetail = callonce read("getToken.feature")
    * def token = tokenRetail.response.accessToken
    * header x-access-token = token
    * def addressId = read('file:./target/address.json')

  @postAddress
  Scenario: Post Address Tek Retail API
    * def generateData = Java.type("test.DataGenerator")
    * def country = generateData.getCountry()
    * def fullName = generateData.getFullName()
    * def phoneNumber = generateData.getPhoneNumber()
    * def street = generateData.getStreet()
    * def apartment = generateData.getApartment()
    * def city = generateData.getCity()
    * def state = generateData.getState()
    * def zipCode = generateData.getZipCode()
    * def requestBody = read('request.json')
    * path "/address"
    * request requestBody      
    * method post
    * status 200


  @getAddress
  Scenario: get address API test
    * path "/address"
    * method get
    * status 200
    * print response[0].id
    * karate.write(response,'address.json')

  @putAddress
  Scenario: put (update) address API test
    * def id = addressId[0].id
    * def generateData = Java.type("test.DataGenerator")
    * def country = generateData.getCountry()
    * def fullName = generateData.getFullName()
    * def phoneNumber = generateData.getPhoneNumber()
    * def street = generateData.getStreet()
    * def apartment = generateData.getApartment()
    * def city = generateData.getCity()
    * def state = generateData.getState()
    * def zipCode = generateData.getZipCode()
    * path "/address/"+id
    * param id = id
    * def requestBody = read('request.json')
    * request requestBody
    * method put
    * status 200

  @deleteAddress
  Scenario: delete Address
    * def id = addressId[1].id
    * path "/address/"+id
    * param id = id
    * method delete
    * status 200
