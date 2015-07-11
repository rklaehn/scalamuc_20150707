import scala.collection.immutable.HashSet

object FilterStructuralSharing extends App {
  val mm = new org.github.jamm.MemoryMeter()
  val a = HashSet(0 until 1000:_*)
  val b = a.filter(_ => true)
  require(a eq b)
  println(mm.measureDeep(a))
  println(mm.measureDeep(b))
  println(mm.measureDeep((a,b)))
}
