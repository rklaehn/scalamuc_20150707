import scala.collection.immutable.SortedMap

object ObjectCounter {

  private class CountingSampler extends Sampler {
    case class Stats(count: Int, size: Long)
    var counts = SortedMap.empty[String, Stats].withDefaultValue(Stats(0,0))
    def sampleAllocation(count: Int, desc: String, newObj: AnyRef, size: Long): Unit = {
      val name = if(count == -1) desc else s"desc[$count]"
      counts = counts.updated(name, {
        val prev = counts(name)
        Stats(prev.count + 1, prev.size + size)
      })
    }
  }

  def withCount[U](prefix: String)(f: => U): U = {
    val sampler = new CountingSampler
    AllocationRecorder.addSampler(sampler)
    val result = f
    AllocationRecorder.removeSampler(sampler)
    for((k, v) <- sampler.counts)
      if(k.startsWith(prefix))
      println(s"$k ${v.count}")
    result
  }
}
