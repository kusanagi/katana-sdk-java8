package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ServiceSchemaTest {

    private ServiceSchema serviceSchema;

    @Before
    public void setup() {
        serviceSchema = new ServiceSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(serviceSchema.isFiles());
        Assert.assertNotEquals(null, serviceSchema.getHttpSchema());
        Assert.assertNotEquals(null, serviceSchema.getActions());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ServiceSchema object = mockFactory.getServiceSchema();
        Assert.assertEquals(object, new ServiceSchema(object));
        Assert.assertEquals(-1047441469, object.hashCode());
//        Assert.assertEquals(
//                "ServiceSchema{address='12.34.56.78:1234', files=true, httpSchema=HttpSchema{gateway=true, basePath='/1.0.0'}, actionSchemas={list=ActionSchema{name='null', timeout=1000, entityPath='entity:data', pathDelimiter=':', primaryKey='uid', collection=true, calls=[[Ljava.lang.String;@58659bc9], remoteCalls=[[Ljava.lang.String;@76d6d675], fallbacks={error=TransportSchema{properties={foo=123}, data=[{id=ValueSchema{type='integer', value=321}, name=ValueSchema{type='string', value=James}, status=ValueSchema{type='null', value=null}, info=ValueSchema{type='array', value=[{v=Hello World}]}, stats=ValueSchema{type='object', value={count={t=integer, v=12345}}}}], relations=[[1, user, 5], [1, comments, [3, 8]]], links={self=/1.0.0/posts}, errors=[[Unable to process posts, 0, 560 Logic Error]]}}, deprecated=true, http=ActionHttpSchema{gateway=true, path='/posts/{user_id}', method='get', input='path', body='[text/plain]'}, params={user_id=ActionParamSchema{type='string', format='uuid', arrayFormat='csv', pattern='[a-zA-Z0-9]+', allowEmpty=false, defaultValue='0', required=true, items='{\"user_id\": \"0\"}', max=100, exclusiveMax=true, min=0, exclusiveMin=true, maxLength=500, minLength=3, maxItems=20, minItems=2, uniqueItems=false, enumeration=[0, 1, 2], multipleOf=5, http=ActionParamHttpSchema{gateway=true, input='path', param='user_id'}}}, files={avatar=FileSchema{mime='image/jpeg', required=true, max=10240, exclusiveMax=true, min=1024, exclusiveMin=true, http=FileHttpSchema{gateway=false, param='avatar'}}}, entity=EntitySchema{field=[FieldSchema{name='id', type='integer', optional='false'}, FieldSchema{name='name', type='string', optional='false'}, FieldSchema{name='active', type='boolean', optional='false'}, FieldSchema{name='is_admin', type='boolean', optional='true'}], fields=[ObjectFieldSchema{name='contact', optional=false, field=[FieldSchema{name='id', type='integer', optional='false'}, FieldSchema{name='email', type='null', optional='false'}, FieldSchema{name='location', type='object', optional='false'}], fields=[]}], validate=true}, relations=[RelationSchema{name='accounts', type='one'}, RelationSchema{name='posts', type='many'}]}}}",
//                object.toString());
    }

}