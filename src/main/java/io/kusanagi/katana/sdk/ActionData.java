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

/**
 * Created by jega on 1/03/18.
 */
public class ActionData {

    private String name;
    private boolean isCollection;
    private Object data;

    public ActionData(String name, boolean isCollection, Object data) {
        this.name = name;
        this.isCollection = isCollection;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public Object getData() {
        return data;
    }
}
