package io.kusanagi.katana.api.component.utils;

/**
 * Created by jega on 7/03/17.
 */
public class Factory {

    private Factory() {
        // private constructor to block the instantiation of this object
    }

    public static OptionManager getOptionManager() {
        return new OptionManager();
    }

    public static MessagePackSerializer getSerializer() {
        return new MessagePackSerializer();
    }
}
