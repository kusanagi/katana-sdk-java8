package io.kusanagi.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.sdk.Transport;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportReplyPayload implements CommandReplyResult {

    /**
     * The reply to a command
     */
    @JsonProperty(Key.REPLY_PAYLOAD_COMMAND_REPLY)
    private TransportCommandReply transportCommandReply;

    public TransportReplyPayload() {
        //Empty constructor for serialization
    }

    public TransportReplyPayload(TransportReplyPayload other) {
        this.transportCommandReply = other.transportCommandReply;
    }

    @JsonIgnore
    public TransportCommandReply getCommandReply() {
        return transportCommandReply;
    }

    public void setCommandReply(TransportCommandReply transportCommandReply) {
        this.transportCommandReply = transportCommandReply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportReplyPayload)) {
            return false;
        }

        TransportReplyPayload that = (TransportReplyPayload) o;

        return getCommandReply().equals(that.getCommandReply());

    }

    @Override
    public int hashCode() {
        return getCommandReply().hashCode();
    }

    @Override
    public String toString() {
        return "CommandReplyPayload{" +
                "transportCommandReply=" + transportCommandReply +
                '}';
    }

    public static class TransportCommandReply {

        /**
         * The name of the command processing the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_NAME)
        private String name;

        /**
         * The data provided by the component for the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_RESULT)
        private TransportResult transportResult;

        @JsonIgnore
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonIgnore
        public TransportResult getResult() {
            return transportResult;
        }

        public void setResult(TransportResult commandReplyTransportResult) {
            this.transportResult = commandReplyTransportResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof TransportCommandReply)) {
                return false;
            }

            TransportCommandReply that = (TransportCommandReply) o;

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
                    ", transportResult=" + transportResult +
                    '}';
        }
    }

    public static class TransportResult {

        @JsonProperty(Key.TRANSPORT_RESULT_TRANSPORT)
        private Transport transport;

        @JsonProperty(Key.TRANSPORT_RESULT_RETURN_OBJECT)
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

            TransportResult that = (TransportResult) o;

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
            return "TransportResult{" +
                    "transport=" + transport +
                    ", returnObject=" + returnObject +
                    '}';
        }
    }
}
