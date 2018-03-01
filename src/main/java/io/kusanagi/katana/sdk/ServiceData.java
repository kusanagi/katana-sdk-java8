package io.kusanagi.katana.sdk;

import java.util.List;

/**
 * Created by jega on 1/03/18.
 */
public class ServiceData {

    private String address;
    private String name;
    private String version;
    private List<ActionData> actions;

    public ServiceData(String address, String name, String version, List<ActionData> actions) {
        this.address = address;
        this.name = name;
        this.version = version;
        this.actions = actions;
    }

    public String getAddress(){
        return this.address;
    }

    public String getName(){
        return this.name;
    }

    public String getVersion(){
        return this.version;
    }

    public List<ActionData> getActions(){
        return this.actions;
    }
}
