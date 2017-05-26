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

package io.kusanagi.katana.utils;

import io.kusanagi.katana.sdk.Service;

/**
 * Created by juane on 2/13/17.
 */
public class TestService extends Thread {
    private final Service service;

    public TestService(String args) {
        this.service = new Service(args.split(" "));
    }

    public Service getService() {
        return service;
    }

    @Override
    public void run() {
        this.service.run();
    }

    public void close() {
        this.service.stopSocket();
    }
}
