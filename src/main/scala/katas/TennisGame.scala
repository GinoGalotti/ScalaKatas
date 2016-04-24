package katas

/**
  * Scores from zero to three points are described as “love”, “fifteen”, “thirty”, and “forty” respectively.
    If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is “advantage” for the player in the lead.
    If at least three points have been scored by each player, and the scores are equal, the score is “deuce”.
    A game is won by the first player to have won at least four points in total and at least two points more than the opponent.

    I've made some improvements as well like:
      User can't score if the game is finished

  * https://technologyconversations.com/2014/01/16/scala-tutorial-through-katas-tennis-game-easy/
  * -- Gino --
  */
class TennisGame(player1Name: String, player2Name: String) {

  var finished: Boolean = false
  val player1: Player = Player(player1Name)
  val player2: Player = Player(player2Name)

  def score = {
    if (isMatchFinished()) {
      finished = true
      leadPlayer.name + " won"
    } else if (player1.score >= 3 && player2.score >= 3) {
        if (player1.score == player2.score) "deuce"
        else "advantage " + leadPlayer.name
    } else player1.scoreDescription + ", " + player2.scoreDescription
  }

  private def isMatchFinished(): Boolean = {
     (player1.score > 3 || player2.score > 3) && (Math.abs(player2.score - player1.score) >= 2)
  }

  private def leadPlayer = if (player1 > player2) player1 else player2

  def playerWinBall(playerName: String) {
    if (finished) throw new CantScoreIfGameIsOverException(s"Player ${playerName} can't score if the game is finished")

    if (playerName == player1.name) player1.winBall
    else if (playerName == player2.name) player2.winBall
    else throw new PlayerNotInGameException(s"Player ${playerName} is not in the game")

    finished = isMatchFinished()
  }

  /**
    * Instead of the standard solution having Player outside the class and increasing the score from the tests,
    * for me this makes more sense as the game should handle the logic of winning balls
    * @param name
    */
  class Player(val name: String) extends Ordered[Player] {
    val pointsDescription = Array("love", "fifteen", "thirty", "forty")
    var score = 0

    def scoreDescription = pointsDescription(score)

    def compare(that: Player) = this.score - that.score

    def winBall {
      score += 1
    }
  }

  object Player {
    def apply(name: String) = {
      new Player(name)
    }
  }
}




class PlayerNotInGameException(msg: String) extends Exception

class CantScoreIfGameIsOverException(msg: String) extends Exception