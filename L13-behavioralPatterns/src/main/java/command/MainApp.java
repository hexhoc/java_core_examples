package command;

import command.database.Database;
import command.database.impl.*;

public class MainApp {
    public static void main(String[] args) {
        Database database = new Database();
        DatabaseCommandExecutor executor = new DatabaseCommandExecutor();
        executor.executeCommand(new SelectCommand(database));
        executor.executeCommand(new InsertCommand(database));
        executor.executeCommand(new DeleteCommand(database));
        executor.executeCommand(new UpdateCommand(database));

        System.out.println(executor.getCommandsHistory());
    }
}
