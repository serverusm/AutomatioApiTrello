@Board
Feature: Board

    Background: Setup ApiRequestHandler
        Given I set apiRequestHandler with proper credential

@Board_001 @deleteBoard
Scenario: Crete board
    When I create a board with name "Sergio Boards API cucumber"
    Then I should see field "name" with value "Sergio Boards API cucumber"
    And I validate createBoard response schema