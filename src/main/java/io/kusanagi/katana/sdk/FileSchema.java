package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

/**
 * Created by juan on 3/01/17.
 */
public class FileSchema {

    private String name;

    /**
     * Defines the expected MIME type of the file's content, MAY include multiple MIME types separated by comma (",")
     * and MUST have a value
     */
    @JsonProperty(Key.FILE_SCHEMA_MIME)
    private String mime;

    /**
     * Defines whether the parameter is required or not, defaults to false if not defined
     */
    @JsonProperty(Key.FILE_SCHEMA_REQUIRED)
    private boolean required;

    /**
     * Defines the maximum (inclusive by default) size allowed for the given file, not applicable if not defined
     */
    @JsonProperty(Key.FILE_SCHEMA_MAX)
    private int max;

    /**
     * Defines that the file size MUST be less than "mx" (maximum), and which MUST be ignored if "mx" (maximum) is not
     * defined, defaults to false if not defined
     */
    @JsonProperty(Key.FILE_SCHEMA_EXCLUSIVE_MAX)
    private boolean exclusiveMax;

    /**
     * Defines the minimum (inclusive by default) size allowed for the given file, not applicable if not defined
     */
    @JsonProperty(Key.FILE_SCHEMA_MIN)
    private int min;

    /**
     * Defines that the file size MUST be greater than "mn" (minimum), and which MUST be ignored if "mn" (minimum) is
     * not defined, defaults to false if not defined
     */
    @JsonProperty(Key.FILE_SCHEMA_EXCLUSIVE_MIN)
    private boolean exclusiveMin;

    /**
     * The HTTP semantics defined for the file parameter
     */
    @JsonProperty(Key.FILE_SCHEMA_HTTP)
    private FileHttpSchema http;

    public FileSchema() {
        this.required = false;
        this.max = Integer.MAX_VALUE;
        this.exclusiveMax = false;
        this.min = 0;
        this.exclusiveMax = false;
        this.http = new FileHttpSchema();
        this.mime = "text/plain";
    }

    public FileSchema(FileSchema other) {
        this.mime = other.mime;
        this.required = other.required;
        this.max = other.max;
        this.exclusiveMax = other.exclusiveMax;
        this.min = other.min;
        this.exclusiveMin = other.exclusiveMin;
        this.http = other.http;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setRequired(boolean required) {
        this.required = required;
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

    public FileHttpSchema getHttp() {
        return http;
    }

    public void setHttp(FileHttpSchema http) {
        this.http = http;
    }

    //SDK Methods

    /**
     * @return the unique name of the file parameter.
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * @return the expected MIME type of the file's content, MAY include multiple MIME types separated by comma (","),
     * or "text/plain" if not defined.
     */
    public String getMime() {
        return mime;
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
     * @return the minimum (inclusive by default) value allowed for the parameter, or 0 if not defined.
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
     * @return an instance of the HttpFileSchema class for the file parameter using the stored mapping of schemas.
     */
    @JsonIgnore
    public FileHttpSchema getHttpSchema() {
        return getHttp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileSchema)) {
            return false;
        }

        FileSchema that = (FileSchema) o;

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
        if (getMime() != null ? !getMime().equals(that.getMime()) : that.getMime() != null) {
            return false;
        }
        return getHttp() != null ? getHttp().equals(that.getHttp()) : that.getHttp() == null;

    }

    @Override
    public int hashCode() {
        int result = getMime() != null ? getMime().hashCode() : 0;
        result = 31 * result + (isRequired() ? 1 : 0);
        result = 31 * result + getMax();
        result = 31 * result + (isExclusiveMax() ? 1 : 0);
        result = 31 * result + getMin();
        result = 31 * result + (isExclusiveMin() ? 1 : 0);
        result = 31 * result + (getHttp() != null ? getHttp().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileSchema{" +
                "mime='" + mime + '\'' +
                ", required=" + required +
                ", max=" + max +
                ", exclusiveMax=" + exclusiveMax +
                ", min=" + min +
                ", exclusiveMin=" + exclusiveMin +
                ", http=" + http +
                '}';
    }
}
