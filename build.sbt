name := "TuaPee"

version := "1.0"

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

scalacOptions += "-feature"

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc
    exclude("com.h2database", "h2")
    exclude("com.jolbox", "bonecp")
  , cache
  , "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  , "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)
