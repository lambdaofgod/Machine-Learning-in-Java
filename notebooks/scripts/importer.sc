import ammonite.ops._
import java.io.File

val toplevelPath = Path("/" +
  pwd.segments
    .dropRight(1)
    .mkString("/"))

val datadir = toplevelPath / 'data

private val jarPaths = (ls! toplevelPath)
  .filter { d => 
    d.isDir &&
    d.segments.takeRight(1).head.startsWith("ML") }
  .map(_ / 'target) 

def loadProjectDependencies = {
  val jars = jarPaths flatMap (ls! _)
  jars.foreach(interp.load.cp)
}

def loadData(fileName: String) = {
  lazy val dataPath = toplevelPath / 'data / fileName
  new File(dataPath.toString)
}
