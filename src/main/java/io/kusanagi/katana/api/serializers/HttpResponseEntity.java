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

package io.kusanagi.katana.api.serializers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;

import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpResponseEntity implements CommandReplyResult {
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

    public HttpResponseEntity() {
        // Default constructor to make possible the serialization of this object.
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HttpResponseEntity that = (HttpResponseEntity) o;

        if (protocolVersion != null ? !protocolVersion.equals(that.protocolVersion) : that.protocolVersion != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) {
            return false;
        }
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) {
            return false;
        }
        return body != null ? body.equals(that.body) : that.body == null;
    }

    @Override
    public int hashCode() {
        int result = protocolVersion != null ? protocolVersion.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpResponseEntity{" +
                "protocolVersion='" + protocolVersion + '\'' +
                ", status='" + status + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
