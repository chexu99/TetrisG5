Feature: Show stopwatch with time left till random ficha appear

  Scenario: Show the time left till the ransom ficha fall
    Given Im in game screen
    When I am playing the game
    Then The stopwatch has to show the time left until next random shape