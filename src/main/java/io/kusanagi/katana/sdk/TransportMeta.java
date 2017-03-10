package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 26/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportMeta {

    @JsonProperty(Key.TRANSPORT_META_VERSION)
    private String version;

    @JsonProperty(Key.TRANSPORT_META_ID)
    private String id;

    @JsonProperty(Key.TRANSPORT_META_DATETIME)
    private String datetime;

    @JsonProperty(Key.TRANSPORT_META_GATEWAY)
    private List<String> gateway;

    @JsonProperty(Key.TRANSPORT_META_ORIGIN)
    private String[] origin;

    @JsonProperty(Key.TRANSPORT_META_LEVEL)
    private int level;

    @JsonProperty(Key.TRANSPORT_META_FALLBACK)
    private List<List<Object>> fallback;

    @JsonProperty(Key.TRANSPORT_META_PROPERTIES)
    private Map<String, String> properties;

    public TransportMeta() {
        // Default constructor to make possible the serialization of this object.
        this.origin = new String[0];
    }

    public TransportMeta(TransportMeta other) {
        this.version = other.version;
        this.id = other.id;
        this.datetime = other.datetime;
        this.gateway = other.gateway;
        this.origin = other.origin;
        this.level = other.level;
        this.fallback = other.fallback;
        this.properties = other.properties;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public List<String> getGateway() {
        return gateway;
    }

    public void setGateway(List<String> gateway) {
        this.gateway = gateway;
    }

    public String[] getOrigin() {
        return origin;
    }

    public void setOrigin(String[] origin) {
        this.origin = origin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<List<Object>> getFallback() {
        return fallback;
    }

    public void setFallback(List<List<Object>> fallback) {
        this.fallback = fallback;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransportMeta that = (TransportMeta) o;

        if (level != that.level) {
            return false;
        }
        if (version != null ? !version.equals(that.version) : that.version != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (datetime != null ? !datetime.equals(that.datetime) : that.datetime != null) {
            return false;
        }
        if (gateway != null ? !gateway.equals(that.gateway) : that.gateway != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(origin, that.origin)) {
            return false;
        }
        if (fallback != null ? !fallback.equals(that.fallback) : that.fallback != null) {
            return false;
        }
        return properties != null ? properties.equals(that.properties) : that.properties == null;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (gateway != null ? gateway.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(origin);
        result = 31 * result + level;
        result = 31 * result + (fallback != null ? fallback.hashCode() : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransportMeta{" +
                "version='" + version + '\'' +
                ", id='" + id + '\'' +
                ", datetime='" + datetime + '\'' +
                ", gateway=" + gateway +
                ", origin=" + Arrays.toString(origin) +
                ", level=" + level +
                ", fallback=" + fallback +
                ", properties=" + properties +
                '}';
    }
}
