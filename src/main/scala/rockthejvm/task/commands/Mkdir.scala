package rockthejvm.task.commands

import rockthejvm.task.files.{DirEntry, Directory}
import rockthejvm.task.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = {
    Directory.empty(state.wd.path, name)
  }
}
