/*
 * Java 8 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java8
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

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
     * OPTIONAL Defines the entity name, defaults to an empty string
     */
    @JsonProperty(Key.ENTITY_SCHEMA_NAME)
    private String name;

    /**
     * OPTIONAL property that determines if an entity returned by the action MUST be validated, defaults to false
     */
    @JsonProperty(Key.ENTITY_SCHEMA_VALIDATE)
    private boolean validate;

    public EntitySchema() {
        field = new ArrayList<>();
        fields = new ArrayList<>();
        name = "";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntitySchema that = (EntitySchema) o;

        if (validate != that.validate) {
            return false;
        }
        if (field != null ? !field.equals(that.field) : that.field != null) {
            return false;
        }
        if (fields != null ? !fields.equals(that.fields) : that.fields != null) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (validate ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EntitySchema{" +
                "field=" + field +
                ", fields=" + fields +
                ", name='" + name + '\'' +
                ", validate=" + validate +
                '}';
    }
}
