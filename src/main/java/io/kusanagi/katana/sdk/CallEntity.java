/*
 * Java 8 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java8
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;

import java.util.List;

/**
 * Created by juan on 18/11/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallEntity {

    /**
     * The duration of the **Service** call in milliseconds, which is **0** until the call is processed
     */
    @JsonProperty(Key.CALL_DURATION)
    private int duration;

    /**
     * The name of the Service to call
     */
    @JsonProperty(Key.CALL_NAME)
    private String name;

    /**
     * The version of the Service to call
     */
    @JsonProperty(Key.CALL_VERSION)
    private String version;

    /**
     * The name of the action to call
     */
    @JsonProperty(Key.CALL_ACTION)
    private String action;

    /**
     * The name of the action that registers the call
     */
    @JsonProperty(Key.CALL_CALLER)
    private String caller;

    /**
     * Array of objects, each of which define a parameter for the Service call
     */
    @JsonProperty(Key.CALL_PARAMS)
    private List<Param> params;

    /**
     * The public address of a Gateway for the Service in another Realm
     */
    @JsonProperty(Key.CALL_GATEWAY)
    private String gateway;

    /**
     * The timeout in milliseconds for the call to a Service in another Realm
     */
    @JsonProperty(Key.CALL_TIMEOUT)
    private int timeout;

    public CallEntity() {
        // Default constructor to make possible the serialization of this object.
    }

    public CallEntity(CallEntity other) {
        this.name = other.name;
        this.version = other.version;
        this.action = other.action;
        this.params = other.params;
        this.gateway = other.gateway;
        this.timeout = other.timeout;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CallEntity call = (CallEntity) o;

        if (duration != call.duration) {
            return false;
        }
        if (timeout != call.timeout) {
            return false;
        }
        if (name != null ? !name.equals(call.name) : call.name != null) {
            return false;
        }
        if (version != null ? !version.equals(call.version) : call.version != null) {
            return false;
        }
        if (action != null ? !action.equals(call.action) : call.action != null) {
            return false;
        }
        if (caller != null ? !caller.equals(call.caller) : call.caller != null) {
            return false;
        }
        if (params != null ? !params.equals(call.params) : call.params != null) {
            return false;
        }
        return gateway != null ? gateway.equals(call.gateway) : call.gateway == null;
    }

    @Override
    public int hashCode() {
        int result = duration;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (caller != null ? caller.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (gateway != null ? gateway.hashCode() : 0);
        result = 31 * result + timeout;
        return result;
    }

    @Override
    public String toString() {
        return "CallEntity{" +
                "duration=" + duration +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                ", caller='" + caller + '\'' +
                ", params=" + params +
                ", gateway='" + gateway + '\'' +
                ", timeout=" + timeout +
                '}';
    }
}
