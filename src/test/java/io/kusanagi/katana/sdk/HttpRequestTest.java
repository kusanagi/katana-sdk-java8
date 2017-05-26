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
public class HttpRequestTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        HttpRequest object = mockFactory.getHttpRequest();
        Assert.assertEquals(object, new HttpRequest(object));
        Assert.assertEquals(-110193202, object.hashCode());
        Assert.assertEquals(
                "HttpRequest{protocolVersion='1.1', method='POST', url='http://example.com/v1.0.0/users', queryParamsArray={name=[James], age=[32]}, postParamsArray={name=[Juan], age=[27]}, headers={Accept=[application/json]}, body='body', files=null}",
                object.toString());
    }

}