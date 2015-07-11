# ScalaMuc 2015-07-07

Example code for the munich scala user group meetup from 2015-07-07

http://www.meetup.com/ScalaMuc/events/223353943/

## Slides

http://tinyurl.com/o98esa4

## Play around with thyme

```
sbt scala210/console
sbt scala211/console
```

Creating a warmed-up thyme instance will take a few seconds.

```
th.pbenchWarm("title")(th.Warm(<code1>))(th.Warm(<code2>))
```

## Predefined thyme benchmarks

```
sbt scala211/run (FastDiffBench)
sbt scala211/run (MutableVsImmutableSetBench)
```

## JMH benchmarks

```
sbt jmh210/run
sbt jmh211/run
```

## Compare filter allocations

```
sbt scala210/run (CountFilterAllocations)
sbt scala211/run (CountFilterAllocations)
```

## Compare memory usage after filter

```
sbt scala210/run (FilterStructuralSharing)
sbt scala211/run (FilterStructuralSharing)
```
