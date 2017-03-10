package io.kusanagi.katana.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kusanagi.katana.api.commands.Mapping;
import io.kusanagi.katana.api.component.Component;
import io.kusanagi.katana.api.component.ExceptionMessage;
import io.kusanagi.katana.api.component.utils.Logger;
import io.kusanagi.katana.sdk.Callable;
import io.kusanagi.katana.sdk.ServiceSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juan on 28/08/16.
 */
public class Api {

    protected Component component;

    protected String path;

    protected String name;

    protected String version;

    protected String platformVersion;

    protected Map<String, String> variables;

    protected boolean isDebug;

    protected Mapping mapping;

    /**
     * Default constructor
     */
    public Api() {
        // Default constructor to make possible the serialization of this object.
        this.variables = new HashMap<>();
        this.isDebug = false;
    }

    /**
     * Constructor with params
     *
     * @param path            Path of the call
     * @param name            Name of the Service
     * @param version         Version of the Service
     * @param platformVersion Version of the Platform
     * @param variables       Sdk variables
     * @param isDebug         Debug state
     */
    public Api(Component component, String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        this.component = component;
        this.path = path;
        this.name = name;
        this.version = version;
        this.platformVersion = platformVersion;
        this.variables = variables;
        this.isDebug = isDebug;
    }

    public Api(Api other) {
        this.path = other.path;
        this.name = other.name;
        this.version = other.version;
        this.platformVersion = other.platformVersion;
        this.variables = other.variables;
        this.isDebug = other.isDebug;
    }

    /**
     * Path setter
     *
     * @param path Path of the call
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Name setter
     *
     * @param name Name of the Service
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Version setter
     *
     * @param version Version of the service
     */
    public void setProtocolVersion(String version) {
        this.version = version;
    }

    /**
     * Platform version setter
     *
     * @param platformVersion Version of the platform
     */
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    /**
     * Variables setter
     *
     * @param variables Sdk variables
     */
    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    /**
     * Debug state setter
     *
     * @param debug Debug state
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    // SDK Method

    /**
     * @return Return whether or not the component is currently running in debug mode.
     */
    @JsonIgnore
    public boolean isDebug() {
        return isDebug;
    }

    /**
     * @return Return the version of the platform
     */
    @JsonIgnore
    public String getPlatformVersion() {
        return platformVersion;
    }

    /**
     * @return Return the path to the executing userland source file.
     */
    @JsonIgnore
    public String getPath() {
        return path;
    }

    /**
     * @return Return the unique name of the component.
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * @return Return the version of the component.
     */
    @JsonIgnore
    public String getVersion() {
        return version;
    }

    /**
     * @return Return an object, where each property name is the name of the variable, and the value to the variable value.
     */
    @JsonIgnore
    public Map<String, String> getVariables() {
        return variables;
    }

    /**
     * Get the variable with the REQUIRED case-sensitive name argument, and which MUST be returned as a string.
     *
     * @param name Name of the variable
     * @return Return the value of the variable
     */
    @JsonIgnore
    public String getVariable(String name) {
        return this.variables.get(name);
    }

    /**
     * @param name Name of the resource
     * @return Determine if a resource has been registered with the component using the REQUIRED case sensitive name argument.
     */
    public boolean hasResource(String name) {
        return this.component.hasResource(name);
    }

    /**
     * @param name Name of the resource
     * @return Return the resource registered with the component using the REQUIRED case sensitive name argument.
     */
    public Callable getResource(String name) {
        return this.component.getResource(name);
    }

    /**
     * Return an instance of the Mapping class for the Service defined by the REQUIRED case sensitive name and
     * version arguments, using the stored mapping of schemas.
     * If a Service with the specified name and version does not exist in the stored schema mapping then an exception
     * MUST be thrown with the following message:
     * Cannot resolve schema for Service: %SERVICE% (%VERSION%)
     * Where %SERVICE% is the name and %VERSION% is the version provided for the Service.
     *
     * @return The service schema object
     */
    @JsonIgnore
    public ServiceSchema getServiceSchema(String name, String version) {
        Map<String, Map<String, ServiceSchema>> serviceSchema = mapping.getServiceSchema();
        if (serviceSchema != null && serviceSchema.containsKey(name) && serviceSchema.get(name).containsKey(version)) {
            return serviceSchema.get(name).get(version);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessage.CANNOT_RESOLVE_SCHEMA_FOR_SERVICE, name, version));
    }

    /**
     * send a string representation of the value argument to stdout as a "DEBUG" log, with a length limit on the value
     * of 100,000 characters (not including the other elements of the log message, such as the timestamp), and return
     * true. If the component is not running in debug mode this function MUST NOT send a log, and SHOULD return false.
     *
     * @param value String to log
     * @return true if the value gets logged
     */
    public boolean log(String value) {
        Logger.log(Logger.DEBUG, value);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Api api = (Api) o;

        if (isDebug != api.isDebug) {
            return false;
        }
        if (component != null ? !component.equals(api.component) : api.component != null) {
            return false;
        }
        if (path != null ? !path.equals(api.path) : api.path != null) {
            return false;
        }
        if (name != null ? !name.equals(api.name) : api.name != null) {
            return false;
        }
        if (version != null ? !version.equals(api.version) : api.version != null) {
            return false;
        }
        if (platformVersion != null ? !platformVersion.equals(api.platformVersion) : api.platformVersion != null) {
            return false;
        }
        if (variables != null ? !variables.equals(api.variables) : api.variables != null) {
            return false;
        }
        return mapping != null ? mapping.equals(api.mapping) : api.mapping == null;
    }

    @Override
    public int hashCode() {
        int result = component != null ? component.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (platformVersion != null ? platformVersion.hashCode() : 0);
        result = 31 * result + (variables != null ? variables.hashCode() : 0);
        result = 31 * result + (isDebug ? 1 : 0);
        result = 31 * result + (mapping != null ? mapping.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Api{" +
                "component=" + component +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", variables=" + variables +
                ", isDebug=" + isDebug +
                ", mapping=" + mapping +
                '}';
    }
}
