package rockthejvm.task.commands

import rockthejvm.task.filesystem.State

class UnknownCommand extends Command {

  override def apply(state: State): State =
    state.setMessage("Command not found!")
}
