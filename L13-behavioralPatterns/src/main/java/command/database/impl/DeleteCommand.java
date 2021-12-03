package command.database.impl;

import command.database.Database;

public class DeleteCommand implements DatabaseCommand {
    private final Database database;

    public DeleteCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.delete();
    }
}
