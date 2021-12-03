package command.database.impl;

import command.database.impl.DatabaseCommand;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCommandExecutor {
    List<DatabaseCommand> databaseCommands = new ArrayList<>();

    public void addCommand(DatabaseCommand databaseCommand) {
        databaseCommands.add(databaseCommand);
    }
    public void undoCommand() {
        databaseCommands.remove(databaseCommands.get(databaseCommands.size()-1));
    }

    public void executeCommands() {
        databaseCommands.forEach(DatabaseCommand::execute);
    }

    public String getCommandsHistory() {
        StringBuilder sb = new StringBuilder();
        databaseCommands.forEach(c->sb.append(c.getClass().getSimpleName()
                + " "));

        return sb.toString();
    }
}
