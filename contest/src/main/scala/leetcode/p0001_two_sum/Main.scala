package contest.leetcode.p0001_two_sum

object Main extends App {
  val r = Solution.twoSum(Array(2, 7, 11, 15), 9)
  println(r.mkString(","))
}

object Solution {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val indexed = nums.toStream.zipWithIndex

    val (ra, rb) = (for {
      a <- indexed
      b <- indexed
    } yield (a, b))
      .find { case ((av, ai), (bv, bi)) =>
        ai < bi && av + bv == target
      }
      .get

    Array(ra._2, rb._2)
  }
}



