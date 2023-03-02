Name:

TDD TODO/Task list

**Build Tests**

These are for implementing the EscapeGameBuilder's `makeGameManager()` method.

| **#** | Test                                                                                                   | Comments             |
| :---------: | :----------------------------------------------------------------------------------------------------- | :------------------- |
|      1      | Build game manager 2 X 2, 2 players, and coordinate type SQUARE                                        | create game object   |
|      2      | Use game manager to create a Coordinate                                                                | create Coordinate    |
|      3      | Two Coordinates with the same values are equal                                                         | compare Coordinates  |
|      4      | 2 X 2 SQUARE, 2 players, and 1 piece                                                                   | initialize piece     |
|      5      | 2 X 2 SQUARE, 2 players, and 1 piece each                                                              | initialize pieces    |
|      6      | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (move outside grid)                                  | first bad move       |
|      7      | 8 X 8 SQUARE, 2 players, 1 piece, 1 valid move (snail)                                                 | first good move      |
|      8      | 8 X 8 SQUARE, 2 players, 2 pieces, 1 valid move each                                                   | first turn           |
|      9      | 8 X 8 SQUARE, 2 players, 2 pieces, move wrong piece                                                    | wrong piece          |
|     10     | 8 X 8 SQUARE, 2 players, 2 pieces, 2 moves each                                                        | first two turns      |
|     11     | 8 X 8 SQUARE, 2 players, 2 pieces, invalid move then valid move                                        | try again            |
|     12     | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (move too far)                                        | wrong type           |
|     13     | 8 X 8 SQUARE, 2 players, 1 piece, 1 valid move (triple snail)                                          | first long move      |
|     14     | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (distance 0)                                          | no distance          |
|     15     | 8 X 8 SQUARE, 2 players, 1 piece, 1 invalid move (space occupied)                                      | space occupied       |
|     16     | 8 X 1 SQUARE, 2 players, 2 pieces, 1 invalid move (other piece blocks)                                 | blocking piece       |
|     17     | 8 X 1 SQUARE, 2 players, 2 pieces, 1 valid move (fly over piece)                                       | above blocking piece |
|     18     | 8 X 1 SQUARE, 2 players, 2 pieces, 1 invalid move (fly onto piece)                                     | blocking flight      |
|     19     | Infinite SQUARE, 2 players, 1 piece, 1 invalid move (diag for ortho piece)                             | invalid ortho        |
|     20     | Infinite SQUARE, 2 players, 1 piece, 1 valid move (diag for ortho piece dist 2)                       | valid ortho          |
|     21     | Infinite SQUARE, 2 players, 1 piece, 1 invalid move (ortho for diag piece)                            | invalid diag         |
|     22     | Infinite SQUARE, 2 players, 1 piece, 1 valid move (ortho for diag piece dist 2)                      | valid diag           |
|     23     | Infinite SQUARE, 2 players, 1 piece, 1 invalid move (chess knight pattern for linear)                 | invalid linear       |
|     24     | Infinite SQUARE, 2 players, 2 pieces, 1 invalid move (blocked by piece)                               | blocked linear       |
|     25     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (fly over piece)                                   | valid linear fly     |
|     26     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (normal linear)                                    | valid linear         |
|     27     | Infinite SQUARE, 2 players, 1 piece, 1 valid move (move into negative coords)                        | valid negative       |
|     28     | Infinite SQUARE, 2 players, 2 pieces, 2 invalid moves 2 valid moves (different types)                | two types            |
|     29     | 4 X Infinite SQUARE, 2 players, 2 pieces, invalid more then valid move (check mixed bounds)            | mixed bounds         |
|     30     | Infinite SQUARE, 2 players, 2 pieces, 2 valid moves (including exit) then invalid move                 | can exit             |
|     31     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (causing no move result)                            | can not lose         |
|     32     | Infinite SQUARE, 2 players, 2 pieces, 1 invalid move (causing a loss)                                  | can lose             |
|     33     | Infinite SQUARE, 2 players, 4 pieces, 1 turn, player 2 exits, game ends due to turn limit            | can win              |
|     34     | Infinite SQUARE, 2 players, 4 pieces, 1 turn, player 1 exits, game ends due to turn limit           | can timeout          |
|     35     | Infinite SQUARE, 2 players, 4 pieces, 1 turn, nobody exits, game ends due to turn limit             | can tie              |
|     36     | Infinite SQUARE, 2 players, 4 pieces, 1 turn, nobody exits, turn limit, different piece values       | has value            |
|     37     | Infinite SQUARE, 1 clear space, 2 players, 2 pieces, 1 valid move (move onto clear)                   | unobtrusive clear    |
|     38     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (exit), no rules                                   | win by clear         |
|     39     | Infinite SQUARE, 2 players, 2 pieces, 1 invalid move (land on block)                                  | block blocks         |
|     40     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (exit), score limit reached                        | score win            |
|     41     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (exit), score limit passed                         | score win beyond     |
|     42     | Infinite SQUARE, 2 players, 2 pieces, valid then invalid move (unblock, no unblock)                    | can unblock          |
|     43     | Infinite SQUARE, 2 players, 2 pieces, 1 valid move (fly over block)                                   | fly over block       |
|     44     | Infinite HEX, 2 players, 2 pieces, valid move then invalid move                                        | first hex omni       |
|     45     | Infinite HEX, 2 players, 2 pieces, valid move then invalid move                                       | first hex linear     |
|     46     | 4 X 4 HEX, 2 players, 2 pieces, valid move then invalid move                                          | hex bounded          |
|     47     | 4 X Infinite HEX, 2 players, 2 pieces, valid move then invalid move                                   | hex semi bounded     |
|     48     | 4 X 4 SQUARE, 2 players, 2 pieces, 1 valid move, results in win by trapping player 2                   | end if stuck         |
|     49     | 4 X 4 SQUARE, 2 players, 2 pieces, 1 valid move, results in none by untrapping player 2                | can unstick          |
|     50     | 4 X 4 SQUARE, 2 players, 2 pieces, invalid move and observer notified                                 | observer invalid     |
|     51     | 4 X 4 SQUARE, 2 players, 2 pieces, valid move and observer not notified                               | observer valid       |
|     52     | 4 X 4 SQUARE, 2 players, 2 pieces, valid move (win) and observer notified                             | observer win         |
|     53     | 4 X 4 SQUARE, 2 players, 2 pieces, 2 valid moves, game ends in draw and observer notified             | observer draw        |
|     54     | 4 X 4 SQUARE, 2 players, 2 pieces, 1 valid move onto player 2 and loses (point conflict, no score)     | conflict win         |
|     55     | 4 X 4 SQUARE, 2 players, 4 pieces, 1 valid move onto player 2, loses their conflict                    | conflict casualty    |
|     56     | 4 X 4 SQUARE, 2 players, 4 pieces, conflict loss, then exit, score limit missed due to conflict       | conflict penalty     |
|     57     | 4 X 4 SQUARE, 2 players, 4 pieces, same moves as above, then 2 valid, win on time (all 3 rules)        | all rules           |
|     58     | 4 X 4 SQUARE, 2 players, 4 pieces, 1 valid move onto player 2, loses their conflict, observer          | conflict observer    |
|     59     | 4 X 4 SQUARE, 2 players, 4 pieces, 1 valid move onto player 2, conflict tie                            | conflict tie         |
|     60     | 4 X 4 SQUARE, 2 players, 2 pieces, 1 valid move onto player 2 and loses (point conflict, no score)     | sacrifice win        |
|     61     | 4 X 4 SQUARE, 2 players, 4 pieces, player 2 loses conflict, player 1 scores, player 2 scores (3 rules) | comeback win         |
|     62     | 4 X 4 SQUARE, 2 players, 2 pieces, player 1 wins conflict, loses (no score), linear movement          | combat linear        |
|     63     | Infinite SQUARE, 2 players, 2 pieces, linear jump                                                      | linear jump success  |
|     64     | Infinite SQUARE, 2 players, 2 pieces, no jump over block                                               | blocked jump         |
|     65     | Infinite SQUARE, 2 players, 2 pieces, blocked landing for jump                                         | blocked landing      |
|            |                                                                                                        |                      |
