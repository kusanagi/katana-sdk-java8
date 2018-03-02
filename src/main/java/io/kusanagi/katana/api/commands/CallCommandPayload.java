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
import io.kusanagi.katana.api.serializers.CalleeEntity;

/**
 * Created by jega on 3/03/17.
 */
public class CallCommandPayload extends CommandPayload<CalleeEntity> {

    /**
     * The semantics of the command
     */
    @JsonProperty(Key.COMMAND_PAYLOAD_COMMAND)
    private CallCommandPayload.CallCommand command;

    public CallCommandPayload() {
        //Empty constructor for serialization
    }

    public CallCommandPayload(CallCommandPayload other) {
        super(other);
        this.command = other.command;
    }

    @Override
    public CallCommandPayload.CallCommand getCommand() {
        return command;
    }

    public void setCommand(CallCommandPayload.CallCommand command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CallCommandPayload that = (CallCommandPayload) o;

        return command != null ? command.equals(that.command) : that.command == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (command != null ? command.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CallCommandPayload{" +
                "command=" + command +
                '}';
    }

    public static class CallCommand extends CommandPayload.Command<CalleeEntity> {

        /**
         * The key/value arguments for the command, if no arguments exist this property SHOULD NOT be defined
         */
        @JsonProperty(Key.COMMAND_ARGUMENT)
        private CalleeEntity argument;

        public CallCommand() {
            //Empty constructor for serialization
        }

        public CallCommand(CallCommandPayload.CallCommand other) {
            super(other);
            this.argument = new CalleeEntity(other.argument);
        }

        @Override
        public CalleeEntity getArgument() {
            return argument;
        }

        public void setArgument(CalleeEntity argument) {
            this.argument = argument;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }

            CallCommand that = (CallCommand) o;

            return argument != null ? argument.equals(that.argument) : that.argument == null;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (argument != null ? argument.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "CallCommand{" +
                    "argument=" + argument +
                    '}';
        }
    }
}
