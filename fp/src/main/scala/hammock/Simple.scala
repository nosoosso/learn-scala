package hammock

import cats.effect.IO
import hammock._
import hammock.apache.ApacheInterpreter

object Simple extends App {
  implicit val interpreter = ApacheInterpreter[IO]

  val response = Hammock
    .request(Method.GET, uri"https://api.fidesmo.com/apps", Map())
    .exec[IO]
}
