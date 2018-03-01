/*
 * Java 8 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java8
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.commands.CallCommandPayload;
import io.kusanagi.katana.api.commands.Mapping;
import io.kusanagi.katana.api.commands.common.CommandMeta;
import io.kusanagi.katana.api.component.Component;
import io.kusanagi.katana.api.component.Constants;
import io.kusanagi.katana.api.component.ExceptionMessage;
import io.kusanagi.katana.api.component.Serializer;
import io.kusanagi.katana.api.component.utils.Logger;
import io.kusanagi.katana.api.component.utils.MessagePackSerializer;
import io.kusanagi.katana.api.replies.ErrorPayload;
import io.kusanagi.katana.api.replies.ReturnReplyPayload;
import io.kusanagi.katana.api.serializers.ActionEntity;
import io.kusanagi.katana.api.serializers.TransportEntity;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Action extends Api {

    private ActionEntity actionEntity;

    private Transport transport;

    public Action(Component component, String path, String name, String version, String platformVersion,
                  Map<String, String> variables, boolean isDebug, Mapping mapping, ActionEntity actionEntity, Transport transport) {
        super(component, path, name, version, platformVersion, variables, isDebug, mapping);
        this.actionEntity = actionEntity;
        this.transport = transport;
    }

    /**
     * Default constructor
     */
    public Action() {
        // Default constructor to make possible the serialization of this object.
    }

    public Action(Action other) {
        super(other);
        this.actionEntity = other.actionEntity;
        this.transport = other.transport;
    }

    public Transport getTransport() {
        return transport;
    }

    public Object getReturnObject(){
        return actionEntity.getReturnObject();
    }

    // SDK Methods

    /**
     * Determine whether or not the current Service is the initial Service called in a request.
     *
     * @return Return true if the Service is the initial Service called in a request
     */
    public boolean isOrigin() {
        return this.transport.getOriginService().length != 0 && this.transport.getOriginService()[0].equals(getName());
    }

    /**
     * @return Return the unique name of the action called.
     */
    public String getActionName() {
        return actionEntity.getActionName();
    }

    /**
     * Register a custom userland property in the Transport with the REQUIRED name and REQUIRED value arguments.
     * If a property with the specified name already exists the value MUST be replaced with value. The function MUST
     * NOT accept any other data type for a value other than string.
     *
     * @param name  Name of the property
     * @param value Value of the property
     * @return Return the instance of the action
     */
    public Action setProperty(String name, String value) {
        TransportMeta meta = actionEntity.getTransport().getMeta();
        Map<String, String> properties = meta.getProperties();
        properties.put(name, value);
        return this;
    }

    /**
     * Determine if a parameter with the name specified by the REQUIRED case sensitive name argument
     * was provided for the action.
     *
     * @param name Name of the Param
     * @return Return true if the Action has the param
     */
    public boolean hasParam(String name) {
        for (Param param : actionEntity.getParams()) {
            if (param.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the parameter with the REQUIRED case sensitive name argument which MUST be returned as a Param object.
     * To deserialize a file provided with the request the getFile() function MUST be used to access the File object.
     *
     * @param name Name of the param
     * @return Return the value of the param
     */
    public Param getParam(String name) {
        for (Param param : actionEntity.getParams()) {
            if (param.getName().equals(name)) {
                return param;
            }
        }

        Param param = new Param();
        param.setName(name);
        param.setExists(false);
        return param;
    }

    /**
     * Param getter
     *
     * @return Return all the params of the action
     */
    public List<Param> getParams() {
        return actionEntity.getParams();
    }

    /**
     * Create a new parameter with the REQUIRED name argument
     * If the OPTIONAL value or type arguments are specified these MUST also be applied to the Param object. The value
     * of the type argument MUST be either "null", "boolean", "integer", "float", "string", "array", "object" or "binary", where
     * any other value MUST be accepted as "string".
     * When creating a new Param object the value of the exists property MUST be false.
     *
     * @param name  Name of the new param
     * @param value Value of the new param
     * @param type  Data type of the new param, MUST be either "null", "boolean", "integer", "float", "string", "array", "object" or "binary"
     * @return Return the new param
     */
    public Param newParam(String name, String value, String type) {
        Param param = new Param();
        param.setName(name);
        param.setValue(value);
        param.setType(type);
        return param;
    }

    public Param newParam(String name) {
        return newParam(name, "", "");
    }

    /**
     * Determine if a file with the parameter name specified by the REQUIRED case sensitive
     * name argument was provided for the action.
     *
     * @param name Name of the file
     * @return Return true if the action has the file
     */
    public boolean hasFile(String name) {
        Map<String, Map<String, Map<String, Map<String, List<File>>>>> pathFiles = actionEntity.getTransport().getFiles();
        for (Map.Entry path : pathFiles.entrySet()) {
            Map<String, Map<String, Map<String, List<File>>>> serviceFiles = pathFiles.get((String) path.getKey());
            for (Map.Entry service : serviceFiles.entrySet()) {
                Map<String, Map<String, List<File>>> versionFiles = serviceFiles.get((String) service.getKey());
                for (Map.Entry version : versionFiles.entrySet()) {
                    Map<String, List<File>> actionFiles = versionFiles.get((String) version.getKey());
                    for (Map.Entry action : actionFiles.entrySet()) {
                        List<File> nameFiles = actionFiles.get((String) action.getKey());
                        for (File file: nameFiles) {
                            if(file.getFilename().equals(name)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Get the file with the REQUIRED case sensitive name argument, which MUST be returned as a File object. If the
     * file is not found, a File object with the REQUIRED name as first argument and an empty path as second argument
     * MUST be returned.
     *
     * @param name Name of the file
     * @return Return the File
     */
    public File getFile(String name) {
        Map<String, Map<String, Map<String, Map<String, List<File>>>>> pathFiles = actionEntity.getTransport().getFiles();
        for (Map.Entry path : pathFiles.entrySet()) {
            Map<String, Map<String, Map<String, List<File>>>> serviceFiles = pathFiles.get((String) path.getKey());
            for (Map.Entry service : serviceFiles.entrySet()) {
                Map<String, Map<String, List<File>>> versionFiles = serviceFiles.get((String) service.getKey());
                for (Map.Entry version : versionFiles.entrySet()) {
                    Map<String, List<File>> actionFiles = versionFiles.get((String) version.getKey());
                    for (Map.Entry action : actionFiles.entrySet()) {
                        List<File> nameFiles = actionFiles.get((String) action.getKey());
                        for (File file: nameFiles) {
                            if(file.getFilename().equals(name)) {
                                return file;
                            }
                        }
                    }
                }
            }
        }

        File file = new File();
        file.setName(name);
        file.setPath("");
        return file;
    }

    /**
     * Get all the files, which MUST be returned as an array of File objects. If no files are found an empty array
     * MUST be returned.
     *
     * @return Return all the files
     */
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        Map<String, Map<String, Map<String, Map<String, List<File>>>>> pathFiles = actionEntity.getTransport().getFiles();
        for (Map.Entry path : pathFiles.entrySet()) {
            Map<String, Map<String, Map<String, List<File>>>> serviceFiles = pathFiles.get((String) path.getKey());
            for (Map.Entry service : serviceFiles.entrySet()) {
                Map<String, Map<String, List<File>>> versionFiles = serviceFiles.get((String) service.getKey());
                for (Map.Entry version : versionFiles.entrySet()) {
                    Map<String, List<File>> actionFiles = versionFiles.get((String) version.getKey());
                    for (Map.Entry action : actionFiles.entrySet()) {
                        List<File> nameFiles = actionFiles.get((String) action.getKey());
                        for (File file: nameFiles) {
                            files.add(file);
                        }
                    }
                }
            }
        }
        return files;
    }

    /**
     * Create a new file object from the REQUIRED name argument as name of the parameter, the REQUIRED path argument
     * as the file system path, which MUST be returned as a File object. If the OPTIONAL mime argument is specified it
     * MUST also be applied to the File object.
     *
     * @param name Name of the File
     * @param path Path of the file
     * @param mime Mime type of the file
     * @return Return the new file
     */
    public File newFile(String name, String path, String mime) {
        File file = new File();
        file.setName(name);
        file.setPath(path);
        file.setMime(mime);
        return file;
    }
    public File newFile(String name, String path) {
        return newFile(name, path, "");
    }

    /**
     * Register a file to be downloaded by the Gateway to the client with the REQUIRED file argument, which MUST be an
     * instance of a File object. A File object returned by the getFile() function MAY be used, to register a file
     * provided to this Service.
     * If the configuration of the Service did not include a file server this function MUST return false.
     * Only one file MAY be defined, and which MUST be overwritten if another is defined. Providing a file for download
     * MUST take preference over content for the HTTP response body, and replace it if already defined.
     *
     * @param file File object to register
     * @return Return the instance of the action
     */
    public Action setDownload(File file) {
        if (mapping != null && !getServiceSchema(getName(), getVersion()).hasFileServer()) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.FILE_SERVER_NOT_CONFIGURED, getName(), getVersion()));
        }
        actionEntity.getTransport().setBody(file);
        return this;
    }

    /**
     * Register an entity object with the REQUIRED entity argument under the current Service namespace, version and
     * action name.
     * If data already exists it MUST be replaced with the given entity.
     *
     * @param entity Entity to be registered
     * @return Return the instance of the action
     */
    public Action setEntity(Object entity) {
        Map<String, Map<String, Map<String, Map<String, Object>>>> pathData = actionEntity.getTransport().getData();

        if (pathData == null) {
            pathData = new HashMap<>();
            actionEntity.getTransport().setData(pathData);
        }

        Map<String, Map<String, Map<String, Object>>> serviceData = new HashMap<>();
        Map<String, Map<String, Object>> versionData = new HashMap<>();
        Map<String, Object> actionData = new HashMap<>();
        if (pathData.containsKey(getPath())) {
            serviceData = pathData.get(getPath());
            if (serviceData.containsKey(getName())) {
                versionData = serviceData.get(getName());
                if (versionData.containsKey(getVersion())) {
                    actionData = versionData.get(getVersion());
                } else {
                    versionData.put(getVersion(), actionData);
                }
            } else {
                versionData.put(getVersion(), actionData);
                serviceData.put(getName(), versionData);
            }
        } else {
            versionData.put(getVersion(), actionData);
            serviceData.put(getName(), versionData);
            pathData.put(getPath(), serviceData);
        }
        if (!(entity instanceof List)) {
            List<Object> entities = new ArrayList<>();
            entities.add(entity);
            actionData.put(getActionName(), entities);
        } else {
            actionData.put(getActionName(), entity);
        }
        return this;
    }

    /**
     * Register an array of entity objects with the REQUIRED collection argument under the current Service namespace,
     * version and action name.
     * If data already exists it MUST be replaced with the given collection.
     *
     * @param collection Collection array to be registered
     * @return Return the instance of the action
     */
    public Action setCollection(List<?> collection) {
        setEntity(collection);
        return this;
    }

    /**
     * Register a "one-to-one" relation between the entity with the REQUIRED primary argument and the foreign
     * Service, defined by the REQUIRED case sensitive service argument, and the foreign key on the entity from that
     * Service, defined by the REQUIRED foreign argument.
     * If the relation already exists it MUST be replaced with the given foreign.
     *
     * @param primaryKey Primary key argument
     * @param service    Foreign Service
     * @param foreignKey Foreign key argument
     * @return Return the instance of the action
     */
    public Action relateOne(String primaryKey, String service, String foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = actionEntity.getTransport().getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(path, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(primaryKey, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(path)) {
                        relations.get(path).get(name).get(primaryKey).get(path).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(path, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(name, nameRelation);
            relations.put(path, pathRelation);
        }

        return this;
    }

    /**
     * Register a "one-to-many" relation between the entity with the REQUIRED primary argument and the foreign
     * Service, defined by the REQUIRED case sensitive service argument, and the foreign keys of the entities from
     * that Service, defined by the REQUIRED foreigns argument.
     * If the relation already exists it MUST be replaced with the given foreigns.
     *
     * @param primaryKey Primary key argument
     * @param service    Foreign Service
     * @param foreignKey Foreign key argument
     * @return Return the instance of the action
     */
    public Action relateMany(String primaryKey, String service, List<String> foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = actionEntity.getTransport().getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(path, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(primaryKey, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(path)) {
                        relations.get(path).get(name).get(primaryKey).get(path).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(path, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(name, nameRelation);
            relations.put(path, pathRelation);
        }

        return this;
    }

    /**
     * register a "one-to-one" relation between the entity with the REQUIRED primary_key argument and the remote Service
     * from another Realm, defined by the REQUIRED address argument as the public address of a Gateway for that Realm,
     * the REQUIRED service as the name of the Service to relate to, and the foreign key on the entity from that
     * Service, defined by the REQUIRED foreign_key argument.
     * <p>
     * If the relation already exists it MUST be replaced with the given foreign_key.
     *
     * @param primaryKey Primary key argument
     * @param address    remote address
     * @param service    Foreign Service
     * @param foreignKey Foreign key argument
     * @return Return the instance of the action
     */
    public Action relateOneRemote(String primaryKey, String address, String service, String foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = actionEntity.getTransport().getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(address, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(primaryKey, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(address)) {
                        relations.get(path).get(name).get(primaryKey).get(address).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(address, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(name, nameRelation);
            relations.put(path, pathRelation);
        }
        return this;
    }

    /**
     * register a "one-to-many" relation between the entity with the REQUIRED primary_key argument and the remote
     * Service, defined by the REQUIRED address argument as the public address of a Gateway for that Realm, the REQUIRED
     * service as the name of the Service to relate to, and the foreign keys of the entities from that Service, defined
     * by the REQUIRED foreign_keys argument.
     * <p>
     * If the relation already exists it MUST be replaced with the given foreign_keys.
     *
     * @param primaryKey Primary key argument
     * @param address    remote address
     * @param service    Foreign Service
     * @param foreignKey Foreign keys
     * @return Return the instance of the action
     */
    public Action relateManyRemote(String primaryKey, String address, String service, List<String> foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = actionEntity.getTransport().getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(address, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(primaryKey, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(address)) {
                        relations.get(path).get(name).get(primaryKey).get(address).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(address, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(name, nameRelation);
            relations.put(path, pathRelation);
        }
        return this;
    }

    /**
     * Register a link with the REQUIRED link argument as the name, and REQUIRED uri argument as the value, under the
     * current Service namespace.
     * If the link name specified with link is already defined it MUST be replaced with the given uri.
     *
     * @param link Link name
     * @param uri  Uri of the link
     * @return Return the instance of the action
     */
    public Action setLink(String link, String uri) {
        Map<String, Map<String, Map<String, String>>> pathLink = actionEntity.getTransport().getLinks();
        Map<String, Map<String, String>> serviceLink = new HashMap<>();
        Map<String, String> linkMap = new HashMap<>();
        if (pathLink.containsKey(getPath())) {
            serviceLink = pathLink.get(getPath());
            if (serviceLink.containsKey(getName())) {
                linkMap = serviceLink.get(getName());
            } else {
                serviceLink.put(getName(), linkMap);
            }
        } else {
            serviceLink.put(getName(), linkMap);
            pathLink.put(getPath(), serviceLink);
        }
        linkMap.put(link, uri);
        return this;
    }

    /**
     * Register a "commit" transaction call with the REQUIRED case sensitive action argument as the name of the action
     * to call.
     * The OPTIONAL params argument MUST allow parameters to be specified, where each item in the array MUST be a valid
     * Param object.
     * If a commit transaction call has already been defined for the given action it MUST be aggregated to the array of
     * actions to call on commit. The order of this registry is relevant.
     *
     * @param action Action argument name
     * @param params Params argument
     * @return Return the instance of the action
     */
    public Action commit(String action, List<Param> params) {
        List<ServiceTransaction> commit = actionEntity.getTransport().getTransactions().getCommit();
        if (commit == null) {
            commit = new ArrayList<>();
        }
        ServiceTransaction serviceTransaction = new ServiceTransaction();
        serviceTransaction.setName(name);
        serviceTransaction.setVersion(version);
        serviceTransaction.setAction(action);
        serviceTransaction.setParams(params);
        commit.add(serviceTransaction);
        return this;
    }

    /**
     * Register a "rollback" transaction call with the REQUIRED case sensitive action argument as the name of the
     * action to call.
     * The OPTIONAL params argument MUST allow parameters to be specified, where each item in the array MUST be a valid
     * Param object.
     * If a rollback transaction call has already been defined for the given action it MUST be aggregated to the array
     * of actions to call on rollback. The order of this registry is relevant.
     *
     * @param action Action argument name
     * @param params Params argument
     * @return Return the instance of the action
     */
    public Action rollback(String action, List<Param> params) {
        List<ServiceTransaction> rollback = actionEntity.getTransport().getTransactions().getRollback();
        if (rollback == null) {
            rollback = new ArrayList<>();
        }
        ServiceTransaction serviceTransaction = new ServiceTransaction();
        serviceTransaction.setName(name);
        serviceTransaction.setVersion(version);
        serviceTransaction.setAction(action);
        serviceTransaction.setParams(params);
        rollback.add(serviceTransaction);
        return this;
    }

    /**
     * Register a "complete" transaction call with the REQUIRED case sensitive action argument as the name of the
     * action to call.
     * The OPTIONAL params argument MUST allow parameters to be specified, where each item in the array MUST be a valid
     * Param object.
     * If a complete transaction call has already been defined for the given action it MUST be aggregated to the array
     * of actions to call on complete. The order of this registry is relevant.
     *
     * @param action Action argument name
     * @param params Params argument
     * @return Return the instance of the action
     */
    public Action complete(String action, List<Param> params) {
        List<ServiceTransaction> complete = actionEntity.getTransport().getTransactions().getComplete();
        if (complete == null) {
            complete = new ArrayList<>();
        }
        ServiceTransaction serviceTransaction = new ServiceTransaction();
        serviceTransaction.setName(name);
        serviceTransaction.setVersion(version);
        serviceTransaction.setAction(action);
        serviceTransaction.setParams(params);
        complete.add(serviceTransaction);
        return this;
    }

    /**
     * perform a run-time Service call within the same Realm with the REQUIRED service argument as the name of the
     * Service to call, the REQUIRED version argument as the version of the given Service, and the REQUIRED action
     * argument as the name of the action to call.
     *
     * @param service Service name
     * @param version Service version
     * @param action  Action name
     * @param params  Optional parameters
     * @param files   Optional files
     * @param timeout timeout in milliseconds
     * @return The return object of the action called
     */
    public Object call(String service, String version, String action, List<Param> params, List<File> files, int timeout) {
        ServiceSchema serviceSchema = getServiceSchema(this.name, this.version);

        if (!serviceSchema.getActionSchema(actionEntity.getActionName()).hasCalls()) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.CALL_NOT_CONFIGURED, this.name, this.version, actionEntity.getActionName()));
        }

        // Validate is there are local files
        if (files != null) {
            if (mapping != null && !getServiceSchema(this.name, this.version).hasFileServer()) {
                return false;
            }
            for (File file : files) {
                if (file.isLocal()) {
                    throw new IllegalArgumentException(String.format(ExceptionMessage.CANNOT_REFERENCE_LOCAL_FILE, this.name, this.version, actionEntity.getActionName()));
                }
            }
        }

        // Build the payload
        CalleeEntity callee = new CalleeEntity();
        callee.setAction(getActionName());
        callee.setCalleeInfo(new String[]{service, version, action});
        callee.setTransport(actionEntity.getTransport());
        callee.setParam(params);
        callee.setFiles(files);

        CallCommandPayload.CallCommand callCommand = new CallCommandPayload.CallCommand();
        callCommand.setName("runtime-call");
        callCommand.setArgument(callee);

        CommandMeta callCommandMeta = new CommandMeta();
        callCommandMeta.setScope("service");

        CallCommandPayload payload = new CallCommandPayload();
        payload.setCommandMeta(callCommandMeta);
        payload.setCommand(callCommand);

        // Send Payload
        Serializer serializer = new MessagePackSerializer();
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        String addr = "tcp://" + serviceSchema.getAddress();
        requester.connect(addr);
        requester.send(new byte[]{0x01}, zmq.ZMQ.ZMQ_SNDMORE);
        try {
            requester.send(serializer.serializeInBytes(payload), 0);
        } catch (JsonProcessingException e) {
            Logger.log(e);
            throw new IllegalArgumentException(e.getMessage());
        }

        // Receive Reply
        ZMQ.Poller poll = new ZMQ.Poller(1);
        poll.register(requester, ZMQ.Poller.POLLIN);
        int response = poll.poll(timeout);

        byte[] bytes;
        if (response > 0) {
            bytes = requester.recv();

            // Parse Reply
            ReturnReplyPayload returnCommandReply;
            ErrorPayload errorPayload;
            try {

                // Extract return and return it
                returnCommandReply = serializer.deserialize(bytes, ReturnReplyPayload.class);

//                //Merge transports
                TransportEntity responseTransport = returnCommandReply.getCommandReply().getResult().getTransport();
                merge(actionEntity.getTransport().getMeta().getFallback(), responseTransport.getMeta().getFallback());
                merge(actionEntity.getTransport().getMeta().getProperties(), responseTransport.getMeta().getProperties());
                merge(actionEntity.getTransport().getData(), responseTransport.getData());
                merge(actionEntity.getTransport().getRelations(), responseTransport.getRelations());
                merge(actionEntity.getTransport().getLinks(), responseTransport.getLinks());
                merge(actionEntity.getTransport().getCalls(), responseTransport.getCalls());
                merge(actionEntity.getTransport().getTransactions().getCommit(), responseTransport.getTransactions().getCommit());
                merge(actionEntity.getTransport().getTransactions().getComplete(), responseTransport.getTransactions().getComplete());
                merge(actionEntity.getTransport().getTransactions().getRollback(), responseTransport.getTransactions().getRollback());
                merge(actionEntity.getTransport().getErrors(), responseTransport.getErrors());
                actionEntity.getTransport().setBody(responseTransport.getBody());
                merge(actionEntity.getTransport().getFiles(), responseTransport.getFiles());

                return returnCommandReply.getCommandReply().getResult().getReturnObject();
            } catch (IOException e) {
                try {
                    // Throw ErrorEntity Payload as exception
                    errorPayload = serializer.deserialize(bytes, ErrorPayload.class);
                    Logger.log(e);
                    throw new IllegalArgumentException(errorPayload.getError().getMessage());
                } catch (IOException e1) {
                    Logger.log(e1);
                    // Throw serialization exception
                    throw new IllegalArgumentException(e.getMessage());
                }
            } finally {
                // Close sockets and terminate context
                requester.close();
                context.term();
            }
        }

        requester.close();
        context.term();

        throw new RuntimeException("Runtime call timeout");
    }

    private void merge(List list1, List list2) {
        if (list2 != null && list1 != null) {
            for (Object object : list2) {
                list1.add(object);
            }
        }
    }

    private void merge(Map map1, Map map2) {
        if (map2 != null && map1 != null) {
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) map2).entrySet()) {
                Object object = map2.get(entry.getKey());
                if (map1.containsKey(entry.getKey()) && map1.get(entry.getKey()) instanceof Map && object instanceof Map) {
                    merge((Map) map1.get(entry.getKey()), (Map) object);
                } else {
                    map1.put(entry.getKey(), map2.get(entry.getKey()));
                }
            }
        }
    }

    public Object call(String service, String version, String action, List<Param> params, List<File> files) {
        return call(service, version, action, params, files, 10000);
    }

    /**
     * Register a Service call with the REQUIRED service argument as the name of the Service to call, the REQUIRED
     * version argument as the version of the given Service, and the REQUIRED action argument as the name of the action
     * to call.
     *
     * @param service Service name
     * @param version Version of the service
     * @param action  Action name
     * @param params  Optional parameters
     * @param files   An optional array of files
     * @return Return the instance of the action
     */
    public Action deferCall(String service, String version, String action, List<Param> params, List<File> files) {
        ServiceSchema serviceSchema = getServiceSchema(this.name, this.version);

        if (mapping != null && !serviceSchema.getActionSchema(actionEntity.getActionName()).hasDeferCall(service, version, action)) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.DEFERRED_CALL_NOT_CONFIGURED, this.name, this.version, actionEntity.getActionName()));
        }

        if (files != null) {
            for (File file : files) {
                if (file.isLocal() && mapping != null && !serviceSchema.hasFileServer()) {
                    throw new IllegalArgumentException(String.format(
                            ExceptionMessage.FILE_SERVER_NOT_CONFIGURED,
                            this.name,
                            this.version));
                }
                this.transport.addFile(this.path, service, version, action, file);
            }
        }

        Map<String, Map<String, List<CallEntity>>> calls = this.transport.getTransportEntity().getCalls();

        if (calls == null) {
            calls = new HashMap<>();
            actionEntity.getTransport().setCalls(calls);
        }

        CallEntity call = new CallEntity();
        call.setName(service);
        call.setVersion(version);
        call.setAction(action);
        call.setCaller(actionEntity.getActionName());
        call.setParams(params);

        List<CallEntity> callList = new ArrayList<>();

        Map<String, List<CallEntity>> versionCalls = new HashMap<>();
        versionCalls.put(this.version, callList);

        if (calls.containsKey(this.name)) {
            if (calls.get(this.name).containsKey(version)) {
                callList = calls.get(this.name).get(this.version);
            } else {
                calls.get(this.name).put(this.version, callList);
            }
        } else {
            calls.put(this.name, versionCalls);
        }

        callList.add(call);

        return this;
    }

    /**
     * register a remote Service call with the REQUIRED address argument as the public address of a Gateway for that
     * Realm, the REQUIRED service argument as the name of the remote Service to call, the REQUIRED version argument as
     * the version of the given Service, and the REQUIRED action argument as the name of the action to call.
     *
     * @param address Remote address
     * @param service Service name
     * @param version Version of the service
     * @param action  Action name
     * @param params  Optional parameters
     * @param files   An optional array of files
     * @param timeout time to wait for the response
     * @return Return the instance of the action
     */
    public Action remoteCall(String address, String service, String version, String action, List<Param> params, List<File> files, int timeout) {
        ServiceSchema serviceSchema = getServiceSchema(this.name, this.version);

        if (mapping != null && !serviceSchema.getActionSchema(actionEntity.getActionName()).hasRemoteCalls()) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.REMOTE_CALL_NOT_CONFIGURED, address, this.name, this.version, actionEntity.getActionName()));
        }

        if (files != null) {
            for (File file : files) {
                if (file.isLocal() && mapping != null && !serviceSchema.hasFileServer()) {
                    throw new IllegalArgumentException(String.format(
                            ExceptionMessage.FILE_SERVER_NOT_CONFIGURED,
                            this.name,
                            this.version));
                }
                this.transport.addFile(address, service, version, action, file);
            }
        }

        Map<String, Map<String, List<CallEntity>>> calls = transport.getTransportEntity().getCalls();
        List<CallEntity> callList = new ArrayList<>();

        Map<String, List<CallEntity>> versionCalls = new HashMap<>();
        versionCalls.put(this.version, callList);

        if (calls.containsKey(this.name)) {
            if (calls.get(this.name).containsKey(version)) {
                callList = calls.get(this.name).get(this.version);
            } else {
                calls.get(this.name).put(this.version, callList);
            }
        } else {
            calls.put(this.name, versionCalls);
        }

        CallEntity call = new CallEntity();
        call.setGateway(address);
        call.setName(service);
        call.setVersion(version);
        call.setAction(action);
        call.setCaller(actionEntity.getActionName());
        call.setTimeout(timeout);
        call.setParams(params);
        callList.add(call);
        return this;
    }

    public Action remoteCall(String address, String service, String version, String action, List<Param> params, List<File> files) {
        return remoteCall(address, service, version, action, params, files, 10000);
    }

    /**
     * Register a userland error with the REQUIRED message argument as the natural language description of the error.
     * The OPTIONAL code MUST allow a code to be defined for the error, which MUST default to 0 if not defined.
     * The OPTIONAL case insensitive status argument MUST allow a suggested HTTP status code to be defined for the
     * error. The usage of this status code in the HTTP response depends upon the Service being the origin of the
     * request and this being the first registered error, or there being no errors for the origin Service and this
     * being the first error of the first Service which registered errors, or the Middleware choosing this error and
     * response status code.
     * If an error has already been registered for the given action it MUST be aggregated to the array of errors for
     * that action. The order of this registry is relevant.
     *
     * @param message ErrorEntity message
     * @param code    ErrorEntity code
     * @param status  ErrorEntity status
     * @return Return the instance of the action
     */
    public Action error(String message, int code, String status) {
        ErrorEntity error = new ErrorEntity();
        error.setMessage(message);
        error.setCode(code);
        error.setStatus(status);

        Map<String, Map<String, Map<String, List<ErrorEntity>>>> pathError = actionEntity.getTransport().getErrors();
        Map<String, Map<String, List<ErrorEntity>>> serviceError = new HashMap<>();
        Map<String, List<ErrorEntity>> versionError = new HashMap<>();
        List<ErrorEntity> errors = new ArrayList<>();

        if (pathError.containsKey(getPath())) {
            serviceError = pathError.get(getPath());
            if (serviceError.containsKey(getName())) {
                versionError = serviceError.get(getName());
                if (versionError.containsKey(getVersion())) {
                    errors = versionError.get(getVersion());
                } else {
                    versionError.put(getVersion(), errors);
                }
            } else {
                versionError.put(getVersion(), errors);
                serviceError.put(getName(), versionError);
            }
        } else {
            versionError.put(getVersion(), errors);
            serviceError.put(getName(), versionError);
            pathError.put(getPath(), serviceError);
        }

        errors.add(error);

        return this;
    }

    /**
     * define the value to be returned to a Service which is requesting a value to be returned, and which MUST be of the
     * type defined as the type property in the action configuration.
     *
     * @param returnObject the object to be returned
     */
    public void setReturn(Object returnObject) {
        ServiceSchema serviceSchema = getServiceSchema(getName(), getVersion());
        ActionSchema actionSchema = serviceSchema.getActionSchema(getActionName());

        if (!actionSchema.hasReturn()) {
            throw new IllegalArgumentException(String.format(ExceptionMessage.CANNOT_SET_A_RETURN_VALUE, getName(), getVersion(), getActionName()));
        }

        actionEntity.setReturnObject(returnObject);
        validateReturnObjectType(actionSchema.getReturnType());

    }

    private void validateReturnObjectType(String returnType) {
        if ((returnType.equals(Constants.TYPE_BOOLEAN) && !(actionEntity.getReturnObject() instanceof Boolean)) ||
                (returnType.equals(Constants.TYPE_INTEGER) && !(actionEntity.getReturnObject() instanceof Integer)) ||
                (returnType.equals(Constants.TYPE_FLOAT) && !(actionEntity.getReturnObject() instanceof Float)) ||
                (returnType.equals(Constants.TYPE_STRING) && !(actionEntity.getReturnObject() instanceof String)) ||
                (returnType.equals(Constants.TYPE_ARRAY) && !(actionEntity.getReturnObject() instanceof List)) ||
                (returnType.equals(Constants.TYPE_OBJECT) && !(actionEntity.getReturnObject() instanceof Map))) {
            throwInvalidTypeException();
        }
    }

    private Object throwInvalidTypeException() {
        throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_RETURN_TYPE, getName(), getVersion(), getActionName()));
    }

    public static class Builder extends Api.Builder<Action>{

        private ActionEntity actionEntity;
        private Transport transport;

        public Builder() {
        }

        public Builder setActionEntity(ActionEntity actionEntity) {
            this.actionEntity = actionEntity;
            this.transport = new Transport.Builder().setTransportEntity(actionEntity.getTransport()).build();
            return this;
        }

        public Action build(){
            return new Action(
                getComponent(),
                getPath(),
                getName(),
                getVersion(),
                getPlatformVersion(),
                getVariables(),
                isDebug(),
                getMapping(),
                actionEntity,
                transport
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Action action = (Action) o;

        if (actionEntity != null ? !actionEntity.equals(action.actionEntity) : action.actionEntity != null) {
            return false;
        }
        return transport != null ? transport.equals(action.transport) : action.transport == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (actionEntity != null ? actionEntity.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionEntity=" + actionEntity +
                ", transport=" + transport +
                '}';
    }
}
