package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.InvalidIndexException;
import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int selectedIndex) {
        this.index = selectedIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws
            DukeMainExceptions {
        try {
            Task task = tasks.unmark(this.index);
            return ui.printMarked(task);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidIndexException(1 + tasks.getSize());
        }
    }
}
