package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.Api;
import com.katana.api.component.Component;
import com.katana.api.component.Constants;
import com.katana.api.component.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Request extends Api {
    /**
     * The meta-information about the payload
     */
    @JsonProperty(Key.REQUEST_META)
    private Meta meta;

    /**
     * The semantics of the request
     */
    @JsonProperty(Key.REQUEST_HTTP_REQUEST)
    private HttpRequest httpRequest;

    /**
     * The semantics of the Service to contact
     */
    @JsonProperty(Key.REQUEST_REQUEST_CALL)
    private RequestCall requestCall;

    public Request() {
        // Default constructor to make possible the serialization of this object.
    }

    /**
     * @param path
     * @param name
     * @param version
     * @param platformVersion
     * @param variables
     * @param isDebug
     */
    public Request(Component component, String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(component, path, name, version, platformVersion, variables, isDebug);
    }

    public Request(Request other) {
        super(other);
        this.meta = other.meta;
        this.httpRequest = other.httpRequest;
        this.requestCall = other.requestCall;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public RequestCall getRequestCall() {
        return this.requestCall;
    }

    public void setRequestCall(RequestCall requestCall) {
        this.requestCall = requestCall;
    }

    // SDK Methods

    /**
     * return the protocol implemented by the Gateway component handling the request.
     *
     * @return the protocol.
     */
    @JsonIgnore
    public String getGatewayProtocol() {
        return this.meta.getProtocol();
    }

    /**
     * return the public address of the Gateway component handling the request.
     *
     * @return the public address
     */
    @JsonIgnore
    public String getGatewayAddress() {
        return this.meta.getGateway().get(1);
    }

    /**
     * return the IP address and port of the client which sent the request.
     *
     * @return the IP address
     */
    @JsonIgnore
    public String getClientAddress() {
        return this.meta.getClient();
    }

    /**
     * @return the name currently defined for the Service, or an empty string if not defined.
     */
    @JsonIgnore
    public String getServiceName() {
        return this.requestCall.getService();
    }

    /**
     * Set the name for the Service with the value specified by the REQUIRED service argument.
     *
     * @param name Name of the service
     * @return Return true if the operation was successful
     */
    public Request setServiceName(String name) {
        this.requestCall.setService(name);
        return this;
    }

    /**
     * @return Return the version currently defined for the Service, or an empty string if not defined.
     */
    @JsonIgnore
    public String getServiceVersion() {
        return this.requestCall.getVersion();
    }

    /**
     * Set the version for the Service with the value specified by the REQUIRED version argument.
     *
     * @param version Version for the service
     * @return Return true if the operation was successful
     */
    public Request setServiceVersion(String version) {
        this.requestCall.setVersion(version);
        return this;
    }

    /**
     * @return Return the name currently defined for the Service action, or an empty string if not defined.
     */
    @JsonIgnore
    public String getActionName() {
        return this.requestCall.getAction();
    }

    /**
     * Set the name for the Service action with the value specified by the REQUIRED action argument.
     *
     * @param action Action name
     * @return Return true if the operation was successful
     */
    public Request setActionName(String action) {
        this.requestCall.setAction(action);
        return this;
    }

    /**
     * determine if a parameter with the name specified by the REQUIRED case sensitive name argument has been defined.
     *
     * @param name param name
     * @return true if the param has been defined
     */
    public boolean hasParam(String name) {
        if (this.requestCall.getParams() == null) {
            return false;
        }
        for (Param param : this.requestCall.getParams()) {
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
    @JsonIgnore
    public Param getParam(String name) {
        if (this.requestCall.getParams() == null) {
            this.requestCall.setParams(new ArrayList<>());
        }
        for (Param param : this.requestCall.getParams()) {
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
    @JsonIgnore
    public List<Param> getParams() {
        if (this.requestCall.getParams() == null) {
            return new ArrayList<>();
        } else {
            return this.requestCall.getParams();
        }
    }

    /**
     * add the parameter provided as the param argument.
     *
     * @param param param to be added
     * @return the instance of the request
     */
    public Request setParam(Param param) {
        if (this.requestCall == null) {
            this.requestCall.setParams(new ArrayList<>());
        }
        this.requestCall.getParams().add(param);
        return this;
    }

    /**
     * create a new parameter with the REQUIRED name argument, and which MUST be returned as a Param object.
     * <p>
     * If the OPTIONAL value or type arguments are specified these MUST also be applied to the Param object. The value of
     * the type argument MUST be either "null", "boolean", "integer", "float", "string", "array" or "object", where any
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

    /**
     * @param code Response code
     * @param text Response text
     * @return Return a new instance of the Response class, setting the HTTP status code with the value specified by the
     * REQUIRED code argument and the status text with the REQUIRED text argument.
     */
    public Response newResponse(int code, String text) {
        HttpResponse httpResponse = new HttpResponse();
        if (getGatewayProtocol().equals(Constants.KATANA_PROTOCOL_HTTP)) {
            httpResponse.setStatus(code, text);
        }

        Response response = new Response(this.component, this.path, this.name, this.version, this.platformVersion, this.variables, this.isDebug);
        response.setTransport(new Transport());
        response.setHttpResponse(httpResponse);
        return response;
    }

    /**
     * @return Return the instance of the HttpRequest class which contains the HTTP semantics of the request made to
     * the Gateway component.
     */
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Request request = (Request) o;

        if (getMeta() != null ? !getMeta().equals(request.getMeta()) : request.getMeta() != null) {
            return false;
        }
        if (getHttpRequest() != null ? !getHttpRequest().equals(request.getHttpRequest()) : request.getHttpRequest() != null) {
            return false;
        }
        return getRequestCall() != null ? getRequestCall().equals(request.getRequestCall()) : request.getRequestCall() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMeta() != null ? getMeta().hashCode() : 0);
        result = 31 * result + (getHttpRequest() != null ? getHttpRequest().hashCode() : 0);
        result = 31 * result + (getRequestCall() != null ? getRequestCall().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", requestCall=" + requestCall +
                "} " + super.toString();
    }
}
