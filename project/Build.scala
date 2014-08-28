import sbt._
import Keys._

object NodiMacwirePresBuild extends Build {

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization  := "com.softwaremill.nodi-macwire-pres",
    version       := "1.0",
    scalaVersion  := "2.11.2"
  )

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings
  ) aggregate(macros, core)

  lazy val macros: Project = Project(
    "macros",
    file("macros"),
    settings = buildSettings ++ Seq(
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _))
  )

  lazy val core: Project = Project(
    "core",
    file("core"),
    settings = buildSettings ++ Seq(
      libraryDependencies ++= Seq(
        // macwire
        "com.softwaremill.macwire" %% "macros" % "0.7",
        "com.softwaremill.macwire" %% "runtime" % "0.7",
        // util
        "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
        "ch.qos.logback" % "logback-classic" % "1.1.2",
        "org.scalatest" %% "scalatest" % "2.1.3" % "test"
      ))
  ) dependsOn (macros)
}