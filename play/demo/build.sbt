name := "demo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.softwaremill.macwire" %% "macros" % "0.7"
)

val root = (project in file(".")).enablePlugins(PlayScala)
