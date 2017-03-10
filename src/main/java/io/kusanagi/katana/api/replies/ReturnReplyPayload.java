package io.kusanagi.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.sdk.Transport;

/**
 * Created by jega on 3/03/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnReplyPayload implements CommandReplyResult {

    /**
     * The reply to a command
     */
    @JsonProperty(Key.REPLY_PAYLOAD_COMMAND_REPLY)
    private ReturnCommandReply returnCommandReply;

    public ReturnReplyPayload() {
        //Empty constructor for serialization
    }

    public ReturnReplyPayload(ReturnReplyPayload other) {
        this.returnCommandReply = other.returnCommandReply;
    }

    @JsonIgnore
    public ReturnCommandReply getCommandReply() {
        return returnCommandReply;
    }

    public void setCommandReply(ReturnCommandReply returnCommandReply) {
        this.returnCommandReply = returnCommandReply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReturnReplyPayload)) {
            return false;
        }

        ReturnReplyPayload that = (ReturnReplyPayload) o;

        return getCommandReply().equals(that.getCommandReply());

    }

    @Override
    public int hashCode() {
        return getCommandReply().hashCode();
    }

    @Override
    public String toString() {
        return "CommandReplyPayload{" +
                "ReturnReplyPayload=" + returnCommandReply +
                '}';
    }

    public static class ReturnCommandReply {

        /**
         * The name of the command processing the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_NAME)
        private String name;

        /**
         * The data provided by the component for the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_RESULT)
        private ReturnResult returnResult;

        @JsonIgnore
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonIgnore
        public ReturnResult getResult() {
            return returnResult;
        }

        public void setResult(ReturnResult commandReplyReturnResult) {
            this.returnResult = commandReplyReturnResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ReturnCommandReply)) {
                return false;
            }

            ReturnCommandReply that = (ReturnCommandReply) o;

            if (!getName().equals(that.getName())) {
                return false;
            }
            return getResult().equals(that.getResult());

        }

        @Override
        public int hashCode() {
            int code = getName().hashCode();
            code = 31 * code + getResult().hashCode();
            return code;
        }

        @Override
        public String toString() {
            return "CallResult{" +
                    "name='" + name + '\'' +
                    ", returnResult=" + returnResult +
                    '}';
        }
    }

    public static class ReturnResult {

        @JsonProperty(Key.RETURN_RESULT_TRANSPORT)
        private Transport transport;

        @JsonProperty(Key.RETURN_RESULT_RETURN_OBJECT)
        private Object returnObject;

        public Transport getTransport() {
            return transport;
        }

        public void setTransport(Transport transport) {
            this.transport = transport;
        }

        public Object getReturnObject() {
            return returnObject;
        }

        public void setReturnObject(Object returnObject) {
            this.returnObject = returnObject;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ReturnResult that = (ReturnResult) o;

            if (transport != null ? !transport.equals(that.transport) : that.transport != null) {
                return false;
            }
            return returnObject != null ? returnObject.equals(that.returnObject) : that.returnObject == null;
        }

        @Override
        public int hashCode() {
            int result = transport != null ? transport.hashCode() : 0;
            result = 31 * result + (returnObject != null ? returnObject.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "returnResult{" +
                    "transport=" + transport +
                    ", returnObject=" + returnObject +
                    '}';
        }
    }
}