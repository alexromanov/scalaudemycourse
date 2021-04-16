package rockthejvm.task.commands

import rockthejvm.task.filesystem.State

class Pwd extends Command {
  override def apply(state: State): State = {
    state.setMessage(state.wd.path)
  }
}
