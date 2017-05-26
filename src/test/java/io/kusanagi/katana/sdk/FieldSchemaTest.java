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
public class FieldSchemaTest {

    private FieldSchema fieldSchema;

    @Before
    public void setup() {
        fieldSchema = new FieldSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(fieldSchema.isOptional());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        FieldSchema object = mockFactory.getFieldSchema();
        Assert.assertEquals(object, new FieldSchema(object));
        Assert.assertEquals(573298909, object.hashCode());
        Assert.assertEquals(
                "FieldSchema{name='id', type='integer', optional='false'}",
                object.toString());
    }

}