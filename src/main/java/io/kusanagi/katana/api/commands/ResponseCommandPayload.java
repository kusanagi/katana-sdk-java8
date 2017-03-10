package io.kusanagi.katana.api.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.commands.common.CommandPayload;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.sdk.Response;

/**
 * Created by juan on 26/09/16.
 */
public class ResponseCommandPayload extends CommandPayload<Response> {

    /**
     * The semantics of the command
     */
    @JsonProperty(Key.COMMAND_PAYLOAD_COMMAND)
    private ResponseCommand command;

    public ResponseCommandPayload() {
        //Empty constructor for serialization
    }

    public ResponseCommandPayload(ResponseCommandPayload other) {
        super(other);
        this.command = other.command;
    }

    @Override
    public ResponseCommand getCommand() {
        return command;
    }

    public void setCommand(ResponseCommand command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponseCommandPayload)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ResponseCommandPayload that = (ResponseCommandPayload) o;

        return getCommand().equals(that.getCommand());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCommand().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ResponseCommandPayload{" +
                "command=" + command +
                "} " + super.toString();
    }

    public static class ResponseCommand extends Command<Response> {

        /**
         * The key/value arguments for the command, if no arguments exist this property SHOULD NOT be defined
         */
        @JsonProperty(Key.COMMAND_ARGUMENT)
        private Response argument;

        public ResponseCommand() {
            //Empty constructor for serialization
        }

        public ResponseCommand(ResponseCommand other) {
            super(other);
            this.argument = new Response(other.argument);
        }

        @Override
        public Response getArgument() {
            return argument;
        }

        public void setArgument(Response argument) {
            this.argument = argument;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ResponseCommand)) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }

            ResponseCommand that = (ResponseCommand) o;

            return getArgument().equals(that.getArgument());

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + getArgument().hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "ResponseCommand{" +
                    "argument=" + argument +
                    "} " + super.toString();
        }
    }
}
