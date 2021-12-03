package command.database.impl;

import command.database.Database;

public class InsertCommand implements DatabaseCommand {
    private final Database database;

    public InsertCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.insert();
    }
}
