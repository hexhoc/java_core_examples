package command.database.impl;

import command.database.Database;
import command.database.DatabaseCommand;

public class SelectCommand implements DatabaseCommand {

    private final Database database;

    public SelectCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.select();
    }
}
