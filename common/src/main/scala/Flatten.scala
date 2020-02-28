object Flatten extends App {
  println(Seq(Seq(1, 2, 3), Seq(), Seq(4, 5)).flatten)

  println(Seq(Some(1), None, Some(2)).flatten)

  println(Seq(1, 2, 3, 4).flatMap(n => {
    Seq(n, n)
  }))

  println(Seq(1, 2, 3, 4).map(n => {
    Seq(n, n)
  }))

  println(Seq(Seq("hello", "world"), Seq("good", "morning")).map(s => {
    s.mkString(" ").toUpperCase
  }))

  println(Seq(Some(1), None, Some(2)).flatten)

  println(
    Seq(1, 2, 3, 4, 5, 6, 7, 8)
      .withFilter(_ % 2 == 0)
      .map(_ + 1)
  )

  println(
    Seq(1, 2, 3, 4, 5, 6, 7, 8)
      .collect { case n if n % 2 == 0 => n + 1 }
  )
}
