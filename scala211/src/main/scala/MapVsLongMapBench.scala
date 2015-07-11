import scala.collection.mutable.{HashMap, LongMap}

object MapVsLongMapBench extends App {

  val size = 10000L
  val elems = (0L until size).map(_ -> ()).toArray
  val a = LongMap(elems: _*)
  val b = HashMap(elems: _*)
  val mid = size / 2

  val th = ichi.bench.Thyme.warmed(warmth = Thyme.HowWarm.BenchOff, verbose = println)
  th.pbenchOffWarm("Lookup LongMap vs. HashMap")(
    th.Warm(a(mid)))(
    th.Warm(b(mid)))
}
