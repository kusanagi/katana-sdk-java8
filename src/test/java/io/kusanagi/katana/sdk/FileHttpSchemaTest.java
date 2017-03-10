package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FileHttpSchemaTest {

    private FileHttpSchema fileHttpSchema;

    @Before
    public void setup() {
        fileHttpSchema = new FileHttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(fileHttpSchema.isGateway());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        FileHttpSchema object = mockFactory.getFileHttpSchema();
        Assert.assertEquals(object, new FileHttpSchema(object));
        Assert.assertEquals(-1405959847, object.hashCode());
        Assert.assertEquals(
                "FileHttpSchema{gateway=false, param='avatar'}",
                object.toString());
    }
}