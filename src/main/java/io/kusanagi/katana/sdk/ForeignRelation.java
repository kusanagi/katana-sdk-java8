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
public class ForeignRelation {

    private String address;
    private String name;
    private String type;
    private List<String> keys;

    public ForeignRelation(String address, String name, String type, List<String> keys) {
        this.address = address;
        this.name = name;
        this.type = type;
        this.keys = keys;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getKeys() {
        return keys;
    }
}
