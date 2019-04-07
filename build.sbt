lazy val common = (project in file("common"))
  .settings(
    scalaVersion := "2.12.8",
  )

lazy val contest = (project in file("contest"))
  .settings(
    scalaVersion := "2.12.8",
  )

lazy val fp = (project in file("fp"))
  .settings(
    scalaVersion := "2.12.8",
    scalacOptions += "-Ypartial-unification",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "1.6.0",
      "org.typelevel" %% "cats-free" % "1.6.0",
      "org.typelevel" %% "cats-effect" % "1.2.0",

      "com.pepegar" %% "hammock-core" % "0.9.0",
      "com.pepegar" %% "hammock-apache-http" % "0.9.0",
    )
  )

