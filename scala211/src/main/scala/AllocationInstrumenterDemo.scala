import com.google.monitoring.runtime.instrumentation.{Sampler, AllocationRecorder}

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
