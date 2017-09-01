/*
 * Java 7 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java7
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.api.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.commands.ActionCommandPayload;
import io.kusanagi.katana.api.commands.Mapping;
import io.kusanagi.katana.api.commands.RequestCommandPayload;
import io.kusanagi.katana.api.commands.ResponseCommandPayload;
import io.kusanagi.katana.api.component.utils.MessagePackSerializer;
import io.kusanagi.katana.api.replies.CallReplyPayload;
import io.kusanagi.katana.api.replies.ResponseReplyPayload;
import io.kusanagi.katana.api.replies.TransportReplyPayload;
import io.kusanagi.katana.utils.MockFactory;
import io.kusanagi.katana.utils.TestClient;
import io.kusanagi.katana.utils.TestMiddleware;
import io.kusanagi.katana.utils.TestService;
import io.kusanagi.katana.sdk.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by juan on 26/08/16.
 */

@RunWith(JUnit4.class)
public class ComponentTest {

    public static final String PORT = "5001";

    public static final SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final MockFactory mockFactory = new MockFactory();

    private Component component;
    private Serializer serializer;
    private String addr;

    @Before
    public void setup() {
        System.setProperty("katanaip", "127.0.0.1");
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        this.addr = "tcp://" + System.getProperty("katanaip") + ":" + PORT;

        String args = "-c service -n name -v 0.2.0 -f 0.1.0 -s socket -t " + PORT + " -d -A list --debug " +
                "-V var1=value1 -V var2=value2 --var var3=value3";
        this.component = new Service(args.split(" "));

        this.serializer = new MessagePackSerializer();
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
    }

    private void assertComponentArgs(String args, boolean valid) {
        try {
            new Service(args.split(" "));
            assertTrue(valid);
        } catch (IllegalArgumentException e) {
            assertTrue(!valid);
        }
    }

    @Test
    public void componentArgValues_invalid_throwException() {
        //Invalid argument values
        assertComponentArgs("-f 0.1.0 -c service -n users -v 0.1.{version}", false);
        assertComponentArgs("-f {version} -c service -n users -v 0.1.0", false);
        assertComponentArgs("-f 0.1.0 -c service1 -n users -v 0.1.0", false);
        assertComponentArgs("-f 0.1.0 -c middleware1 -n users -v 0.1.0", false);
        assertComponentArgs("-f 0.1.0 -c middleware -n users -v 0.1.0 -V name", false);
        assertComponentArgs("-c component -n name -v 0.1.0", false);
        assertComponentArgs("-c component -n name -f 0.1.0", false);
        assertComponentArgs("-c component -v 0.1.0 -f 0.1.0", false);
        assertComponentArgs("-c component -v 0.1.0 -f 0.1.0 -l invalid option", false);
        assertComponentArgs("-c component -V name=value -s socket -D", false);
        assertComponentArgs("--name name -i invalid_option --version 0.1.0 -f 0.1.0", false);
        assertComponentArgs("-c component -n name --name name --version 0.1.0 -f 0.1.0", false);
        assertComponentArgs("--name name --version 0.1.0 -f 0.1.0 version", false);
        assertComponentArgs("-c component -n name -v 0.1.0 -f 0.1.0 -s socket --socket socket2 --debug -V name=value", false);
        assertComponentArgs("-c component -n name -v 0.1.0 -f 0.1.0 -s socket --debug -D -V name=value", false);

        //Valid argument values
        assertComponentArgs("-f 0.1.0 -c service -n users -v 0.1.0", true);
        assertComponentArgs("-f 0.1.0 -c middleware -n users -v 0.1.0", true);
        assertComponentArgs("-f 0.1.0 -c middleware1 -n users -v 0.1.0 -V name=value", false);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0", true);
        assertComponentArgs("-c service --name name --version 0.1.0 --framework-version 0.1.0", true);
        assertComponentArgs("-c service -n name --version 0.1.0 --framework-version 0.1.0", true);
        assertComponentArgs("-c service --name name -v 0.1.0 --framework-version 0.1.0", true);
        assertComponentArgs("-c service --name name --version 0.1.0 -f 0.1.0", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -s socket", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -D", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -V name=value", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -t " + PORT, true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -d", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -A list", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -q", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 --socket socket", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 --debug", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 --var name=value", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 --tcp " + PORT, true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 --disable-compact-names", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 --action list", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 --quiet", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -s socket --debug -V name=value", true);
        assertComponentArgs("-c service -n name -v 0.1.0 -f 0.1.0 -s socket --debug -V name1=value -V name2=value --var name3=value", true);
    }

    @Test
    public void main_withValidArguments_setClassMembers() {
        String args = "-c service -n name -v 0.2.0 -f 0.1.0 -s socket -t " + PORT + " -d -A list -q --debug " +
                "-V var1=value1 -V var2=value2 --var var3=value3";
        Component component = new Service(args.split(" "));

        assertEquals("service", component.getComponent());
        assertEquals("name", component.getName());
        assertEquals("0.2.0", component.getVersion());
        assertEquals("0.1.0", component.getFrameworkVersion());
        assertEquals(PORT, component.getTcp());
        assertEquals(true, component.isDebug());
        assertEquals(true, component.isQuiet());
        assertEquals("list", component.getAction());
        assertEquals("socket", component.getSocket());
        assertEquals(3, component.getVar().size());
        assertEquals("value1", component.getVar().get("var1"));
        assertEquals("value2", component.getVar().get("var2"));
        assertEquals("value3", component.getVar().get("var3"));

    }


    @Test
    public void hasResource_existingResource_returnTrue() {
        String resource = "resource";
        Callable<Action> callable = object -> null;

        component.setResource(resource, callable);

        assertTrue(component.hasResource("resource"));
        assertFalse(component.hasResource("resource2"));
        Assert.assertEquals(callable, component.getResource("resource"));
        Assert.assertEquals(null, component.getResource("resource2"));
    }

    @Test
    public void log_debugMode_printLog() throws ParseException {
        boolean logged = component.log("message");

        String[] errors = outContent.toString().split("\n");
        String[] split = errors[0].split(" ");

        assertTrue(logged);
        Date date = STANDARD_DATE_FORMAT.parse(split[0]);
        assertEquals(date.getTime(), Calendar.getInstance().getTimeInMillis(), 1000);
        assertEquals("[DEBUG]", split[1]);
        assertEquals("[SDK]", split[2]);
        assertEquals("message", split[3]);
    }

    @Test
    public void log_notInDebugMode_dontPrintLogAndReturnFalse() throws ParseException {
        String args = "-c service -n name -v 0.2.0 -f 0.1.0 -s socket -t " + PORT + " -A list " +
                "-V var1=value1 -V var2=value2 --var var3=value3";
        Component service = new Service(args.split(" "));

        boolean logged = service.log("message");

        assertFalse(logged);
        assertEquals("", outContent.toString());
    }

    @Test
    public void receiveRequestPayload() throws InterruptedException, JsonProcessingException {
        //SETUP
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        final RequestCommandPayload[] requestCommandPayloads = new RequestCommandPayload[1];
        final Request[] requests = new Request[1];
        final Mapping[] mappings = new Mapping[1];
        final CallReplyPayload[] callReplyPayloads = new CallReplyPayload[1];

        final RequestCommandPayload requestCommandPayload = mockFactory.getRequestCommandPayload();
        final Mapping mapping = mockFactory.getMapping("users", "0.2.0");

        TestMiddleware testMiddleware = new TestMiddleware("-c middleware -n users -v 0.2.0 -f 0.1.0 -t " + PORT + " -D -V workers=1");
        testMiddleware.getMiddleware().request(request -> {
            requestCommandPayloads[0] = requestCommandPayload;
            requests[0]= request;
            mappings[0] = request.getMapping();
            request.setServiceName("users");
            request.setServiceVersion("0.2.0");
            request.setActionName("example");
            Param param = request.newParam("tlf", "555555", "string");
            request.setParam(param);
            countDownLatch.countDown();
            return request;
        });
        testMiddleware.start();

        TestClient testClient = new TestClient(addr,
                (part1, reply) -> {
                    callReplyPayloads[0] = serializer.deserialize(reply, CallReplyPayload.class);
                    countDownLatch.countDown();
                },
                "request".getBytes(),
                serializer.serializeInBytes(mapping.getServiceSchema()),
                serializer.serializeInBytes(requestCommandPayload));

        //ACTION
        testClient.start();

        //RESULT
        countDownLatch.await();
        testClient.close();
        testMiddleware.close();

        assertEquals(requestCommandPayload, requestCommandPayloads[0]);
        assertEquals(mapping, mappings[0]);
        Assert.assertEquals(requests[0].getRequestCall(), callReplyPayloads[0].getCommandReply().getResult().getRequestCall());

        Request request = requests[0];
        assertRequest(request);

        RequestCommandPayload otherRequestCommandPayload = new RequestCommandPayload(requestCommandPayloads[0]);
        assertEquals(requestCommandPayloads[0], otherRequestCommandPayload);

        CallReplyPayload otherCallReplyPayload = new CallReplyPayload(callReplyPayloads[0]);
        assertEquals(callReplyPayloads[0], otherCallReplyPayload);
    }

    private void assertRequest(Request request) {
        assertEquals("http", request.getGatewayProtocol());
        assertEquals("http://127.0.0.1:80", request.getGatewayAddress());
        assertEquals("205.81.5.62:7681", request.getClientAddress());
        assertEquals("users", request.getServiceName());
        assertEquals("0.2.0", request.getServiceVersion());
        assertEquals("example", request.getActionName());
        assertEquals(false, request.hasParam("name"));
        assertEquals(false, request.getParam("name").exists());
        assertEquals(true, request.hasParam("tlf"));
        assertEquals("555555", request.getParam("tlf").getValue());
        assertEquals("string", request.getParam("tlf").getType());
        assertHttpRequest(request.getHttpRequest());
    }

    private void assertHttpRequest(HttpRequest httpRequest) {
        assertEquals(true, httpRequest.isProtocolVersion("1.1"));
        assertEquals("1.1", httpRequest.getProtocolVersion());
        assertEquals(true, httpRequest.hasHeader("Accept"));
        assertEquals("application/json", httpRequest.getHeader("Accept", ""));
        assertEquals(1, httpRequest.getHeaders().size());
        assertEquals(true, httpRequest.hasBody());
        assertEquals("body", httpRequest.getBody());
        assertEquals(true, httpRequest.isMethod("POST"));
        assertEquals("POST", httpRequest.getMethod());
        assertEquals("http://example.com/v1.0.0/users", httpRequest.getUrl());
        assertEquals("http", httpRequest.getUrlScheme());
        assertEquals("example.com", httpRequest.getUrlHost());
        assertEquals("/v1.0.0/users", httpRequest.getUrlPath());
        assertEquals(true, httpRequest.hasQueryParam("name"));
        assertEquals("James", httpRequest.getQueryParam("name", ""));
        assertEquals("Unknown", httpRequest.getQueryParam("addr", "Unknown"));
        assertEquals(1, httpRequest.getQueryParamArray("name", new ArrayList<>()).size());
        assertEquals(2, httpRequest.getQueryParamArray("addr", Arrays.asList("Unknown", "Unknown")).size());
        assertEquals("32", httpRequest.getQueryParams().get("age"));
        assertEquals(true, httpRequest.hasPostParam("name"));
        assertEquals("Juan", httpRequest.getPostParam("name", ""));
        assertEquals("Unknown", httpRequest.getPostParam("addr", "Unknown"));
        assertEquals(1, httpRequest.getPostParamArray("name", new ArrayList<>()).size());
        assertEquals(2, httpRequest.getPostParamArray("addr", Arrays.asList("Unknown", "Unknown")).size());
        assertEquals("27", httpRequest.getPostParams().get("age"));
    }

    @Test
    public void receiveResponsePayload() throws InterruptedException, JsonProcessingException {
        //SETUP
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        final ResponseCommandPayload[] responseCommandPayloads = new ResponseCommandPayload[1];
        final Response[] responses = new Response[1];
        final Mapping[] mappings = new Mapping[1];
        final ResponseReplyPayload[] responseReplyPayloads = new ResponseReplyPayload[1];

        final ResponseCommandPayload responseCommandPayload = mockFactory.getResponseCommandPayload();
        final Mapping mapping = mockFactory.getMapping("users", "0.2.0");

        TestMiddleware testMiddleware = new TestMiddleware("-c middleware -n users -v 0.2.0 -f 0.1.0 -t " + PORT + " -D -V workers=1");
        testMiddleware.getMiddleware().response(object -> {
            responseCommandPayloads[0] = responseCommandPayload;
            responses[0] = object;
            mappings[0] = object.getMapping();
            object.getHttpResponse().setProtocolVersion("1.0");
            object.getHttpResponse().setStatus(201, "Created");
            object.getHttpResponse().setHeader("Authorization", "Token");
            countDownLatch.countDown();
            return object;
        });
        testMiddleware.start();

        TestClient testClient = new TestClient(addr,
                (part1, reply) -> {
                    responseReplyPayloads[0] = serializer.deserialize(reply, ResponseReplyPayload.class);
                    countDownLatch.countDown();
                },
                "response".getBytes(),
                serializer.serializeInBytes(mapping.getServiceSchema()),
                serializer.serializeInBytes(responseCommandPayload));

        //ACTION
        testClient.start();

        //RESULT
        countDownLatch.await();
        testClient.close();
        testMiddleware.close();

        assertEquals(responseCommandPayload, responseCommandPayloads[0]);
        assertEquals(mapping, mappings[0]);
        Assert.assertEquals(responses[0].getHttpResponse(), new HttpResponse.Builder().setHttpResponseEntity(responseReplyPayloads[0].getCommandReply().getResult().getHttpResponse()).build());

        Response response = responses[0];
        assertResponse(response);
//        assertEquals(responses[0].getHttpRequest(), response.getHttpRequest());
//        assertEquals(responses[0].getTransport(), response.getTransport());

        ResponseCommandPayload otherResponseCommandPayload = new ResponseCommandPayload(responseCommandPayloads[0]);
        assertEquals(responseCommandPayloads[0], otherResponseCommandPayload);

        ResponseReplyPayload otherResponseReplyPayload = new ResponseReplyPayload(responseReplyPayloads[0]);
        assertEquals(responseReplyPayloads[0], otherResponseReplyPayload);

    }

    private void assertResponse(Response response) {
        assertEquals("http", response.getGatewayProtocol());
        assertEquals("http://127.0.0.1:80", response.getGatewayAddress());
        assertHttpResponse(response.getHttpResponse());
    }

    private void assertHttpResponse(HttpResponse httpResponse) {
        assertEquals(false, httpResponse.isProtocolVersion("1.1"));
        assertEquals("1.0", httpResponse.getProtocolVersion());
        assertEquals(false, httpResponse.isStatus("200 OK"));
        assertEquals("201 Created", httpResponse.getStatus());
        assertEquals(201, httpResponse.getStatusCode());
        assertEquals("Created", httpResponse.getStatusText());
        assertEquals(true, httpResponse.hasHeader("Content-Type"));
        assertEquals("application/json", httpResponse.getHeader("Content-Type"));
        assertEquals(true, httpResponse.hasHeader("Authorization"));
        assertEquals("Token", httpResponse.getHeader("Authorization"));
        assertEquals(2, httpResponse.getHeaders().size());
        assertEquals(true, httpResponse.hasBody());
        assertEquals("{\"result\":\"OK\",\"message\":\"Created new user\"}", httpResponse.getBody());
    }

    @Test
    public void receiveActionPayload() throws InterruptedException, JsonProcessingException {
        //SETUP
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        final ActionCommandPayload[] actionCommandPayloads = new ActionCommandPayload[1];
        final Action[] actions = new Action[1];
        final Mapping[] mappings = new Mapping[1];
        final Transport[] transports = new Transport[1];
        final TransportReplyPayload[] transportReplyPayloads = new TransportReplyPayload[1];

        final ActionCommandPayload actionCommandPayload = mockFactory.getActionCommandPayload();
        final Mapping mapping = mockFactory.getMapping("users", "0.2.0");

        TestService testService = new TestService("-c service -n users -v 0.2.0 -f 0.1.0 -t " + PORT + " -D -V workers=1");
        testService.getService().action("read", object -> {
            actionCommandPayloads[0] = actionCommandPayload;
            actions[0] = object;
            mappings[0] = object.getMapping();
//                object.setProperty("property", "value");
            File file = object.newFile("file", "path", "image/jpeg");
            object.setDownload(file);
            file.setName(null);
            object.setEntity("entity");
            List<String> collection = new ArrayList<>();
            collection.add("entity1");
            collection.add("entity2");
            object.setCollection(collection);
            object.relateOne("id", "post", "author");
            object.relateMany("id", "post", Arrays.asList("collaborator", "writer"));
            object.relateOneRemote("id", "http://192.168.55.10", "post", "author");
            object.relateManyRemote("id", "http://192.168.55.10", "post", Arrays.asList("collaborator", "writer"));
            object.setLink("self", "/0.1.0/users");
            List<Param> params = new ArrayList<>();
            object.newParam("param1", "value", "string");
            object.newParam("param2", "value", "string");
            object.commit("create", params);
            object.rollback("create", params);
            object.complete("create", params);
//            object.deferCall("posts", "0.1.0", "read", params, null);
//            object.remoteCall("http://192.168.55.10", "posts", "0.1.0", "read", params, null, 1000);
            object.error("Unauthorized", 401, "401 Unauthorized");
            transports[0] = object.getTransport();
            countDownLatch.countDown();
            return object;
        });
        testService.start();

        TestClient testClient = new TestClient(addr,
                (part1, reply) -> {
                    transportReplyPayloads[0] = serializer.deserialize(reply, TransportReplyPayload.class);
                    countDownLatch.countDown();
                },
                "read".getBytes(),
                serializer.serializeInBytes(mapping.getServiceSchema()),
                serializer.serializeInBytes(actionCommandPayload));

        //ACTION
        testClient.start();

        //RESULT
        countDownLatch.await();
        testClient.close();
        testService.close();

        assertEquals(actionCommandPayload, actionCommandPayloads[0]);
        assertEquals(mapping, mappings[0]);
        assertEquals(transports[0], new Transport.Builder().setTransportEntity(transportReplyPayloads[0].getCommandReply().getResult().getTransport()).build());
        assertAction(actions[0]);

        ActionCommandPayload otherActionCommandPayload = new ActionCommandPayload(actionCommandPayloads[0]);
        assertEquals(actionCommandPayloads[0], otherActionCommandPayload);

        Mapping otherMapping = new Mapping(mappings[0]);
        assertEquals(mappings[0], otherMapping);

        TransportReplyPayload otherTransportReplyPayload = new TransportReplyPayload(transportReplyPayloads[0]);
        assertEquals(transportReplyPayloads[0], otherTransportReplyPayload);

    }

    private void assertHttpSchema(HttpSchema httpSchema) {
        assertEquals(true, httpSchema.isAccesible());
        assertEquals("/1.0.0", httpSchema.getBasePath());
    }

    private void assertActionSchema(ActionSchema actionSchema) {
        assertEquals(true, actionSchema.isDeprecated());
        assertEquals(1000, actionSchema.getTimeout());
        assertEquals(true, actionSchema.isCollection());
        assertEquals("read", actionSchema.getName());
        assertEquals("entity:data", actionSchema.getEntityPath());
        assertEquals(":", actionSchema.getPathDelimiter());
        assertEquals("uid", actionSchema.getPrimaryKey());
//        assertEquals("404 Not Found", actionSchema.resolveEntity());
        assertEquals(true, actionSchema.hasEntity());
        assertEquals(true, actionSchema.hasRelations());
        assertEquals("accounts", actionSchema.getRelations().get(0).getName());
        assertEquals("many", actionSchema.getRelations().get(1).getType());
        assertEquals("posts", actionSchema.getRelations().get(1).getName());
        assertEquals(true, actionSchema.hasCall("comments", "1.2.0", "list"));
        assertEquals(false, actionSchema.hasCall("comments", "1.2.0", "read"));
        assertEquals(false, actionSchema.hasCall("comments", "1.2.1", "read"));
        assertEquals(false, actionSchema.hasCall("users", "1.2.1", "read"));
        assertEquals(true, actionSchema.hasCalls());
        assertEquals("comments", actionSchema.getCalls()[0][0]);
        assertEquals("1.2.0", actionSchema.getCalls()[0][1]);
        assertEquals("list", actionSchema.getCalls()[0][2]);
        assertEquals(true, actionSchema.hasRemoteCall("12.34.56.78:1234", "cdn", "1.0.0", "upload"));
        assertEquals(false, actionSchema.hasRemoteCall("12.34.56.78:1234", "cdn", "1.0.0", "read"));
        assertEquals(false, actionSchema.hasRemoteCall("12.34.56.78:1234", "cdn", "0.2.0", "read"));
        assertEquals(false, actionSchema.hasRemoteCall("12.34.56.78:1234", "users", "0.2.0", "read"));
        assertEquals(false, actionSchema.hasRemoteCall("12.34.56.78:8888", "users", "0.2.0", "read"));
        assertEquals(true, actionSchema.hasRemoteCalls());
        assertEquals("12.34.56.78:1234", actionSchema.getRemoteCalls()[0][0]);
        assertEquals("cdn", actionSchema.getRemoteCalls()[0][1]);
        assertEquals("1.0.0", actionSchema.getRemoteCalls()[0][2]);
        assertEquals("upload", actionSchema.getRemoteCalls()[0][3]);
        assertEquals(1, actionSchema.getParams().size());
        assertEquals(1, actionSchema.getFiles().size());
        assertEquals(false, actionSchema.hasFile("image"));

        assertEntitySchema(actionSchema.getEntity());
        assertParamSchema(actionSchema.getParamSchema("user_id"));
        assertFileSchema(actionSchema.getFileSchema("avatar"));
        assertActionHttpSchema(actionSchema.getHttpSchema());
    }

    private void assertEntitySchema(EntitySchema entity) {

    }

    private void assertActionHttpSchema(ActionHttpSchema httpSchema) {
        assertEquals(true, httpSchema.isAccesible());
        assertEquals("get", httpSchema.getMethod());
        assertEquals("/posts/{user_id}", httpSchema.getPath());
        assertEquals("path", httpSchema.getInput());
        assertEquals("text/plain", httpSchema.getBody()[0]);
    }

    private void assertFileSchema(FileSchema fileSchema) {
        assertEquals("avatar", fileSchema.getName());
        assertEquals("image/jpeg", fileSchema.getMime());
        assertEquals(true, fileSchema.isRequired());
        assertEquals(10240, fileSchema.getMax());
        assertEquals(true, fileSchema.isExclusiveMax());
        assertEquals(1024, fileSchema.getMin());
        assertEquals(true, fileSchema.isExclusiveMin());
        assertFileHttpSchema(fileSchema.getHttpSchema());
    }

    private void assertFileHttpSchema(FileHttpSchema httpSchema) {
        assertEquals(false, httpSchema.isAccessible());
        assertEquals("avatar", httpSchema.getParam());
    }

    private void assertParamSchema(ActionParamSchema paramSchema) {
        assertEquals("user_id", paramSchema.getName());
        assertEquals("string", paramSchema.getType());
        assertEquals("uuid", paramSchema.getFormat());
        assertEquals("csv", paramSchema.getArrayFormat());
        assertEquals("[a-zA-Z0-9]+", paramSchema.getPattern());
        assertEquals(false, paramSchema.allowEmpty());
        assertEquals(true, paramSchema.hasDefaultValue());
        assertEquals("0", paramSchema.getDefaultValue());
        assertEquals(true, paramSchema.isRequired());
        assertEquals("{\"user_id\": \"0\"}", paramSchema.getItems());
        assertEquals(100, paramSchema.getMax());
        assertEquals(true, paramSchema.isExclusiveMax());
        assertEquals(0, paramSchema.getMin());
        assertEquals(true, paramSchema.isExclusiveMin());
        assertEquals(500, paramSchema.getMaxLength());
        assertEquals(3, paramSchema.getMinLength());
        assertEquals(20, paramSchema.getMaxItems());
        assertEquals(2, paramSchema.getMinItems());
        assertEquals("0", paramSchema.getEnum().get(0));
        assertEquals("1", paramSchema.getEnum().get(1));
        assertEquals("2", paramSchema.getEnum().get(2));
        assertEquals(5, paramSchema.getMultipleOf());
        assertParamHttpSchema(paramSchema.getHttpSchema());
    }

    private void assertParamHttpSchema(ActionParamHttpSchema httpSchema) {
        assertEquals(true, httpSchema.isAccessible());
        assertEquals("path", httpSchema.getInput());
        assertEquals("user_id", httpSchema.getParam());
    }

    private void assertServiceSchema(ServiceSchema serviceSchema) {
        assertEquals("users", serviceSchema.getName());
        assertEquals("0.2.0", serviceSchema.getVersion());
        assertEquals(true, serviceSchema.hasFileServer());
        assertEquals("read", serviceSchema.getActions().get(0));
        assertEquals(true, serviceSchema.hasAction("read"));
        assertEquals(false, serviceSchema.hasAction("list"));
        assertActionSchema(serviceSchema.getActionSchema("read"));
        assertHttpSchema(serviceSchema.getHttpSchema());
    }

    private void assertTransport(Transport transport) {
        assertEquals("f1b27da9-240b-40e3-99dd-a567e4498ed7", transport.getRequestId());
        assertEquals("2016-04-12T02:49:05.761", transport.getRequestTimeStamp());
        assertEquals("users", transport.getOriginService()[0]);
        assertEquals("1.0.0", transport.getOriginService()[1]);
        assertEquals("list", transport.getOriginService()[2]);
        assertEquals("value", transport.getProperty("property", "Unknown Property"));
        assertEquals("Unknown Property", transport.getProperty("name", "Unknown Property"));
        assertEquals(1, transport.getProperties().size());
        assertEquals(true, transport.hasDownload());
        assertEquals("path", transport.getDownload().getPath());
        assertEquals("image/jpeg", transport.getDownload().getMime());

        assertData((List) transport.getData("http://127.0.0.1:80", "users", "0.2.0", "read"));
//        assertEquals("", transport.getRelations());
        assertLinks((Map) transport.getLinks("http://127.0.0.1:80", "users"));
//        assertCalls((List<Call>) ((Map) transport.getCalls("users")).get("0.2.0"));
        assertTransactions(transport.getTransactions("users"));
        assertErrors((List<io.kusanagi.katana.sdk.Error>) ((Map) transport.getErrors("http://127.0.0.1:80", "users")).get("1.0.0"));
    }

    private void assertErrors(List<io.kusanagi.katana.sdk.Error> errors) {
        assertEquals("The user does not exist", errors.get(0).getMessage());
        assertEquals(9, errors.get(0).getCode());
        assertEquals("404 Not Found", errors.get(0).getStatus());
    }

    private void assertTransactions(Transaction transactions) {
        assertEquals("users", transactions.getCommit().get(0).getName());
        assertEquals("1.0.0", transactions.getCommit().get(0).getVersion());
        assertEquals("create", transactions.getCommit().get(0).getAction());
        assertEquals("save", transactions.getCommit().get(0).getCaller());
    }

    private void assertCalls(List<Call> calls) {
        assertEquals("posts", calls.get(0).getName());
        assertEquals("0.1.0", calls.get(0).getVersion());
        assertEquals("read", calls.get(0).getAction());
        assertEquals("http://192.168.55.10", calls.get(0).getGateway());
        assertEquals(1000, calls.get(0).getTimeout());
    }

    private void assertLinks(Map links) {
        assertEquals("/0.1.0/users", links.get("self"));
    }

    private void assertData(List data) {
        assertEquals("entity1", data.get(0));
        assertEquals("entity2", data.get(1));
    }

    private void assertAction(Action action) {
        assertEquals(true, action.isOrigin());
        assertEquals("read", action.getActionName());
        assertEquals(true, action.hasParam("name"));
        assertEquals("James", action.getParam("name").getValue());
        assertEquals("string", action.getParam("name").getType());
        assertEquals(false, action.hasParam("addr"));
        assertEquals(false, action.getParam("addr").exists());
        assertEquals(2, action.getParams().size());
        assertEquals(true, action.hasFile("avatar"));
        assertEquals("smiley.jpg", action.getFile("avatar").getFilename());
        assertEquals(false, action.hasFile("image"));
        assertEquals("image", action.getFile("image").getName());
        assertEquals("", action.getFile("image").getPath());
        assertEquals(2, action.getFiles().size());

        assertTransport(action.getTransport());
        assertServiceSchema(action.getServiceSchema("users", "0.2.0"));
    }

    @Test
    public void startupAndShutdown() throws InterruptedException, JsonProcessingException {
        //SETUP
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        final int[] secuence = new int[1];
        secuence[0] = 0;

        final ActionCommandPayload actionCommandPayload = mockFactory.getActionCommandPayload();
        final Mapping mapping = mockFactory.getMapping("users", "0.2.0");

        TestService testService = new TestService("-c service -n users -v 0.2.0 -f 0.1.0 -t " + PORT + " -D -V workers=1");
        testService.getService().startup(object -> {
            if (secuence[0] == 0) {
                secuence[0] = 1;
            }
            countDownLatch.countDown();
            return object;
        });
        testService.getService().action("users", object -> {
            if (secuence[0] == 1) {
                secuence[0] = 2;
            }
            countDownLatch.countDown();
            return object;
        });
//        testService.getService().shutdown(new EventCallable<Service>() {
//            @Override
//            public Service run(Service object) {
//                if (secuence[0] == 2){
//                    secuence[0] = 3;
//                }
//                countDownLatch.countDown();
//                return null;
//            }
//        });
        testService.start();

        TestClient testClient = new TestClient(addr,
                (part1, reply) -> {
                },
                "users".getBytes(),
                serializer.serializeInBytes(mapping.getServiceSchema()),
                serializer.serializeInBytes(actionCommandPayload));

        //ACTION
        testClient.start();

        //RESULT
        countDownLatch.await();
        testClient.close();
        testService.close();

        assertEquals(2, secuence[0]);
    }

    @Test
    public void apiMethods() throws InterruptedException, JsonProcessingException {
        //SETUP
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        final Api[] apis = new Api[1];

        final ActionCommandPayload actionCommandPayload = mockFactory.getActionCommandPayload();
        final Mapping mapping = mockFactory.getMapping("users", "0.2.0");
        Callable<Action> usersResource = object -> {
            apis[0] = object;
            countDownLatch.countDown();
            return object;
        };

        TestService testService = new TestService("-c service -n users -v 0.2.0 -f 0.1.0 -t " + PORT + " -D -V workers=1");
        testService.getService().action("read", usersResource);
        testService.getService().setResource("resource", usersResource);
        testService.start();

        TestClient testClient = new TestClient(addr,
                (part1, reply) -> {
                },
                "read".getBytes(),
                serializer.serializeInBytes(mapping.getServiceSchema()),
                serializer.serializeInBytes(actionCommandPayload)
        );

        //ACTION
        testClient.start();

        //RESULT
        countDownLatch.await();
        testClient.close();
        testService.close();

        assertTrue(apis[0].isDebug());
        assertEquals("http://127.0.0.1:80", apis[0].getPath());
        assertEquals("0.1.0", apis[0].getFrameworkVersion());
        assertEquals("0.1.0", apis[0].getFrameworkVersion());
        assertEquals("users", apis[0].getName());
        assertEquals("0.2.0", apis[0].getVersion());
        assertTrue(apis[0].hasResource("resource"));
        assertEquals(usersResource, apis[0].getResource("resource"));
        assertEquals(mapping.getServiceSchema().get("users").get("0.2.0"), apis[0].getServiceSchema("users", "0.2.0"));
    }

}
