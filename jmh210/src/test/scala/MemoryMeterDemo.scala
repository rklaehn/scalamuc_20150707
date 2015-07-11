import scala.collection.immutable.HashSet

object MemoryMeterDemo extends App{
  val mm = new org.github.jamm.MemoryMeter()
  mm.measureDeep(Array(1,2,3,4))

  val a = HashSet(0 until 1000:_*)
  val b = a.filter(_ => true)
  require(a eq b)
  println(mm.measureDeep(a))
  println(mm.measureDeep(b))
  println(mm.measureDeep((a,b)))
}
