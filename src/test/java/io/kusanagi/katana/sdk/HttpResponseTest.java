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
public class HttpResponseTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        HttpResponse object = mockFactory.getHttpResponse();
        Assert.assertEquals(object, new HttpResponse(object));
        Assert.assertEquals(-2003792283, object.hashCode());
        Assert.assertEquals(
                "HttpResponse{protocolVersion='1.1', status='200 OK', headers={Content-Type=[application/json]}, body='{\"result\":\"OK\",\"message\":\"Created new user\"}'}",
                object.toString());
    }

}