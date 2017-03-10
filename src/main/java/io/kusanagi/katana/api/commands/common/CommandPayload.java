package io.kusanagi.katana.api.commands.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.component.Key;

/**
 * Created by juan on 26/09/16.
 */

public class CommandPayload<T extends Api> {

    /**
     * Meta-data associated with the command
     */
    @JsonProperty(Key.COMMAND_PAYLOAD_COMMAND_META)
    private CommandMeta commandMeta;

    public CommandPayload() {
        //Empty constructor for serialization
    }

    public CommandPayload(CommandPayload other) {
        this.commandMeta = other.commandMeta;
    }

    public CommandMeta getCommandMeta() {
        return commandMeta;
    }

    public void setCommandMeta(CommandMeta commandMeta) {
        this.commandMeta = commandMeta;
    }

    public Command<T> getCommand() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandPayload)) {
            return false;
        }

        CommandPayload<?> that = (CommandPayload<?>) o;

        return getCommandMeta().equals(that.getCommandMeta());

    }

    @Override
    public int hashCode() {
        return getCommandMeta().hashCode();
    }

    @Override
    public String toString() {
        return "CommandPayload{" +
                "commandMeta=" + commandMeta +
                '}';
    }

    public static class Command<T extends Api> {

        /**
         * The name of the command to call
         */
        @JsonProperty(Key.COMMAND_NAME)
        private String name;

        public Command() {
            //Empty constructor for serialization
        }

        public Command(Command other) {
            this.name = other.name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getArgument() {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Command)) {
                return false;
            }

            Command<?> command = (Command<?>) o;

            return getName().equals(command.getName());

        }

        @Override
        public int hashCode() {
            return getName().hashCode();
        }

        @Override
        public String toString() {
            return "Command{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
