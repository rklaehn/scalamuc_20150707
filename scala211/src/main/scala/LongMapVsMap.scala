import scala.collection._

object LongMapVsMap extends App {
  val mm = new org.github.jamm.MemoryMeter()
  val elems = (0L until 1000L).map(_ -> ()).toArray
  val a = mutable.LongMap(elems: _*)
  val b = mutable.Map(elems: _*)
  val size = mm.measureDeep(Array(1,2,3,4))
  println("LongMap n=1000:"+ mm.measureDeep(a))
  println("Map n=1000:    "+ mm.measureDeep(b))
}
