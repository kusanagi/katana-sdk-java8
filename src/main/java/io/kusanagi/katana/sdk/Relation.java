package io.kusanagi.katana.sdk;

import java.util.List;

/**
 * Created by jega on 1/03/18.
 */
public class Relation {

    private String address;
    private String name;
    private String primaryKey;
    private List<ForeignRelation> foreignRelations;

    public Relation(String address, String name, String primaryKey, List<ForeignRelation> foreignRelations) {
        this.address = address;
        this.name = name;
        this.primaryKey = primaryKey;
        this.foreignRelations = foreignRelations;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public List<ForeignRelation> getForeignRelations() {
        return foreignRelations;
    }
}
