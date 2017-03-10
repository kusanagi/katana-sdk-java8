package io.kusanagi.katana.api.component.utils;

import java.util.Arrays;

/**
 * Created by juan on 27/08/16.
 */
public class Option {

    private String[] names;
    private boolean unique;
    private boolean required;
    private boolean hasValue;

    private String value;

    /**
     * @param names
     * @param unique
     * @param required
     * @param hasValue
     */
    public Option(String[] names, boolean unique, boolean required, boolean hasValue) {
        this.names = names;
        this.unique = unique;
        this.required = required;
        this.hasValue = hasValue;
    }

    /**
     * @param option
     */
    public Option(Option option) {
        setNames(option.getNames());
        setUnique(option.isUnique());
        setRequired(option.isRequired());
        setHasValue(option.isHasValue());
        setValue(option.getValue());
    }

    /**
     * @return
     */
    public String[] getNames() {
        return names;
    }

    /**
     * @param names
     */
    public void setNames(String[] names) {
        this.names = names;
    }

    /**
     * @return
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * @param unique
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     * @return
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return
     */
    public boolean isHasValue() {
        return hasValue;
    }

    /**
     * @param hasValue
     */
    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }

    /**
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Option)) {
            return false;
        }

        Option option = (Option) o;

        if (isUnique() != option.isUnique()) {
            return false;
        }
        if (isRequired() != option.isRequired()) {
            return false;
        }
        if (isHasValue() != option.isHasValue()) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getNames(), option.getNames())) {
            return false;
        }
        return getValue().equals(option.getValue());

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(getNames());
        result = 31 * result + (isUnique() ? 1 : 0);
        result = 31 * result + (isRequired() ? 1 : 0);
        result = 31 * result + (isHasValue() ? 1 : 0);
        result = 31 * result + getValue().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Option{" +
                "names=" + Arrays.toString(names) +
                ", unique=" + unique +
                ", required=" + required +
                ", hasValue=" + hasValue +
                ", value='" + value + '\'' +
                '}';
    }
}
