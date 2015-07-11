import scala.collection.immutable.HashSet

object CountFilterAllocations extends App {

  val s = HashSet(0L until 1000L: _*)
  val s1 = ObjectCounter.withCount("scala/collection/immutable/") {
    s.filter(_ < 500L)
  }
}
