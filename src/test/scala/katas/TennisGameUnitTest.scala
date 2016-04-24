package katas

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by gino on 24/04/2016.
  */
class TennisGameUnitTest extends FlatSpec with Matchers {

  "Love" should "be description for score 0" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    game.score should be ("love, love")
  }

  "Fifteen" should "be description for score 1" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    game.playerWinBall(sarah)
    game.score should be ("love, fifteen")
  }

  "Thirty" should "be description for score 2" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    game.playerWinBall(victor)
    game.playerWinBall(victor)
    game.playerWinBall(sarah)
    game.score should be ("thirty, fifteen")
  }

  "Forty" should "be description for score 3" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    (1 to 3).foreach(x =>  game.playerWinBall(victor))
    game.score should be ("forty, love")
  }

  "CantScoreIfGameIsOverException" should "be raised if a player try to score after finishing" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    (1 to 4).foreach(x =>  game.playerWinBall(victor))
    intercept[CantScoreIfGameIsOverException] {
      game.playerWinBall(victor)
    }
    intercept[CantScoreIfGameIsOverException] {
      game.playerWinBall(sarah)
    }
  }

  "PlayerNotInGameException" should "be raised if a player not in the game tries to win a ball" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    intercept[PlayerNotInGameException] {
      game.playerWinBall("Paco")
    }
  }

  "Advantage" should "describe when least three points have been scored by each side and a player has one more point than his opponent" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    (1 to 3).foreach(x => game.playerWinBall(victor))
    (1 to 4).foreach(x => game.playerWinBall(sarah))
    game.score should be ("advantage Sarah")
  }

  "Deuce" should "be description when at least three points have been scored by each player and the scores are equal" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    (1 to 3).foreach(x => game.playerWinBall(victor))
    (1 to 3).foreach(x => game.playerWinBall(sarah))
    game.score should be ("deuce")
    game.playerWinBall(victor)
    game.score should not be "deuce"
    game.playerWinBall(sarah)
    game.score should be ("deuce")
  }

  "Game" should "be won by the first player to have won at least four points in total and with at least two points more than the opponent" in {
    val victor = "Victor"
    val sarah = "Sarah"
    val game = new TennisGame(victor, sarah)
    (1 to 3).foreach(x => game.playerWinBall(victor))
    (1 to 3).foreach(x => game.playerWinBall(sarah))
    game.playerWinBall(victor)
    game.score should not be "Victor won"
    game.score should not be "Sarah won"
    game.playerWinBall(victor)
    game.score should be ("Victor won")
  }

}
