package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.component.Key;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jega on 3/03/17.
 */
public class Callee extends Api {

    @JsonProperty(Key.CALLEE_ACTION)
    private String action;

    @JsonProperty(Key.CALLEE_CALLEE_INFO)
    private String[] calleeInfo;

    @JsonProperty(Key.CALLEE_TRANSPORT)
    private Transport transport;

    @JsonProperty(Key.CALLEE_PARAM)
    private List<Param> param;

    @JsonProperty(Key.CALLEE_FILES)
    private List<File> files;

    public Callee() {
        //Empty constructor for serialization
    }

    public Callee(Callee other) {
        this.action = other.action;
        this.calleeInfo = other.calleeInfo;
        this.transport = other.transport;
        this.param = other.param;
        this.files = other.files;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String[] getCalleeInfo() {
        return calleeInfo;
    }

    public void setCalleeInfo(String[] calleeInfo) {
        this.calleeInfo = calleeInfo;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public List<Param> getParam() {
        return param;
    }

    public void setParam(List<Param> param) {
        this.param = param;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Callee callee = (Callee) o;

        if (action != null ? !action.equals(callee.action) : callee.action != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(calleeInfo, callee.calleeInfo)) {
            return false;
        }
        if (transport != null ? !transport.equals(callee.transport) : callee.transport != null) {
            return false;
        }
        if (param != null ? !param.equals(callee.param) : callee.param != null) {
            return false;
        }
        return files != null ? files.equals(callee.files) : callee.files == null;
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(calleeInfo);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (param != null ? param.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Callee{" +
                "action='" + action + '\'' +
                ", calleeInfo=" + Arrays.toString(calleeInfo) +
                ", transport=" + transport +
                ", param=" + param +
                ", files=" + files +
                '}';
    }
}
