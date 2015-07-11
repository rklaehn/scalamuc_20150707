import ichi.bench.Thyme

object MutableVsImmutableSetBench extends App {

  val size = 10000L
  val elems = (0L until size).toArray
  val imm = scala.collection.immutable.HashSet(elems: _*)
  val mut = scala.collection.mutable.HashSet(elems: _*)
  val mid = size / 2

  val th = ichi.bench.Thyme.warmed(warmth = Thyme.HowWarm.BenchOff, verbose = println)
  th.pbenchOffWarm("Lookup immutable.HashSet vs. mutable.HashSet")(
    th.Warm(imm(mid)))(
      th.Warm(mut(mid)))

  th.pbenchOffWarm("Filter immutable.HashSet vs. mutable.HashSet")(
    th.Warm(imm.filter(_ < 5000L): AnyRef))(
      th.Warm(mut.filter(_ < 5000L): AnyRef))
}
