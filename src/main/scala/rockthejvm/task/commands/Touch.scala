package rockthejvm.task.commands

import rockthejvm.task.files.{DirEntry, File}
import rockthejvm.task.filesystem.State

class Touch(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
