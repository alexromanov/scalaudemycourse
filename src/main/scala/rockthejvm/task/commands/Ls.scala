package rockthejvm.task.commands

import rockthejvm.task.files.DirEntry
import rockthejvm.task.filesystem.State

class Ls extends Command {
  override def apply(state: State): State = {
    val contents = state.wd.contents
    val niceOutput = createOutput(contents)
    state.setMessage(niceOutput)
  }

  def createOutput(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.name + "[" + entry.getType + "]" + "\n" + createOutput(contents.tail)
    }
  }

}
