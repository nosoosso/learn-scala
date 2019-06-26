package refined

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.boolean.AllOf
import eu.timepit.refined.collection.MaxSize
import eu.timepit.refined.numeric._
import eu.timepit.refined.string._
import shapeless.{HNil, ::}

object Main extends App {
  def shotUrl: Unit = {
    type ShortUrl = String Refined AllOf[Url :: MaxSize[W.`20`.T] :: HNil]

    val valid: ShortUrl = "http://example.com"
    //    val notUrl: ShortUrl = "hogehogehoge"
    //    val tooLongUrl: ShortUrl = "http://goooooooooooooooogle.com"

  }

  def url: Unit = {
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

}
