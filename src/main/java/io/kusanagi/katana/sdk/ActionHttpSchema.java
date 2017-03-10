package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.Arrays;

/**
 * Created by juan on 3/01/17.
 */
public class ActionHttpSchema {

    /**
     * Determines if the action is accessible to a HTTP request via the Gateway, defaults to true
     */
    @JsonProperty(Key.ACTION_HTTP_SCHEMA_GATEWAY)
    private boolean gateway;

    /**
     * Defines the path to resolve to the action, prepended with the value of the OPTIONAL http-base-path configuration
     * property, and which MAY containing parameters using "{" and "}" around a parameter name, defaults to "/"
     */
    @JsonProperty(Key.ACTION_HTTP_SCHEMA_PATH)

    private String path;
    /**
     * Defines the HTTP method expected for the request to the Gateway, allowed methods are "get", "put", "post",
     * "delete", "options", "head" and "patch", defaults to "get" if not defined
     */
    @JsonProperty(Key.ACTION_HTTP_SCHEMA_METHOD)
    private String method;

    /**
     * Defines the default HTTP parameter location, MUST be either "path", "query", "header", "form-data" or "body", and
     * defaults to "query"
     */
    @JsonProperty(Key.ACTION_HTTP_SCHEMA_INPUT)
    private String input;

    /**
     * Defines the expected MIME type of the HTTP request body for methods other than "get", "options" and "head", which
     * MAY include multiple MIME types, defaults to ["application/x-www-form-urlencoded", "multipart/form-data",
     * "text/plain"]
     */
    @JsonProperty(Key.ACTION_HTTP_SCHEMA_BODY)
    private String[] body;

    public ActionHttpSchema() {
        this.gateway = true;
        this.path = "/";
        this.method = "get";
        this.input = "query";
        this.body = new String[]{"text/plain"};
    }

    public ActionHttpSchema(ActionHttpSchema other) {
        this.gateway = other.gateway;
        this.path = other.path;
        this.method = other.method;
        this.input = other.input;
        this.body = other.body;
    }

    public boolean isGateway() {
        return gateway;
    }

    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setBody(String[] body) {
        this.body = body;
    }

    //SDK Methods

    /**
     * determine if the Gateway has access to the action.
     *
     * @return true if the Gateway has access to the action.
     */
    @JsonIgnore
    public boolean isAccesible() {
        return isGateway();
    }

    /**
     * @return the HTTP method for the action, or "get" if not defined.
     */
    public String getMethod() {
        return method;
    }

    /**
     * @return the URL path for the action, or an empty string if not defined. Note that if a value is defined for the
     * http-base-path property it would be prepended to the value of the http-path property if defined.
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the default location of parameters for the action, which MAY be "path", "query", "form-data", "header"
     * or "body", or "query" if not defined.
     */
    public String getInput() {
        return input;
    }

    /**
     * @return the expected MIME type of the HTTP request body, which MAY include multiple MIME types separated by a
     * comma (","), or "text/plain" if not defined.
     */
    public String[] getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionHttpSchema that = (ActionHttpSchema) o;

        if (gateway != that.gateway) {
            return false;
        }
        if (path != null ? !path.equals(that.path) : that.path != null) {
            return false;
        }
        if (method != null ? !method.equals(that.method) : that.method != null) {
            return false;
        }
        if (input != null ? !input.equals(that.input) : that.input != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        int result = gateway ? 1 : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (input != null ? input.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }

    @Override
    public String toString() {
        return "ActionHttpSchema{" +
                "gateway=" + gateway +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", input='" + input + '\'' +
                ", body='" + Arrays.toString(body) + '\'' +
                '}';
    }
}
