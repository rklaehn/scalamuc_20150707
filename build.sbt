import pl.project13.scala.sbt.SbtJmh

lazy val scala210 = project.in(file("scala210"))
  .settings(scalaVersion := "2.10.4")
  .settings(commonSettings:_*)

lazy val scala211 = project.in(file("scala211"))
  .settings(scalaVersion := "2.11.7")
  .settings(commonSettings:_*)

lazy val scala212 = project.in(file("scala212"))
  .settings(scalaVersion := "2.12.0-M1")
  .settings(commonSettings:_*)
  .dependsOn(scalaStreamSupport)

lazy val jmh210 = project.in(file("jmh210"))
  .settings(scalaVersion := "2.10.4")
  .settings(commonSettings:_*)
  .settings(benchmarkJmhSettings:_*)

lazy val jmh211 = project.in(file("jmh211"))
  .settings(scalaVersion := "2.11.7")
  .settings(commonSettings:_*)
  .settings(benchmarkJmhSettings:_*)

lazy val scalaStreamSupport = RootProject(uri("https://github.com/Ichoran/scala-java8-streams#master"))

lazy val thyme = "ichi.bench" % "thyme" % "0.1.1" from "https://github.com/Ichoran/thyme/raw/master/Thyme.jar"

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "com.rklaehn",
  libraryDependencies += thyme,
  libraryDependencies += "com.google.code.java-allocation-instrumenter" % "java-allocation-instrumenter" % "3.0",
  libraryDependencies += "com.github.jbellis" % "jamm" % "0.3.0",
  javaOptions in Test <++= (dependencyClasspath in Test).map(makeAgentOptions),
  fork in Test := true
)

lazy val benchmarkJmhSettings = commonSettings ++ SbtJmh.jmhSettings ++ Seq(
  name := "spire-benchmark-jmh"
)

def makeAgentOptions(classpath:Classpath) : Seq[String] = {
  val jammJar = classpath.map(_.data).filter(_.toString.contains("jamm")).head
  val jaiJar = classpath.map(_.data).filter(_.toString.contains("instrumenter")).head
  Seq(s"-javaagent:$jammJar", s"-javaagent:$jaiJar")
}
