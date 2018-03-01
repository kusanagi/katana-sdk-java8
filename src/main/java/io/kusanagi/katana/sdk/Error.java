package io.kusanagi.katana.sdk;

/**
 * Created by jega on 2/03/18.
 */
public class Error {

    private String address;
    private String name;
    private String version;
    private String message;
    private int code;
    private String status;

    public Error(String address, String name, String version, String message, int code, String status) {
        this.address = address;
        this.name = name;
        this.version = version;
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
