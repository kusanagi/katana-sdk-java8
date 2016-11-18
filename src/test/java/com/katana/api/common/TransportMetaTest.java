package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juan on 12/11/16.
 */
public class TransportMetaTest {

    private TransportMeta transportMeta;

    @Before
    public void setUp() {
        this.transportMeta = new TransportMeta();
    }

    @Test
    public void getOrigin() {
        // SETUP
        String[] origin = {"Origin"};
        this.transportMeta.setOrigin(origin);

        // ACTION
        String[] originObtained = this.transportMeta.getOrigin();

        // EXPECTED
        Assert.assertEquals(origin, originObtained);
    }

    @Test
    public void setOrigin() {
        // SETUP
        String[] origin = {"Origin"};

        // ACTION
        this.transportMeta.setOrigin(origin);

        // EXPECTED
        Assert.assertEquals(origin, this.transportMeta.getOrigin());
    }

    @Test
    public void getLevel() {
        // SETUP
        int level = 200;
        this.transportMeta.setLevel(level);

        // ACTION
        int levelObtained = this.transportMeta.getLevel();

        // EXPECTED
        Assert.assertEquals(level, levelObtained);
    }

    @Test
    public void setLevel() {
        // SETUP
        int level = 200;

        // ACTION
        this.transportMeta.setLevel(level);

        // EXPECTED
        Assert.assertEquals(level, this.transportMeta.getLevel());
    }

    @Test
    public void getProperties() {
        // SETUP
        Map<String, String> properties = new HashMap<>();
        this.transportMeta.setProperties(properties);

        // ACTION
        Map<String, String> propertiesObtained = this.transportMeta.getProperties();

        // EXPECTED
        Assert.assertEquals(properties, propertiesObtained);
    }

    @Test
    public void setProperties() {
        // SETUP
        Map<String, String> properties = new HashMap<>();

        // ACTION
        this.transportMeta.setProperties(properties);

        // EXPECTED
        Assert.assertEquals(properties, this.transportMeta.getProperties());
    }
}