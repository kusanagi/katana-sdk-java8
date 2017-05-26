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
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.component.Component;
import io.kusanagi.katana.api.component.Key;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api {

    /**
     * The meta-information about the payload
     */
    @JsonProperty(Key.RESPONSE_META)
    private Meta meta;

    /**
     * The semantics of the original request
     */
    @JsonProperty(Key.RESPONSE_HTTP_REQUEST)
    private HttpRequest httpRequest;

    /**
     * The semantics of the response
     */
    @JsonProperty(Key.RESPONSE_HTTP_RESPONSE)
    private HttpResponse httpResponse;

    /**
     * The Transport instance
     */
    @JsonProperty(Key.RESPONSE_TRANSPORT)
    private Transport transport;

    /**
     * The value returned by the initial **Service** called in the request
     */
    @JsonProperty(Key.RESPONSE_RETURN)
    private Object returnObject;

    public Response() {
        // Default constructor to make possible the serialization of this object.
    }

    public Response(Response other) {
        super(other);
        this.meta = other.meta;
        this.httpRequest = other.httpRequest;
        this.httpResponse = other.httpResponse;
        this.transport = other.transport;
    }

    public Response(Component component, String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(component, path, name, version, platformVersion, variables, isDebug);
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
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

    // SDK METHODS

    /**
     * @return the protocol implemented by the Gateway component handling the request.
     */
    @JsonIgnore
    public String getGatewayProtocol() {
        return this.meta.getProtocol();
    }

    /**
     * @return the public address of the Gateway component handling the request.
     */
    @JsonIgnore
    public String getGatewayAddress() {
        return this.meta.getGateway().get(1);
    }

    /**
     * @return the instance of the HttpRequest class which contains the HTTP semantics of the request made to the Gateway component.
     */
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    /**
     * @return Return the instance of the HttpResponse class which contains the HTTP semantics for the response to be
     * made from the Gateway component.
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    /**
     * @return whether or not the initial **Service** called in the request has a return value, and returned a value
     * in its command reply.
     */
    public boolean hasReturn(){
        return this.returnObject != null;
    }

    /**
     *
     * @return the value returned by the initial **Service** called in the request.
     */
    @JsonIgnore
    public Object getReturn(){
        if (this.returnObject == null){
            throw new IllegalArgumentException("No return value defined on " + getName() + " (" + version + ")");//TODO add action to the error message
        }
        return getReturnObject();
    }

    /**
     * @return Return an instance of the deserialize-only Transport interface.
     */
    public Transport getTransport() {
        return transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Response response = (Response) o;

        if (meta != null ? !meta.equals(response.meta) : response.meta != null) {
            return false;
        }
        if (httpRequest != null ? !httpRequest.equals(response.httpRequest) : response.httpRequest != null) {
            return false;
        }
        if (httpResponse != null ? !httpResponse.equals(response.httpResponse) : response.httpResponse != null) {
            return false;
        }
        if (transport != null ? !transport.equals(response.transport) : response.transport != null) {
            return false;
        }
        return returnObject != null ? returnObject.equals(response.returnObject) : response.returnObject == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        result = 31 * result + (httpRequest != null ? httpRequest.hashCode() : 0);
        result = 31 * result + (httpResponse != null ? httpResponse.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (returnObject != null ? returnObject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", httpResponse=" + httpResponse +
                ", transport=" + transport +
                ", returnObject=" + returnObject +
                '}';
    }
}
