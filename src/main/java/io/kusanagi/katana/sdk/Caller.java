package io.kusanagi.katana.sdk;

/**
 * Created by jega on 1/03/18.
 */
public class Caller {

    private String name;
    private String version;
    private String action;
    private Callee callee;

    public Caller(String name, String version, String action, Callee callee) {
        this.name = name;
        this.version = version;
        this.action = action;
        this.callee = callee;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAction() {
        return action;
    }

    public Callee getCallee() {
        return callee;
    }
}
