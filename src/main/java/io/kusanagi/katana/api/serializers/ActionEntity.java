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

package io.kusanagi.katana.api.serializers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.sdk.Param;

import java.util.List;

/**
 * Created by juan on 27/08/16.
 */
public class ActionEntity {
    /**
     * The parameters sent to the action, where each property name is the "name" of the parameter and the value an
     * object, with a property named "v" (value) containing the value of the parameter, and "t" (type) with the data
     * type of the parameter (object)
     */
    @JsonProperty(Key.ACTION_PARAMS)
    private List<Param> params;

    /**
     * The Transport payload as sent from the Gateway or calling Service
     */
    @JsonProperty(Key.ACTION_TRANSPORT)
    private TransportEntity transport;

    /**
     * The return object for a runtime call
     */
    @JsonProperty(Key.ACTION_RETURN_OBJECT)
    private Object returnObject;

    private String actionName;

    /**
     * Default constructor
     */
    public ActionEntity() {
        // Default constructor to make possible the serialization of this object.
    }

    public ActionEntity(ActionEntity other) {
        this.params = other.params;
        this.transport = other.transport;
        this.returnObject = other.returnObject;
        this.actionName = other.actionName;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public TransportEntity getTransport() {
        return transport;
    }

    public void setTransport(TransportEntity transport) {
        this.transport = transport;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionEntity that = (ActionEntity) o;

        if (params != null ? !params.equals(that.params) : that.params != null) {
            return false;
        }
        if (transport != null ? !transport.equals(that.transport) : that.transport != null) {
            return false;
        }
        if (returnObject != null ? !returnObject.equals(that.returnObject) : that.returnObject != null) {
            return false;
        }
        return actionName != null ? actionName.equals(that.actionName) : that.actionName == null;
    }

    @Override
    public int hashCode() {
        int result = params != null ? params.hashCode() : 0;
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (returnObject != null ? returnObject.hashCode() : 0);
        result = 31 * result + (actionName != null ? actionName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionEntity{" +
                "params=" + params +
                ", transport=" + transport +
                ", returnObject=" + returnObject +
                ", actionName='" + actionName + '\'' +
                '}';
    }
}
