object IteratorTransformDemo extends App {
  val s1 = Set(1,2,3).iterator.map(_ * 2).filter(_ < 5).to[Set]
  println(s1)
}
