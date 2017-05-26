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

package io.kusanagi.katana.api.commands;

import io.kusanagi.katana.sdk.ServiceSchema;

import java.util.Map;

/**
 * Created by juan on 3/01/17.
 */
public class Mapping {

    /**
     * defines the schema for each Service registered with the Discovery component as an object, where each property is
     * the unique name of each Service, and the value an object, of which each property is a version of that Service and
     * the value a Service Schema Object
     */
    private Map<String, Map<String, ServiceSchema>> serviceSchema;

    public Mapping() {
        //Empty constructor for serialization
    }

    public Mapping(Mapping other) {
        this.serviceSchema = other.serviceSchema;
    }

    public Map<String, Map<String, ServiceSchema>> getServiceSchema() {
        return serviceSchema;
    }

    public void setServiceSchema(Map<String, Map<String, ServiceSchema>> serviceSchema) {
        this.serviceSchema = serviceSchema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mapping)) {
            return false;
        }

        Mapping that = (Mapping) o;

        return getServiceSchema() != null ? getServiceSchema().equals(that.getServiceSchema()) : that.getServiceSchema() == null;

    }

    @Override
    public int hashCode() {
        return getServiceSchema() != null ? getServiceSchema().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "serviceSchema=" + serviceSchema +
                '}';
    }
}
