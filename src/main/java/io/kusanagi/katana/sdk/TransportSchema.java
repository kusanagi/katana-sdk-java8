package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 3/01/17.
 */
public class TransportSchema {

    /**
     * Defines the properties to write to the Transport, where each property is a property name, and the value the value
     * as a string
     */
    @JsonProperty(Key.TRANSPORT_SCHEMA_PROPERTIES)
    private Map<String, String> properties;

    /**
     * Defines the entities to write to the Transport as an array, in which each item of the array is a data object,
     * where each property is the name of the field, and the value a value schema
     */
    @JsonProperty(Key.TRANSPORT_SCHEMA_DATA)
    private List<Map<String, ValueSchema>> data;

    /**
     * Defines the relations to write to the Transport as an array, where each item is an array, where the first item is
     * the ID of the related entity, the second item the name of the Service being related to, and the third either a
     * single string value for a one-to-one relationship, or an array of string values for a one-to-many relationship
     */
    @JsonProperty(Key.TRANSPORT_SCHEMA_RELATIONS)
    private List<List<Object>> relations;

    /**
     * Defines the links to write to the Transport, where each property is the link name, and the value the link URI
     */
    @JsonProperty(Key.TRANSPORT_SCHEMA_LINKS)
    private Map<String, String> links;

    /**
     * Defines the errors to write to the Transport as an array, in which each item is an array, where the first item is
     * the error message, the second the error code, and the third an optional HTTP status code
     */
    @JsonProperty(Key.TRANSPORT_SCHEMA_ERRORS)
    private List<List<String>> errors;

    public TransportSchema() {
        this.properties = new HashMap<>();
        this.data = new ArrayList<>();
        this.relations = new ArrayList<>();
        this.links = new HashMap<>();
        this.errors = new ArrayList<>();
    }

    public TransportSchema(TransportSchema other) {
        this.properties = other.properties;
        this.data = other.data;
        this.relations = other.relations;
        this.links = other.links;
        this.errors = other.errors;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<Map<String, ValueSchema>> getData() {
        return data;
    }

    public void setData(List<Map<String, ValueSchema>> data) {
        this.data = data;
    }

    public List<List<Object>> getRelations() {
        return relations;
    }

    public void setRelations(List<List<Object>> relations) {
        this.relations = relations;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public List<List<String>> getErrors() {
        return errors;
    }

    public void setErrors(List<List<String>> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportSchema)) {
            return false;
        }

        TransportSchema that = (TransportSchema) o;

        if (getProperties() != null ? !getProperties().equals(that.getProperties()) : that.getProperties() != null) {
            return false;
        }
        if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) {
            return false;
        }
        if (getRelations() != null ? !getRelations().equals(that.getRelations()) : that.getRelations() != null) {
            return false;
        }
        if (getLinks() != null ? !getLinks().equals(that.getLinks()) : that.getLinks() != null) {
            return false;
        }
        return getErrors() != null ? getErrors().equals(that.getErrors()) : that.getErrors() == null;

    }

    @Override
    public int hashCode() {
        int result = getProperties() != null ? getProperties().hashCode() : 0;
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + (getRelations() != null ? getRelations().hashCode() : 0);
        result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
        result = 31 * result + (getErrors() != null ? getErrors().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransportSchema{" +
                "properties=" + properties +
                ", data=" + data +
                ", relations=" + relations +
                ", links=" + links +
                ", errors=" + errors +
                '}';
    }
}
