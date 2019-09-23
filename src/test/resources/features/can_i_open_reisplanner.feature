@browser
Feature: Can I open reisplanner?
  We want to know if the homepage of reisplanner can be openend

  Scenario: The reisplanner homepage can be opened succesfully
    Given I am on the Reisplanner homepage
    When  I choose to travel FROM "Rotterdam Centraal"
    And   I choose to travel TO "Amsterdam Centraal"
    And   I click ON "Plannen"
    Then  the page shows reisadvies with following information:
    | field           | value                 |
    | vertrek         | Rotterdam Centraal    |
    | aankomst        | Amsterdam Centraal    |

