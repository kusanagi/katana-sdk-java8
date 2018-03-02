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

public class Caller {

    private String name;
    private String version;
    private String action;
    private Callee callee;

    public Caller(String name, String version, String action, Callee callee) {
        this.name = name;
        this.version = version;
        this.action = action;
        this.callee = callee;
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

    public Callee getCallee() {
        return callee;
    }
}
