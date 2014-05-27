name := "demo"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.softwaremill.macwire" %% "macros" % "0.5"
)     

play.Project.playScalaSettings
