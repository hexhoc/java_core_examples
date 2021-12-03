package command.database.impl;

import command.database.impl.DatabaseCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseCommandExecutor {
    List<DatabaseCommand> databaseCommands = new ArrayList<>();

    public void executeCommand(DatabaseCommand databaseCommand) {
        databaseCommands.add(databaseCommand);
        databaseCommand.execute();
    }

    public String getCommandsHistory() {
        return databaseCommands.toString();
    }
}
