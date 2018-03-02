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
 * Created by jega on 2/03/18.
 */
public class Transaction {

    private String type;
    private String name;
    private String version;
    private String callerAction;
    private String calleeAction;
    private List<Param> params;

    public Transaction(String type, String name, String version, String callerAction, String calleeAction, List<Param> params) {
        this.type = type;
        this.name = name;
        this.version = version;
        this.callerAction = callerAction;
        this.calleeAction = calleeAction;
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getCallerAction() {
        return callerAction;
    }

    public String getCalleeAction() {
        return calleeAction;
    }

    public List<Param> getParams() {
        return params;
    }
}
