Name:

TDD TODO/Task list

**Build Tests**

These are for implementing the EscapeGameBuilder's `makeGameManager()` method.

| **#** | Test                                                                                   | Comments             |
| :---------: | :------------------------------------------------------------------------------------- | :------------------- |
|      1      | Build game manager 2 X 2, 2 players, and coordinate type SQUARE                        | create game object   |
|      2      | Use game manager to create a Coordinate                                                | create Coordinate    |
|      3      | Two Coordinates with the same values are equal                                         | compare Coordinates  |
|      4      | 2 X 2 SQUARE, 2 players, and 1 piece                                                   | initialize piece     |
|      5      | 2 X 2 SQUARE, 2 players, and 1 piece each                                              | initialize pieces    |
|      6      | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (move outside grid)                  | first bad move       |
|      7      | 8 X 8 SQUARE, 2 players, 1 piece, 1 valid move (snail)                                 | first good move      |
|      8      | 8 X 8 SQUARE, 2 players, 2 pieces, 1 valid move each                                   | first turn           |
|      9      | 8 X 8 SQUARE, 2 players, 2 pieces, move wrong piece                                    | wrong piece          |
|     10     | 8 X 8 SQUARE, 2 players, 2 pieces, 2 moves each                                        | first two turns      |
|     11     | 8 X 8 SQUARE, 2 players, 2 pieces, invalid move then valid move                        | try again            |
|     12     | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (move too far)                        | wrong type           |
|     13     | 8 X 8 SQUARE, 2 players, 1 piece, 1 valid move (triple snail)                          | first long move      |
|     14     | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (distance 0)                          | no distance          |
|     15     | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (space occupied)                      | space occupied       |
|     16     | 8 X 1 SQUARE, 2 players, 2 pieces, 1 invalid move (other piece blocks)                 | blocking piece       |
|     17     | 8 X 1 SQUARE, 2 players, 2 pieces, 1 valid move (fly over piece)                       | above blocking piece |
|     18     | 8 X 1 SQUARE, 2 players, 2 pieces, 1 invalid move (fly onto piece)                     | blocking flight      |
|     19     | Infinite SQUARE, 2 players, 1 piece, 1 invalid move (diag for ortho piece)             | invalid ortho        |
|     20     | Infinite SQUARE, 2 players, 1 piece, 1 valid move (diag for ortho piece dist 2)       | valid ortho          |
|     21     | Infinite SQUARE, 2 players, 1 piece, 1 invalid move (ortho for diag piece)            | invalid diag         |
|     22     | Infinite SQUARE, 2 players, 1 piece, 1 valid move (ortho for diag piece dist 2)      | valid diag           |
|     23     | Infinite SQUARE, 2 players, 1 piece, 1 invalid move (chess knight pattern for linear) | invalid linear       |
|     24     | Infinite SQUARE, 2 players, 2 pieces, 1 invalid move (blocked by piece)               | blocked linear       |
|     25     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (fly over piece)                   | valid linear fly     |
|     26     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (normal linear)                    | valid linear         |
|     27     | Infinite SQUARE, 2 players, 1 piece, 1 valid move (move into negative coords)        | valid negative       |
