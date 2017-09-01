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
import io.kusanagi.katana.api.serializers.ActionEntity;

/**
 * Created by juan on 26/09/16.
 */
public class ActionCommandPayload extends CommandPayload<ActionEntity> {

    /**
     * The semantics of the command
     */
    @JsonProperty(Key.COMMAND_PAYLOAD_COMMAND)
    private ActionCommand command;

    public ActionCommandPayload() {
        //Empty constructor for serialization
    }

    public ActionCommandPayload(ActionCommandPayload other) {
        super(other);
        this.command = other.command;
    }

    @Override
    public ActionCommand getCommand() {
        return command;
    }

    public void setCommand(ActionCommand command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionCommandPayload)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ActionCommandPayload that = (ActionCommandPayload) o;

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
        return "ActionCommandPayload{" +
                "command=" + command +
                "} " + super.toString();
    }

    public static class ActionCommand extends Command<ActionEntity> {

        /**
         * The key/value arguments for the command, if no arguments exist this property SHOULD NOT be defined
         */
        @JsonProperty(Key.COMMAND_ARGUMENT)
        private ActionEntity argument;

        public ActionCommand() {
            //Empty constructor for serialization
        }

        public ActionCommand(ActionCommand other) {
            super(other);
            this.argument = new ActionEntity(other.argument);
        }

        @Override
        public ActionEntity getArgument() {
            return argument;
        }

        public void setArgument(ActionEntity argument) {
            this.argument = argument;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ActionCommand)) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }

            ActionCommand that = (ActionCommand) o;

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
            return "ActionCommand{" +
                    "argument=" + argument +
                    "} " + super.toString();
        }
    }
}
