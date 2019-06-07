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

object Main extends App {

  case class Person(age: Int Refined Positive, homepage: String Refined Url);

  {
    val json =
      """
        |{
        |  "age": 30,
        |  "homepage": "https://example.com"
        |}
      """.stripMargin

    val decodedFoo = decode[Person](json)
    println(decodedFoo)
  }

  {
    val json =
      """
        |{
        |  "age": -1,
        |  "homepage": "https://example.com"
        |}
      """.stripMargin

    val decodedFoo = decode[Person](json)
    println(decodedFoo)
  }

  {
    val json =
      """
        |{
        |  "age": 18,
        |  "homepage": "hoge"
        |}
      """.stripMargin

    val decodedFoo = decode[Person](json)
    println(decodedFoo)

  }
}
