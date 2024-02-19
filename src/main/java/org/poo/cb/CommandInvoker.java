package org.poo.cb;

public class CommandInvoker {
    // clasa care invoca executia comenzilor
    public void executeCommand(Command command) {
        command.execute();
    }
}
