package cats

import cats._
import cats.implicits._
import cats.data.NonEmptyList

object Main extends App {
  val list = NonEmptyList("a", List("b", "c"))

  println(List(1, 2, 3, 4, 5, 6).combineAll)
  println(List("a", "b", "c", "d").combineAll)
  println(List(Set(1, 2), Set(2, 3)).combineAll)
}
