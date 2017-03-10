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
