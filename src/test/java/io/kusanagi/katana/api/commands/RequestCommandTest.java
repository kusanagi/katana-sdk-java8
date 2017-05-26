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

package io.kusanagi.katana.api.commands;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by juane on 2/11/17.
 */
@RunWith(JUnit4.class)
public class RequestCommandTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        RequestCommandPayload.RequestCommand object = mockFactory.getRequestCommand();
        Assert.assertEquals(object, new RequestCommandPayload.RequestCommand(object));
        Assert.assertEquals(1904559845, object.hashCode());
    }

}