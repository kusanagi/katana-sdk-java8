package io.kusanagi.katana.api.component;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Created by juan on 25/09/16.
 */
public interface Serializer {
    /**
     * @param message
     * @param aClass
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] message, Class<T> aClass) throws IOException;

    <T> T deserialize(String jsonMessage, Class<T> aClass) throws IOException;

    /**
     * @param message
     * @return
     */
    byte[] serializeInBytes(Object message) throws JsonProcessingException;

    String serializeInJson(Object message) throws JsonProcessingException;

}
