package monoids

object Mainu extends App {
  println(Monoid.optionMonoid.op(None, None))
  println(Monoid.optionMonoid.op(Some(1), None))
  println(Monoid.optionMonoid.op(None, Some(2)))
  println(Monoid.optionMonoid.op(Some(1), Some(2)))
}
