package rockthejvm.task.commands

import rockthejvm.task.files.Directory
import rockthejvm.task.filesystem.State

class Rm(name: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    val absolutePath = {
      if (name.startsWith(Directory.SEPARATOR)) name
      else if (wd.isRoot) wd.path + name
      else wd.path + Directory.SEPARATOR + name
    }

    if (Directory.ROOT_PATH.equals(absolutePath))
      state.setMessage("Nuclear war not supprted yet!")
    else
      doRm(state, absolutePath)
  }

  def doRm(state: State, path: String): State = {
    def rmHelper(directory: Directory, path: List[String]): Directory = {
      if (path.isEmpty) directory
      else if (path.tail.isEmpty) directory.removeEntry(path.head)
      else {
        val nextDirectory = directory.findEntry(path.head)
        if (!nextDirectory.isDirectory) directory
        else {
          val newNextDirectory = rmHelper(nextDirectory.asDirectory, path.tail)
          if (newNextDirectory == nextDirectory) directory
          else directory.replaceEntry(path.head, newNextDirectory)
        }
      }
    }

    val tokens = path.substring(1).split(Directory.SEPARATOR).toList
    val newRoot: Directory = rmHelper(state.root, tokens)
    if (newRoot == state.root)
      state.setMessage(path + ": no such file or directory")
    else
      State(newRoot, newRoot.findDescendant(state.wd.path.substring(1)))
  }
}
