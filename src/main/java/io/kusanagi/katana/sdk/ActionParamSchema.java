package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Constants;
import io.kusanagi.katana.api.component.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 3/01/17.
 */
public class ActionParamSchema {

    /**
     * Defines the name of the parameter
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_NAME)
    private String name;

    /**
     * Defines the type of data expected, which MUST be either "null", "boolean", "integer", "number", "string", "array"
     * or "object", defaults to "string" if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_TYPE)
    private String type;

    /**
     * Extends the definition of the "t" (type) with further formatting, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_FORMAT)
    private String format;

    /**
     * Defines the format if the value "t" (type) is set to "array", and which MUST be "csv" for comma separated values,
     * "ssv" for space separated values, "tsv" for tab separated values, "pipes" for pipe separated values, or "multi"
     * for multiple parameter instances instead of multiple values for a single instance, such as foo=bar&foo=bar and
     * which SHOULD only be valid for parameters located in "query" or "form-data", defaults to "csv" if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_ARRAY_FORMAT)
    private String arrayFormat;

    /**
     * Defines an ECMA 262 compliant regular expression to validate the value, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_PATTERN)
    private String pattern;

    /**
     * Determines if an empty value MAY be allowed for the parameter, defaults to false if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_ALLOW_EMPTY)
    private boolean allowEmpty;

    /**
     * Defines a default value if none is provided that MUST conform with the value defined by "t" (type), not
     * applicable for parameters which are required or if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_DEFAULT_VALUE)
    private String defaultValue;

    /**
     * Defines whether the parameter is required or not, defaults to false if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_REQUIRED)
    private boolean required;

    /**
     * Defines the JSON schema definition for the parameter value items, which MUST be ignored if "t" (type) is not set
     * to "array", not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_ITEMS)
    private String items;

    /**
     * Defines the maximum (inclusive by default) value allowed for the given parameter, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_MAX)
    private int max;

    /**
     * Defines that the value of the parameter MUST be less than "mx" (maximum), and which MUST be ignored if "mx"
     * (maximum) is not defined, defaults to false if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_EXCLUSIVE_MAX)
    private boolean exclusiveMax;

    /**
     * Defines the minimum (inclusive by default) value allowed for the given parameter, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_MIN)
    private int min;

    /**
     * Defines that the value of the parameter MUST be greater than "mn" (minimum), and which MUST be ignored if "mn"
     * (minimum) is not defined, defaults to false if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_EXCLUSIVE_MIN)
    private boolean exclusiveMin;

    /**
     * Defines the maximum length for the value of the parameter if "t" (type) is set to "string", which MUST be less
     * than or equal to the value, where the length of the value of the parameter is defined as the number of its
     * characters, as defined in RFC4627, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_MAX_LENGTH)
    private int maxLength;

    /**
     * Defines the minimum length for the value of the parameter if "t" (type) is set to "string", which MUST be greater
     * than or equal to the value, where the length of the value of the parameter is defined as the number of its
     * characters, as defined in RFC4627, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_MIN_LENGTH)
    private int minLength;

    /**
     * Defines the maximum size of the parameter if "t" (type) is set to "array", which MUST be less than or equal to
     * the value, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_MAX_ITEMS)
    private int maxItems;

    /**
     * Defines the minimum size of the parameter if "t" (type) is set to "array", which MUST be greater than or equal to
     * the value, not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_MIN_ITEMS)
    private int minItems;

    /**
     * Defines that a parameter MUST contain a set of unique elements, defaults to false if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_UNIQUE_ITEMS)
    private boolean uniqueItems;

    /**
     * Defines a list of unique values which specify the possible values that the value of the parameter MUST equal,
     * not applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_ENUMERATION)
    private List<String> enumeration;

    /**
     * Defines that the value of the parameter MUST be an integer as a result of the division by this value, not
     * applicable if not defined
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_MULTIPLE_OF)
    private int multipleOf;

    /**
     * The HTTP semantics defined for the action parameter
     */
    @JsonProperty(Key.ACTION_PARAM_SCHEMA_HTTP)
    private ActionParamHttpSchema http;

    public ActionParamSchema() {
        this.type = Constants.TYPE_STRING;
        this.format = "";
        this.pattern = "";
        this.defaultValue = "";
        this.arrayFormat = "";
        this.items = "";
        this.allowEmpty = false;
        this.required = false;
        this.max = Integer.MAX_VALUE;
        this.exclusiveMax = false;
        this.min = Integer.MIN_VALUE;
        this.exclusiveMin = false;
        this.maxLength = -1;
        this.minLength = -1;
        this.maxItems = -1;
        this.minItems = -1;
        this.enumeration = new ArrayList<>();
        this.multipleOf = -1;
        this.uniqueItems = false;
        this.http = new ActionParamHttpSchema();
    }

    public ActionParamSchema(ActionParamSchema other) {
        this.type = other.type;
        this.format = other.format;
        this.arrayFormat = other.arrayFormat;
        this.pattern = other.pattern;
        this.allowEmpty = other.allowEmpty;
        this.defaultValue = other.defaultValue;
        this.required = other.required;
        this.items = other.items;
        this.max = other.max;
        this.exclusiveMax = other.exclusiveMax;
        this.min = other.min;
        this.exclusiveMin = other.exclusiveMin;
        this.maxLength = other.maxLength;
        this.minLength = other.minLength;
        this.maxItems = other.maxItems;
        this.minItems = other.minItems;
        this.uniqueItems = other.uniqueItems;
        this.enumeration = other.enumeration;
        this.multipleOf = other.multipleOf;
        this.http = other.http;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setArrayFormat(String arrayFormat) {
        this.arrayFormat = arrayFormat;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    public void setAllowEmpty(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setExclusiveMax(boolean exclusiveMax) {
        this.exclusiveMax = exclusiveMax;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setExclusiveMin(boolean exclusiveMin) {
        this.exclusiveMin = exclusiveMin;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public void setMinItems(int minItems) {
        this.minItems = minItems;
    }

    public void setUniqueItems(boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public void setEnumeration(List<String> enumeration) {
        this.enumeration = enumeration;
    }

    public void setMultipleOf(int multipleOf) {
        this.multipleOf = multipleOf;
    }

    public ActionParamHttpSchema getHttp() {
        return http;
    }

    public void setHttp(ActionParamHttpSchema http) {
        this.http = http;
    }

    public List<String> getEnumeration() {
        return enumeration;
    }

    //SDK Methods

    /**
     * @return the unique name of the parameter.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the data type of the parameter, which MAY be "null", "boolean", "integer", "number", "string", "array",
     * "object" or "file", or "string" if not defined.
     */
    public String getType() {
        return type;
    }

    /**
     * @return the custom format for the parameter, or an empty string if not defined.
     */
    public String getFormat() {
        return format;
    }

    /**
     * @return the format for the parameter if the type property is set to "array", and which MAY be "csv" for comma
     * separated values, "ssv" for space separated values, "tsv" for tab separated values, "pipes" for pipe separated
     * values, or "multi" for multiple parameter instances instead of multiple values for a single instance, defaults to
     * "csv" if the type property is set to "array", or otherwise an empty string if not defined.
     */
    public String getArrayFormat() {
        if (this.type.equals(Constants.TYPE_ARRAY) && this.arrayFormat.isEmpty()) {
            return Constants.ARRAY_TYPE_CSV;
        }
        return arrayFormat;
    }

    /**
     * @return the ECMA 262 compliant regular expression to validate the parameter, or an empty string if not defined.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * determine if the parameter allows an empty value, defaults to false.
     *
     * @return true if the parameter allows an empty value
     */
    public boolean allowEmpty() {
        return isAllowEmpty();
    }

    /**
     * determine if the parameter has a default value defined, defaults to false.
     *
     * @return true if the parameter has a default value defined
     */
    public boolean hasDefaultValue() {
        return defaultValue != null && !defaultValue.isEmpty();
    }

    /**
     * @return the default value defined for the parameter, or an empty string if not defined.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * determine if the parameter is required, defaults to false.
     *
     * @return true if the parameter is required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @return the JSON schema defined for the parameter items, or an empty object if not defined or the type property
     * is not set to "array".
     */
    public String getItems() {
        return items;
    }

    /**
     * @return the maximum (inclusive by default) value allowed for the parameter, or the highest possible integer value
     * of the implementation language if not defined.
     */
    public int getMax() {
        return max;
    }

    /**
     * determine if the parameter MUST be less than the value set for the max property, and which MUST return false if
     * the max property is not defined, defaults to false.
     *
     * @return true if the parameter MUST be less than the value set for the max property
     */
    public boolean isExclusiveMax() {
        return exclusiveMax;
    }

    /**
     * @return the minimum (inclusive by default) value allowed for the parameter, or the lowest possible integer value
     * of the implementation language if not defined.
     */
    public int getMin() {
        return min;
    }

    /**
     * determine if the parameter MUST be greater than the value set for the min property, and which MUST return false
     * if the min property is not defined, defaults to false.
     *
     * @return true if the parameter MUST be greater than the value set for the min property
     */
    public boolean isExclusiveMin() {
        return exclusiveMin;
    }

    /**
     * @return the maximum length defined for the parameter, or -1 if not defined.
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * @return the minimum length defined for the parameter, or -1 if not defined.
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * @return the maximum number of items allowed for the parameter if the type property is set to "array", and which
     * MUST return -1 if the type property is not set to "array" or not defined.
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     * @return the minimum number of items allowed for the parameter if the type property is set to "array", and which
     * MUST return -1 if the type property is not set to "array" or not defined.
     */
    public int getMinItems() {
        return minItems;
    }

    /**
     * determine if the parameter MUST contain a set of unique elements, defaults to false.
     *
     * @return true if the parameter MUST contain a set of unique elements
     */
    public boolean isUniqueItems() {
        return uniqueItems;
    }


    /**
     * @return the list of unique values which specify the possible values that the parameter MUST equal, or an empty
     * array if not defined.
     */
    @JsonIgnore
    public List<String> getEnum() {
        return getEnumeration();
    }

    /**
     * @return the value that the parameter MUST be divisible by, or -1 if not defined.
     */
    public int getMultipleOf() {
        return multipleOf;
    }

    /**
     * @return an instance of the HttpParamSchema class for the parameter using the stored mapping of schemas.
     */
    @JsonIgnore
    public ActionParamHttpSchema getHttpSchema() {
        return getHttp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionParamSchema)) {
            return false;
        }

        ActionParamSchema that = (ActionParamSchema) o;

        if (isAllowEmpty() != that.isAllowEmpty()) {
            return false;
        }
        if (isRequired() != that.isRequired()) {
            return false;
        }
        if (getMax() != that.getMax()) {
            return false;
        }
        if (isExclusiveMax() != that.isExclusiveMax()) {
            return false;
        }
        if (getMin() != that.getMin()) {
            return false;
        }
        if (isExclusiveMin() != that.isExclusiveMin()) {
            return false;
        }
        if (getMaxLength() != that.getMaxLength()) {
            return false;
        }
        if (getMinLength() != that.getMinLength()) {
            return false;
        }
        if (getMaxItems() != that.getMaxItems()) {
            return false;
        }
        if (getMinItems() != that.getMinItems()) {
            return false;
        }
        if (isUniqueItems() != that.isUniqueItems()) {
            return false;
        }
        if (getMultipleOf() != that.getMultipleOf()) {
            return false;
        }
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) {
            return false;
        }
        if (getFormat() != null ? !getFormat().equals(that.getFormat()) : that.getFormat() != null) {
            return false;
        }
        if (getArrayFormat() != null ? !getArrayFormat().equals(that.getArrayFormat()) : that.getArrayFormat() != null) {
            return false;
        }
        if (getPattern() != null ? !getPattern().equals(that.getPattern()) : that.getPattern() != null) {
            return false;
        }
        if (getDefaultValue() != null ? !getDefaultValue().equals(that.getDefaultValue()) : that.getDefaultValue() != null) {
            return false;
        }
        if (getItems() != null ? !getItems().equals(that.getItems()) : that.getItems() != null) {
            return false;
        }
        if (getEnumeration() != null ? !getEnumeration().equals(that.getEnumeration()) : that.getEnumeration() != null) {
            return false;
        }
        return getHttp() != null ? getHttp().equals(that.getHttp()) : that.getHttp() == null;

    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getFormat() != null ? getFormat().hashCode() : 0);
        result = 31 * result + (getArrayFormat() != null ? getArrayFormat().hashCode() : 0);
        result = 31 * result + (getPattern() != null ? getPattern().hashCode() : 0);
        result = 31 * result + (isAllowEmpty() ? 1 : 0);
        result = 31 * result + (getDefaultValue() != null ? getDefaultValue().hashCode() : 0);
        result = 31 * result + (isRequired() ? 1 : 0);
        result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
        result = 31 * result + getMax();
        result = 31 * result + (isExclusiveMax() ? 1 : 0);
        result = 31 * result + getMin();
        result = 31 * result + (isExclusiveMin() ? 1 : 0);
        result = 31 * result + getMaxLength();
        result = 31 * result + getMinLength();
        result = 31 * result + getMaxItems();
        result = 31 * result + getMinItems();
        result = 31 * result + (isUniqueItems() ? 1 : 0);
        result = 31 * result + (getEnumeration() != null ? getEnumeration().hashCode() : 0);
        result = 31 * result + getMultipleOf();
        result = 31 * result + (getHttp() != null ? getHttp().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionParamSchema{" +
                "type='" + type + '\'' +
                ", format='" + format + '\'' +
                ", arrayFormat='" + arrayFormat + '\'' +
                ", pattern='" + pattern + '\'' +
                ", allowEmpty=" + allowEmpty +
                ", defaultValue='" + defaultValue + '\'' +
                ", required=" + required +
                ", items='" + items + '\'' +
                ", max=" + max +
                ", exclusiveMax=" + exclusiveMax +
                ", min=" + min +
                ", exclusiveMin=" + exclusiveMin +
                ", maxLength=" + maxLength +
                ", minLength=" + minLength +
                ", maxItems=" + maxItems +
                ", minItems=" + minItems +
                ", uniqueItems=" + uniqueItems +
                ", enumeration=" + enumeration +
                ", multipleOf=" + multipleOf +
                ", http=" + http +
                '}';
    }
}
