package scala.katas

import org.scalatest.{Matchers, FlatSpec}
/**
  * For a given natural number greater than zero return:
  * "fizz" if the number is dividable by 3
  * "buzz" if the number is dividable by 5
  * "fizzbuzz" if the number is dividable by 15
  * the same number if no other requirement is fulfilled
  *
  * https://technologyconversations.com/2014/01/09/scala-tutorial-through-katas-fizz-buzz-easy/
  * -- Gino --
  */
class FizzBuzzTest extends FlatSpec with Matchers {

  "FizzBuzz" should "return fizz if the number is divisible by 3" in {
    FizzBuzz.getResult(3) should be ("fizz")
    FizzBuzz.getResult(27) should be ("fizz")
  }

  "FizzBuzz" should "return buzz if the number is divisible by 5" in {
    FizzBuzz.getResult(5) should be ("buzz")
    FizzBuzz.getResult(200) should be ("buzz")
  }

  "FizzBuzz" should "return fizzbuzz if the number is divisible by 5 and 3" in {
    FizzBuzz.getResult(15) should be ("fizzbuzz")
    FizzBuzz.getResult(0) should be ("fizzbuzz")
    FizzBuzz.getResult(90) should be ("fizzbuzz")
  }

  "FizzBuzz" should "return the same number otherwise" in {
    FizzBuzz.getResult(2) should be ("2")
    FizzBuzz.getResult(1) should be ("1")
    FizzBuzz.getResult(43) should be ("43")
  }

}

