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
import io.kusanagi.katana.api.component.utils.Logger;
import io.kusanagi.katana.api.serializers.HttpRequestEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpRequest {

    private HttpRequestEntity httpRequestEntity;

    private HttpRequest(HttpRequestEntity httpRequestEntity){
        this.httpRequestEntity = httpRequestEntity;
    }

    public HttpRequest(HttpRequest other) {
        this.httpRequestEntity = other.httpRequestEntity;
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
        return httpRequestEntity.getMethod();
    }

    /**
     * @return Return the full URL provided for the request.
     */
    public String getUrl() {
        return httpRequestEntity.getUrl();
    }

    /**
     * @return Return the scheme used for the URL provided for the request.
     */
    @JsonIgnore
    public String getUrlScheme() {
        try {
            return new URL(httpRequestEntity.getUrl()).getProtocol();
        } catch (MalformedURLException e) {
            Logger.log(e);
            return "";
        }
    }

    /**
     * @return Return the hostname from the URL provided for the request.
     */
    @JsonIgnore
    public String getUrlHost() {
        try {
            return new URL(httpRequestEntity.getUrl()).getHost();
        } catch (MalformedURLException e) {
            Logger.log(e);
            return "";
        }
    }

    /**
     * @return Return the path part of the URL provided for the request.
     */
    @JsonIgnore
    public String getUrlPath() {
        try {
            return new URL(httpRequestEntity.getUrl()).getPath();
        } catch (MalformedURLException e) {
            Logger.log(e);
            return "";
        }
    }

    /**
     * Determine if the parameter name, specified by the REQUIRED case sensitive name argument, is defined in the queryParamsArray
     * object. If the parameter is defined but does not have a value it MUST consider that it exists.
     *
     * @return Return true if the Http request has a queryParamsArray param that matches the name specified in the parameter
     */
    public boolean hasQueryParam(String name) {
        return httpRequestEntity.getQueryParamsArray().containsKey(name);
    }

    /**
     * Return the value of the parameter specified by the REQUIRED case sensitive name argument. If more than 1
     * parameter exists with the specified name it MUST return the value of the first occurrence in the queryParamsArray string.
     * The default argument is the OPTIONAL value to use if the parameter does not exist. If the parameter is defined
     * in the queryParamsArray string, but does not have a value, the value of the default argument SHOULD NOT be applied.
     * If a parameter with the specified name does not exist, and no default is provided, and empty string MUST be
     * returned.
     *
     * @param name         Name of the queryParamsArray
     * @param defaultValue Default value is the queryParamsArray doesn't exist.
     * @return Return the value of the param or the default value if the param doesn't exist.
     */
    public String getQueryParam(String name, String defaultValue) {
        List<String> values = httpRequestEntity.getQueryParamsArray().get(name);
        return values == null || values.isEmpty() ? defaultValue == null ? "" : defaultValue : values.get(0);
    }

    /**
     * Return the value(s) of the parameter specified by the REQUIRED case sensitive name argument as an array of
     * values.
     * The default argument is the OPTIONAL value to use if the parameter does not exist, and MUST be an array of
     * string values. If the parameter is defined in the queryParamsArray string, but does not have a value, the value of the
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
        List<String> values = httpRequestEntity.getQueryParamsArray().get(name);
        return values != null && !values.isEmpty() ? values : defaultArray != null ? defaultArray : new ArrayList<>();
    }

    /**
     * @return Return an object with the parameters provided in the queryParamsArray string, where each property name is the parameter
     * name, and the value the parameter value as a string.
     */
    @JsonIgnore
    public Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();

        for (Map.Entry key : httpRequestEntity.getQueryParamsArray().entrySet()) {
            queryParams.put((String) key.getKey(), httpRequestEntity.getQueryParamsArray().get((String) key.getKey()).get(0));
        }

        return queryParams;
    }

    /**
     * @return Return an object with the parameters provided in the queryParamsArray string, where each property name is the parameter
     * name, and the value an array with the parameter value(s), each as a string.
     */
    public Map<String, List<String>> getQueryParamsArray() {
        return httpRequestEntity.getQueryParamsArray();
    }

    /**
     * Determine if the parameter name, specified by the REQUIRED case sensitive name argument, is defined in the post
     * data. If the parameter is defined but does not have a value it MUST consider that it exists.
     *
     * @param name Name of the post parameter
     * @return Return true if the post param exist in the http request
     */
    public boolean hasPostParam(String name) {
        return httpRequestEntity.getPostParamsArray().containsKey(name);
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
    @JsonIgnore
    public String getPostParam(String name, String defaultValue) {
        List<String> values = httpRequestEntity.getPostParamsArray().get(name);
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
    @JsonIgnore
    public List<String> getPostParamArray(String name, List<String> defaultArray) {
        List<String> values = httpRequestEntity.getPostParamsArray().get(name);
        return values != null && !values.isEmpty() ? values : defaultArray != null ? defaultArray : new ArrayList<>();
    }

    /**
     * @return return an object with the parameters provided in the post data, where each property name is the parameter
     * name, and the value the parameter value as a string.
     */
    @JsonIgnore
    public Map<String, String> getPostParams() {
        Map<String, String> postParams = new HashMap<>();

        for (Map.Entry key : httpRequestEntity.getPostParamsArray().entrySet()) {
            postParams.put((String) key.getKey(), httpRequestEntity.getPostParamsArray().get((String) key.getKey()).get(0));
        }

        return postParams;
    }


    /**
     * @return Return an object with the parameters provided in the post data, where each property name is the
     * parameter name, and the value an array with the parameter value(s), each as a string.
     */
    public Map<String, List<String>> getPostParamsArray() {
        return httpRequestEntity.getPostParamsArray();
    }

    /**
     * Determine if the HTTP protocolVersion of the request is equal to that specified by the REQUIRED protocolVersion argument.
     *
     * @param version Version to compare
     * @return Return true if the protocolVersion of the http request is the same protocolVersion as the one specified in the parameters
     */
    public boolean isProtocolVersion(String version) {
        return httpRequestEntity.getProtocolVersion().equals(version);
    }

    /**
     * @return Return the value of the HTTP protocol protocolVersion specified by the request.
     */
    public String getProtocolVersion() {
        return httpRequestEntity.getProtocolVersion();
    }

    /**
     * Determine if the request contains the HTTP header specified by the REQUIRED case sensitive name argument.
     *
     * @param name Header name
     * @return Return true if the http request has the header specified in the parameter
     */
    public boolean hasHeader(String name) {
        return httpRequestEntity.getHeaders().containsKey(name);
    }

    /**
     * @param name         Header name
     * @param defaultValue Default value
     * @return Return the value of the HTTP header specified by the REQUIRED case sensitive name argument.
     * The default argument is the OPTIONAL value to use if the header does not exist.
     * If a header with the specified name does not exist, and no default is provided, and empty string MUST be returned.
     */
    public String getHeader(String name, String defaultValue) {
        List<String> values = httpRequestEntity.getHeaders().get(name);
        return values == null || values.isEmpty() ? defaultValue == null ? "" : defaultValue : values.get(0);
    }

    /**
     * @return Return an object with the HTTP headers provided in the request, where each property name is the header
     * name, and the value the header value as a string.
     */
    public Map<String, List<String>> getHeaders() { //TODO return the value of the headers as a string
        return httpRequestEntity.getHeaders();
    }

    /**
     * @return Determine if the HTTP request body contains content. If the request body contains only whitespace this
     * MUST be considered valid content.
     */
    public boolean hasBody() {
        return httpRequestEntity.getBody() != null && !httpRequestEntity.getBody().isEmpty();
    }

    /**
     * @return Return the content of the HTTP request body. If the request body does not contain any content an empty
     * string MUST be returned.
     */
    public String getBody() {
        return httpRequestEntity.getBody();
    }

    /**
     * @param name File name
     * @return Determine if a file with the parameter name specified by the REQUIRED case sensitive name argument was
     * uploaded in the request.
     */
    public boolean hasFile(String name) {
        for (File file : httpRequestEntity.getFiles()) {
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
        for (File file : httpRequestEntity.getFiles()) {
            if (file.getFilename().equals(name)) {
                return file;
            }
        }

        File file = new File();
        file.setName(name);
        return file;
    }

    /**
     * @return Return an array with the files uploaded in the request, where each MUST be a File object.
     */
    public List<File> getFiles() {
        return httpRequestEntity.getFiles();
    }

    public static class Builder {

        private HttpRequestEntity httpRequestEntity;

        public Builder() {
        }

        public HttpRequest.Builder setHttpRequestEntity(HttpRequestEntity httpRequestEntity) {
            this.httpRequestEntity = httpRequestEntity;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(httpRequestEntity);
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

        HttpRequest that = (HttpRequest) o;

        return httpRequestEntity != null ? httpRequestEntity.equals(that.httpRequestEntity) : that.httpRequestEntity == null;
    }

    @Override
    public int hashCode() {
        return httpRequestEntity != null ? httpRequestEntity.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "httpRequestEntity=" + httpRequestEntity +
                '}';
    }
}
