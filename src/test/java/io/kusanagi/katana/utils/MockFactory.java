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

package io.kusanagi.katana.utils;

import io.kusanagi.katana.api.commands.ActionCommandPayload;
import io.kusanagi.katana.api.commands.Mapping;
import io.kusanagi.katana.api.commands.RequestCommandPayload;
import io.kusanagi.katana.api.commands.ResponseCommandPayload;
import io.kusanagi.katana.api.commands.common.CommandMeta;
import io.kusanagi.katana.api.component.Serializer;
import io.kusanagi.katana.api.component.utils.Logger;
import io.kusanagi.katana.api.component.utils.MessagePackSerializer;
import io.kusanagi.katana.sdk.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by juane on 2/11/17.
 */
public class MockFactory {

    public static final String MOCKS_PATH = "/resources/";

    private <T> T getFromJson(String filename, Class<T> aClass) {
        try {
            URL url = getClass().getResource(MOCKS_PATH + filename);
            Path path = Paths.get(url.toURI());
            byte[] bytes = Files.readAllBytes(path);
            String command = new String(bytes);
            Serializer serializer = new MessagePackSerializer();
            return serializer.deserialize(command, aClass);
        } catch (IOException | URISyntaxException e) {
            Logger.log(e);
            return null;
        }
    }

    public RequestCommandPayload getRequestCommandPayload() {
        return getFromJson("request_command_payload.json", RequestCommandPayload.class);
    }

    public ResponseCommandPayload getResponseCommandPayload() {
        return getFromJson("response_command_payload.json", ResponseCommandPayload.class);
    }

    public ActionCommandPayload getActionCommandPayload() {
        return getFromJson("action_command_payload.json", ActionCommandPayload.class);
    }

    public Mapping getMapping(String name, String version) {
        Mapping mapping = new Mapping();
        Map<String, Map<String, ServiceSchema>> serviceMap = new HashMap<>();
        Map<String, ServiceSchema> versionMap = new HashMap<>();

        versionMap.put(version, getServiceSchema());
        serviceMap.put(name, versionMap);
        mapping.setServiceSchema(serviceMap);

        return mapping;
    }

    public ServiceSchema getServiceSchema() {
        return getFromJson("mapping.json", ServiceSchema.class);
    }

    public CommandMeta getCommandMeta() {
        return getRequestCommandPayload().getCommandMeta();
    }

    public ActionCommandPayload.ActionCommand getActionCommand() {
        return getActionCommandPayload().getCommand();
    }

    public RequestCommandPayload.RequestCommand getRequestCommand() {
        return getRequestCommandPayload().getCommand();
    }

    public ResponseCommandPayload.ResponseCommand getResponseCommand() {
        return getResponseCommandPayload().getCommand();
    }

    public ActionHttpSchema getActionHttpSchama() {
        return getServiceSchema().getActionSchema("read").getHttp();
    }

    public ActionParamHttpSchema getActionParamHttpSchema() {
        return getActionParamSchema().getHttpSchema();
    }

    public ActionParamSchema getActionParamSchema() {
        return getActionSchema().getParamSchema("user_id");
    }

    public ActionSchema getActionSchema() {
        return getServiceSchema().getActionSchema("read");
    }

    public EntitySchema getEntitySchema() {
        return getActionSchema().getEntity();
    }

    public FieldSchema getFieldSchema() {
        return getActionSchema().getEntity().getField().get(0);
    }

    public FileHttpSchema getFileHttpSchema() {
        return getFileSchema().getHttpSchema();
    }

    public FileSchema getFileSchema() {
        return getActionSchema().getFileSchema("avatar");
    }

    public HttpSchema getHttpSchema() {
        return getServiceSchema().getHttpSchema();
    }

    public ObjectFieldSchema getObjectFieldSchema() {
        return getActionSchema().getEntity().getFields().get(0);
    }

    public RelationSchema getRelationSchema() {
        return getActionSchema().getRelations().get(0);
    }

    public TransportSchema getTransportSchema() {
        return getActionSchema().getFallbacks().get("error");
    }

    public ValueSchema getValueSchema() {
        return getTransportSchema().getData().get(0).get("id");
    }

    public Action getAction() {
        return new Action.Builder().setActionEntity(getActionCommand().getArgument()).build();
    }

    public CallEntity getCall() {
        return getAction().getTransport().getTransportEntity().getCalls().get("users").get("1.0.0").get(0);
    }

    public File getFile() {
        return getAction().getFile("avatar");
    }

    public Transport getTransport() {
        return getAction().getTransport();
    }

    public HttpRequest getHttpRequest() {
        return getRequest().getHttpRequest();
    }

    public Request getRequest() {
        return new Request.Builder().setRequestEntity(getRequestCommand().getArgument()).build();
    }

    public HttpResponse getHttpResponse() {
        return getResponse().getHttpResponse();
    }

    public Response getResponse() {
        return new Response.Builder().setResponseEntity(getResponseCommand().getArgument()).build();
    }

    public Param getParam() {
        return getAction().getParams().get(0);
    }

    public TransportMeta getTransportMeta() {
        return getTransport().getTransportEntity().getMeta();
    }

    public TransactionEntity getTransaction() {
        return getTransport().getTransportEntity().getTransactions();
    }

    public ServiceTransaction getServiceTransaction() {
        return getTransaction().getCommit().get(0);
    }
}
