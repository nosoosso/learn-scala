package contest.atcoder.begginer.p01

object B extends App {
  val sc = new java.util.Scanner(System.in)

  val a = sc.nextInt()

  if (a < 100) {
    println("00")
  } else if (100 <= a && a <= 5000) {
    val result = toKilometer(a * 10) match {
      case b if b < 10 => "0" + b.toString
      case default => default.toString
    }
    println(result)
  } else if (6000 <= a && a <= 30000) {
    println(toKilometer(a + 50000))
  } else if (35000 <= a && a <= 70000) {
    println(toKilometer((a - 30000) / 5 + 80000))
  } else {
    println(89)
  }

  def toKilometer(meter: Int) = {
    meter / 1000
  }
}
