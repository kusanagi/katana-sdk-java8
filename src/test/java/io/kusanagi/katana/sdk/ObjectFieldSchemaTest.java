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
public class ObjectFieldSchemaTest {

    private ObjectFieldSchema objectFieldSchema;

    @Before
    public void setup() {
        objectFieldSchema = new ObjectFieldSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(objectFieldSchema.isOptional());
        Assert.assertNotEquals(null, objectFieldSchema.getField());
        Assert.assertNotEquals(null, objectFieldSchema.getFields());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ObjectFieldSchema object = mockFactory.getObjectFieldSchema();
        Assert.assertEquals(object, new ObjectFieldSchema(object));
        Assert.assertEquals(964088491, object.hashCode());
        Assert.assertEquals(
                "ObjectFieldSchema{name='contact', optional=false, field=[FieldSchema{name='id', type='integer', optional='false'}, FieldSchema{name='email', type='null', optional='false'}, FieldSchema{name='location', type='object', optional='false'}], fields=[]}",
                object.toString());
    }

}