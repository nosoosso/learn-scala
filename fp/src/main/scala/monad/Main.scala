package monad

import cats._

object Main extends App {
  def stateMonad: Unit = {}

  def desugar(): Unit = {
    // この行をREPLに貼り、Tab補完する
    for { a <- Some(1); b <- Some(2); c <- Some(3) } yield a + b + c // print

    Some(1).flatMap { a => Some(2).flatMap { b => Some(3).map { c => a.+(b).+(c) } } }
  }
}
