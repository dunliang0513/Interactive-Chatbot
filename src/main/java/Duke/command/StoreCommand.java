package Duke.command;

import Duke.Storage.Storage;
import Duke.Ui;
import Duke.Exceptions.CommandNotFoundException;
import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.FileException;
import Duke.TaskList;

public class StoreCommand extends Command {
    private String filePath;
    private Storage storage;
    private TaskList taskList;

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeMainExceptions {
        return ui.printStoredTasks(this.storage, this.taskList);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }
}
