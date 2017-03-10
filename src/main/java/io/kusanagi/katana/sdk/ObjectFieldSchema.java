package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 3/01/17.
 */
public class ObjectFieldSchema {

    /**
     * Defines the name of the field
     */
    @JsonProperty(Key.OBJECT_FIELD_SCHEMA_NAME)
    private String name;

    /**
     * Defines if the field is optional, defaults to false
     */
    @JsonProperty(Key.OBJECT_FIELD_SCHEMA_OPTIONAL)
    private boolean optional;

    /**
     * Defines entity fields as an array, where each item is a field schema object
     */
    @JsonProperty(Key.OBJECT_FIELD_SCHEMA_FIELD)
    private List<FieldSchema> field;

    /**
     * Defines entity fields which contain other fields as an array, where each item is an object containing an object
     * field schema
     */
    @JsonProperty(Key.OBJECT_FIELD_SCHEMA_FIELDS)
    private List<List<ObjectFieldSchema>> fields;

    public ObjectFieldSchema() {
        this.optional = false;
        this.field = new ArrayList<>();
        this.fields = new ArrayList<>();
    }

    public ObjectFieldSchema(ObjectFieldSchema other) {
        this.name = other.name;
        this.optional = other.optional;
        this.field = other.field;
        this.fields = other.fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public List<FieldSchema> getField() {
        return field;
    }

    public void setField(List<FieldSchema> field) {
        this.field = field;
    }

    public List<List<ObjectFieldSchema>> getFields() {
        return fields;
    }

    public void setFields(List<List<ObjectFieldSchema>> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectFieldSchema)) {
            return false;
        }

        ObjectFieldSchema that = (ObjectFieldSchema) o;

        if (isOptional() != that.isOptional()) {
            return false;
        }
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
            return false;
        }
        if (getField() != null ? !getField().equals(that.getField()) : that.getField() != null) {
            return false;
        }
        return getFields() != null ? getFields().equals(that.getFields()) : that.getFields() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (isOptional() ? 1 : 0);
        result = 31 * result + (getField() != null ? getField().hashCode() : 0);
        result = 31 * result + (getFields() != null ? getFields().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ObjectFieldSchema{" +
                "name='" + name + '\'' +
                ", optional=" + optional +
                ", field=" + field +
                ", fields=" + fields +
                '}';
    }
}
