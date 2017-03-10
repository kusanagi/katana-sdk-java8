package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

/**
 * Created by juan on 18/11/16.
 */
public class Param {

    /**
     * The name of the parameter
     */
    @JsonProperty(Key.PARAM_NAME)
    private String name;

    /**
     * The value of the variable, which MAY be converted from the configuration based on the given type value, or null
     * if the variable for the given name does not exist
     */
    @JsonProperty(Key.PARAM_VALUE)
    private Object value;

    /**
     * The data type of the variable, which MAY be "null", "boolean", "integer", "float", "string", "array" or "object"
     */
    @JsonProperty(Key.PARAM_TYPE)
    private String type;

    /**
     * Determines if the parameter was provided in the request
     */
    private boolean exists;

    public Param() {
        // Default constructor to make possible the serialization of this object.
        this.value = "";
        this.type = "string";
        this.exists = false;
    }

    public Param(String name, Object value, String type, boolean exists) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.exists = exists;
    }

    public Param(Param other) {
        this.name = other.name;
        this.value = other.value;
        this.type = other.type;
        this.exists = other.exists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    // SDK Methods

    /**
     * @return the name of the parameter.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value of the parameter, cast to the native data type defined for the parameter. The data type SHOULD
     * be limited to the data types available.
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return the data type of the parameter.
     */
    public String getType() {
        return type;
    }

    /**
     * determine if the parameter exists in the request.
     *
     * @return true if the parameter exists in the request.
     */
    public boolean exists() {
        return exists;
    }

    /**
     * @param name Parameter name
     * @return a new instance of the Param object, which MUST use the existing attributes of the current instance, but
     * MUST update the name of the parameter with the given value of the REQUIRED name argument.
     */
    public Param copyWithName(String name) {
        Param param = new Param();
        param.setName(name);
        param.setType(this.type);
        param.setValue(this.value);
        param.setExists(this.exists);
        return param;
    }

    /**
     * @param value Parameter value
     * @return a new instance of the Param object, which MUST use the existing attributes of the current instance, but
     * MUST update the value of the parameter with the given value of the REQUIRED value argument.
     */
    public Param copyWithValue(String value) {
        Param param = new Param();
        param.setName(this.name);
        param.setType(this.type);
        param.setValue(value);
        param.setExists(this.exists);
        return param;
    }

    /**
     * @param type Parameter type
     * @return a new instance of the Param object, which MUST use the existing attributes of the current instance, but
     * MUST update the data type of the parameter with the given value of the REQUIRED type argument.
     */
    public Param copyWithType(String type) {
        Param param = new Param();
        param.setName(this.name);
        param.setType(type);
        param.setValue(this.value);
        param.setExists(this.exists);
        return param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Param)) {
            return false;
        }

        Param param = (Param) o;

        if (exists() != param.exists()) {
            return false;
        }
        if (getName() != null ? !getName().equals(param.getName()) : param.getName() != null) {
            return false;
        }
        if (getValue() != null ? !getValue().equals(param.getValue()) : param.getValue() != null) {
            return false;
        }
        return getType() != null ? getType().equals(param.getType()) : param.getType() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (exists() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Param{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", exists=" + exists +
                '}';
    }
}
