package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpResponse implements CommandReplyResult {
    /**
     * The HTTP version of the response
     */
    @JsonProperty(Key.HTTP_RESPONSE_PROTOCOL_VERSION)
    private String protocolVersion;

    /**
     * The HTTP status code and text for the response
     */
    @JsonProperty(Key.HTTP_RESPONSE_STATUS)
    private String status;

    /**
     * An object, where each property is the name of the HTTP header, and the value the header value, if no headers
     * exist this property SHOULD NOT be defined
     */
    @JsonProperty(Key.HTTP_RESPONSE_HEADERS)
    private Map<String, List<String>> headers;

    /**
     * The contents of the HTTP response body
     */
    @JsonProperty(Key.HTTP_RESPONSE_BODY)
    private String body;

    public HttpResponse() {
        // Default constructor to make possible the serialization of this object.
    }

    public HttpResponse(HttpResponse other) {
        this.protocolVersion = other.protocolVersion;
        this.status = other.status;
        this.headers = other.headers;
        this.body = other.body;
    }

    /**
     * Set a HTTP header for the response, with the name specified by the REQUIRED name argument and the value specified by the REQUIRED value argument.
     *
     * @param headers Headers to set
     */
    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    // SDK Methods

    /**
     * Determine if the HTTP protocolVersion set for the response is equal to that specified by the REQUIRED protocolVersion argument.
     *
     * @param version Version of the protocol
     * @return Return true if the response has the protocolVersion specified in the parameter
     */
    public boolean isProtocolVersion(String version) {
        return this.protocolVersion.equals(version);
    }

    /**
     * @return Return the value set for the HTTP protocolVersion of the response.
     */
    @JsonIgnore
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Set the value specified by the REQUIRED protocolVersion argument as the HTTP protocolVersion of the response.
     *
     * @param version Protocol protocolVersion to set
     */
    public HttpResponse setProtocolVersion(String version) {
        this.protocolVersion = version;
        return this;
    }

    /**
     * Determine if the HTTP status code and text set for the response is equal to that specified by the REQUIRED case
     * insensitive status argument.
     *
     * @param status Http status code and text.
     * @return Return true if the status is the same as the one specified in the argument.
     */
    public boolean isStatus(String status) {
        return this.status.equals(status);
    }

    /**
     * Status getter
     *
     * @return Return the value set for the HTTP status code and text of the response.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return Return the value set for the HTTP status code of the response.
     */
    @JsonIgnore
    public int getStatusCode() {
        return Integer.valueOf(status.split(" ")[0]);
    }

    /**
     * @return Return the value set for the HTTP status text of the response.
     */
    @JsonIgnore
    public String getStatusText() {
        String[] split = status.split(" ");
        StringBuilder statusText = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            statusText.append(split[i]);
        }
        return statusText.toString();
    }

    /**
     * Set the values specified by the REQUIRED code argument as the HTTP status code and the REQUIRED case insensitive
     * text argument as the HTTP status text of the response.
     *
     * @param code HTTP status code of the response.
     * @param text HTTP status text of the response.
     */
    public HttpResponse setStatus(int code, String text) {
        this.status = code + " " + text;
        return this;
    }

    /**
     * Determine if the HTTP header specified by the REQUIRED case sensitive name argument has been set for the response.
     *
     * @param name Header name
     * @return Return true if the response contains the header specified in the parameter
     */
    public boolean hasHeader(String name) {
        return this.headers.containsKey(name);
    }

    /**
     * @param name Name of the header
     * @return Return the value of the HTTP header with the name specified by the REQUIRED case sensitive name
     * argument, or an empty string if the header does not exist.
     */
    public String getHeader(String name) {
        if (!this.headers.containsKey(name)) {
            return "";
        }
        return this.headers.get(name).get(0);
    }

    /**
     * Headers getter
     *
     * @return Return an object with the HTTP headers defined for the response, where each property name is the header
     * name, and the value the header value as a string.
     */
    public Map<String, List<String>> getHeaders() {
        if (this.headers == null) {
            this.headers = new HashMap<>();
        }
        return headers;
    }

    /**
     * Add a header to the Http Response
     *
     * @param name  Header name
     * @param value Header value
     */
    public HttpResponse setHeader(String name, String value) {
        if (this.headers != null && this.headers.containsKey(name)) {
            this.headers.get(name).add(value);
        } else {
            if (this.headers == null) {
                this.headers = new HashMap<>();
            }
            List<String> values = new ArrayList<>();
            values.add(value);
            this.headers.put(name, values);
        }
        return this;
    }

    /**
     * Determine if the HTTP response has content defined for the response body.
     *
     * @return Return true if the response has a body
     */
    public boolean hasBody() {
        return this.body != null;
    }

    /**
     * Body getter
     *
     * @return Return the content set for the body of the HTTP response.
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the content for the body of the HTTP response. If the content argument is not specified the body of the
     * response SHOULD be reset to an empty string.
     *
     * @param body Body to set
     */
    public HttpResponse setBody(String body) {
        this.body = body == null ? "" : body;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpResponse)) {
            return false;
        }

        HttpResponse that = (HttpResponse) o;

        if (!getProtocolVersion().equals(that.getProtocolVersion())) {
            return false;
        }
        if (!getStatus().equals(that.getStatus())) {
            return false;
        }
        if (!getHeaders().equals(that.getHeaders())) {
            return false;
        }
        return getBody() != null ? getBody().equals(that.getBody()) : that.getBody() == null;

    }

    @Override
    public int hashCode() {
        int result = getProtocolVersion().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getHeaders().hashCode();
        result = 31 * result + getBody().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "protocolVersion='" + protocolVersion + '\'' +
                ", status='" + status + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
