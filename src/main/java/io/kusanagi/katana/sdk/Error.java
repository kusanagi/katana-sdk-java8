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
