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

package io.kusanagi.katana.api.component;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Created by juan on 25/09/16.
 */
public interface Serializer {
    /**
     * @param message
     * @param aClass
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] message, Class<T> aClass) throws IOException;

    <T> T deserialize(String jsonMessage, Class<T> aClass) throws IOException;

    /**
     * @param message
     * @return
     */
    byte[] serializeInBytes(Object message) throws JsonProcessingException;

    String serializeInJson(Object message) throws JsonProcessingException;

}
