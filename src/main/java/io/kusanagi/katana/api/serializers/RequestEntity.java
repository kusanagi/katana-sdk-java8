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
import io.kusanagi.katana.sdk.RequestCall;

/**
 * Created by juan on 27/08/16.
 */
public class RequestEntity {
    /**
     * The meta-information about the payload
     */
    @JsonProperty(Key.REQUEST_META)
    private Meta meta;

    /**
     * The semantics of the request
     */
    @JsonProperty(Key.REQUEST_HTTP_REQUEST)
    private HttpRequestEntity httpRequest;

    /**
     * The semantics of the Service to contact
     */
    @JsonProperty(Key.REQUEST_REQUEST_CALL)
    private RequestCall requestCall;

    public RequestEntity() {
        // Default constructor to make possible the serialization of this object.
    }

    public RequestEntity(RequestEntity other) {
        this.meta = other.meta;
        this.httpRequest = other.httpRequest;
        this.requestCall = other.requestCall;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestEntity that = (RequestEntity) o;

        if (meta != null ? !meta.equals(that.meta) : that.meta != null) {
            return false;
        }
        if (httpRequest != null ? !httpRequest.equals(that.httpRequest) : that.httpRequest != null) {
            return false;
        }
        return requestCall != null ? requestCall.equals(that.requestCall) : that.requestCall == null;
    }

    @Override
    public int hashCode() {
        int result = meta != null ? meta.hashCode() : 0;
        result = 31 * result + (httpRequest != null ? httpRequest.hashCode() : 0);
        result = 31 * result + (requestCall != null ? requestCall.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", requestCall=" + requestCall +
                '}';
    }
}
