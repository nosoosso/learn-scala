lazy val common = (project in file("common"))
  .settings(
    scalaVersion := "2.12.8",
  )

lazy val contest = (project in file("contest"))
  .settings(
    scalaVersion := "2.12.8",
  )

lazy val fp = project in file("fp")

