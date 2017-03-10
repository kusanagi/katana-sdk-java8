package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.component.utils.Logger;

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
    /**
     * The HTTP version of the request
     */
    @JsonProperty(Key.HTTP_REQUEST_PROTOCOL_VERSION)
    private String protocolVersion;

    /**
     * The HTTP method used for the request
     */
    @JsonProperty(Key.HTTP_REQUEST_METHOD)
    private String method;

    /**
     * The URL used for the request
     */
    @JsonProperty(Key.HTTP_REQUEST_URL)
    private String url;

    /**
     * An object, where each property is the name of the query string parameter, and the value an array with the
     * parameter value(s), if no query string exists this property SHOULD NOT be defined
     */
    @JsonProperty(Key.HTTP_REQUEST_QUERY_PARAMS_ARRAY)
    private Map<String, List<String>> queryParamsArray;

    /**
     * An object, where each property is the name of the post data parameter, and the value an array with the parameter
     * value(s), if no post data exists this property SHOULD NOT be defined
     */
    @JsonProperty(Key.HTTP_REQUEST_POST_PARAMS_ARRAY)
    private Map<String, List<String>> postParamsArray;

    /**
     * An object, where each property is the name of the HTTP header, and the value the header value, if no headers
     * exist this property SHOULD NOT be defined
     */
    @JsonProperty(Key.HTTP_REQUEST_HEADERS)
    private Map<String, List<String>> headers;

    /**
     * The contents of the HTTP request body, if no content exists in the body an empty string MUST be assumed
     */
    @JsonProperty(Key.HTTP_REQUEST_BODY)
    private String body;

    /**
     * An array of File objects which were uploaded in the request, if no files were uploaded this property SHOULD NOT
     * be defined
     */
    @JsonProperty(Key.HTTP_REQUEST_FILES)
    private List<File> files;

    public HttpRequest() {
        // Default constructor to make possible the serialization of this object.
        this.body = "";
    }

    public HttpRequest(HttpRequest other) {
        this.protocolVersion = other.protocolVersion;
        this.method = other.method;
        this.url = other.url;
        this.queryParamsArray = other.queryParamsArray;
        this.postParamsArray = other.postParamsArray;
        this.headers = other.headers;
        this.body = other.body;
        this.files = other.files;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setQueryParamsArray(Map<String, List<String>> queryParamsArray) {
        this.queryParamsArray = queryParamsArray;
    }

    public void setPostParamsArray(Map<String, List<String>> postParamsArray) {
        this.postParamsArray = postParamsArray;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

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
    @JsonIgnore
    public String getUrlScheme() {
        try {
            return new URL(this.url).getProtocol();
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
            return new URL(this.url).getHost();
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
            return new URL(this.url).getPath();
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
        return this.queryParamsArray.containsKey(name);
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
        List<String> values = this.queryParamsArray.get(name);
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
        List<String> values = this.queryParamsArray.get(name);
        return values != null && !values.isEmpty() ? values : defaultArray != null ? defaultArray : new ArrayList<>();
    }

    /**
     * @return Return an object with the parameters provided in the queryParamsArray string, where each property name is the parameter
     * name, and the value the parameter value as a string.
     */
    @JsonIgnore
    public Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();

        for (Map.Entry key : this.queryParamsArray.entrySet()) {
            queryParams.put((String) key.getKey(), this.queryParamsArray.get(key.getKey()).get(0));
        }

        return queryParams;
    }

    /**
     * @return Return an object with the parameters provided in the queryParamsArray string, where each property name is the parameter
     * name, and the value an array with the parameter value(s), each as a string.
     */
    public Map<String, List<String>> getQueryParamsArray() {
        return queryParamsArray;
    }

    /**
     * Determine if the parameter name, specified by the REQUIRED case sensitive name argument, is defined in the post
     * data. If the parameter is defined but does not have a value it MUST consider that it exists.
     *
     * @param name Name of the post parameter
     * @return Return true if the post param exist in the http request
     */
    public boolean hasPostParam(String name) {
        return this.postParamsArray.containsKey(name);
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
        List<String> values = this.postParamsArray.get(name);
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
        List<String> values = this.postParamsArray.get(name);
        return values != null && !values.isEmpty() ? values : defaultArray != null ? defaultArray : new ArrayList<>();
    }

    /**
     * @return return an object with the parameters provided in the post data, where each property name is the parameter
     * name, and the value the parameter value as a string.
     */
    @JsonIgnore
    public Map<String, String> getPostParams() {
        Map<String, String> postParams = new HashMap<>();

        for (Map.Entry key : this.postParamsArray.entrySet()) {
            postParams.put((String) key.getKey(), this.postParamsArray.get(key.getKey()).get(0));
        }

        return postParams;
    }


    /**
     * @return Return an object with the parameters provided in the post data, where each property name is the
     * parameter name, and the value an array with the parameter value(s), each as a string.
     */
    public Map<String, List<String>> getPostParamsArray() {
        return this.postParamsArray;
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
        return this.body != null && !this.body.isEmpty();
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
        if (queryParamsArray != null ? !queryParamsArray.equals(that.queryParamsArray) : that.queryParamsArray != null) {
            return false;
        }
        if (postParamsArray != null ? !postParamsArray.equals(that.postParamsArray) : that.postParamsArray != null) {
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
        result = 31 * result + (queryParamsArray != null ? queryParamsArray.hashCode() : 0);
        result = 31 * result + (postParamsArray != null ? postParamsArray.hashCode() : 0);
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
                ", queryParamsArray=" + queryParamsArray +
                ", postParamsArray=" + postParamsArray +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", files=" + files +
                '}';
    }
}
