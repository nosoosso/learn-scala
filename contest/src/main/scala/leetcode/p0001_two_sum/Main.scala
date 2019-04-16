package leetcode.p0001_two_sum

object Main extends App {
  //  val r = Solution.slowTwoSum(Array(2, 7, 11, 15), 9)
  //  println(r.mkString(","))

    val r = Solution.bruteForce(Array(2, 7, 11, 15), 9)
    println(r.mkString)

}

object Solution {
  def slowTwoSum(nums: Array[Int], target: Int): Array[Int] = {
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

  def bruteForce(nums: Array[Int], target: Int): Array[Int] = {
    var r = Array[Int]()
    for (i <- nums.indices) {
      for (j <- (i + 1) until nums.length) {
        if (nums(j) == target - nums(i)) {
          r = Array(i, j)
        }
      }
    }
    r
  }
}
