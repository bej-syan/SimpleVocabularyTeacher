name := "SimpleVocabularyTeacher"

version := "1.0"

lazy val `simplevocabularyteacher` = (project in file(".")).enablePlugins(PlayScala)

resolvers +=  "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers +=  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  jdbc,
  ehcache,
  ws,
  specs2 % Test,
  guice,
  filters,
  "com.typesafe.play" %% "play-iteratees" % "2.6.1",
  "com.typesafe.play" %% "play-iteratees-reactive-streams" % "2.6.1"
)

unmanagedResourceDirectories in Test <+= baseDirectory ( _ /"target/web/public/test" )

routesImport ++= Seq(
  "binders.PathBinders._",
  "binders.QueryStringBinders._"
)