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
import io.kusanagi.katana.api.commands.Mapping;
import io.kusanagi.katana.api.component.Component;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.serializers.ActionEntity;
import io.kusanagi.katana.api.serializers.ResponseEntity;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api {

    private ResponseEntity responseEntity;

    private HttpRequest httpRequest;

    private HttpResponse httpResponse;

    private Transport transport;

    public Response() {
        // Default constructor to make possible the serialization of this object.
    }

    public Response(Component component, String path, String name, String version, String platformVersion,
                    Map<String, String> variables, boolean isDebug, Mapping mapping, ResponseEntity responseEntity,
                    HttpRequest httpRequest, HttpResponse httpResponse, Transport transport) {
        super(component, path, name, version, platformVersion, variables, isDebug, mapping);
        this.responseEntity = responseEntity;
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
        this.transport = transport;
    }

    public Response(Response other) {
        super(other);
        this.responseEntity = other.responseEntity;
        this.httpRequest = other.httpRequest;
        this.httpResponse = other.httpResponse;
        this.transport = other.transport;
    }

    // SDK METHODS

    /**
     * @return the protocol implemented by the Gateway component handling the request.
     */
    @JsonIgnore
    public String getGatewayProtocol() {
        return responseEntity.getMeta().getProtocol();
    }

    /**
     * @return the public address of the Gateway component handling the request.
     */
    @JsonIgnore
    public String getGatewayAddress() {
        return responseEntity.getMeta().getGateway().get(1);
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
        return responseEntity.getReturnObject() != null;
    }

    /**
     *
     * @return the value returned by the initial **Service** called in the request.
     */
    @JsonIgnore
    public Object getReturn(){
        if (responseEntity.getReturnObject() == null){
            throw new IllegalArgumentException("No return value defined on " + getName() + " (" + version + ")");//TODO add action to the error message
        }
        return responseEntity.getReturnObject();
    }

    /**
     * @return Return an instance of the deserialize-only Transport interface.
     */
    public Transport getTransport() {
        return transport;
    }

    public static class Builder extends Api.Builder<Response>{

        private ResponseEntity responseEntity;
        private HttpRequest httpRequest;
        private HttpResponse httpResponse;
        private Transport transport;

        public Builder() {
        }

        public Response.Builder setResponseEntity(ResponseEntity responseEntity) {
            this.responseEntity = responseEntity;
            this.httpRequest = new HttpRequest.Builder().setHttpRequestEntity(responseEntity.getHttpRequest()).build();
            this.httpResponse = new HttpResponse.Builder().setHttpResponseEntity(responseEntity.getHttpResponse()).build();
            this.transport = new Transport.Builder().setTransportEntity(responseEntity.getTransport()).build();
            return this;
        }

        public Response build(){
            return new Response(
                    getComponent(),
                    getPath(),
                    getName(),
                    getVersion(),
                    getPlatformVersion(),
                    getVariables(),
                    isDebug(),
                    getMapping(),
                    responseEntity,
                    httpRequest,
                    httpResponse,
                    transport
            );
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
        if (!super.equals(o)) {
            return false;
        }

        Response response = (Response) o;

        if (responseEntity != null ? !responseEntity.equals(response.responseEntity) : response.responseEntity != null) {
            return false;
        }
        if (httpRequest != null ? !httpRequest.equals(response.httpRequest) : response.httpRequest != null) {
            return false;
        }
        if (httpResponse != null ? !httpResponse.equals(response.httpResponse) : response.httpResponse != null) {
            return false;
        }
        return transport != null ? transport.equals(response.transport) : response.transport == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (responseEntity != null ? responseEntity.hashCode() : 0);
        result = 31 * result + (httpRequest != null ? httpRequest.hashCode() : 0);
        result = 31 * result + (httpResponse != null ? httpResponse.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseEntity=" + responseEntity +
                ", httpRequest=" + httpRequest +
                ", httpResponse=" + httpResponse +
                ", transport=" + transport +
                '}';
    }
}
