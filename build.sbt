import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val root = crossProject(JVMPlatform, NativePlatform)
  .in(file("."))
  .enablePlugins(ScalaNativePlugin)
  .settings(publishSettings)
  .settings(
    organization := "org.akka-js",
    name := "scalanative-java-time",
    version := "0.0.2",
    scalaVersion := "2.11.12",
    scalacOptions += "-target:jvm-1.8",
    nativeLinkStubs := true,
    testFrameworks += new TestFramework("utest.runner.Framework"),
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.3" % "test"
  )

lazy val rootJVM = root.jvm
lazy val rootNative = root.native

ThisBuild / scalafmtOnCompile := true

lazy val publishSettings = Seq(
  pomExtra := {
    <url>https://github.com/akka-js/scalanative-java-time</url>
    <licenses>
      <license>
        <name>BSD New</name>
        <url>https://github.com/akka-js/scalanative-java-time/blob/master/LICENSE.txt</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/akka-js/scalanative-java-time</connection>
      <developerConnection>scm:git:git@github.com:akka-js/scalanative-java-time</developerConnection>
      <url>github.com/akka-js/scalanative-java-time</url>
    </scm>
    <developers>
      <developer>
        <id>andreaTP</id>
        <name>Andrea Peruffo</name>
        <url>https://github.com/andreaTP/</url>
      </developer>
    </developers>
  },
  publishMavenStyle := true,
  pomIncludeRepository := { x =>
    false
  },
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  credentials += Credentials(Path.userHome / ".ivy2" / "sonatype.credentials")
)
