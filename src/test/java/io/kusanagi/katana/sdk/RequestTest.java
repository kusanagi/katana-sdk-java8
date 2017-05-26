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
public class RequestTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Request object = mockFactory.getRequest();
        Assert.assertEquals(object, new Request(object));
        Assert.assertEquals(-33019121, object.hashCode());
        Assert.assertEquals(
                "Request{meta=Meta{version='1.0.0', id='f1b27da9-240b-40e3-99dd-a567e4498ed7', datetime='2016-04-12T02:49:05.761', type=1, protocol='http', gateway=[12.34.56.78:1234, http://127.0.0.1:80], client='205.81.5.62:7681'}, httpRequest=HttpRequest{protocolVersion='1.1', method='POST', url='http://example.com/v1.0.0/users', queryParamsArray={name=[James], age=[32]}, postParamsArray={name=[Juan], age=[27]}, headers={Accept=[application/json]}, body='body', files=null}, requestCall=RequestCall{service='', version='', action='', params=[]}} Api{component=null, path='null', name='null', version='null', platformVersion='null', variables={}, isDebug=false, mapping=null}",
                object.toString());
    }

}