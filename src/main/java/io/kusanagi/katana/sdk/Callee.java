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
