package command;

import command.database.Database;
import command.database.impl.*;

public class MainApp {
    public static void main(String[] args) {
        Database database = new Database();
        DatabaseCommandExecutor executor = new DatabaseCommandExecutor();
        executor.addCommand(new SelectCommand(database));
        executor.addCommand(new InsertCommand(database));
        executor.addCommand(new SelectCommand(database));
        executor.addCommand(new InsertCommand(database));

        executor.undoCommand();

        executor.executeCommands();

        System.out.println(executor.getCommandsHistory());
    }
}
