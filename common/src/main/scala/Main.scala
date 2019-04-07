import scala.annotation.tailrec

//object Main extends App {
//  val input =
//    """3
//      |1
//      |apple
//      |10 10
//      |wwwwwwwwww
//      |sassssssss
//      |gpgggggggg
//      |tptttatttt
//      |ylyyypyyyy
//      |ueuuupuuuu
//      |tttttltttt
//      |uuuuueuuuu
//      |kkkkkkkkkk
//      |nnnnnnnnnn
//      |2
//      |apple orange
//      |10 50
//      |jfoaeoiairjeoajr9a394u3a9ur9jaojorijoajroj3oajroja
//      |ejoajro93alj3paipkrpa3i3porjtj3airo3ajrojotjoajt3a
//      |nonpanueiriiau3irhihh39a8r9au9u39aajoa3jr93arj9ajr
//      |jofp9aofjaojfjoaj94au9jojooaljflajjn3au3a0ikappaar
//      |eaolropjrioajorroajoijroaroifjoajojagj30ojojaiojof
//      |eoaeor0p9a0890a80joaori30ai30rioaraopepraoiiroi3pa
//      |joefaijolairoanru03aroajinrioajiojrojoaj32o2ououor
//      |malfjfoihe39uogiorjoj3ojrgjo3jofjoaj90380840qjoijr
//      |oeauroaurioajieoa3rioaurue3aurioaoriaoruoia3uioruo
//      |893j9jrj9a4iooitu9autu8a9utajgojnboajrrjaojroai3rj
//      |3
//      |apple orange grape
//      |10 50
//      |ejoojoru3a90r79aurauour93u9ur9au9ur9ua9r3u9auru9u3
//      |jgro3au93raorj3u8au9gojp3epaut90uoajaljf0oaur9apgj
//      |jfraofyigrufoaijfipjojaoijotajoifjpaijfojaeojfojao
//      |jfoaaurauroijoajfoapifeaurituaour0p0qouroiojfljofa
//      |jofypuoruaaojfajgao3la98ur0u0jfjaolfljaljfoajforoj
//      |fjoaeeo3uarpaojfojaojeiejaofjoajfoejtou0aipgk9aaro
//      |nmaoj3oruy9aeyutoafjajfoajojtaojfojatojfoiajofjnoj
//      |jaorj9au3r9uajoirfjaiojr03au9ru9aujfojajra0ur09gao
//      |jajroau8ru9oajiojtp39t09uojgajlnhlaj5rjapu38797e9a
//      |mfoauir090ojjaoijr8u3auorjoajrao9u3ruajofjaojfpare
//    """.stripMargin
//
//  val probLines = input.split("\n").toSeq
//
//  type Problem = (Int, Seq[String], (Int, Int), Seq[String])
//  type Problems = Seq[Problem]
//
//  val problems: Problems = parse(probLines.tail)
//
//  println(problems.mkString)
//
//  def parse(text: Seq[String]): Problems = {
//    @tailrec
//    def go(text: Seq[String], index: Int, result: Problems): (Problems, Int) = {
//      if (index >= text.length - 1) {
//        (result, index)
//      } else {
//        val wordNum = text(index).toInt
//        val words: Seq[String] = text(index + 1).split(" ").toSeq
//        val heightAndWidthArray = text(index + 2).split(" ")
//        val heightAndWidth = (heightAndWidthArray(0).toInt, heightAndWidthArray(1).toInt)
//
//        val searchText = text.slice(index + 3, index + 3 + heightAndWidth._1)
//
//        val next = result :+ (wordNum, words, heightAndWidth, searchText)
//
//        go(text, index + 3 + heightAndWidth._1, next)
//      }
//    }
//
//    val (problems, _) = go(text, 0, Seq())
//    problems
//  }
//}