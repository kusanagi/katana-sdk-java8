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
 * Created by juan on 27/08/16.
 */
public class Utils {

    private Utils() {
        // private constructor to block the instantiation of this object
    }

    /**
     * @param options
     * @param optionName
     * @return
     */
    public static boolean contain(String[] options, String optionName) {
        for (String option : options) {
            if (option.equals(optionName)) {
                return true;
            }
        }

        return false;
    }
}
