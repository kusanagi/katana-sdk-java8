package io.kusanagi.katana.sdk;

/**
 * Created by jega on 1/03/18.
 */
public class ActionData {

    private String name;
    private boolean isCollection;
    private Object data;

    public ActionData(String name, boolean isCollection, Object data) {
        this.name = name;
        this.isCollection = isCollection;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public Object getData() {
        return data;
    }
}
