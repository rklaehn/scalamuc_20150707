package scala.collection.immutable

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

// sbt "benchmarks/run HashSetBenchmark"

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class HashSetBenchmark {
  @Param(Array("1", "10", "100", "1000"))
  var size = 0
  @Param(Array("0.0", "0.25", "0.5", "0.75", "1.0"))
  var offset = 0.0
  var a: HashSet[Int] = null
  var b: HashSet[Int] = null
  var k: Int = 0

  @Setup
  def setup(): Unit = {
    k = (offset * size).toInt
    a = HashSet(0 until size: _*)
    b = HashSet(k until (k + size): _*)
  }

  @Benchmark
  def union(x: Blackhole): Unit = {
    x.consume(a union b)
  }

  @Benchmark
  def intersect(x: Blackhole): Unit = {
    x.consume(a intersect b)
  }

  @Benchmark
  def diff(x: Blackhole): Unit = {
    x.consume(a diff b)
  }

  @Benchmark
  def subsetOf(x: Blackhole): Unit = {
    x.consume(a subsetOf b)
  }

  @Benchmark
  def filter(x: Blackhole): Unit = {
    x.consume(a filter(_ < k))
  }
}
