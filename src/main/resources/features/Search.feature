Feature: Lieferando.com Search Feature

  Background:
    Given I navigate to lieferand.com

  Scenario: Search with a valid postcode
    When I fill the search bar with "10115"
    And I click on submit button
    Then I should navigate to restaurant list page for "10115" successfully

  Scenario: Search with full address including both postcode and street address
    When I fill the search bar with "Spielplatz Kreuzbergstraße, 40489, Düsseldorf"
    And I click on Enter button
    Then I should navigate to restaurant list page for "Spielplatz Kreuzbergstraße, 40489, Düsseldorf" successfully

  Scenario: Search and check if suggestions are relevant
    When I fill the search bar with "airport"
    Then Suggestions should be relevant to search "airport" keyword

  Scenario: Search with a postcode less than 5 digits
    When I fill the search bar with "123"
    And I click on submit button
    Then I should see an error message "The entered postcode is invalid. A valid postcode needs to consist out of 5 digits, for example: 10115."

  Scenario: Search with a postcode more than 5 digits
    When I fill the search bar with "123456"
    And I click on submit button
    Then I should see an error message "The entered postcode is invalid. A valid postcode needs to consist out of 5 digits, for example: 10115."

  Scenario: Search with a postcode that doesn't exist
    When I fill the search bar with "76859"
    And I click on submit button
    Then I should see an error message "The entered postcode does not exist or is not valid. Please check your input and try again."

  Scenario: Search with only postcode and warning "Please enter your street and house number" should be seen
    When I fill the search bar with "10407"
    Then I should see an error warning "Please enter your street and house number"

  Scenario: Search for full address by clicking on "Enter"
    When I fill the search bar with "strasse der"
    And I click on Enter button
    And I should navigate to restaurant list page for "Straße der Pariser Kommune, Berlin" successfully

  Scenario: Search with blank space
    When I fill the search bar with "     "
    Then I should see an error warning "Please enter your street and house number"

  Scenario: Search without filling anything
    When I click on submit button
    Then I should see an error message "The entered postcode is invalid. A valid postcode needs to consist out of 5 digits, for example: 10115."
