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
public class FileHttpSchemaTest {

    private FileHttpSchema fileHttpSchema;

    @Before
    public void setup() {
        fileHttpSchema = new FileHttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(fileHttpSchema.isGateway());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        FileHttpSchema object = mockFactory.getFileHttpSchema();
        Assert.assertEquals(object, new FileHttpSchema(object));
        Assert.assertEquals(-1405959847, object.hashCode());
        Assert.assertEquals(
                "FileHttpSchema{gateway=false, param='avatar'}",
                object.toString());
    }
}