package leetcode.p0007_reverse_integer


object Main extends App {
  println(Solution.reverse(1534236469))
}

object Solution {

  import scala.util.Try

  def reverse(x: Int): Int = {
    if (x == 0) {
      0
    } else {
      val str = x.toString
      val (sign, number) = str.partition(c => c == '-')

      val normalizedNum = number.reverse.dropWhile(c => c == '0')


      Try {
        (sign + normalizedNum).toInt
      }.getOrElse(0)
    }
  }
}
