package leetcode.p0002_add_two_numbers

import scala.collection.mutable

object Main extends App {
}

object Solution {
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    def toInt(l: Seq[Int]): Int = {
      var result: Int = 0
      l.zipWithIndex.foreach { case (numStr, index) =>
        val num = numStr.toInt
        result += num * math.pow(10, index).toInt
      }
      result
    }

    val l1num = toInt(ListNode.toSeq(l1))
    val l2num = toInt(ListNode.toSeq(l2))

    val sum = l1num + l2num
    val sumSeq = sum.toString.toSeq.map(c => c.toInt).reverse

    ListNode.fromSeq(sumSeq)
  }

  def hoge(l1: ListNode, l2: ListNode): ListNode = {
    var currentL1 = l1
    var currentL2 = l2
    var kuriagari = 0
    var result = new ListNode(0)

    while (currentL1 != null && currentL2 != null) {
      val l1Val = if (currentL1 != null) currentL1.x else 0
      val l2Val = if (currentL2 != null) currentL2.x else 0

      val added = l1Val + l2Val
      val (ten, one) = (added / 10, added % 10)

      currentL1 = currentL1.next
      currentL2 = currentL2.next
      kuriagari = ten
    }

    new ListNode()
  }

}

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

object ListNode {
  def toSeq(l: ListNode): mutable.Seq[Int] = {
    var current = l
    val result = mutable.Seq[Int]()
    while (current != null) {
      result :+ current.x
      current = current.next
    }
    result
  }

  def fromSeq(seq: Seq[Int]): ListNode = {
    var result = new ListNode(0)
    seq.reverse.zipWithIndex.foreach {
      case (num, index) =>
        if (index == 0) {} else {}
        val n = new ListNode(0)
      //        n.x = i
    }

    List(1)

    ???
  }
}
