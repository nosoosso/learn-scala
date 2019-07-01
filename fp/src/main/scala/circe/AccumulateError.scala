package circe

import io.circe._
import io.circe.generic.semiauto._

object AccumulateError extends App {

  case class Company(employees: Seq[Person])

  case class Person(name: String)

  implicit val decodePerson = deriveDecoder[Person]
    .ensure(i =>
      (if (i.name.nonEmpty) Nil else List("name is required")) ++
        (if (i.name.length <= 10) Nil else List("name is too long")) ++
        (if (!i.name.matches(".*!.*")) Nil else List("! is not allowed"))
    )

  implicit val decodeCompany = deriveDecoder[Company]
    .ensure(i =>
      if (i.employees.length < 4) Nil else List("too many employees")
    )

  // "too many employees" は表示されない
  val jsonString1 =
    """{"employees": [{"name": "yamada"}, {"name": "ito"}, {"name": "tanakaaaaaaaaaaaaaaaa!"}, {"name": ""}]}"""
  // Invalid(NonEmptyList(DecodingFailure(name is too long, List(MoveRight, MoveRight, DownArray, DownField(employees))), DecodingFailure(! is not allowed, List(MoveRight, MoveRight, DownArray, DownField(employees))), DecodingFailure(name is required, List(MoveRight, MoveRight, MoveRight, DownArray, DownField(employees)))))
  println(parser.decodeAccumulating[Company](jsonString1))

  // "too many employees" が表示される
  val jsonString2 =
    """{"employees": [{"name": "yamada"},{"name": "yamada"},{"name": "yamada"},{"name": "yamada"}]}"""
  // Invalid(NonEmptyList(DecodingFailure(too many employees, List())))
  println(parser.decodeAccumulating[Company](jsonString2))

}
