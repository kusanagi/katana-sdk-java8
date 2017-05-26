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
public class EntitySchemaTest {

    private EntitySchema entitySchema;

    @Before
    public void setup() {
        entitySchema = new EntitySchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertNotEquals(null, entitySchema.getField());
        Assert.assertNotEquals(null, entitySchema.getFields());
        Assert.assertFalse(entitySchema.isValidate());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        EntitySchema object = mockFactory.getEntitySchema();
        Assert.assertEquals(object, new EntitySchema(object));
        Assert.assertEquals(2003584586, object.hashCode());
        Assert.assertEquals(
                "EntitySchema{field=[FieldSchema{name='id', type='integer', optional='false'}, FieldSchema{name='name', type='string', optional='false'}, FieldSchema{name='active', type='boolean', optional='false'}, FieldSchema{name='is_admin', type='boolean', optional='true'}], fields=[ObjectFieldSchema{name='contact', optional=false, field=[FieldSchema{name='id', type='integer', optional='false'}, FieldSchema{name='email', type='null', optional='false'}, FieldSchema{name='location', type='object', optional='false'}], fields=[]}], validate=true}",
                object.toString());
    }

}