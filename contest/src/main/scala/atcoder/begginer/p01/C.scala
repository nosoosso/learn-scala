package atcoder.begginer.p01

object C {
  val sc = new java.util.Scanner(System.in)

  val deg, dis = sc.nextInt()

  def windPower(dis: Int): Int = {
    dis * 10 / 60
  }

  def dir(deg: Int): String = {
    var count = 0
    var remain = deg
    while (remain > 0) {
      remain = remain - 225
      count = count + 1
    }
    count match {
      case 0 | 1 => "N"
    }
  }
}
