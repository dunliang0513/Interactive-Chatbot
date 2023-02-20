package Duke.command;

import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Ui;
import Duke.Exceptions.FileException;

import java.io.IOException;
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        terminate = true;
        String output;
        try {
            storage.store(tasks);
            output = ui.printStoredTasks(storage, tasks);
        } catch(FileException e) {
            output = ui.printLoadingError();
        }
        output += ui.bye();
        return output;
    }
}
