/*
 * Java 8 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java8
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.api.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.commands.common.CommandPayload;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.sdk.Request;

/**
 * Created by juan on 26/09/16.
 */
public class RequestCommandPayload extends CommandPayload<Request> {

    /**
     * The semantics of the command
     */
    @JsonProperty(Key.COMMAND_PAYLOAD_COMMAND)
    private RequestCommand command;

    public RequestCommandPayload() {
        //Empty constructor for serialization
    }

    public RequestCommandPayload(RequestCommandPayload other) {
        super(other);
        this.command = other.command;
    }

    @Override
    public RequestCommand getCommand() {
        return command;
    }

    public void setCommand(RequestCommand command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestCommandPayload)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        RequestCommandPayload that = (RequestCommandPayload) o;

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
        return "RequestCommandPayload{" +
                "command=" + command +
                "} " + super.toString();
    }

    public static class RequestCommand extends Command<Request> {

        /**
         * The key/value arguments for the command, if no arguments exist this property SHOULD NOT be defined
         */
        @JsonProperty(Key.COMMAND_ARGUMENT)
        private Request argument;

        public RequestCommand() {
            //Empty constructor for serialization
        }

        public RequestCommand(RequestCommand other) {
            super(other);
            this.argument = new Request(other.argument);
        }

        @Override
        public Request getArgument() {
            return argument;
        }

        public void setArgument(Request argument) {
            this.argument = argument;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof RequestCommand)) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }

            RequestCommand that = (RequestCommand) o;

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
            return "RequestCommand{" +
                    "argument=" + argument +
                    "} " + super.toString();
        }
    }
}
