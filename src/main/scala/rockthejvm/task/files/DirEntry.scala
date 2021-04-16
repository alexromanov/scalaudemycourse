package rockthejvm.task.files

abstract class DirEntry(val parentPath: String, val name: String) {
  def path: String = {
    val separatorNecessary =
      if (Directory.ROOT_PATH.equals(parentPath)) ""
      else Directory.SEPARATOR
    parentPath + separatorNecessary + name
  }

  def asDirectory: Directory

  def getType: String

  def asFile: File

  def isDirectory: Boolean

  def isFile: Boolean
}
