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

import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.commands.Mapping;
import io.kusanagi.katana.api.component.Component;
import io.kusanagi.katana.api.component.Constants;
import io.kusanagi.katana.api.serializers.HttpResponseEntity;
import io.kusanagi.katana.api.serializers.RequestEntity;
import io.kusanagi.katana.api.serializers.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Request extends Api {

    private RequestEntity requestEntity;

    private HttpRequest httpRequest;

    public Request() {
        // Default constructor to make possible the serialization of this object.
    }

    public Request(Component component, String path, String name, String version, String platformVersion,
                   Map<String, String> variables, boolean isDebug, Mapping mapping, RequestEntity requestEntity, HttpRequest httpRequest) {
        super(component, path, name, version, platformVersion, variables, isDebug, mapping);
        this.requestEntity = requestEntity;
        this.httpRequest = httpRequest;
    }

    public Request(Request other) {
        super(other);
        this.requestEntity = other.requestEntity;
        this.httpRequest = other.httpRequest;
    }

    public RequestCall getRequestCall() {
        return requestEntity.getRequestCall();
    }

    // SDK Methods

    /**
     *
     * @return return the UUID of the request.
     */
    public String getId() { //TODO
        return requestEntity.getMeta().getId();
    }

    /**
     *
     * @return return the creation datetime of the request.
     */
    public String getTimeStamp(){ //TODO
        return requestEntity.getMeta().getDatetime();
    }

    /**
     * return the protocol implemented by the Gateway component handling the request.
     *
     * @return the protocol.
     */
    public String getGatewayProtocol() {
        return requestEntity.getMeta().getProtocol();
    }

    /**
     * return the public address of the Gateway component handling the request.
     *
     * @return the public address
     */
    public String getGatewayAddress() {
        return requestEntity.getMeta().getGateway().get(1);
    }

    /**
     * return the IP address and port of the client which sent the request.
     *
     * @return the IP address
     */
    public String getClientAddress() {
        return requestEntity.getMeta().getClient();
    }

    /**
     * register a request attribute with the REQUIRED name and REQUIRED value arguments. If an attribute with the
     * specified name already exists the value MUST be replaced with value. The function MUST NOT accept any other
     * data type for a value other than string.
     * @param name attribute name
     * @param value attribute value
     * @return return this request
     */
    public Request setAttribute(String name, String value) {
        requestEntity.getMeta().getAttributes().put(name, value);
        return this;
    }

    /**
     * @return the name currently defined for the Service, or an empty string if not defined.
     */
    public String getServiceName() {
        return requestEntity.getRequestCall().getService();
    }

    /**
     * Set the name for the Service with the value specified by the REQUIRED service argument.
     *
     * @param name Name of the service
     * @return Return true if the operation was successful
     */
    public Request setServiceName(String name) {
        requestEntity.getRequestCall().setService(name);
        return this;
    }

    /**
     * @return Return the version currently defined for the Service, or an empty string if not defined.
     */
    public String getServiceVersion() {
        return requestEntity.getRequestCall().getVersion();
    }

    /**
     * Set the version for the Service with the value specified by the REQUIRED version argument.
     *
     * @param version Version for the service
     * @return Return true if the operation was successful
     */
    public Request setServiceVersion(String version) {
        requestEntity.getRequestCall().setVersion(version);
        return this;
    }

    /**
     * @return Return the name currently defined for the Service action, or an empty string if not defined.
     */
    public String getActionName() {
        return requestEntity.getRequestCall().getAction();
    }

    /**
     * Set the name for the Service action with the value specified by the REQUIRED action argument.
     *
     * @param action Action name
     * @return Return true if the operation was successful
     */
    public Request setActionName(String action) {
        requestEntity.getRequestCall().setAction(action);
        return this;
    }

    /**
     * determine if a parameter with the name specified by the REQUIRED case sensitive name argument has been defined.
     *
     * @param name param name
     * @return true if the param has been defined
     */
    public boolean hasParam(String name) {
        if (requestEntity.getRequestCall().getParams() == null) {
            return false;
        }
        for (Param param : requestEntity.getRequestCall().getParams()) {
            if (param.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get the parameter with the REQUIRED case sensitive name argument, and which MUST be returned as a Param object.
     * <p>
     * If the parameter was not provided in the request a Param object MUST be returned with the exists property set to
     * false.
     *
     * @param name param name
     * @return the param with the name argument
     */
    public Param getParam(String name) {
        if (requestEntity.getRequestCall().getParams() == null) {
            requestEntity.getRequestCall().setParams(new ArrayList<>());
        }
        for (Param param : requestEntity.getRequestCall().getParams()) {
            if (param.getName().equals(name)) {
                return param;
            }
        }

        Param param = new Param();
        param.setName(name);
        param.setExists(false);
        return param;
    }

    /**
     * @return all the parameters and which MUST be returned as an array of Param objects. If no parameters are found an
     * empty array MUST be returned.
     */
    public List<Param> getParams() {
        if (requestEntity.getRequestCall().getParams() == null) {
            return new ArrayList<>();
        } else {
            return requestEntity.getRequestCall().getParams();
        }
    }

    /**
     * add the parameter provided as the param argument.
     *
     * @param param param to be added
     * @return the instance of the request
     */
    public Request setParam(Param param) {
        if (requestEntity.getRequestCall() == null) {
            requestEntity.getRequestCall().setParams(new ArrayList<>());
        }
        requestEntity.getRequestCall().getParams().add(param);
        return this;
    }

    /**
     * create a new parameter with the REQUIRED name argument, and which MUST be returned as a Param object.
     * <p>
     * If the OPTIONAL value or type arguments are specified these MUST also be applied to the Param object. The value of
     * the type argument MUST be either "null", "boolean", "integer", "float", "string", "array", "object" or "binary", where any
     * other value MUST be accepted as "string".
     *
     * @param name  parameter name
     * @param value parameter value
     * @param type  parameter data type
     * @return an instance of the parameter
     */
    public Param newParam(String name, String value, String type) {
        Param param = new Param();
        param.setName(name);
        param.setType(type);
        param.setValue(value);
        return param;
    }

    public Param newParam(String name) {
        return newParam(name, "", "");
    }

    /**
     * @param code ResponseEntity code
     * @param text ResponseEntity text
     * @return Return a new instance of the ResponseEntity class, setting the HTTP status code with the value specified by the
     * REQUIRED code argument and the status text with the REQUIRED text argument.
     */
    public Response newResponse(int code, String text) {
        HttpResponse httpResponse = new HttpResponse.Builder().setHttpResponseEntity(new HttpResponseEntity()).build();
        if (getGatewayProtocol().equals(Constants.KATANA_PROTOCOL_HTTP)) {
            httpResponse.setStatus(code, text);
        }

        return new Response.Builder()
                .setResponseEntity(new ResponseEntity())
                .setComponent(this.component)
                .setPath(this.path)
                .setName(this.name)
                .setVersion(this.version)
                .setPlatformVersion(this.platformVersion)
                .setVariables(this.variables)
                .setDebug(this.isDebug)
                .setMapping(this.mapping)
                .build();
    }

    public Response newResponse() {
        return newResponse(200, "OK");
    }

    /**
     * @return Return the instance of the HttpRequest class which contains the HTTP semantics of the request made to
     * the Gateway component.
     */
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    public static class Builder extends Api.Builder<Request>{

        private RequestEntity requestEntity;
        private HttpRequest httpRequest;

        public Builder() {
        }

        public Request.Builder setRequestEntity(RequestEntity requestEntity) {
            this.requestEntity = requestEntity;
            this.httpRequest = new HttpRequest.Builder().setHttpRequestEntity(requestEntity.getHttpRequest()).build();
            return this;
        }

        public Request build(){
            return new Request(
                    getComponent(),
                    getPath(),
                    getName(),
                    getVersion(),
                    getPlatformVersion(),
                    getVariables(),
                    isDebug(),
                    getMapping(),
                    requestEntity,
                    httpRequest
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

        Request request = (Request) o;

        if (requestEntity != null ? !requestEntity.equals(request.requestEntity) : request.requestEntity != null) {
            return false;
        }
        return httpRequest != null ? httpRequest.equals(request.httpRequest) : request.httpRequest == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (requestEntity != null ? requestEntity.hashCode() : 0);
        result = 31 * result + (httpRequest != null ? httpRequest.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestEntity=" + requestEntity +
                ", httpRequest=" + httpRequest +
                '}';
    }
}
