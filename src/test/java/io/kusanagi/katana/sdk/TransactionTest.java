package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class TransactionTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Transaction object = mockFactory.getTransaction();
        Assert.assertEquals(object, new Transaction(object));
        Assert.assertEquals(-669451010, object.hashCode());
        Assert.assertEquals(
                "Transaction{commit=[ServiceTransaction{name='users', version='1.0.0', action='create', caller='save', params=[Param{name='user_id', value='123', type='integer', exists=false}]}], rollback=[ServiceTransaction{name='users', version='1.0.0', action='create', caller='undo', params=[Param{name='user_id', value='123', type='integer', exists=false}]}], complete=[ServiceTransaction{name='users', version='1.0.0', action='create', caller='cleanup', params=[Param{name='user_id', value='123', type='integer', exists=false}]}]}",
                object.toString());
    }

}