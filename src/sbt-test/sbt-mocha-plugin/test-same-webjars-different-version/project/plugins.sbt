addSbtPlugin("com.github.sbt" % "sbt-mocha" % sys.props("project.version"))

// When the same webjar (= same name) from different groupIds (org.webjars[.npm|bower]?)
// are are on the classpath, those webjars get extracted into subfolders which are named by the version of the webjar.
// However, if there is just one type (npm, bower, classic) of a webjar on the classpath, NO subfolders gets created.
// Now, because in a project we don't know which other webjars get pulled in from other dependencies, it can happen
// that subfolders get created, or may not. However, require(...) can't know that and therefore has to look in both places.
// To test that the lookup is correct, we force this scripted test to create subfolders by pulling in the same webjar
// but from different groupIds (the other scripted test does not do that and therefore no subfolders get created there)
// btw: dependency eviction within the same type of webjar still works correctly, so e.g. 0.2 wins over 0.1 within the same type of webjar
// and no subfolder will be forced for that case but the newest version will be choosen. Like normal dependency resolution.
libraryDependencies ++= Seq(
  // Pulling in the classic and the npm webjar to subfolders for this webjar will be created
  "org.webjars.npm" % "mocha" % "3.0.0-2",
  ("org.webjars" % "mocha" % "1.17.1")
    .exclude("org.webjars", "debug")
    .exclude("org.webjars", "diff"),
)
