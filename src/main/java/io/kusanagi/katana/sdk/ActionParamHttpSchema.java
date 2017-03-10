package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

/**
 * Created by juan on 3/01/17.
 */
public class ActionParamHttpSchema {

    /**
     * Determines if the action parameter is writable by a HTTP request via the Gateway, if false and the parameter is
     * provided in the HTTP request it MUST be ignored, defaults to true
     */
    @JsonProperty(Key.ACTION_PARAM_HTTP_SCHEMA_GATEWAY)
    private boolean gateway;

    /**
     * Defines the location of the parameter in the HTTP request, which MUST be "path", "query", "header", "form-data"
     * or "body", and where if "path" the value of the name property (or a value for http-param was defined) MUST be
     * present in the template for the http-path property of the action element, defaults to "query"
     */
    @JsonProperty(Key.ACTION_PARAM_HTTP_SCHEMA_INPUT)
    private String input;

    /**
     * Defines the name of the action parameter as specified via HTTP to be mapped to the name property
     */
    @JsonProperty(Key.ACTION_PARAM_HTTP_SCHEMA_PARAM)
    private String param;

    public ActionParamHttpSchema() {
        this.gateway = true;
        this.input = "query";
    }

    public ActionParamHttpSchema(ActionParamHttpSchema other) {
        this.gateway = other.gateway;
        this.input = other.input;
        this.param = other.param;
    }

    public boolean isGateway() {
        return gateway;
    }

    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setParam(String param) {
        this.param = param;
    }

    //SDK Methods

    /**
     * determine if the Gateway can write to the parameter.
     *
     * @return true if the Gateway can write to the parameter.
     */
    @JsonIgnore
    public boolean isAccessible() {
        return isGateway();
    }

    /**
     * @return the location of the parameter, which MAY be "path", "query", "form-data", "header" or "body", or "query"
     * if not defined.
     */
    public String getInput() {
        return input;
    }

    /**
     * @return the name as specified via HTTP to be mapped to the name property, or the value of the name property.
     */
    public String getParam() {
        return param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionParamHttpSchema)) {
            return false;
        }

        ActionParamHttpSchema that = (ActionParamHttpSchema) o;

        if (isGateway() != that.isGateway()) {
            return false;
        }
        if (getInput() != null ? !getInput().equals(that.getInput()) : that.getInput() != null) {
            return false;
        }
        return getParam() != null ? getParam().equals(that.getParam()) : that.getParam() == null;

    }

    @Override
    public int hashCode() {
        int result = isGateway() ? 1 : 0;
        result = 31 * result + (getInput() != null ? getInput().hashCode() : 0);
        result = 31 * result + (getParam() != null ? getParam().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionParamHttpSchema{" +
                "gateway=" + gateway +
                ", input='" + input + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
