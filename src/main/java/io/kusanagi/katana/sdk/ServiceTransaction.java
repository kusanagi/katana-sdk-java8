package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.List;

/**
 * Created by juan on 2/01/17.
 */
public class ServiceTransaction {

    /**
     * The name of the Service
     */
    @JsonProperty(Key.SERVICE_TRANSACTION_NAME)
    private String name;

    /**
     * The version of the Service
     */
    @JsonProperty(Key.SERVICE_TRANSACTION_VERSION)
    private String version;

    /**
     * The name of the action to call
     */
    @JsonProperty(Key.SERVICE_TRANSACTION_ACTION)
    private String action;

    /**
     * The name of the action that registers the transaction
     */
    @JsonProperty(Key.SERVICE_TRANSACTION_CALLER)
    private String caller;

    /**
     * Array of objects, each of which define a parameter for the Service call
     */
    @JsonProperty(Key.SERVICE_TRANSACTION_PARAMS)
    private List<Param> params;

    public ServiceTransaction() {
        // Default constructor to make possible the serialization of this object.
    }

    public ServiceTransaction(ServiceTransaction other) {
        this.name = other.name;
        this.version = other.version;
        this.action = other.action;
        this.caller = other.caller;
        this.params = other.params;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceTransaction that = (ServiceTransaction) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (version != null ? !version.equals(that.version) : that.version != null) {
            return false;
        }
        if (action != null ? !action.equals(that.action) : that.action != null) {
            return false;
        }
        if (caller != null ? !caller.equals(that.caller) : that.caller != null) {
            return false;
        }
        return params != null ? params.equals(that.params) : that.params == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (caller != null ? caller.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceTransaction{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                ", caller='" + caller + '\'' +
                ", params=" + params +
                '}';
    }
}
