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
public class CallTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Call object = mockFactory.getCall();
        Assert.assertEquals(object, new Call(object));
        Assert.assertEquals(1937583924, object.hashCode());
        Assert.assertEquals(
                "Call{duration=0, name='posts', version='1.2.0', action='list', caller='null', params=[Param{name='X-Request-Token', value='ac3bd4b8-7da3-4c40-8661-746adfa55e0d', type='string', exists=false}, Param{name='user_id', value='123', type='integer', exists=false}], gateway='null', timeout=0}",
                object.toString());
    }

}