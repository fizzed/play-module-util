import sbt._
import Keys._
import play.Project._
import java.net.URL

object ApplicationBuild extends Build {

  val appName         = "mfz-play-module-util"
  val appVersion      = "1.0"

  val appDependencies = Seq(
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    organization := "com.mfizz",
    organizationName := "Mfizz Inc",
    organizationHomepage := Some(new URL("http://mfizz.com")),
    
    // required for publishing artifact to maven central via sonatype
    publishMavenStyle := true,
    publishTo <<= version { v: String =>
	  val nexus = "https://oss.sonatype.org/"
	  if (v.trim.endsWith("SNAPSHOT"))
	    Some("snapshots" at nexus + "content/repositories/snapshots")
	  else
	    Some("releases" at nexus + "service/local/staging/deploy/maven2")
	},
	
	// in order to pass sonatype's requirements the following properties are required as well
	startYear := Some(2013),
	description := "Play framework 2.x utility module used across Mfizz projects",
    licenses := Seq("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    homepage := Some(url("http://mfizz.com/oss/play-module-util")),
    scmInfo := Some(ScmInfo(url("https://github.com/mfizz-inc/play-module-util"), "https://github.com/mfizz-inc/play-module-util.git")),
    pomExtra := (
      <developers>
        <developer>
    	  <name>Mfizz Inc (twitter: @mfizz_inc)</name>
          <email>oss@mfizz.com</email>
        </developer>
        <developer>
    	  <name>Joe Lauer (twitter: @jjlauer)</name>
        </developer>
      </developers>
    )
  )

}
