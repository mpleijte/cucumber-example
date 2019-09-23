@browser
Feature: Can I use the reisplanner?
  We want to know if the basic functionality works

  Scenario: Plan a trip between two Dutch cities
    Given I am on the Reisplanner homepage
    When  I choose to travel FROM "Rotterdam Centraal"
    And   I choose to travel TO "Amsterdam Centraal"
    And   I click ON "Plannen"
    Then  the page shows reisadvies with following information:
    | field           | value                 |
    | vertrek         | Rotterdam Centraal    |
    | aankomst        | Amsterdam Centraal    |

