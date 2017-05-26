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

package io.kusanagi.katana.api.component.utils;

/**
 * Created by jega on 7/03/17.
 */
public class Factory {

    private Factory() {
        // private constructor to block the instantiation of this object
    }

    public static OptionManager getOptionManager() {
        return new OptionManager();
    }

    public static MessagePackSerializer getSerializer() {
        return new MessagePackSerializer();
    }
}
