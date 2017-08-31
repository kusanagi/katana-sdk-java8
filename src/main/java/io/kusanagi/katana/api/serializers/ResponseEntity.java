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
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.sdk.Meta;

/**
 * Created by juan on 27/08/16.
 */
public class ResponseEntity {

    /**
     * The meta-information about the payload
     */
    @JsonProperty(Key.RESPONSE_META)
    private Meta meta;

    /**
     * The semantics of the original request
     */
    @JsonProperty(Key.RESPONSE_HTTP_REQUEST)
    private HttpRequestEntity httpRequest;

    /**
     * The semantics of the response
     */
    @JsonProperty(Key.RESPONSE_HTTP_RESPONSE)
    private HttpResponseEntity httpResponse;

    /**
     * The Transport instance
     */
    @JsonProperty(Key.RESPONSE_TRANSPORT)
    private TransportEntity transport;

    /**
     * The value returned by the initial **Service** called in the request
     */
    @JsonProperty(Key.RESPONSE_RETURN)
    private Object returnObject;

    public ResponseEntity() {
        // Default constructor to make possible the serialization of this object.
    }

    public ResponseEntity(ResponseEntity other) {
        this.meta = other.meta;
        this.httpRequest = other.httpRequest;
        this.httpResponse = other.httpResponse;
        this.transport = other.transport;
        this.returnObject = other.returnObject;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public HttpRequestEntity getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequestEntity httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpResponseEntity getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponseEntity httpResponse) {
        this.httpResponse = httpResponse;
    }

    public TransportEntity getTransport() {
        return transport;
    }

    public void setTransport(TransportEntity transport) {
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

        ResponseEntity that = (ResponseEntity) o;

        if (meta != null ? !meta.equals(that.meta) : that.meta != null) {
            return false;
        }
        if (httpRequest != null ? !httpRequest.equals(that.httpRequest) : that.httpRequest != null) {
            return false;
        }
        if (httpResponse != null ? !httpResponse.equals(that.httpResponse) : that.httpResponse != null) {
            return false;
        }
        if (transport != null ? !transport.equals(that.transport) : that.transport != null) {
            return false;
        }
        return returnObject != null ? returnObject.equals(that.returnObject) : that.returnObject == null;
    }

    @Override
    public int hashCode() {
        int result = meta != null ? meta.hashCode() : 0;
        result = 31 * result + (httpRequest != null ? httpRequest.hashCode() : 0);
        result = 31 * result + (httpResponse != null ? httpResponse.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (returnObject != null ? returnObject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", httpResponse=" + httpResponse +
                ", transport=" + transport +
                ", returnObject=" + returnObject +
                '}';
    }
}
