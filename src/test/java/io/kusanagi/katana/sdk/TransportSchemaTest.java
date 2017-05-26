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
public class TransportSchemaTest {

    private TransportSchema transportSchema;

    @Before
    public void setup() {
        transportSchema = new TransportSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertNotEquals(null, transportSchema.getProperties());
        Assert.assertNotEquals(null, transportSchema.getData());
        Assert.assertNotEquals(null, transportSchema.getRelations());
        Assert.assertNotEquals(null, transportSchema.getLinks());
        Assert.assertNotEquals(null, transportSchema.getErrors());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        TransportSchema object = mockFactory.getTransportSchema();
        Assert.assertEquals(object, new TransportSchema(object));
        Assert.assertEquals(-296674616, object.hashCode());
        Assert.assertEquals(
                "TransportSchema{properties={foo=123}, data=[{id=ValueSchema{type='integer', value=321, items=null}, name=ValueSchema{type='string', value=James, items=null}, status=ValueSchema{type='null', value=null, items=null}, info=ValueSchema{type='array', value=[{v=Hello World}], items=null}, stats=ValueSchema{type='object', value={count={t=integer, v=12345}}, items=null}}], relations=[[1, user, 5], [1, comments, [3, 8]]], links={self=/1.0.0/posts}, errors=[[Unable to process posts, 0, 560 Logic Error]]}",
                object.toString());
    }

}