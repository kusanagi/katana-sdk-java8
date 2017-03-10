package io.kusanagi.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.sdk.HttpResponse;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseReplyPayload implements CommandReplyResult {

    /**
     * The reply to a command
     */
    @JsonProperty(Key.REPLY_PAYLOAD_COMMAND_REPLY)
    private ResponseCommandReply responseCommandReply;

    public ResponseReplyPayload() {
        //Empty constructor for serialization
    }

    public ResponseReplyPayload(ResponseReplyPayload other) {
        this.responseCommandReply = other.responseCommandReply;
    }

    @JsonIgnore
    public ResponseCommandReply getCommandReply() {
        return responseCommandReply;
    }

    public void setCommandReply(ResponseCommandReply responseCommandReply) {
        this.responseCommandReply = responseCommandReply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponseReplyPayload)) {
            return false;
        }

        ResponseReplyPayload that = (ResponseReplyPayload) o;

        return getCommandReply().equals(that.getCommandReply());

    }

    @Override
    public int hashCode() {
        return getCommandReply().hashCode();
    }

    @Override
    public String toString() {
        return "CommandReplyPayload{" +
                "responseCommandReply=" + responseCommandReply +
                '}';
    }

    public static class ResponseCommandReply {

        /**
         * The name of the command processing the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_NAME)
        private String name;

        /**
         * The data provided by the component for the reply
         */
        @JsonProperty(Key.COMMAND_REPLY_RESULT)
        private ResponseResult responseResult;

        @JsonIgnore
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonIgnore
        public ResponseResult getResult() {
            return responseResult;
        }

        public void setResult(ResponseResult commandReplyResponseResult) {
            this.responseResult = commandReplyResponseResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ResponseCommandReply)) {
                return false;
            }

            ResponseCommandReply that = (ResponseCommandReply) o;

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
                    ", responseResult=" + responseResult +
                    '}';
        }
    }

    public static class ResponseResult {

        @JsonProperty(Key.RESPONSE_RESULT_HTTP_RESPONSE)
        private HttpResponse httpResponse;

        @JsonIgnore
        public HttpResponse getHttpResponse() {
            return httpResponse;
        }

        public void setHttpResponse(HttpResponse httpResponse) {
            this.httpResponse = httpResponse;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ResponseResult)) {
                return false;
            }

            ResponseResult responseResult = (ResponseResult) o;

            return getHttpResponse().equals(responseResult.getHttpResponse());

        }

        @Override
        public int hashCode() {
            return getHttpResponse().hashCode();
        }

        @Override
        public String toString() {
            return "CallResult{" +
                    "httpResponse=" + httpResponse +
                    '}';
        }
    }
}
