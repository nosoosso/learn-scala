trait Monoid[A] {
  def append(x: A, y: A): A

  def zero(): A

  def sum(seq: Seq[A]): A = seq.foldLeft(zero())((x, y) => append(x, y))
}

class ReportRow(val click: Int, val view: Int) {
  def plus(that: ReportRow): ReportRow = new ReportRow(this.click + that.click, this.view + that.view)

  override def toString: String = s"ReportRow(${this.click}, ${this.view})"
}


object ReportRow {
  def sumRows(rows: Seq[ReportRow]): ReportRow = rows
    .foldLeft(new ReportRow(0, 0))((current, total) => current.plus(total))

  val reportRowMonoid: Monoid[ReportRow] = new Monoid[ReportRow] {
    override def append(x: ReportRow, y: ReportRow): ReportRow = new ReportRow(x.click + y.click, x.view + y.view)

    override def zero(): ReportRow = new ReportRow(0, 0)
  }
}

object MonoidMain {
  def fetchReports(): Seq[ReportRow] = Seq(new ReportRow(3, 10), new ReportRow(20, 30))

  def main(args: Array[String]): Unit = {
    val fetched = fetchReports()
    val result = ReportRow.reportRowMonoid.sum(fetched)

    println(result)
  }
}

