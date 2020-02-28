enablePlugins(ScalaJSPlugin)
libraryDependencies ++= Seq("org.scala-js" %%% "scalajs-dom" % "0.9.7")

name := "Scala.js Tutorial"
scalaVersion := "2.13.0"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true
