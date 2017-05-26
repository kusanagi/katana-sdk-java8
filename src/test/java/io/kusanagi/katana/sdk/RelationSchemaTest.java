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
public class RelationSchemaTest {

    private RelationSchema relationSchema;

    @Before
    public void setup() {
        relationSchema = new RelationSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertEquals("one", relationSchema.getType());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        RelationSchema object = mockFactory.getRelationSchema();
        Assert.assertEquals(object, new RelationSchema(object));
        Assert.assertEquals(-1826918592, object.hashCode());
        Assert.assertEquals(
                "RelationSchema{name='accounts', type='one'}",
                object.toString());
    }

}