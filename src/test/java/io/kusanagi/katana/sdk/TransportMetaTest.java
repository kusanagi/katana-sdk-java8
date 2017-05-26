/*
 * Java 7 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java7
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class TransportMetaTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        TransportMeta object = mockFactory.getTransportMeta();
        Assert.assertEquals(object, new TransportMeta(object));
        Assert.assertEquals(915438976, object.hashCode());
        Assert.assertEquals(
                "TransportMeta{version='1.0.0', id='f1b27da9-240b-40e3-99dd-a567e4498ed7', datetime='2016-04-12T02:49:05.761', startTime='null', endTime='null', duration=0, gateway=[12.34.56.78:1234, http://127.0.0.1:80], origin=[users, 1.0.0, list], level=1, fallback=[[users, 1.0.0, [create, update]]], properties={property=value}}",
                object.toString());
    }

}