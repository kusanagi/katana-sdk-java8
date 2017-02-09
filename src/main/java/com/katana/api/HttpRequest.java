package com.katana.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.common.utils.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpRequest {
    @JsonProperty("v")
    private String protocolVersion;

    @JsonProperty("m")
    private String method;

    @JsonProperty("u")
    private String url;

    @JsonProperty("q")
    private Map<String, List<String>> query;

    @JsonProperty("p")
    private Map<String, List<String>> postData;

    @JsonProperty("h")
    private Map<String, List<String>> headers;

    @JsonProperty("b")
    private String body;

    @JsonProperty("f")
    private List<File> files;

    public HttpRequest() {
    }

    /**
     * @param protocolVersion
     */
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param query
     */
    public void setQuery(Map<String, List<String>> query) {
        this.query = query;
    }

    /**
     * @param postData
     */
    public void setPostData(Map<String, List<String>> postData) {
        this.postData = postData;
    }

    /**
     * @param headers
     */
    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    /**
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @param files
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    //SDK Methods

    /**
     * Determine if the specified method matches that of the HTTP request. The method argument is the REQUIRED case
     * insensitive HTTP method to match against.
     *
     * @param method Method to compare
     * @return Return true is the request method matches the one specified in the parameter
     */
    public boolean isMethod(String method) {
        return getMethod().equals(method);
    }

    /**
     * @return Return the HTTP method used for the request as an uppercase string.
     */
    public String getMethod() {
        return method;
    }

    /**
     * @return Return the full URL provided for the request.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return Return the scheme used for the URL provided for the request.
     */
    public String getUrlScheme() {
        try {
            return new URL(this.url).getProtocol();
        } catch (MalformedURLException e) {
            Logger.log(e);
            return null;
        }
    }

    /**
     * @return Return the hostname from the URL provided for the request.
     */
    public String getUrlHost() {
        try {
            return new URL(this.url).getHost();
        } catch (MalformedURLException e) {
            Logger.log(e);
            return null;
        }
    }

    /**
     * @return Return the path part of the URL provided for the request.
     */
    public String getUrlPath() {
        try {
            return new URL(this.url).getPath();
        } catch (MalformedURLException e) {
            Logger.log(e);
            return null;
        }
    }

    /**
     * Determine if the parameter name, specified by the REQUIRED case sensitive name argument, is defined in the query
     * object. If the parameter is defined but does not have a value it MUST consider that it exists.
     *
     * @return Return true if the Http request has a query param that matches the name specified in the parameter
     */
    public boolean hasQueryParam(String name) {
        return this.query.containsKey(name);
    }

    /**
     * Return the value of the parameter specified by the REQUIRED case sensitive name argument. If more than 1
     * parameter exists with the specified name it MUST return the value of the first occurrence in the query string.
     * The default argument is the OPTIONAL value to use if the parameter does not exist. If the parameter is defined
     * in the query string, but does not have a value, the value of the default argument SHOULD NOT be applied.
     * If a parameter with the specified name does not exist, and no default is provided, and empty string MUST be
     * returned.
     *
     * @param name         Name of the query
     * @param defaultValue Default value is the query doesn't exist.
     * @return Return the value of the param or the default value if the param doesn't exist.
     */
    public String getQueryParam(String name, String defaultValue) {
        List<String> values = this.query.get(name);
        return values == null || values.isEmpty() ? defaultValue == null ? "" : defaultValue : values.get(0);
    }

    /**
     * Return the value(s) of the parameter specified by the REQUIRED case sensitive name argument as an array of
     * values.
     * The default argument is the OPTIONAL value to use if the parameter does not exist, and MUST be an array of
     * string values. If the parameter is defined in the query string, but does not have a value, the value of the
     * default argument SHOULD NOT be applied.
     * If a parameter with the specified name does not exist, and no default is provided, and empty array MUST be
     * returned.
     *
     * @param name         Name of the param
     * @param defaultArray default array if the param does not exist
     * @return Return the values of the parameter specified in the argument, if the parameter does not exist, the
     * default array will be returned, if no default is specified an empty array will be returned
     */
    public List<String> getQueryParamArray(String name, List<String> defaultArray) {
        List<String> values = this.query.get(name);
        return values == null || values.isEmpty()
                ? defaultArray == null
                ? new ArrayList<String>()
                : defaultArray
                : values;
    }

    /**
     * @return Return an object with the parameters provided in the query string, where each property name is the parameter
     * name, and the value the parameter value as a string.
     */
    public Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();

        for (String key : this.query.keySet()) {
            queryParams.put(key, this.query.get(key).get(0));
        }

        return queryParams;
    }

    /**
     * @return Return an object with the parameters provided in the query string, where each property name is the parameter
     * name, and the value an array with the parameter value(s), each as a string.
     */
    public Map<String, List<String>> getQueryParamsArray() {
        return query;
    }

    /**
     * Determine if the parameter name, specified by the REQUIRED case sensitive name argument, is defined in the post
     * data. If the parameter is defined but does not have a value it MUST consider that it exists.
     *
     * @param name Name of the post parameter
     * @return Return true if the post param exist in the http request
     */
    public boolean hasPostParam(String name) {
        return this.postData.containsKey(name);
    }

    /**
     * Return the value of the parameter specified by the REQUIRED case sensitive name argument. If more than 1
     * parameter exists with the specified name it MUST return the value of the first occurrence in the post data.
     *
     * @param name         Name of the post parameter
     * @param defaultValue Default value
     * @return Return the value of the first occurrence of the Post parameter, if the post parameter does not exist the
     * default value will be returned, if the default value is not specified, an empty string will be returned.
     */
    public String getPostParam(String name, String defaultValue) {
        List<String> values = this.postData.get(name);
        return values == null || values.isEmpty() ? defaultValue == null ? "" : defaultValue : values.get(0);
    }

    /**
     * Return the value(s) of the parameter specified by the REQUIRED case sensitive name argument as an array of
     * values.
     * The default argument is the OPTIONAL value to use if the parameter does not exist, and MUST be an array of
     * string values. If the parameter is defined in the post data, but does not have a value, the value of the default
     * argument SHOULD NOT be applied.
     * If a parameter with the specified name does not exist, and no default is provided, and empty array MUST be
     * returned.
     *
     * @param name         Name of the post parameter
     * @param defaultArray Default array
     * @return Return the values of the Post parameter, if the post parameter does not exist the default array will be
     * returned, if the default array is not specified, an empty array will be returned.
     */
    public List<String> getPostParamArray(String name, List<String> defaultArray) {
        List<String> values = this.postData.get(name);
        return values == null || values.isEmpty()
                ? defaultArray == null
                ? new ArrayList<String>()
                : defaultArray
                : values;
    }

    /**
     * @return return an object with the parameters provided in the post data, where each property name is the parameter
     * name, and the value the parameter value as a string.
     */
    public Map<String, String> getPostParams() {
        Map<String, String> postParams = new HashMap<>();

        for (String key : this.postData.keySet()) {
            postParams.put(key, this.postData.get(key).get(0));
        }

        return postParams;
    }


    /**
     * @return Return an object with the parameters provided in the post data, where each property name is the
     * parameter name, and the value an array with the parameter value(s), each as a string.
     */
    public Map<String, List<String>> getPostParamsArray() {
        return this.postData;
    }

    /**
     * Determine if the HTTP protocolVersion of the request is equal to that specified by the REQUIRED protocolVersion argument.
     *
     * @param version Version to compare
     * @return Return true if the protocolVersion of the http request is the same protocolVersion as the one specified in the parameters
     */
    public boolean isProtocolVersion(String version) {
        return this.protocolVersion.equals(version);
    }

    /**
     * @return Return the value of the HTTP protocol protocolVersion specified by the request.
     */
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Determine if the request contains the HTTP header specified by the REQUIRED case sensitive name argument.
     *
     * @param name Header name
     * @return Return true if the http request has the header specified in the parameter
     */
    public boolean hasHeader(String name) {
        return this.headers.containsKey(name);
    }

    /**
     * @param name         Header name
     * @param defaultValue Default value
     * @return Return the value of the HTTP header specified by the REQUIRED case sensitive name argument.
     * The default argument is the OPTIONAL value to use if the header does not exist.
     * If a header with the specified name does not exist, and no default is provided, and empty string MUST be returned.
     */
    public String getHeader(String name, String defaultValue) {
        List<String> values = this.headers.get(name);
        return values == null || values.isEmpty() ? defaultValue == null ? "" : defaultValue : values.get(0);
    }

    /**
     * @return Return an object with the HTTP headers provided in the request, where each property name is the header
     * name, and the value the header value as a string.
     */
    public Map<String, List<String>> getHeaders() { //TODO return the value of the headers as a string
        return headers;
    }

    /**
     * @return Determine if the HTTP request body contains content. If the request body contains only whitespace this
     * MUST be considered valid content.
     */
    public boolean hasBody() {
        return this.body != null;
    }

    /**
     * @return Return the content of the HTTP request body. If the request body does not contain any content an empty
     * string MUST be returned.
     */
    public String getBody() {
        return body;
    }

    /**
     * @param name File name
     * @return Determine if a file with the parameter name specified by the REQUIRED case sensitive name argument was
     * uploaded in the request.
     */
    public boolean hasFile(String name) {
        for (File file : this.files) {
            if (file.getFilename().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the file with the REQUIRED case sensitive name argument, which MUST be returned as a File object. If the
     * file is not found, a File object with the REQUIRED name as first argument and an empty path as second argument
     * MUST be returned.
     *
     * @param name File name
     * @return Return the file specified in the parameter. If the file is not found, a File object with the REQUIRED
     * name as first argument and an empty path as second argument will be returned.
     */
    public File getFile(String name) {
        for (File file : this.files) {
            if (file.getFilename().equals(name)) {
                return file;
            }
        }

        File file = new File();
        file.setFilename(name);
        return file;
    }

    /**
     * @return Return an array with the files uploaded in the request, where each MUST be a File object.
     */
    public List<File> getFiles() {
        return files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpRequest)) {
            return false;
        }

        HttpRequest that = (HttpRequest) o;

        if (getProtocolVersion() != null ? !getProtocolVersion().equals(that.getProtocolVersion()) : that.getProtocolVersion() != null) {
            return false;
        }
        if (getMethod() != null ? !getMethod().equals(that.getMethod()) : that.getMethod() != null) {
            return false;
        }
        if (getUrl() != null ? !getUrl().equals(that.getUrl()) : that.getUrl() != null) {
            return false;
        }
        if (query != null ? !query.equals(that.query) : that.query != null) {
            return false;
        }
        if (postData != null ? !postData.equals(that.postData) : that.postData != null) {
            return false;
        }
        if (getHeaders() != null ? !getHeaders().equals(that.getHeaders()) : that.getHeaders() != null) {
            return false;
        }
        if (getBody() != null ? !getBody().equals(that.getBody()) : that.getBody() != null) {
            return false;
        }
        return getFiles() != null ? getFiles().equals(that.getFiles()) : that.getFiles() == null;

    }

    @Override
    public int hashCode() {
        int result = getProtocolVersion() != null ? getProtocolVersion().hashCode() : 0;
        result = 31 * result + (getMethod() != null ? getMethod().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (query != null ? query.hashCode() : 0);
        result = 31 * result + (postData != null ? postData.hashCode() : 0);
        result = 31 * result + (getHeaders() != null ? getHeaders().hashCode() : 0);
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        result = 31 * result + (getFiles() != null ? getFiles().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "protocolVersion='" + protocolVersion + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", query=" + query +
                ", postData=" + postData +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", files=" + files +
                '}';
    }

    public HttpRequest(HttpRequest other) {
        this.protocolVersion = other.protocolVersion;
        this.method = other.method;
        this.url = other.url;
        this.query = other.query;
        this.postData = other.postData;
        this.headers = other.headers;
        this.body = other.body;
        this.files = other.files;
    }
}