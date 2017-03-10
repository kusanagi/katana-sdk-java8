package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.ExceptionMessage;
import io.kusanagi.katana.api.component.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 13/01/17.
 */
public class ServiceSchema {

    private String name;

    private String version;

    /**
     * Defines the internal address for the host (string)
     */
    @JsonProperty(Key.SERVICE_SCHEMA_ADDRESS)
    private String address;

    /**
     * Determines if the Service has a file server configured, defaults to false if not defined (boolean)
     */
    @JsonProperty(Key.SERVICE_SCHEMA_FILES)
    private boolean files;

    /**
     * The HTTP semantics defined for the Service (object)
     */
    @JsonProperty(Key.SERVICE_SCHEMA_HTTP_SCHEMA)
    private HttpSchema httpSchema;

    /**
     * Defines the actions for the Service as an object, where each property is the action name, and the value an object
     * containing the action schema (object)
     */
    @JsonProperty(Key.SERVICE_SCHEMA_ACTION_SCHEMAS)
    private Map<String, ActionSchema> actionSchemas;

    public ServiceSchema() {
        files = false;
        httpSchema = new HttpSchema();
        actionSchemas = new HashMap<>();
    }

    public ServiceSchema(ServiceSchema other) {
        this.address = other.address;
        this.files = other.files;
        this.httpSchema = other.httpSchema;
        this.actionSchemas = other.actionSchemas;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFiles(boolean files) {
        this.files = files;
    }

    public boolean isFiles() {
        return files;
    }

    public void setHttpSchema(HttpSchema httpSchema) {
        this.httpSchema = httpSchema;
    }

    public Map<String, ActionSchema> getActionSchemas() {
        return actionSchemas;
    }

    public void setActionSchemas(Map<String, ActionSchema> actionSchemas) {
        this.actionSchemas = actionSchemas;
    }

    //SDK Methods

    /**
     * @return the unique name of the Service.
     */
    @JsonIgnore
    public String getName() {
        return this.name;
    }

    /**
     * @return the version of the Service.
     */
    @JsonIgnore
    public String getVersion() {
        return this.version;
    }

    /**
     * determine if the Service has a configured file server.
     *
     * @return true if the service has a configured file server
     */
    public boolean hasFileServer() {
        return isFiles();
    }

    /**
     * @return an array with the names of all the actions defined for the Service, in the order in which they are defined in the configuration file.
     */
    @JsonIgnore
    public List<String> getActions() {
        List<String> actions = new ArrayList<>();

        for (String s : this.actionSchemas.keySet()) {
            actions.add(s);
        }
        return actions;
    }

    /**
     * determine if an action schema exists for the REQUIRED case sensitive name argument.
     *
     * @param name Action name
     * @return true if the action exists
     */
    public boolean hasAction(String name) {
        return this.actionSchemas.containsKey(name);
    }

    /**
     * an instance of the ActionSchema class for the action defined by the REQUIRED case sensitive name argument, using
     * the stored mapping of schemas.
     *
     * @param name Action name
     * @return the Action Schema object
     */
    public ActionSchema getActionSchema(String name) {
        if (!this.actionSchemas.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.CANNOT_RESOLVE_SCHEMA_FOR_ACTION, name));
        }

        return this.actionSchemas.get(name);
    }

    /**
     * an instance of the HttpServiceSchema class for the Service using the stored mapping of schemas.
     *
     * @return the Http Action Schema object
     */
    public HttpSchema getHttpSchema() {
        return this.httpSchema;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceSchema)) {
            return false;
        }

        ServiceSchema that = (ServiceSchema) o;

        if (isFiles() != that.isFiles()) {
            return false;
        }
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) {
            return false;
        }
        if (getHttpSchema() != null ? !getHttpSchema().equals(that.getHttpSchema()) : that.getHttpSchema() != null) {
            return false;
        }
        return getActions() != null ? getActions().equals(that.getActions()) : that.getActions() == null;

    }

    @Override
    public int hashCode() {
        int result = getAddress() != null ? getAddress().hashCode() : 0;
        result = 31 * result + (isFiles() ? 1 : 0);
        result = 31 * result + (getHttpSchema() != null ? getHttpSchema().hashCode() : 0);
        result = 31 * result + (getActions() != null ? getActions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceSchema{" +
                "address='" + address + '\'' +
                ", files=" + files +
                ", httpSchema=" + httpSchema +
                ", actionSchemas=" + actionSchemas +
                '}';
    }
}
