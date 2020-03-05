package compose

import cats._
import cats.implicits._

object Main extends App {
  // https://gist.github.com/xuwei-k/3769691
  def functorCompose[M[_], N[_]](implicit mx: Functor[M], nx: Functor[N]): Functor[({ type λ[α] = M[N[α]] })#λ] = {
    new Functor[({ type λ[α] = M[N[α]] })#λ] {
      override def map[A, B](fa: M[N[A]])(f: A => B): M[N[B]] =
        mx.map(fa)((na: N[A]) => nx.map(na)(f))
    }
  }

  println(functorCompose[List, Option].map(List(Some(1), Some(2), None))(i => i + 1))
}
