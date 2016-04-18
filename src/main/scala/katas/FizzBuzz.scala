package scala.katas


/**
  * Created by gino on 18/04/2016.
  */
object FizzBuzz  {

  def getResult(number: Int): String = (number % 3, number % 5) match {
    case (0, 0) => "fizzbuzz"
    case (0, _) => "fizz"
    case (_, 0) => "buzz"
    case _ => number.toString()
  }

}

