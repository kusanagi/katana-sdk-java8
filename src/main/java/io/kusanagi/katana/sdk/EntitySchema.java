package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 3/01/17.
 */
public class EntitySchema {

    /**
     * Defines entity fields as an array, where each item is a field schema object
     */
    @JsonProperty(Key.ENTITY_SCHEMA_FIELD)
    private List<FieldSchema> field;

    /**
     * Defines entity fields which contain other fields as an array, where each item is an object containing an object
     * field schema
     */
    @JsonProperty(Key.ENTITY_SCHEMA_FIELDS)
    private List<ObjectFieldSchema> fields;

    /**
     * OPTIONAL property that determines if an entity returned by the action MUST be validated, defaults to false
     */
    @JsonProperty(Key.ENTITY_SCHEMA_VALIDATE)
    private boolean validate;

    public EntitySchema() {
        field = new ArrayList<>();
        fields = new ArrayList<>();
        validate = false;
    }

    public EntitySchema(EntitySchema other) {
        this.field = other.field;
        this.fields = other.fields;
        this.validate = other.validate;
    }

    public List<FieldSchema> getField() {
        return field;
    }

    public void setField(List<FieldSchema> field) {
        this.field = field;
    }

    public List<ObjectFieldSchema> getFields() {
        return fields;
    }

    public void setFields(List<ObjectFieldSchema> fields) {
        this.fields = fields;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntitySchema)) {
            return false;
        }

        EntitySchema that = (EntitySchema) o;

        if (isValidate() != that.isValidate()) {
            return false;
        }
        if (getField() != null ? !getField().equals(that.getField()) : that.getField() != null) {
            return false;
        }
        return getFields() != null ? getFields().equals(that.getFields()) : that.getFields() == null;

    }

    @Override
    public int hashCode() {
        int result = getField() != null ? getField().hashCode() : 0;
        result = 31 * result + (getFields() != null ? getFields().hashCode() : 0);
        result = 31 * result + (isValidate() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EntitySchema{" +
                "field=" + field +
                ", fields=" + fields +
                ", validate=" + validate +
                '}';
    }
}
