package concurrent

object MyThread extends App {
  val thread1 = new Thread1
  thread1.start()
  thread1.join()
}

class Thread1 extends Thread {
  override def run(): Unit = {
    println(s"start task: ${System.currentTimeMillis()}")
    Thread.sleep(1000)
    println(s"end task: ${System.currentTimeMillis()}")
  }
}
