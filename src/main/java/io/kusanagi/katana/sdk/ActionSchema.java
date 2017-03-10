package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.ExceptionMessage;
import io.kusanagi.katana.api.component.Key;

import java.util.*;

/**
 * Created by juan on 3/01/17.
 */
public class ActionSchema {

    private String name;

    /**
     * Defines the number of milliseconds the action is permitted to execute for before considering a timeout by the
     * component making the request, defaults to 1000 milliseconds if not defined
     */
    @JsonProperty(Key.ACTION_SCHEMA_TIMEOUT)
    private int timeout;

    /**
     * Defines the path to the entity in each object returned by the action, defaults to the entity object itself if not
     * defined
     */
    @JsonProperty(Key.ACTION_SCHEMA_ENTITY_PATH)
    private String entityPath;

    /**
     * Defines the delimiter used to specify the traversal between objects in the value provided in the OPTIONAL "e"
     * (entity path) property, if the "e" (entity path) property is not defined the delimiter MUST be ignored, defaults
     * to "/" if not defined
     */
    @JsonProperty(Key.ACTION_SCHEMA_PATH_DELIMITER)
    private String pathDelimiter;

    /**
     * Defines the name of the property in the entity object which defines the primary key for the entity, defaults to
     * "id" if not defined
     */
    @JsonProperty(Key.ACTION_SCHEMA_PRIMARY)
    private String primaryKey;

    /**
     * Determines if the action returns a collection of entities instead of a single entity object, defaults to false if
     * not defined
     */
    @JsonProperty(Key.ACTION_SCHEMA_COLLECTION)
    private boolean collection;

    /**
     * Defines the run-time calls performed from the action to internal Services within the same Realm, where each item
     * is an array, of which the first item is the Service name, the second item the version, and the third item the
     * action to be called
     */
    @JsonProperty(Key.ACTION_SCHEMA_CALLS)
    private String[][] calls;

    /**
     * Defines the deferred calls performed from the action to internal Services within the same Realm, where each item
     * is an array, of which the first item is the Service name, the second item the version, and the third item the
     * action to be called
     */
    @JsonProperty(Key.ACTION_SCHEMA_DEFERRED_CALLS)
    private String[][] deferredCalls;

    /**
     * Defines the calls performed from the action to external Services in another Realm, where each item is an array,
     * of which the first item is the public address of a Gateway for that Realm, the second item the Service name, the
     * third item the version, and the fourth item the action to be called
     */
    @JsonProperty(Key.ACTION_SCHEMA_REMOTE_CALLS)
    private String[][] remoteCalls;

    /**
     * Defines the Transport fallback schema
     */
    @JsonProperty(Key.ACTION_SCHEMA_FALLBACKS)
    private Map<String, TransportSchema> fallbacks;

    /**
     * Defines if the action has been deprecated, defaults to false if not defined
     */
    @JsonProperty(Key.ACTION_SCHEMA_DEPRECATED)
    private boolean deprecated;

    /**
     * The HTTP semantics defined for the action
     */
    @JsonProperty(Key.ACTION_SCHEMA_HTTP)
    private ActionHttpSchema http;

    /**
     * Defines the action parameters as an object, where each property is the parameter name, and the value an object
     * containing the action parameter schema
     */
    @JsonProperty(Key.ACTION_SCHEMA_PARAMS)
    private Map<String, ActionParamSchema> params;

    /**
     * Defines the file parameters as an object, where each property is the parameter name, and the value an object
     * containing the file parameter schema
     */
    @JsonProperty(Key.ACTION_SCHEMA_FILES)
    private Map<String, FileSchema> files;

    /**
     * Defines the entity schema
     */
    @JsonProperty(Key.ACTION_SCHEMA_ENTITY)
    private EntitySchema entity;

    /**
     * Defines the action relations as an array, where each item is an object containing a relation schema
     */
    @JsonProperty(Key.ACTION_SCHEMA_RELATIONS)
    private List<RelationSchema> relations;

    /**
     * The return value schema, not present if not defined
     */
    @JsonProperty(Key.ACTION_SCHEMA_RETURN_OBJECT)
    private ReturnSchema returnObject;

    public ActionSchema() {
        timeout = 1000;
        pathDelimiter = "/";
        primaryKey = "id";
        collection = false;
        deprecated = false;
        files = new HashMap<>();
        entityPath = "";
        entity = new EntitySchema();
        relations = new ArrayList<>();
        calls = new String[0][0];
        deferredCalls = new String[0][0];
        remoteCalls = new String[0][0];
    }

    public ActionSchema(ActionSchema other) {
        this.timeout = other.timeout;
        this.entityPath = other.entityPath;
        this.pathDelimiter = other.pathDelimiter;
        this.primaryKey = other.primaryKey;
        this.collection = other.collection;
        this.calls = other.calls;
        this.remoteCalls = other.remoteCalls;
        this.fallbacks = other.fallbacks;
        this.deprecated = other.deprecated;
        this.http = other.http;
        this.params = other.params;
        this.files = other.files;
        this.entity = other.entity;
        this.relations = other.relations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public void setPathDelimiter(String pathDelimiter) {
        this.pathDelimiter = pathDelimiter;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public void setCalls(String[][] calls) {
        this.calls = calls;
    }

    public String[][] getDeferredCalls() {
        return deferredCalls;
    }

    public void setDeferredCalls(String[][] deferredCalls) {
        this.deferredCalls = deferredCalls;
    }

    public void setRemoteCalls(String[][] remoteCalls) {
        this.remoteCalls = remoteCalls;
    }

    public Map<String, TransportSchema> getFallbacks() {
        return fallbacks;
    }

    public void setFallbacks(Map<String, TransportSchema> fallbacks) {
        this.fallbacks = fallbacks;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public ActionHttpSchema getHttp() {
        return http;
    }

    public void setHttp(ActionHttpSchema http) {
        this.http = http;
    }

    public void setParams(Map<String, ActionParamSchema> params) {
        this.params = params;
    }

    public void setFiles(Map<String, FileSchema> files) {
        this.files = files;
    }

    public void setEntity(EntitySchema entity) {
        this.entity = entity;
    }

    public void setRelations(List<RelationSchema> relations) {
        this.relations = relations;
    }

    //SDM Methods

    /**
     * determine if the action has been deprecated, defaults to false.
     *
     * @return true if the action has been deprecated
     */
    public boolean isDeprecated() {
        return deprecated;
    }

    /**
     * determine if the action returns a collection of entities. If false the action SHOULD return a single entity,
     * defaults to false.
     *
     * @return true if the action returns a collection of entities
     */
    public boolean isCollection() {
        return collection;
    }

    /**
     * @return return the unique name of the action.
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * @return the path to the entity, or an empty string if not defined.
     */
    public String getEntityPath() {
        return entityPath;
    }

    /**
     * @return the delimiter to use for the entity path, or "/" if not defined.
     */
    public String getPathDelimiter() {
        return pathDelimiter;
    }

    /**
     * @return the name of the property in the entity which contains the primary key, or "id" if not defined.
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * take an object and return the entity part, based upon the entity-path and path-delimiter properties in the
     * action configuration.
     *
     * @param data
     * @return
     */
    public Object resolveEntity(Map<String, Object> data) {
        if (this.entityPath == null || this.entityPath.isEmpty()) {
            return data;
        }
        String[] keys = this.entityPath.split(this.pathDelimiter);
        return getEntityObject(data, keys, 0);
    }

    private Object getEntityObject(Map<String, Object> data, String[] keys, int keyIndex) {
        if (!data.containsKey(keys[keyIndex])) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.CANNOT_RESOLVE_ENTITY, this.name));
        }

        if (keyIndex < keys.length - 1) {
            return getEntityObject((Map)data.get(keys[keyIndex]), keys, keyIndex + 1);
        } else {
            return data.get(keys[keyIndex]);
        }
    }

    /**
     * determine if an entity definition exists for the action.
     *
     * @return true if an entity definition exists for the action.
     */
    public boolean hasEntity() {
        return this.entity != null;
    }

    /**
     * @return the entity definition as an object, or an empty object if not defined.
     */
    public EntitySchema getEntity() {
        return entity;
    }

    /**
     * determine if any relations exists for the action.
     *
     * @return true if any relations exists for the action.
     */
    public boolean hasRelations() {
        return this.relations != null && !this.relations.isEmpty();
    }

    /**
     * @return the relations as an array, where each item is an array containing the relation type and the Service name,
     * or an empty array if no relations are defined.
     */
    public List<RelationSchema> getRelations() {
        return relations;
    }

    /**
     * determine if a run-time call exists for a Service with the given case-sensitive name argument, and OPTIONAL
     * version and action arguments.
     *
     * @param name    Service name
     * @param version Service version
     * @param action  Action name
     * @return true if a run-time call exists for a Service with the given case-sensitive name argument, and OPTIONAL
     * version and action arguments.
     */
    public boolean hasCall(String name, String version, String action) {
        for (String[] call : this.calls) {
            if (call[0].equals(name) &&
                    (call[1].equals(version) || version == null || version.isEmpty()) &&
                    (call[2].equals(action) || action == null || action.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    /**
     * determine if any run-time calls exist for the action.
     *
     * @return true if any run-time calls exist for the action.
     */
    public boolean hasCalls() {
        return this.calls != null && this.calls.length != 0;
    }

    /**
     * @return the run-time calls as an array, where each item is an array containing the Service name, the Service version and
     * the action name as a string, or an empty array if no run-time calls are defined.
     */
    public String[][] getCalls() {
        return calls;
    }

    /**
     * determine if a deferred call exists for a Service with the given case-sensitive name argument, and OPTIONAL
     * version and action arguments.
     * version and action arguments.
     *
     * @param name    Service name
     * @param version Service version
     * @param action  Action name
     * @return true if a deferred call exists for a Service with the given case-sensitive name argument, and OPTIONAL
     * version and action arguments.
     */
    public boolean hasDeferCall(String name, String version, String action) {
        for (String[] call : this.deferredCalls) {
            if (call[0].equals(name) && call[1].equals(version) && call[2].equals(action)) {
                return true;
            }
        }
        return false;
    }

    /**
     * determine if any deferred calls exist for the action.
     *
     * @return true if any deferred calls exist for the action.
     */
    public boolean hasDeferCalls() {
        return this.deferredCalls != null && this.deferredCalls.length != 0;
    }

    /**
     * @return the deferred calls as an array, where each item is an array containing the Service name, the Service
     * version and the action name as a string, or an empty array if no deferred calls are defined.
     */
    @JsonIgnore
    public String[][] getDeferCalls() {
        return getDeferredCalls();
    }


    /**
     * determine if a remote call exists for a Service with the given case-sensitive name argument, and OPTIONAL
     * version and action arguments.
     *
     * @param name    Service name
     * @param version Service version
     * @param action  Action name
     * @return true if a remote call exists for a Service with the given case-sensitive name argument, and OPTIONAL
     * version and action arguments.
     */
    public boolean hasRemoteCall(String address, String name, String version, String action) {
        for (String[] call : this.remoteCalls) {
            if (call[0].equals(address) && call[1].equals(name) &&
                    call[2].equals(version) && call[3].equals(action)) {
                return true;
            }
        }
        return false;
    }


    /**
     * determine if any remote calls exist for the action.
     *
     * @return true if any remote calls exist for the action.
     */
    public boolean hasRemoteCalls() {
        return this.remoteCalls != null && this.remoteCalls.length != 0;
    }

    /**
     * @return the remote calls as an array, where each item is an array containing the Service name, the Service
     * version and the action name as a string, or an empty array if no deferred calls are defined.
     */
    public String[][] getRemoteCalls() {
        return remoteCalls;
    }

    /**
     * determine if a return value is defined for the action.
     *
     * @return true if a return value is defined for the action.
     */
    public boolean hasReturn() {
        return this.returnObject != null || this.returnObject.getType() == null;
    }

    /**
     * @return the data type of the returned value, which MAY be "null", "boolean", "integer", "float", "string",
     * "array" or "object".
     */
    @JsonIgnore
    public String getReturnType() {
        return this.returnObject.getType();
    }

    /**
     * @return an array with the parameters defined for the action, in which each item is the parameter name, in the
     * order in which they are defined in the configuration file.
     */
    public Map<String, ActionParamSchema> getParams() {
        return params;
    }

    /**
     * determine if a parameter schema exists for the REQUIRED case sensitive name argument.
     *
     * @param name Parameter name
     * @return true if a parameter schema exists for the REQUIRED case sensitive name argument.
     */
    public boolean hasParam(String name) {
        return this.params != null && this.params.containsKey(name);
    }

    /**
     * @param name Parameter name
     * @return an instance of the ParamSchema class for the parameter defined by the REQUIRED case sensitive name argument using the stored mapping of schemas.
     */
    public ActionParamSchema getParamSchema(String name) {
        if (!this.params.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.CANNOT_RESOLVE_SCHEMA_FOR_PARAMETER, name));
        }

        return this.params.get(name);
    }

    /**
     * @return an array with the files defined for the action, in which each item is the file parameter name, in the
     * order in which they are defined in the configuration file.
     */
    public Map<String, FileSchema> getFiles() {
        return files;
    }

    /**
     * determine if a file parameter schema exists for the REQUIRED case sensitive name argument.
     *
     * @param name File name
     * @return true if a file parameter schema exists for the REQUIRED case sensitive name argument.
     */
    public boolean hasFile(String name) {
        return this.files != null && this.files.containsKey(name);
    }

    /**
     * @param name File name
     * @return an instance of the FileSchema class for the file parameter defined by the REQUIRED case sensitive name
     * argument using the stored mapping of schemas.
     */
    public FileSchema getFileSchema(String name) {
        if (!this.files.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.CANNOT_RESOLVE_SCHEMA_FOR_FILE, name));
        }

        FileSchema fileSchema = this.files.get(name);
        fileSchema.setName(name);
        return fileSchema;
    }

    /**
     * @return an instance of the HttpActionSchema class for the action using the stored mapping of schemas.
     */
    @JsonIgnore
    public ActionHttpSchema getHttpSchema() {
        return getHttp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionSchema that = (ActionSchema) o;

        if (timeout != that.timeout) {
            return false;
        }
        if (collection != that.collection) {
            return false;
        }
        if (deprecated != that.deprecated) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (entityPath != null ? !entityPath.equals(that.entityPath) : that.entityPath != null) {
            return false;
        }
        if (pathDelimiter != null ? !pathDelimiter.equals(that.pathDelimiter) : that.pathDelimiter != null) {
            return false;
        }
        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null) {
            return false;
        }
        if (!Arrays.deepEquals(calls, that.calls)) {
            return false;
        }
        if (!Arrays.deepEquals(remoteCalls, that.remoteCalls)) {
            return false;
        }
        if (fallbacks != null ? !fallbacks.equals(that.fallbacks) : that.fallbacks != null) {
            return false;
        }
        if (http != null ? !http.equals(that.http) : that.http != null) {
            return false;
        }
        if (params != null ? !params.equals(that.params) : that.params != null) {
            return false;
        }
        if (files != null ? !files.equals(that.files) : that.files != null) {
            return false;
        }
        if (entity != null ? !entity.equals(that.entity) : that.entity != null) {
            return false;
        }
        return relations != null ? relations.equals(that.relations) : that.relations == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + timeout;
        result = 31 * result + (entityPath != null ? entityPath.hashCode() : 0);
        result = 31 * result + (pathDelimiter != null ? pathDelimiter.hashCode() : 0);
        result = 31 * result + (primaryKey != null ? primaryKey.hashCode() : 0);
        result = 31 * result + (collection ? 1 : 0);
        result = 31 * result + Arrays.deepHashCode(calls);
        result = 31 * result + Arrays.deepHashCode(remoteCalls);
        result = 31 * result + (fallbacks != null ? fallbacks.hashCode() : 0);
        result = 31 * result + (deprecated ? 1 : 0);
        result = 31 * result + (http != null ? http.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (relations != null ? relations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionSchema{" +
                "name='" + name + '\'' +
                ", timeout=" + timeout +
                ", entityPath='" + entityPath + '\'' +
                ", pathDelimiter='" + pathDelimiter + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", collection=" + collection +
                ", calls=" + Arrays.toString(calls) +
                ", remoteCalls=" + Arrays.toString(remoteCalls) +
                ", fallbacks=" + fallbacks +
                ", deprecated=" + deprecated +
                ", http=" + http +
                ", params=" + params +
                ", files=" + files +
                ", entity=" + entity +
                ", relations=" + relations +
                '}';
    }
}
