package scala.collection.mutable

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

// sbt "benchmarks/run LongMapBenchmark"

@State(Scope.Thread)
class LongMapBenchmark {

  @Param(Array("1", "10", "100", "1000", "10000"))
  var size = 0L
  var mid = 0L
  var a: HashMap[Long, Unit] = null
  var b: LongMap[Unit] = null
  var e: Array[(Long, Unit)] = null

  @Setup
  def setup(): Unit = {
    e = (0L until size).map(_ -> ()).toArray
    a = HashMap(e: _*)
    b = LongMap(e: _*)
    mid = size / 2
  }

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Benchmark
  def createMap(x: Blackhole): Unit = {
    x.consume(HashMap(e:_*))
  }

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Benchmark
  def createLongMap(x: Blackhole): Unit = {
    x.consume(LongMap(e:_*))
  }

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Benchmark
  def lookupMap(x: Blackhole): Unit = {
    x.consume(a(mid))
  }

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Benchmark
  def lookupLongMap(x: Blackhole): Unit = {
    x.consume(b(mid))
  }

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Benchmark
  def map(x: Blackhole): Unit = {
    x.consume(a.map(identity))
  }

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Benchmark
  def mapValuesNow(x: Blackhole): Unit = {
    x.consume(b.mapValuesNow(identity))
  }
}
