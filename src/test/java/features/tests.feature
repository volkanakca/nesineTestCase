@Regression
Feature: Nesine Test Cases


  Scenario: Login to nesine with a valid user and get popular football bets
    Given I login "volkan" member
    And I visit header "popularBets" tabs
    Then Check header at "popularBets" page
    And I click futbol bets
    And I send post request popular bets
    Then The response should have status code 200
    And Verify popular bets ui and backend played count and code
    And I logout nesine


  Scenario: Login to nesine with a valid user and editors page open
    Given I login "volkan" member
    And I visit header "editors" tabs
    Then Check header at "editors" page




