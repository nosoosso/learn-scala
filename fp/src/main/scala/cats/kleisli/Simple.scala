package cats.kleisli

import cats._
import cats.implicits._
import cats.data.Kleisli

import scala.util.Try

object Simple extends App {

  object OptionExample {
    def parseInt(a: String): Option[Int] = Try(a.toInt).toOption

    def positiveOpt(i: Int): Option[Int] = if (i > 0) Some(i) else None

    val parseIntKleisli = Kleisli(parseInt)
    val positiveOptKleisli = Kleisli(positiveOpt)

    val composed = parseIntKleisli andThen positiveOptKleisli

    println(composed.run("hogee"))
    println(composed.run("-123"))
    println(composed.run("123"))
  }

  object ListExample {
    def pair: Kleisli[List, Int, Int] = Kleisli(a => List(a, a))

    def addOnePair: Kleisli[List, Int, Int] = Kleisli(a => List(a, a + 1))

    val composed = pair andThen addOnePair

    println(composed.run(1))
    println(composed.run(100))
  }

  OptionExample
  println("------")
  ListExample

}
