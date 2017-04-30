# GoFish

This was a bit more complicated, but basically it's just a text game of GoFish with played between simple AIs who randomly pick cards. I left open the functionality of being able to create smarter AIs/human players.

Moves are decided and the game is run via the Game Engine class. This regulates moves and determines the game logic, so that the game can be played. Moves in the game are determined play Play Objects. PlayerInfo is a helper class used mainly for the processing and recording of moves by the GameEngine.

Deck/Card classes are self explanatory.

PlayerStrategy interface so that I can make multiple PlayerStrategies should I choose.  
