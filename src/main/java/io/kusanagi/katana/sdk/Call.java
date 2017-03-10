package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.List;

/**
 * Created by juan on 18/11/16.
 */
public class Call {

    /**
     * The name of the Service to call
     */
    @JsonProperty(Key.CALL_NAME)
    private String name;

    /**
     * The version of the Service to call
     */
    @JsonProperty(Key.CALL_VERSION)
    private String version;

    /**
     * The name of the action to call
     */
    @JsonProperty(Key.CALL_ACTION)
    private String action;

    /**
     * The name of the action that registers the call
     */
    @JsonProperty(Key.CALL_CALLER)
    private String caller;

    /**
     * Array of objects, each of which define a parameter for the Service call
     */
    @JsonProperty(Key.CALL_PARAMS)
    private List<Param> params;

    /**
     * The public address of a Gateway for the Service in another Realm
     */
    @JsonProperty(Key.CALL_GATEWAY)
    private String gateway;

    /**
     * The timeout in milliseconds for the call to a Service in another Realm
     */
    @JsonProperty(Key.CALL_TIMEOUT)
    private int timeout;

    public Call() {
        // Default constructor to make possible the serialization of this object.
    }

    public Call(Call other) {
        this.name = other.name;
        this.version = other.version;
        this.action = other.action;
        this.params = other.params;
        this.gateway = other.gateway;
        this.timeout = other.timeout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Call call = (Call) o;

        if (timeout != call.timeout) {
            return false;
        }
        if (name != null ? !name.equals(call.name) : call.name != null) {
            return false;
        }
        if (version != null ? !version.equals(call.version) : call.version != null) {
            return false;
        }
        if (action != null ? !action.equals(call.action) : call.action != null) {
            return false;
        }
        if (params != null ? !params.equals(call.params) : call.params != null) {
            return false;
        }
        return gateway != null ? gateway.equals(call.gateway) : call.gateway == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (gateway != null ? gateway.hashCode() : 0);
        result = 31 * result + timeout;
        return result;
    }

    @Override
    public String toString() {
        return "Call{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                ", params=" + params +
                ", gateway='" + gateway + '\'' +
                ", timeout=" + timeout +
                '}';
    }
}
