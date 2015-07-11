import scala.collection.immutable.HashSet
import scala.collection.{IndexedSeqLike, mutable}

object NanoTime extends App {
  def test() = 0
  val t0 = System.nanoTime()
  test()
  val dt = System.nanoTime() - t0
}

object MemoryMeterDemo extends App {
  val mm = new org.github.jamm.MemoryMeter()
  val size = mm.measureDeep(Array(1,2,3,4))
  println(size)
  val a = HashSet(0 until 1000:_*)
  val b = a.filter(_ => true)
  a.&(b)
  require(a eq b)
  println(mm.measureDeep(a))
  println(mm.measureDeep(b))
  println(mm.measureDeep((a,b)))
}





object AllocationInstrumenterDemo extends App {
  val sampler = new Sampler() {
    def sampleAllocation(count: Int, desc: String, newObj: AnyRef, size: Long): Unit = {
      println(s"I just allocated the object $newObj of type $desc whose size is $size")
      if (count != -1)
        println(s"It's an array of size $count")
    }
  }
  AllocationRecorder.addSampler(sampler)
  val t = new String("OK")
  AllocationRecorder.removeSampler(sampler)
}

class MyIndexedSeq[T] extends IndexedSeq[T] with IndexedSeqLike[T, MyIndexedSeq[T]] {
  override def length: Int = ???
  override def apply(idx: Int): T = ???
  override protected[this] def newBuilder: mutable.Builder[T, MyIndexedSeq[T]] = ???
}

