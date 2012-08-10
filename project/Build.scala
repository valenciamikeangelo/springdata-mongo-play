import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "springdata-mongo-play"
    val appVersion      = "1.0-SNAPSHOT"

     val appDependencies = Seq(
      "org.springframework" % "spring-context" % "3.1.1.RELEASE",
      "org.springframework" % "spring-core" % "3.1.1.RELEASE",
      "org.springframework" % "spring-beans" % "3.1.1.RELEASE",
      "org.springframework" % "spring-test" % "3.1.1.RELEASE",
      "org.springframework.data" % "spring-data-mongodb" % "1.0.2.RELEASE",
      "cglib" % "cglib" % "2.2.2",
      "org.mongodb" % "mongo-java-driver" % "2.8.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
    )

}
