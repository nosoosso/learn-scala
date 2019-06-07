package refined

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.string._

object Main extends App {
  type UrlString = String Refined Url

  def isHttps(url: UrlString) = {
    url.startsWith("https://")
  }

  println(isHttps("http://example.com"))
  println(isHttps("https://example.com"))

  // compilation error
  //  val a = "hoge"
  //  println(isHttps(a))


}
