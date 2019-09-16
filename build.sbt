val ScalatraVersion = "2.7.0-RC1"

organization := "com.github.giwiro"

name := "Courier Web App"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.6"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "org.scalatra" %% "scalatra-forms" % ScalatraVersion,
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.9.v20180320" % "container;compile",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.mchange" % "c3p0" % "0.9.5.4",
  "org.xerial" % "sqlite-jdbc" % "3.28.0" % "container;compile",
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
