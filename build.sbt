name := "Folies"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.sun.mail" % "javax.mail" % "1.5.5",
  "commons-io" % "commons-io" % "2.4"
)     

play.Project.playJavaSettings
