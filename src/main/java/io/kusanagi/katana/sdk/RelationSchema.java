package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Constants;
import io.kusanagi.katana.api.component.Key;

/**
 * Created by juan on 3/01/17.
 */
public class RelationSchema {

    /**
     * Defines the name of the Service this relates to
     */
    @JsonProperty(Key.RELATION_SCHEMA_NAME)
    private String name;

    /**
     * Defines the type of relation, which MUST be either "one" or "many", defaults to "one"
     */
    @JsonProperty(Key.RELATION_SCHEMA_TYPE)
    private String type;

    public RelationSchema() {
        this.type = Constants.RELATION_ONE;
    }

    public RelationSchema(RelationSchema other) {
        this.name = other.name;
        this.type = other.type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelationSchema)) {
            return false;
        }

        RelationSchema that = (RelationSchema) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
            return false;
        }
        return getType() != null ? getType().equals(that.getType()) : that.getType() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RelationSchema{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
