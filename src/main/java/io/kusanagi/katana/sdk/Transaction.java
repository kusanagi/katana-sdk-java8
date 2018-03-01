package io.kusanagi.katana.sdk;

import java.util.List;

/**
 * Created by jega on 2/03/18.
 */
public class Transaction {

    private String type;
    private String name;
    private String version;
    private String callerAction;
    private String calleeAction;
    private List<Param> params;

    public Transaction(String type, String name, String version, String callerAction, String calleeAction, List<Param> params) {
        this.type = type;
        this.name = name;
        this.version = version;
        this.callerAction = callerAction;
        this.calleeAction = calleeAction;
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getCallerAction() {
        return callerAction;
    }

    public String getCalleeAction() {
        return calleeAction;
    }

    public List<Param> getParams() {
        return params;
    }
}
