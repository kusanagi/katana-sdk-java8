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
