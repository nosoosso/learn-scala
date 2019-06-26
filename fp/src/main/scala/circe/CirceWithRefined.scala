package circe

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import io.circe.refined._

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.string._

object CirceWithRefined extends App {

  case class Person(age: Int Refined Positive)

  val validJson = """{ "age": 30 }"""

  val decoded1 = decode[Person](validJson)
  println(decoded1)

  val invalidJson = """{ "age": -2 }"""

  val decoded2 = decode[Person](invalidJson)
  println(decoded2)
}
