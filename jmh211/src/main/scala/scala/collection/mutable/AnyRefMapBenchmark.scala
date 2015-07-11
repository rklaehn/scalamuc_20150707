package scala.collection.mutable

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

// sbt "benchmarks/run AnyRefMapBenchmark"

@State(Scope.Thread)
class AnyRefMapBenchmark {

  @Param(Array("1", "10", "100", "1000", "10000"))
  var size = 0L
  var mid = ""
  var a: HashMap[String, Unit] = null
  var b: AnyRefMap[String, Unit] = null
  var e: Array[(String, Unit)] = null

  @Setup
  def setup(): Unit = {
    e = (0L until size).map(_.toString -> ()).toArray
    a = HashMap(e: _*)
    b = AnyRefMap(e: _*)
    mid = (size / 2).toString
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
  def createAnyRefMap(x: Blackhole): Unit = {
    x.consume(AnyRefMap(e:_*))
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
  def lookupAnyRefMap(x: Blackhole): Unit = {
    x.consume(b(mid))
  }

  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Benchmark
  def lookupAnyRefMapGetOrNull(x: Blackhole): Unit = {
    x.consume(b.getOrNull(mid))
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
