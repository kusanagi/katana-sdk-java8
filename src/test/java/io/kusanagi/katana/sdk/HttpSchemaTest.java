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
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class HttpSchemaTest {

    private HttpSchema httpSchema;

    @Before
    public void setup() {
        httpSchema = new HttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(httpSchema.isGateway());
        Assert.assertEquals("", httpSchema.getBasePath());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        HttpSchema object = mockFactory.getHttpSchema();
        Assert.assertEquals(object, new HttpSchema(object));
        Assert.assertEquals(1392240645, object.hashCode());
        Assert.assertEquals(
                "HttpSchema{gateway=true, basePath='/1.0.0'}",
                object.toString());
    }

}