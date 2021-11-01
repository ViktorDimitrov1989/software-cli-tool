package org.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Builder
@Slf4j
public class Cli {

    @NonNull
    private final JCommander commander;

    public void run(String... args) throws Exception {
        log.info("Running the cli run method");
        try {
            commander.parse(args);
        } catch (ParameterException ex) {
            throw new IllegalArgumentException("Failed to parse arguments", ex);
        }
        val parsedCommand = getParseCommand(commander);

//        if (parsedCommand == null) {
//            throw new IllegalArgumentException("Please specify a command.");
//        }

        final Object command = parsedCommand == null ? commander.getObjects().get(0) : parsedCommand;

        try {
            if (command instanceof Runnable) {
                ((Runnable) command).run();
            } else if (command instanceof ThrowingRunnable) {
                ((ThrowingRunnable) command).run();
            } else {
                throw new AssertionError("Command must implement Runnable or ThrowingRunnable");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private Object getParseCommand(JCommander commander) {
        val commandName = commander.getParsedCommand();

        if (commandName == null) {
            return null;
        }

        val cmd = commander.getCommands().get(commandName);

        if (cmd.getCommands().isEmpty()) {
            return cmd.getObjects().get(0);
        } else {
            return getParseCommand(cmd);
        }
    }
}
