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

package io.kusanagi.katana.api.commands.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

/**
 * Created by juan on 26/09/16.
 */

public class CommandPayload<T> {

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

    public static class Command<T> {

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
