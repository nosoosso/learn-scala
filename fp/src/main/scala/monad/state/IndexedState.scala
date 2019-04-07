package monad.state

import cats.Eval
import cats.data.IndexedStateT

object IndexedStateMain extends App {
  Door.valid.runA(Closed)
}

sealed trait DoorState

case object Open extends DoorState

case object Closed extends DoorState

case class Door(state: DoorState)

object Door {
  def open: IndexedStateT[Eval, Closed.type, Open.type, Unit] = IndexedStateT.set(Open)

  def close: IndexedStateT[Eval, Open.type, Closed.type, Unit] = IndexedStateT.set(Closed)

//  val invalid = for {
//    _ <- open
//    _ <- close
//    _ <- close
//  } yield ()

  val valid = for {
    _ <- open
    _ <- close
    _ <- open
  } yield ()
}
