package scala.collection.immutable

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

// sbt "benchmarks/run HashMapBenchmark"

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class HashMapBenchmark {

  @Param(Array("1", "10", "100", "1000"))
  var size = 0

  @Param(Array("0.0", "0.25", "0.5", "0.75", "1.0"))
  var offset = 0.0

  var a: HashMap[Int, Unit] = null

  var k: Int = 0

  @Setup
  def setup(): Unit = {
    k = (offset * size).toInt
    a = HashMap((0 until size).map(_ -> ()): _*)
  }

  @Benchmark
  def filter(x: Blackhole): Unit = {
    x.consume(a filter(_._1 < k))
  }

  @Benchmark
  def filterNot(x: Blackhole): Unit = {
    x.consume(a filterNot(_._1 < k))
  }
}
