/*
 * Java 8 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java8
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.api.component;

import io.kusanagi.katana.api.component.utils.Logger;
import org.zeromq.ZMQ;

/**
 * Created by juan on 1/10/16.
 */

public class ComponentWorker extends Thread {

    private final String workerEndpoint;

    private ZMQ.Context context;

    private ZMQ.Socket socketObj;

    private WorkerListener workerListener;

    private boolean listen;

    public ComponentWorker(String workerEndpoint) {
        this.workerEndpoint = workerEndpoint;
    }

    public void setWorkerListener(WorkerListener workerListener) {
        this.workerListener = workerListener;
    }

    @Override
    public void run() {
        startSocket();
        int part = 0;
        String part1 = "";
        byte[] part2 = new byte[0];
        byte[] part3 = new byte[0];
        while (listen) {
            part++;
            if (part == 1) {
                part1 = socketObj.recvStr();
            } else if (part == 2 && socketObj.hasReceiveMore()) {
                part2 = socketObj.recv();
            } else if (part == 3 && socketObj.hasReceiveMore()) {
                part3 = socketObj.recv();
            }
            if (!socketObj.hasReceiveMore()) {
                part = 0;
                byte[][] reply = workerListener.onRequestReceived(part1, part2.length == 0 ? null : part2, part3);
                socketObj.sendMore(reply[0]);
                socketObj.send(reply[1]);
            }
        }
    }

    public void startSocket() {
        context = ZMQ.context(1);
        socketObj = context.socket(ZMQ.REP);
        socketObj.connect(this.workerEndpoint);
        this.listen = true;
    }

    public void stopSocket() {
        socketObj.close();
//        context.term();
    }

    public interface WorkerListener {
        byte[][] onRequestReceived(String componentType, byte[] mappings, byte[] request);
    }
}
