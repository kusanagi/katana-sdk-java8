package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Constants;
import io.kusanagi.katana.api.component.Key;

import java.util.List;

/**
 * Created by juan on 3/01/17.
 */
public class ValueSchema {

    /**
     * Defines the data type of the value, defaults to string if not defined
     */
    @JsonProperty(Key.VALUE_SCHEMA_TYPE)
    private String type;

    /**
     * Defines the value of the field when "t" (type) property is not set to "array", if it is set to "object" each
     * property is the name of the field and the value a value schema, and if it is set to "null" this property is not
     * required
     */
    @JsonProperty(Key.VALUE_SCHEMA_VALUE)
    private Object value;

    @JsonProperty(Key.VALUE_SCHEMA_ITEMS)
    private List<ValueSchema> items;

    public ValueSchema() {
        this.type = Constants.TYPE_STRING;
    }

    public ValueSchema(ValueSchema other) {
        this.type = other.type;
        this.value = other.value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<ValueSchema> getItems() {
        return items;
    }

    public void setItems(List<ValueSchema> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValueSchema that = (ValueSchema) o;

        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }
        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ValueSchema{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", items=" + items +
                '}';
    }
}
