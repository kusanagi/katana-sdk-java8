package io.kusanagi.katana.sdk;

import java.util.List;

/**
 * Created by jega on 2/03/18.
 */
public class Callee {

    private int duration;
    private boolean isRemote;
    private String address;
    private int timeout;
    private String name;
    private String version;
    private String action;
    private List<Param> params;

    public Callee(int duration, boolean isRemote, String address, int timeout, String name, String version, String action, List<Param> params) {
        this.duration = duration;
        this.isRemote = isRemote;
        this.address = address;
        this.timeout = timeout;
        this.name = name;
        this.version = version;
        this.action = action;
        this.params = params;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public String getAddress() {
        return address;
    }

    public int getTimeout() {
        return timeout;
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

    public List<Param> getParams() {
        return params;
    }
}
