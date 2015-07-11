import scala.collection.immutable.HashSet

object FilterTest extends App {
  val mm = new org.github.jamm.MemoryMeter()
  val elements = (0 until 1000).map(_.toString).toArray
  val m = HashSet(elements: _*)

  val s1 = elements.scanLeft(m) { case (s,e) => s.filterNot(_ == e) }
  println(s1.map(_.size).mkString(","))
  println(mm.measureDeep(s1))
}
