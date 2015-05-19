name := "demo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.softwaremill.macwire" %% "macros" % "1.0.1"
)

val root = (project in file(".")).enablePlugins(PlayScala)
