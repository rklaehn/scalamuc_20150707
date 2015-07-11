import java.util.stream.Collectors
import collection.j8._

object Java8Streams extends App {

  implicit class StreamExtensions[T](private val underlying:  Iterable[T]) extends AnyRef {
    def spliterator: java.util.Spliterator[T] = new SpliterateIterator[T](underlying.iterator)
    def streamed: java.util.stream.Stream[T] = java.util.stream.StreamSupport.stream(underlying.spliterator, false)
  }

  val x = new java.util.function.Function[Double, String] {
    override def apply(t: Double): String = t.toString
  }
  val s = Seq(1.0, 2.0, 3.0).streamed.map[String](x).collect(Collectors.toSet[String])
//  val s2 = Seq(1.0, 2.0, 3.0).streamed.map(_.toString).collect(Collectors.toSet[String])
}
