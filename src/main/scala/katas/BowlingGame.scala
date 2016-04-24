package katas

/**
  * Created by gino on 24/04/2016.
  */
class BowlingGame {

  var frames = List[Frame]()

  def roll(pins: Int) {
    if (pins > 10) throw new MorePinsThanMaxException("As far as I know, frames only have 10 pins dude")
    if (pins < 0 ) throw new IllegalPinsValueException(s"Can't roll the number of pin ${pins}")
    if (frames.size > 0 && !frames.last.frameFinished) {
      frames.last.roll2 = pins
      if (frames.last.sum > 10) throw new MorePinsThanMaxException("The maximum is 10, even if you do it on several rolls")
      frames.last.frameFinished = true
    } else {
      frames = frames :+ Frame(pins)
    }
  }

  def score: Int = {
    def sumScore(framesLeft: List[Frame], total: Int): Int = {
      var tempTotal = framesLeft.head.sum
      if (framesLeft.tail != Nil && framesLeft.head.sum == 10) {
        tempTotal += framesLeft(1).roll1
        if (framesLeft.head.strike) {
          if (!framesLeft(1).strike) tempTotal += framesLeft(1).roll2
          else tempTotal += framesLeft(2).roll1
        }
      }
      if (!framesLeft.tail.isEmpty && (frames.size - framesLeft.tail.size) < 10) sumScore(framesLeft.tail, total + tempTotal)
      else total + tempTotal
    }
    sumScore(frames, 0)
  }

  case class Frame(roll1: Int, var roll2: Int = 0) {
    def strike = roll1 == 10
    var frameFinished: Boolean = false
    if (strike) frameFinished = true
    def sum = roll1 + roll2
  }

}

class MorePinsThanMaxException(msg: String) extends Exception

class IllegalPinsValueException(msg: String) extends Exception