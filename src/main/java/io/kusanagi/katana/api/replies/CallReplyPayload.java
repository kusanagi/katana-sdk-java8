package io.kusanagi.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.sdk.RequestCall;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallReplyPayload implements CommandReplyResult {

    /**
     * The reply to a command
     */
    @JsonProperty(Key.REPLY_PAYLOAD_COMMAND_REPLY)
    private CallCommandReply callCommandReply;

    public CallReplyPayload() {
        //Empty constructor for serialization
    }

    public CallReplyPayload(CallReplyPayload other) {
        this.callCommandReply = other.callCommandReply;
    }

    @JsonIgnore
    public CallCommandReply getCommandReply() {
        return callCommandReply;
    }

    public void setCommandReply(CallCommandReply callCommandReply) {
        this.callCommandReply = callCommandReply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CallReplyPayload)) {
            return false;
        }

        CallReplyPayload that = (CallReplyPayload) o;

        return getCommandReply().equals(that.getCommandReply());

    }

    @Override
    public int hashCode() {
        return getCommandReply().hashCode();
    }

    @Override
    public String toString() {
        return "CommandReplyPayload{" +
                "callCommandReply=" + callCommandReply +
                '}';
    }

    /**
     * Created by juan on 30/09/16.
     */
    public static class CallCommandReply {

        /**
         * The name of the command processing the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_NAME)
        private String name;

        /**
         * The data provided by the component for the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_RESULT)
        private CallResult callResult;

        @JsonIgnore
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonIgnore
        public CallResult getResult() {
            return callResult;
        }

        public void setResult(CallResult commandReplyCallResult) {
            this.callResult = commandReplyCallResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CallCommandReply)) {
                return false;
            }

            CallCommandReply that = (CallCommandReply) o;

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
                    ", callResult=" + callResult +
                    '}';
        }
    }

    public static class CallResult {

        @JsonProperty(Key.CALL_RESULT_REQUEST_CALL)
        private RequestCall requestCall;

        @JsonIgnore
        public RequestCall getRequestCall() {
            return requestCall;
        }

        public void setRequestCall(RequestCall requestCall) {
            this.requestCall = requestCall;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CallResult)) {
                return false;
            }

            CallResult callResult = (CallResult) o;

            return getRequestCall().equals(callResult.getRequestCall());

        }

        @Override
        public int hashCode() {
            return getRequestCall().hashCode();
        }

        @Override
        public String toString() {
            return "CallResult{" +
                    "requestCall=" + requestCall +
                    '}';
        }
    }
}
