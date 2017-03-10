package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ActionTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Action object = mockFactory.getAction();
        Assert.assertEquals(object, new Action(object));
        Assert.assertEquals(787668137, object.hashCode());
        Assert.assertEquals(
                "Action{params=[Param{name='name', value='James', type='string', exists=false}, Param{name='age', value='32', type='integer', exists=false}], transport=Transport{meta=TransportMeta{version='1.0.0', id='f1b27da9-240b-40e3-99dd-a567e4498ed7', datetime='2016-04-12T02:49:05.761', gateway=[12.34.56.78:1234, http://127.0.0.1:80], origin=[users, 1.0.0, list], level=1, fallback=[[users, 1.0.0, [create, update]]], properties={property=value}}, body=File{name='null', path='file:///tmp/document.pdf', token='', filename='document.pdf', size='1234567890', mime='application/pdf', exists=false}, files={http://127.0.0.1:80={users={1.0.0={create={avatar=File{name='null', path='http://12.34.56.78:1234/files/ac3bd4b8-7da3-4c40-8661-746adfa55e0d', token='fb9an6c46be74s425010896fcbd99e2a', filename='smiley.jpg', size='1234567890', mime='image/jpeg', exists=false}, document=File{name='null', path='file:///tmp/document.pdf', token='', filename='document.pdf', size='1234567890', mime='application/pdf', exists=false}}}}}}, data={http://127.0.0.1:80={users={1.0.0={read_users=[{name=Juan}, {name=Ricardo}], list_users=[[{name=Juan}, {name=Ricardo}]]}}}}, relations={http://127.0.0.1:80={users={123={http://127.0.0.1:80={posts=[1, 2]}}}, posts={1={http://127.0.0.1:80={categories=1}, ktp://87.65.43.21:4321={comments=[1, 2, 3]}}, 2={http://127.0.0.1:80={categories=2}, ktp://87.65.43.21:4321={comments=[4]}}}}}, links={http://127.0.0.1:80={users={self=http://api.example.com/v1/users/123}}}, calls={users={1.0.0=[Call{name='posts', version='1.2.0', action='list', params=[Param{name='X-Request-Token', value='ac3bd4b8-7da3-4c40-8661-746adfa55e0d', type='string', exists=false}, Param{name='user_id', value='123', type='integer', exists=false}], gateway='null', timeout=0}], 1.0.1=[Call{name='comments', version='1.1.0', action='list', params=[Param{name='post_id', value='321', type='integer', exists=false}], gateway='ktp://87.65.43.21:4321', timeout=2000}]}}, transactions=Transaction{commit=[ServiceTransaction{name='users', version='1.0.0', action='create', caller='save', params=[Param{name='user_id', value='123', type='integer', exists=false}]}], rollback=[ServiceTransaction{name='users', version='1.0.0', action='create', caller='undo', params=[Param{name='user_id', value='123', type='integer', exists=false}]}], complete=[ServiceTransaction{name='users', version='1.0.0', action='create', caller='cleanup', params=[Param{name='user_id', value='123', type='integer', exists=false}]}]}, errors={http://127.0.0.1:80={users={1.0.0=[Error{message='The user does not exist', code='9', status='404 Not Found'}]}}}}} Api{component=null, path='null', name='null', version='null', platformVersion='null', variables={}, isDebug=false, mapping=null}",
                object.toString());
    }

}