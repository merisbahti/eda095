import sbt._
import Keys._

object HelloBuild extends Build {
  /*
  override lazy val settings = super.settings ++ Seq(
    resolvers += Resolver.mavenLocal,
    libraryDependencies += "org" % "jacop" % "4.2.0",
    version      := "0.2",
    scalaVersion := "2.11.0")
  */
  //lazy val root = Project(id = "edan01",
  //                        base = file("."))
  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
        javaOptions += "-Djava.net.preferIPv4Stack=true"
  )
}
