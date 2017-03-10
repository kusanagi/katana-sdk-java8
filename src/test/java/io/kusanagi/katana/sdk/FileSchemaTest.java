package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FileSchemaTest {

    private FileSchema fileSchema;

    @Before
    public void setup() {
        fileSchema = new FileSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(fileSchema.isRequired());
        Assert.assertFalse(fileSchema.isExclusiveMax());
        Assert.assertFalse(fileSchema.isExclusiveMin());
        Assert.assertNotEquals(null, fileSchema.getHttpSchema());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        FileSchema object = mockFactory.getFileSchema();
        Assert.assertEquals(object, new FileSchema(object));
        Assert.assertEquals(-594969070, object.hashCode());
        Assert.assertEquals(
                "FileSchema{mime='image/jpeg', required=true, max=10240, exclusiveMax=true, min=1024, exclusiveMin=true, http=FileHttpSchema{gateway=false, param='avatar'}}",
                object.toString());
    }

}