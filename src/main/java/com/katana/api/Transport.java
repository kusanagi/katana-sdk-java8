package com.katana.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.replies.common.CommandReplyResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transport implements CommandReplyResult {

    @JsonProperty("m")
    private TransportMeta meta;

    @JsonProperty("b")
    private File body;

    @JsonProperty("f")
    private Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> files;

    @JsonProperty("d")
    private Map<String, Map<String, Map<String, Map<String, Object>>>> data;

    @JsonProperty("r")
    private Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations;

    @JsonProperty("l")
    private Map<String, Map<String, Map<String, String>>> links;

    @JsonProperty("c")
    private Map<String, Map<String, List<Call>>> calls;

    @JsonProperty("t")
    private Transaction transactions;

    @JsonProperty("e")
    private Map<String, Map<String, Map<String, List<Error>>>> errors;

    /**
     *
     */
    public Transport() {
        this.links = new HashMap<>();
    }

    /**
     * Transport meta getter
     *
     * @return Return the transport meta
     */
    public TransportMeta getMeta() {
        return meta;
    }

    /**
     * Transport meta setter
     *
     * @param meta Transport meta object
     */
    public void setMeta(TransportMeta meta) {
        this.meta = meta;
    }

    /**
     * File getter
     *
     * @return Return the file
     */
    public File getBody() {
        return body;
    }

    /**
     * File body setter
     *
     * @param body File object
     */
    public void setBody(File body) {
        this.body = body;
    }

    /**
     * File list getter
     *
     * @return Return the list of files
     */
    public Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> getFiles() {
        return files;
    }

    /**
     * File list setter
     *
     * @param files File list
     */
    public void setFiles(Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> files) {
        this.files = files;
    }

    /**
     * Data getter
     *
     * @return Return data object
     */
    public Map<String, Map<String, Map<String, Map<String, Object>>>> getData() {
        return data;
    }

    /**
     * Data setter
     *
     * @param data Data object
     */
    public void setData(Map<String, Map<String, Map<String, Map<String, Object>>>> data) {
        this.data = data;
    }

    /**
     * Relations getter
     *
     * @return Return the list of the relations
     */
    public Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> getRelations() {
        return relations;
    }

    /**
     * Relations setter
     *
     * @param relations Relation list
     */
    public void setRelations(Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations) {
        this.relations = relations;
    }

    /**
     * Links getter
     *
     * @return Return the links
     */
    public Map<String, Map<String, Map<String, String>>> getLinks() {
        return links;
    }

    /**
     * Links setter
     *
     * @param links Links to set
     */
    public void setLinks(Map<String, Map<String, Map<String, String>>> links) {
        this.links = links;
    }

    /**
     * Calls getter
     *
     * @return Return the call
     */
    public Map<String, Map<String, List<Call>>> getCalls() {
        return calls;
    }

    /**
     * Calls setter
     *
     * @param calls RequestCall list
     */
    public void setCalls(Map<String, Map<String, List<Call>>> calls) {
        this.calls = calls;
    }

    /**
     * Transaction getter
     *
     * @return Return the transactions
     */
    public Transaction getTransactions() {
        return transactions;
    }

    /**
     * Transaction setter
     *
     * @param transactions Transaction list
     */
    public void setTransactions(Transaction transactions) {
        this.transactions = transactions;
    }

    /**
     * Errors getter
     *
     * @return Return the error list
     */
    public Map<String, Map<String, Map<String, List<Error>>>> getErrors() {
        if (this.errors == null) {
            this.errors = new HashMap<>();
        }
        return errors;
    }

    /**
     * Error setter
     *
     * @param errors Error list
     */
    public void setErrors(Map<String, Map<String, Map<String, List<Error>>>> errors) {
        this.errors = errors;
    }

    // SDK Methods

    /**
     * @return Return the UUID of the request.
     */
    @JsonIgnore
    public String getRequestId() {
        return this.meta.getId();
    }

    /**
     * @return Return the creation datetime of the request.
     */
    @JsonIgnore
    public String getRequestTimeStamp() {
        return this.meta.getDatetime();
    }

    /**
     * @return Return the array containing name, version and action of the Service that was the origin of the request.
     * If an origin was not set this MUST return an empty array.
     */
    @JsonIgnore
    public String[] getOriginService() {
        return this.meta.getOrigin();
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
//    public String getProperty(String name, String defaultString) {
//        String property = this.meta.getProperties().get(name);
//        return property == null ? defaultString == null ? "" : defaultString : property;
//    }
//
//    public Map<String, String> getProperties(){
//        return this.meta.getProperties();
//    }

    /**
     * Determine if a file download has been registered for the HTTP response.
     *
     * @return Return true if a download file has been registered
     */
    public boolean hasDownload() { // TODO review this
        return this.body != null;
    }

    /**
     * @return Return the file download defined for the HTTP response as a File object.
     */
    @JsonIgnore
    public File getDownload() { // TODO review this
        return this.body;
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
                        return this.data.get(address).get(service).get(version).get(action);
                    }
                    return this.data.get(address).get(service).get(version);
                }
                return this.data.get(address).get(service);
            }
            return this.data.get(address);
        }
        return this.data;
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
                return this.relations.get(address).get(service);
            }
            return this.relations.get(address);
        }
        return this.relations;
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
                return this.links.get(address).get(service);
            }
            return this.links.get(address);
        }
        return this.links;
    }

    /**
     * Return all of the calls as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the calls stored under that Service namespace.
     *
     * @param service Service name
     * @return Return all the calls as an object, as they are stored in the Transport.
     */
    public Object getCalls(String address, String service) { // TODO review
        if (service != null) {
            return this.calls.get(service);
        }
        return this.calls;
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
        transaction.setCommit(new ArrayList<ServiceTransaction>());
        transaction.setRollback(new ArrayList<ServiceTransaction>());
        transaction.setComplete(new ArrayList<ServiceTransaction>());

        for(ServiceTransaction serviceTransaction : this.transactions.getCommit()){
            if (serviceTransaction.getName().equals(service)){
                transaction.getCommit().add(serviceTransaction);
            }
        }

        for(ServiceTransaction serviceTransaction : this.transactions.getRollback()){
            if (serviceTransaction.getName().equals(service)){
                transaction.getRollback().add(serviceTransaction);
            }
        }

        for(ServiceTransaction serviceTransaction : this.transactions.getComplete()){
            if (serviceTransaction.getName().equals(service)){
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
                return this.errors.get(address).get(service);
            }
            return this.errors.get(address);
        }
        return this.errors;
    }

    public void addFile(String path, String service, String version, String action, File file) {
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> pathFile = this.getFiles();
        Map<String, Map<String, Map<String, Map<String, File>>>> serviceFile = new HashMap<>();
        Map<String, Map<String, Map<String, File>>> versionFile = new HashMap<>();
        Map<String, Map<String, File>> actionFile = new HashMap<>();
        Map<String, File> nameFile = new HashMap<>();

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

        nameFile.put(file.getName(), file);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transport)) {
            return false;
        }

        Transport transport = (Transport) o;

        if (getMeta() != null ? !getMeta().equals(transport.getMeta()) : transport.getMeta() != null) {
            return false;
        }
        if (getBody() != null ? !getBody().equals(transport.getBody()) : transport.getBody() != null) {
            return false;
        }
        if (getFiles() != null ? !getFiles().equals(transport.getFiles()) : transport.getFiles() != null) {
            return false;
        }
        if (getData() != null ? !getData().equals(transport.getData()) : transport.getData() != null) {
            return false;
        }
        if (getRelations() != null ? !getRelations().equals(transport.getRelations()) : transport.getRelations() != null) {
            return false;
        }
        if (getLinks() != null ? !getLinks().equals(transport.getLinks()) : transport.getLinks() != null) {
            return false;
        }
        if (getCalls() != null ? !getCalls().equals(transport.getCalls()) : transport.getCalls() != null) {
            return false;
        }
        if (getTransactions() != null ? !getTransactions().equals(transport.getTransactions()) : transport.getTransactions() != null) {
            return false;
        }
        return getErrors() != null ? getErrors().equals(transport.getErrors()) : transport.getErrors() == null;

    }

    @Override
    public int hashCode() {
        int result = getMeta() != null ? getMeta().hashCode() : 0;
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        result = 31 * result + (getFiles() != null ? getFiles().hashCode() : 0);
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + (getRelations() != null ? getRelations().hashCode() : 0);
        result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
        result = 31 * result + (getCalls() != null ? getCalls().hashCode() : 0);
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        result = 31 * result + (getErrors() != null ? getErrors().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "meta=" + meta +
                ", body=" + body +
                ", files=" + files +
                ", data=" + data +
                ", relations=" + relations +
                ", links=" + links +
                ", calls=" + calls +
                ", transactions=" + transactions +
                ", errors=" + errors +
                '}';
    }

    public Transport(Transport other) {
        this.meta = other.meta;
        this.body = other.body;
        this.files = other.files;
        this.data = other.data;
        this.relations = other.relations;
        this.links = other.links;
        this.calls = other.calls;
        this.transactions = other.transactions;
        this.errors = other.errors;
    }
}