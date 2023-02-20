package Duke;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.command.Command;
import Duke.command.StoreCommand;

import Duke.Exceptions.CommandNotFoundException;

import java.io.IOException;
import java.util.Scanner;


public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor of Duke.Duke class.
     *
     * @param filePath The path that can access to the file.
     * @throws IOException
     */

    public Duke(String filePath) throws IOException {
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui();
//        parser = new Parser(taskList, storage);


//        try {
//            taskList = storage.loadTasks();
//        } catch (IOException | DukeMainExceptions errMsg) {
//            System.out.println(errMsg);
//        }
//        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.printLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Execute the main program.
     */
    public void run() throws IOException {
////        ui.greet();
//        String input;
//        Scanner scanner = new Scanner(System.in);
//        input = scanner.nextLine();
//        while (!input.equals("bye")) {
////            parser.parse(input);
//            Command command = Parser.parse(input);
//            command.execute(this.taskList, this.storage, this.ui);
//            input = scanner.nextLine();
//        }
//
//        ui.bye();
        this.ui.greet();
        boolean isTerminate = false;
        while (!isTerminate) {
            String input = this.ui.scanInput();
            ui.printLine();
            try {
                Command command = Parser.parse(input);
                command.execute(this.taskList, this.storage, this.ui);
                isTerminate = command.isTerminate();
            } catch (CommandNotFoundException commandException) {
                ui.printCommandError(input, commandException);
            } catch (DukeMainExceptions e) {
                System.out.println(e);
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) throws CommandNotFoundException, IOException {
        new Duke(".\\tasks.txt").run();
    }

    public String getResponse(String input) throws IOException {
        if (this.taskList == null) {
            try {
                taskList = new TaskList(storage.load());
            } catch (DukeMainExceptions exception) {
                System.out.println(exception.toString());
            }
        }
        try {
            Command command = Parser.parse(input);
            if (command instanceof StoreCommand) {
                StoreCommand storeCommand = (StoreCommand) command;
                this.taskList = storeCommand.getTaskList();
                this.storage = storeCommand.getStorage();
            }
            return command.execute(taskList, storage, ui);
        } catch (DukeMainExceptions exception) {
            return exception.toString();
        }
    }
}
