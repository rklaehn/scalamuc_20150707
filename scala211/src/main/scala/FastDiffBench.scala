import scala.collection.immutable.HashSet
import ichi.bench.Thyme

object FastDiffBench extends App {
  val th = ichi.bench.Thyme.warmed(warmth = Thyme.HowWarm.BenchOff, verbose = println)
  val s = HashSet(0L until 1000000L: _*)
  def change(c: HashSet[Long]): HashSet[Long] = (c - 10000L) + 10000000L
  val s1 = change(s)
  def diff(v0: HashSet[Long], v1: HashSet[Long]) = (v0 diff v1, v1 diff v0)
  th.pbenchWarm(th.Warm(diff(s1, s)))
}
