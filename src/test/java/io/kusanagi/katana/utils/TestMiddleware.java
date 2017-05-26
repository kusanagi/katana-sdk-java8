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

import io.kusanagi.katana.sdk.Middleware;

/**
 * Created by juane on 2/13/17.
 */
public class TestMiddleware extends Thread {

    private Middleware middleware;

    public TestMiddleware(String args) {
        this.middleware = new Middleware(args.split(" "));
    }

    public Middleware getMiddleware() {
        return this.middleware;
    }

    @Override
    public void run() {
        this.middleware.run();
    }

    public void close() {
        this.middleware.stopSocket();
        interrupt();
    }
}
