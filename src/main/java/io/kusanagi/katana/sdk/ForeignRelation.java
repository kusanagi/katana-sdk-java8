package io.kusanagi.katana.sdk;

import java.util.List;

/**
 * Created by jega on 1/03/18.
 */
public class ForeignRelation {

    private String address;
    private String name;
    private String type;
    private List<String> keys;

    public ForeignRelation(String address, String name, String type, List<String> keys) {
        this.address = address;
        this.name = name;
        this.type = type;
        this.keys = keys;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getKeys() {
        return keys;
    }
}
