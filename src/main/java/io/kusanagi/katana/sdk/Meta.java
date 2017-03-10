package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.List;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class Meta {

    /**
     * The version of the framework
     */
    @JsonProperty(Key.META_VERSION)
    private String version;

    /**
     * The UUID version 4 of the request
     */
    @JsonProperty(Key.META_ID)
    private String id;

    /**
     * The datetime of the process, in UTC and ISO 8601 format
     */
    @JsonProperty(Key.META_DATETIME)
    private String datetime;

    /**
     * The type of Middleware, either 1 if "request" or 2 if "response"
     */
    @JsonProperty(Key.META_TYPE)
    private int type;

    /**
     * The protocol implemented by the Gateway
     */
    @JsonProperty(Key.META_PROTOCOL)
    private String protocol;

    /**
     * The array of addresses for the Gateway serving the request, where the first item is the internal address and the
     * second item is the public address
     */
    @JsonProperty(Key.META_GATEWAY)
    private List<String> gateway;

    /**
     * The address of the client which sent the request, formatted as "%ADDRESS%:%PORT%", where %ADDRESS% is the IP
     * address and %PORT% is the port number
     */
    @JsonProperty(Key.META_CLIENT)
    private String client;

    public Meta() {
        // Default constructor to make possible the serialization of this object.
    }

    public Meta(Meta other) {
        this.version = other.version;
        this.id = other.id;
        this.datetime = other.datetime;
        this.type = other.type;
        this.protocol = other.protocol;
        this.gateway = other.gateway;
        this.client = other.client;
    }

    /**
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @return
     */
    public int getType() {
        return this.type;
    }

    /**
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @param datetime
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public List<String> getGateway() {
        return gateway;
    }

    public void setGateway(List<String> gateway) {
        this.gateway = gateway;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Meta)) {
            return false;
        }

        Meta meta = (Meta) o;

        if (getType() != meta.getType()) {
            return false;
        }
        if (getVersion() != null ? !getVersion().equals(meta.getVersion()) : meta.getVersion() != null) {
            return false;
        }
        if (getId() != null ? !getId().equals(meta.getId()) : meta.getId() != null) {
            return false;
        }
        if (getDatetime() != null ? !getDatetime().equals(meta.getDatetime()) : meta.getDatetime() != null) {
            return false;
        }
        if (getProtocol() != null ? !getProtocol().equals(meta.getProtocol()) : meta.getProtocol() != null) {
            return false;
        }
        if (getGateway() != null ? !getGateway().equals(meta.getGateway()) : meta.getGateway() != null) {
            return false;
        }
        return getClient() != null ? getClient().equals(meta.getClient()) : meta.getClient() == null;

    }

    @Override
    public int hashCode() {
        int result = getVersion() != null ? getVersion().hashCode() : 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getDatetime() != null ? getDatetime().hashCode() : 0);
        result = 31 * result + getType();
        result = 31 * result + (getProtocol() != null ? getProtocol().hashCode() : 0);
        result = 31 * result + (getGateway() != null ? getGateway().hashCode() : 0);
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "version='" + version + '\'' +
                ", id='" + id + '\'' +
                ", datetime='" + datetime + '\'' +
                ", type=" + type +
                ", protocol='" + protocol + '\'' +
                ", gateway=" + gateway +
                ", client='" + client + '\'' +
                '}';
    }
}
