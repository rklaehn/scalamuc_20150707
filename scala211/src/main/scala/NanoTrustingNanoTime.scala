object NanoTrustingNanoTime extends App {
  def test() = 0
  val t0 = System.nanoTime()
  test()
  val dt = System.nanoTime() - t0
}



