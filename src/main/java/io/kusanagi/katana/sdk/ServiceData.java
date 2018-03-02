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
