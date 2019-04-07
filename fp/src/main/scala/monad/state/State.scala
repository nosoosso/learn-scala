package monad.state

import cats.data.State

object StateMain extends App {
  val transformed = Human.toTanaka.run(Human("Yamada", 21, "man")).value
  println(transformed)
}

case class Human(name: String, age: Int, sex: String) {}

object Human {
  def setName(newName: String): State[Human, Unit] = {
    for {
      oldHuman <- State.get[Human]
      newHuman = oldHuman.copy(name = newName)
      _ <- State.set(newHuman)
    } yield ()

    // 次のようにしてもよい
    // State.modify((h: Human) => h.copy(name = newName))
  }

  def setAge(newAge: Int): State[Human, Unit] = {
    State.modify((h: Human) => h.copy(age = newAge))
  }

  def toTanaka: State[Human, Unit] = {
    for {
      _ <- setName("Tanaka")
      _ <- setAge(56)
    } yield () // ここを renamed に変更すると名前だけ変わる
  }
}
