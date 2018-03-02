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

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.api.serializers.CallEntity;
import io.kusanagi.katana.api.serializers.ErrorEntity;
import io.kusanagi.katana.api.serializers.TransportEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 */

public class Transport implements CommandReplyResult {

    private TransportEntity transportEntity;

    public Transport() {
    }

    public Transport(TransportEntity transportEntity) {
        this.transportEntity = transportEntity;
    }

    public Transport(Transport other) {
        this.transportEntity = other.transportEntity;
    }

    public TransportEntity getTransportEntity() {
        return transportEntity;
    }

// SDK Methods

    /**
     * @return Return the UUID of the request.
     */
    @JsonIgnore
    public String getRequestId() {
        return transportEntity.getMeta().getId();
    }

    /**
     * @return Return the creation datetime of the request.
     */
    @JsonIgnore
    public String getRequestTimeStamp() {
        return transportEntity.getMeta().getDatetime();
    }

    /**
     * @return Return the array containing name, version and action of the Service that was the origin of the request.
     * If an origin was not set this MUST return an empty array.
     */
    @JsonIgnore
    public String[] getOriginService() {
        return transportEntity.getMeta().getOrigin();
    }

    /**
     *
     * @return The execution time in milliseconds that was spent by the **Service** that was the origin of the request,
     * which is **0** until the call is processed *(integer)
     */
    @JsonIgnore
    public int getOriginDuration(){
        return transportEntity.getMeta().getDuration();
    }

    /**
     * Get a custom userland property with the REQUIRED case sensitive name argument.
     * The default argument is the OPTIONAL value to use if the property does not exist. If the property is defined in
     * the Transport, but does not have a value, the value of the default argument SHOULD NOT be applied.
     * If a property with the specified name does not exist, and no default is provided, and empty string MUST be
     * returned.
     *
     * @param name          Argument name
     * @param defaultString Value to use if the property does not exist
     * @return If a property with the specified name does not exist, and no default is provided, and empty string MUST be
     * returned, otherwise the value of the property will be returned
     */
    @JsonIgnore
    public String getProperty(String name, String defaultString) {
        String property = transportEntity.getMeta().getProperties().get(name);
        return property == null ? defaultString == null ? "" : defaultString : property;
    }

    @JsonIgnore
    public String getProperty(String name) {
        return getProperty(name, "");
    }

    @JsonIgnore
    public Map<String, String> getProperties() {
        return transportEntity.getMeta().getProperties();
    }

    /**
     * Determine if a file download has been registered for the HTTP response.
     *
     * @return Return true if a download file has been registered
     */
    public boolean hasDownload() {
        return transportEntity.getBody() != null;
    }

    /**
     * @return Return the file download defined for the HTTP response as a File object.
     */
    @JsonIgnore
    public File getDownload() {
        return transportEntity.getBody();
    }

    /**
     *
     * @return
     */
    public List<ServiceData> getData() {
        List<ServiceData> dataList = new ArrayList<>();
        Map<String, Map<String, Map<String, Map<String, Object>>>> pathData = transportEntity.getData();
        for (Map.Entry path : pathData.entrySet()) {
            Map<String, Map<String, Map<String, Object>>> serviceData = pathData.get((String) path.getKey());
            for (Map.Entry service : serviceData.entrySet()) {
                Map<String, Map<String, Object>> versionData = serviceData.get((String) service.getKey());
                for (Map.Entry version : versionData.entrySet()) {
                    Map<String, Object> actionData = versionData.get((String) version.getKey());

                    List<ActionData> actionDataList = new ArrayList<>();

                    for (Map.Entry action : actionData.entrySet()) {
                        Object dataObject = actionData.get((String) action.getKey());
                        ActionData actionDataEntity = new ActionData(
                            (String) action.getKey(),
                            dataObject instanceof List || dataObject instanceof Array,
                            dataObject
                        );
                        actionDataList.add(actionDataEntity);
                    }

                    ServiceData data = new ServiceData(
                            (String) path.getKey(),
                            (String) service.getKey(),
                            (String) version.getKey(),
                            actionDataList
                    );

                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    /**
     *
     * @return
     */
    public List<Relation> getRelations() {
        List<Relation> relationList = new ArrayList<>();
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> pathData = transportEntity.getRelations();
        for (Map.Entry path : pathData.entrySet()) {
            Map<String, Map<String, Map<String, Map<String, Object>>>> serviceData = pathData.get((String) path.getKey());
            for (Map.Entry service : serviceData.entrySet()) {
                Map<String, Map<String, Map<String, Object>>> idData = serviceData.get((String) service.getKey());
                for (Map.Entry id : idData.entrySet()) {
                    Map<String, Map<String, Object>> relPathData = idData.get((String) id.getKey());

                    List<ForeignRelation> foreignRelations = new ArrayList<>();
                    for (Map.Entry relPath : relPathData.entrySet()) {
                        Map<String, Object> relationData = relPathData.get((String) relPath.getKey());
                        for (Map.Entry foreignRelName : relationData.entrySet()) {
                            Object foreignRel = relationData.get((String) foreignRelName.getKey());

                            List<String> relationIds = new ArrayList<>();
                            if(foreignRel instanceof String){
                                relationIds.add((String)foreignRel);
                            } else {
                                relationIds.addAll((List)foreignRel);
                            }

                            ForeignRelation foreignRelation = new ForeignRelation(
                                    (String) relPath.getKey(),
                                    (String) foreignRelName.getKey(),
                                    relationIds.size() == 1 ? "one" : "many",
                                    relationIds
                            );
                            foreignRelations.add(foreignRelation);
                        }
                    }

                    Relation relation = new Relation(
                            (String) path.getKey(),
                            (String) service.getKey(),
                            (String) id.getKey(),
                            foreignRelations
                    );

                    relationList.add(relation);
                }
            }
        }
        return relationList;
    }

    /**
     *
     * @return
     */
    public List<Link> getLinks() {
        List<Link> links = new ArrayList<>();
        Map<String, Map<String, Map<String, String>>> pathData = transportEntity.getLinks();
        for (Map.Entry path : pathData.entrySet()) {
            Map<String, Map<String, String>> serviceData = pathData.get((String) path.getKey());
            for (Map.Entry service : serviceData.entrySet()) {
                Map<String, String> linkData = serviceData.get((String) service.getKey());
                for (Map.Entry link : linkData.entrySet()) {
                    Link linkEntity = new Link(
                            (String) path.getKey(),
                            (String) service.getKey(),
                            linkData.get((String)link.getKey()),
                            (String)link.getKey()
                    );

                    links.add(linkEntity);
                }
            }
        }
        return links;
    }

    /**
     *
     * @return
     */
    public List<Caller> getCalls() {
        List<Caller> callers = new ArrayList<>();
        Map<String, Map<String, List<CallEntity>>> serviceData = transportEntity.getCalls();
        for (Map.Entry service : serviceData.entrySet()) {
            Map<String, List<CallEntity>> versionData = serviceData.get((String) service.getKey());
            for (Map.Entry version : versionData.entrySet()) {
                List<CallEntity> calls = versionData.get((String) version.getKey());
                for (CallEntity call : calls) {
                    Callee callee = new Callee(
                            call.getDuration(),
                            call.getGateway() == null || call.getGateway().isEmpty(),
                            call.getGateway(),
                            call.getTimeout(),
                            call.getName(),
                            call.getVersion(),
                            call.getAction(),
                            call.getParams()
                    );

                    Caller caller = new Caller(
                            (String) service.getKey(),
                            (String) version.getKey(),
                            call.getCaller(),
                            callee
                    );

                    callers.add(caller);
                }
            }
        }
        return callers;
    }

    public List<Transaction> getTransactions(String type) {
        List<Transaction> transactions = new ArrayList<>();

        if(type.equals("commit")) {
            for (ServiceTransaction serviceTransaction : transportEntity.getTransactions().getCommit()) {
                transactions.add(new Transaction(
                        "commit",
                        serviceTransaction.getName(),
                        serviceTransaction.getVersion(),
                        serviceTransaction.getCaller(),
                        serviceTransaction.getAction(),
                        serviceTransaction.getParams()
                ));
            }
        } else if(type.equals("rollback")){
            for (ServiceTransaction serviceTransaction : transportEntity.getTransactions().getRollback()) {
                transactions.add(new Transaction(
                        "rollback",
                        serviceTransaction.getName(),
                        serviceTransaction.getVersion(),
                        serviceTransaction.getCaller(),
                        serviceTransaction.getAction(),
                        serviceTransaction.getParams()
                ));
            }
        } else if (type.equals("complete")){
            for (ServiceTransaction serviceTransaction : transportEntity.getTransactions().getComplete()) {
                transactions.add(new Transaction(
                        "complete",
                        serviceTransaction.getName(),
                        serviceTransaction.getVersion(),
                        serviceTransaction.getCaller(),
                        serviceTransaction.getAction(),
                        serviceTransaction.getParams()
                ));
            }

        }

        return transactions;
    }

    /**
     *
     * @return
     */
    public List<Error> getErrors() {
        List<Error> errors = new ArrayList<>();
        Map<String, Map<String, Map<String, List<ErrorEntity>>>> pathData = transportEntity.getErrors();
        for (Map.Entry path : pathData.entrySet()) {
            Map<String, Map<String, List<ErrorEntity>>> serviceData = pathData.get((String) path.getKey());
            for (Map.Entry service : serviceData.entrySet()) {
                Map<String, List<ErrorEntity>> versionData = serviceData.get((String)service.getKey());
                for (Map.Entry version : versionData.entrySet()) {
                    List<ErrorEntity> errorData = versionData.get((String)version.getKey());
                    for(ErrorEntity errorEntity : errorData) {
                        errors.add(new Error(
                                (String) path.getKey(),
                                (String)service.getKey(),
                                (String)version.getKey(),
                                errorEntity.getMessage(),
                                errorEntity.getCode(),
                                errorEntity.getStatus()
                        ));
                    }
                }
            }
        }
        return errors;
    }

    public void addFile(String path, String service, String version, String action, File file) {
        Map<String, Map<String, Map<String, Map<String, List<File>>>>> pathFile = transportEntity.getFiles();
        Map<String, Map<String, Map<String, List<File>>>> serviceFile = new HashMap<>();
        Map<String, Map<String, List<File>>> versionFile = new HashMap<>();
        Map<String, List<File>> actionFile = new HashMap<>();
        List<File> nameFile = new ArrayList<>();

        if (pathFile.containsKey(path)) {
            serviceFile = pathFile.get(path);
            if (serviceFile.containsKey(service)) {
                versionFile = serviceFile.get(service);
                if (versionFile.containsKey(version)) {
                    actionFile = versionFile.get(version);
                    if (actionFile.containsKey(action)) {
                        nameFile = actionFile.get(action);
                    } else {
                        actionFile.put(action, nameFile);
                    }
                } else {
                    actionFile.put(action, nameFile);
                    versionFile.put(version, actionFile);
                }
            } else {
                actionFile.put(action, nameFile);
                versionFile.put(version, actionFile);
                serviceFile.put(service, versionFile);
            }
        } else {
            actionFile.put(action, nameFile);
            versionFile.put(version, actionFile);
            serviceFile.put(service, versionFile);
            pathFile.put(path, serviceFile);
        }

        nameFile.add(file);
    }

    public static class Builder{

        private TransportEntity transportEntity;

        public Builder() {
            this.transportEntity = new TransportEntity();
        }

        public Transport.Builder setTransportEntity(TransportEntity transportEntity) {
            this.transportEntity = transportEntity;
            return this;
        }

        public Transport build(){
            return new Transport(transportEntity);
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

        Transport transport = (Transport) o;

        return transportEntity != null ? transportEntity.equals(transport.transportEntity) : transport.transportEntity == null;
    }

    @Override
    public int hashCode() {
        return transportEntity != null ? transportEntity.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportEntity=" + transportEntity +
                '}';
    }
}
