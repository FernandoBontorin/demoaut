# language: en
Feature: Ticket Reserve

  Background: Logged
    Given a URL
    And login screen of application
    And input some valid user
    When input his password
    Then password is filled
    When try Sing-in
    Then should go to Flight Finder screen

  Scenario: Reserve 1 Ticket
    Given I am logged
    When select city from
    And select city in
    And select postpone data
    And select class
    When select passengers 1
    Then should passengers are 1
    When click on flight finder continue button
    Then should appears select flight screen
    When select flight option
    Then should flight is selected
    When click on select flight continue button
    Then should appears book a flight
    And summary with select options
    When fill first name
    And fill last name
    And fill cred card bank number
    When fill passenger name
    Then passenger name is the same name
    When click on Secure Purchase
    Then should receive some order

