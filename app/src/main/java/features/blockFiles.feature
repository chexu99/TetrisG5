Feature: Change locked rows color

  Scenario: Locked rows have a different color
    Given Game started
    When Locked rows start to appear
    Then Locked rows must have a different texture