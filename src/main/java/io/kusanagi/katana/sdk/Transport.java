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
import io.kusanagi.katana.api.serializers.TransportEntity;

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

    public TransportMeta getMeta() {
        return transportEntity.getMeta();
    }

    public File getBody() {
        return transportEntity.getBody();
    }

    public Map<String, Map<String, Map<String, Map<String, List<File>>>>> getFilesEntity() {
        return transportEntity.getFiles();
    }

    public Map<String, Map<String, Map<String, Map<String, Object>>>> getData() {
        return transportEntity.getData();
    }

    public Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> getRelations() {
        return transportEntity.getRelations();
    }

    public Map<String, Map<String, Map<String, String>>> getLinks() {
        return transportEntity.getLinks();
    }

    public Map<String, Map<String, List<Call>>> getCalls() {
        return transportEntity.getCalls();
    }

    public Transaction getTransactions() {
        return transportEntity.getTransactions();
    }

    public Map<String, Map<String, Map<String, List<Error>>>> getErrors() {
        return transportEntity.getErrors();
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
     * Return all of the data as an object, as it is stored in the Transport. If the OPTIONAL case sensitive service
     * argument is specified, it MUST only return the data stored under that Service namespace. If the OPTIONAL case
     * sensitive version argument is specified, it MUST only return the data stored under the specified Service
     * namespace and version. If the OPTIONAL case sensitive action argument is specified, it MUST only return the
     * data stored under the specified Service namespace, version and action name.
     *
     * @param service Service name
     * @param version Version of the service
     * @param action  Name of the action
     * @return Return all the data as an object, as it is stored in the transport
     */
    public Object getData(String address, String service, String version, String action) {
        if (address != null) {
            if (service != null) {
                if (version != null) {
                    if (action != null) {
                        return transportEntity.getData().get(address).get(service).get(version).get(action);
                    }
                    return transportEntity.getData().get(address).get(service).get(version);
                }
                return transportEntity.getData().get(address).get(service);
            }
            return transportEntity.getData().get(address);
        }
        return transportEntity.getData();
    }
    public Object getData(String address, String service, String version) {
        return getData(address, service, version, null);
    }
    public Object getData(String address, String service) {
        return getData(address, service, null, null);
    }
    public Object getData(String address) {
        return getData(address, null, null, null);
    }

    /**
     * Return all of the relations as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the relations stored under that Service namespace.
     *
     * @param service Service name
     * @return Return the relations
     */
    public Object getRelations(String address, String service) {
        if (address != null) {
            if (service != null) {
                return transportEntity.getRelations().get(address).get(service);
            }
            return transportEntity.getRelations().get(address);
        }
        return transportEntity.getRelations();
    }
    public Object getRelations(String address) {
        return getRelations(address, null);
    }

    /**
     * Return all of the links as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the links stored under that Service namespace.
     *
     * @param service Service name
     * @return Return all the links as an object, as they are stored in the Transport
     */
    public Object getLinks(String address, String service) {
        if (address != null) {
            if (service != null) {
                return transportEntity.getLinks().get(address).get(service);
            }
            return transportEntity.getLinks().get(address);
        }
        return transportEntity.getLinks();
    }
    public Object getLinks(String address) {
        return getLinks(address, null);
    }

    /**
     * Return all of the calls as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the calls stored under that Service namespace.
     *
     * @param service Service name
     * @return Return all the calls as an object, as they are stored in the Transport.
     */
    public Object getCalls(String service) {
        if (service != null) {
            return transportEntity.getCalls().get(service);
        }
        return transportEntity.getCalls();
    }

    /**
     * Return all of the transactions as an object, as they are stored in the Transport. If the OPTIONAL case
     * sensitive service argument is specified, it MUST only return the transactions stored under that Service
     * namespace.
     *
     * @param service Service name
     * @return Return all the transactions as an object, as they are stored in the Transport.
     */
    public Transaction getTransactions(String service) {
        Transaction transaction = new Transaction();
        transaction.setCommit(new ArrayList<>());
        transaction.setRollback(new ArrayList<>());
        transaction.setComplete(new ArrayList<>());

        for (ServiceTransaction serviceTransaction : transportEntity.getTransactions().getCommit()) {
            if (serviceTransaction.getName().equals(service)) {
                transaction.getCommit().add(serviceTransaction);
            }
        }

        for (ServiceTransaction serviceTransaction : transportEntity.getTransactions().getRollback()) {
            if (serviceTransaction.getName().equals(service)) {
                transaction.getRollback().add(serviceTransaction);
            }
        }

        for (ServiceTransaction serviceTransaction : transportEntity.getTransactions().getComplete()) {
            if (serviceTransaction.getName().equals(service)) {
                transaction.getComplete().add(serviceTransaction);
            }
        }

        return transaction;
    }

    /**
     * Return all of the errors as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the links stored under that Service namespace.
     *
     * @param service Service name
     * @return Return all the errors as an object, as they are stored in the Transport.
     */
    public Object getErrors(String address, String service) {
        if (address != null) {
            if (service != null) {
                return transportEntity.getErrors().get(address).get(service);
            }
            return transportEntity.getErrors().get(address);
        }
        return transportEntity.getErrors();
    }
    public Object getErrors(String address) {
        return getErrors(address, null);
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
