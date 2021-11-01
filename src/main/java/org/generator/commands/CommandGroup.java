package org.generator.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import lombok.val;

import java.util.List;

public interface CommandGroup {

    List<Object> getSubCommands();

    default void addTo(JCommander commander) {
        commander.addCommand(this);

        val commandGroupNames = this.getClass().getAnnotation(Parameters.class).commandNames();
        val commandGroupCommander = commander.getCommands().get(commandGroupNames[0]);

        for (Object subCommand : getSubCommands()) {
            commandGroupCommander.addCommand(subCommand);
        }
    }
}
