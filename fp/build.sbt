scalaVersion := "2.12.8"
scalacOptions += "-Ypartial-unification"

// for circe
addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)

val circeVersion = "0.11.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.6.0",
  "org.typelevel" %% "cats-free" % "1.6.0",
  "org.typelevel" %% "cats-effect" % "1.2.0",

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "io.circe" %% "circe-refined" % circeVersion,


  "com.pepegar" %% "hammock-core" % "0.9.0",
  "com.pepegar" %% "hammock-apache-http" % "0.9.0",

  "eu.timepit" %% "refined" % "0.9.7",
)
