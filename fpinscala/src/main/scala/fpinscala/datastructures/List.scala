package fpinscala.datastructures

import scala.annotation.tailrec

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](as: List[A]) =
    as match {
      case Nil         => throw new Exception
      case Cons(_, xs) => xs
    }

  def setHead[A](as: List[A], a: A) = {
    as match {
      case Nil         => throw new Exception
      case Cons(_, xs) => Cons(a, xs)
    }
  }

  @tailrec
  def drop[A](l: List[A], n: Int): List[A] =
    if (n == 0) l
    else
      l match {
        case Nil         => Nil
        case Cons(_, xs) => drop(xs, n - 1)
      }

  @tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
    l match {
      case Nil => Nil
      case Cons(x, xs) =>
        if (f(x)) dropWhile(xs, f)
        else l
    }

  def init[A](l: List[A]): List[A] = {
    l match {
      case Nil          => throw new Exception
      case Cons(_, Nil) => Nil
      case Cons(h, t)   => Cons(h, init(t))
    }
  }

  def init2[A](l: List[A]): List[A] = {
    import collection.mutable.ListBuffer
    val buf = new ListBuffer[A]
    def go(cur: List[A]): List[A] = cur match {
      case Nil          => throw new Exception
      case Cons(_, Nil) => List(buf.toList: _*)
    }
  }

}
