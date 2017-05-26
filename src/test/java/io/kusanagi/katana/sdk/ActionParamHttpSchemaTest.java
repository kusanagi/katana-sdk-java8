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
public class ActionParamHttpSchemaTest {

    private ActionParamHttpSchema actionParamHttpSchema;

    @Before
    public void setup() {
        actionParamHttpSchema = new ActionParamHttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(actionParamHttpSchema.isGateway());
        Assert.assertEquals("query", actionParamHttpSchema.getInput());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ActionParamHttpSchema object = mockFactory.getActionParamHttpSchema();
        Assert.assertEquals(object, new ActionParamHttpSchema(object));
        Assert.assertEquals(-40693173, object.hashCode());
        Assert.assertEquals(
                "ActionParamHttpSchema{gateway=true, input='path', param='user_id'}",
                object.toString());
    }

}