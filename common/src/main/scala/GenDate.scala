object GenDate extends App {
  val zeroFill: PartialFunction[Int, String] = {
    case i if i < 10 => "0" + i.toString
    case i => i.toString
  }

  val surroundWithQuote = (str: String) => {
    "\'" + str + "\'"
  }

  val process = zeroFill.andThen(surroundWithQuote)

  val month = (1 to 12)
    .map(process)
    .mkString(",")
  println(month)

  val day = (1 to 31)
    .map(process)
    .mkString(",")
  println(day)
}

