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

package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.api.serializers.HttpResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpResponse implements CommandReplyResult {

    private HttpResponseEntity httpResponseEntity;

    private HttpResponse(HttpResponseEntity httpResponseEntity){
        this.httpResponseEntity = httpResponseEntity;
    }

    public HttpResponse(HttpResponse other) {
        this.httpResponseEntity = other.httpResponseEntity;
    }

    // SDK Methods

    /**
     * Determine if the HTTP protocolVersion set for the response is equal to that specified by the REQUIRED protocolVersion argument.
     *
     * @param version Version of the protocol
     * @return Return true if the response has the protocolVersion specified in the parameter
     */
    public boolean isProtocolVersion(String version) {
        return httpResponseEntity.getProtocolVersion().equals(version);
    }

    /**
     * @return Return the value set for the HTTP protocolVersion of the response.
     */
    @JsonIgnore
    public String getProtocolVersion() {
        return httpResponseEntity.getProtocolVersion();
    }

    /**
     * Set the value specified by the REQUIRED protocolVersion argument as the HTTP protocolVersion of the response.
     *
     * @param version Protocol protocolVersion to set
     */
    public HttpResponse setProtocolVersion(String version) {
        httpResponseEntity.setProtocolVersion(version);
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
        return httpResponseEntity.getStatus().equals(status);
    }

    /**
     * Status getter
     *
     * @return Return the value set for the HTTP status code and text of the response.
     */
    public String getStatus() {
        return httpResponseEntity.getStatus();
    }

    /**
     * @return Return the value set for the HTTP status code of the response.
     */
    @JsonIgnore
    public int getStatusCode() {
        return Integer.valueOf(httpResponseEntity.getStatus().split(" ")[0]);
    }

    /**
     * @return Return the value set for the HTTP status text of the response.
     */
    @JsonIgnore
    public String getStatusText() {
        String[] split = httpResponseEntity.getStatus().split(" ");
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
        httpResponseEntity.setStatus(code + " " + text);
        return this;
    }

    /**
     * Determine if the HTTP header specified by the REQUIRED case sensitive name argument has been set for the response.
     *
     * @param name Header name
     * @return Return true if the response contains the header specified in the parameter
     */
    public boolean hasHeader(String name) {
        return httpResponseEntity.getHeaders().containsKey(name);
    }

    /**
     * @param name Name of the header
     * @return Return the value of the HTTP header with the name specified by the REQUIRED case sensitive name
     * argument, or an empty string if the header does not exist.
     */
    public String getHeader(String name, String defaultValue) {
        if (!httpResponseEntity.getHeaders().containsKey(name)) {
            return defaultValue;
        }
        return httpResponseEntity.getHeaders().get(name).get(0);
    }

    public String getHeader(String name) {
        return getHeader(name, "");
    }

    /**
     * Headers getter
     *
     * @return Return an object with the HTTP headers defined for the response, where each property name is the header
     * name, and the value the header value as a string.
     */
    public List<String> getHeaderArray(String name, List<String> defaultArray) {
        if (httpResponseEntity.getHeaders() == null) {
            httpResponseEntity.setHeaders(new HashMap<>());
        }
        if (!httpResponseEntity.getHeaders().containsKey(name)) {
            return defaultArray;
        }
        return httpResponseEntity.getHeaders().get(name);
    }

    public List<String> getHeaderArray(String name) {
        return getHeaderArray(name, new ArrayList<>());
    }

    /**
     * Headers getter
     *
     * @return Return an object with the HTTP headers defined for the response, where each property name is the header
     * name, and the value the header value as a string.
     */
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        for (Map.Entry key : httpResponseEntity.getHeaders().entrySet() ){
            if (!httpResponseEntity.getHeaders().get((String)key.getKey()).isEmpty()) {
                headers.put((String) key.getKey(), httpResponseEntity.getHeaders().get((String) key.getKey()).get(0));
            }
        }
        return headers;
    }

    /**
     * Headers getter
     *
     * @return Return an object with the HTTP headers defined for the response, where each property name is the header
     * name, and the value the header value as a string.
     */
    public Map<String, List<String>> getHeadersArray() {
        if (httpResponseEntity.getHeaders() == null) {
            httpResponseEntity.setHeaders(new HashMap<>());
        }
        return httpResponseEntity.getHeaders();
    }

    /**
     * Add a header to the Http ResponseEntity
     *
     * @param name  Header name
     * @param value Header value
     */
    public HttpResponse setHeader(String name, String value) {
        if (httpResponseEntity.getHeaders() != null && httpResponseEntity.getHeaders().containsKey(name)) {
            httpResponseEntity.getHeaders().get(name).add(value);
        } else {
            if (httpResponseEntity.getHeaders() == null) {
                httpResponseEntity.setHeaders(new HashMap<>());
            }
            List<String> values = new ArrayList<>();
            values.add(value);
            httpResponseEntity.getHeaders().put(name, values);
        }
        return this;
    }

    /**
     * Determine if the HTTP response has content defined for the response body.
     *
     * @return Return true if the response has a body
     */
    public boolean hasBody() {
        return httpResponseEntity.getBody() != null;
    }

    /**
     * Body getter
     *
     * @return Return the content set for the body of the HTTP response.
     */
    public String getBody() {
        return httpResponseEntity.getBody();
    }

    /**
     * Set the content for the body of the HTTP response. If the content argument is not specified the body of the
     * response SHOULD be reset to an empty string.
     *
     * @param body Body to set
     */
    public HttpResponse setBody(String body) {
        httpResponseEntity.setBody(body == null ? "" : body);
        return this;
    }
    public HttpResponse setBody() {
        return setBody("");
    }

    public static class Builder {

        private HttpResponseEntity httpResponseEntity;

        public Builder() {
        }

        public HttpResponse.Builder setHttpResponseEntity(HttpResponseEntity httpResponseEntity) {
            this.httpResponseEntity = httpResponseEntity;
            return this;
        }

        public HttpResponse build(){
            return new HttpResponse(httpResponseEntity);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HttpResponse that = (HttpResponse) o;

        return httpResponseEntity != null ? httpResponseEntity.equals(that.httpResponseEntity) : that.httpResponseEntity == null;
    }

    @Override
    public int hashCode() {
        return httpResponseEntity != null ? httpResponseEntity.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "httpResponseEntity=" + httpResponseEntity +
                '}';
    }
}
