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

package io.kusanagi.katana.api.serializers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.sdk.File;

import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpRequestEntity {
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

    public HttpRequestEntity() {
        // Default constructor to make possible the serialization of this object.
        this.body = "";
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, List<String>> getQueryParamsArray() {
        return queryParamsArray;
    }

    public void setQueryParamsArray(Map<String, List<String>> queryParamsArray) {
        this.queryParamsArray = queryParamsArray;
    }

    public Map<String, List<String>> getPostParamsArray() {
        return postParamsArray;
    }

    public void setPostParamsArray(Map<String, List<String>> postParamsArray) {
        this.postParamsArray = postParamsArray;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HttpRequestEntity that = (HttpRequestEntity) o;

        if (protocolVersion != null ? !protocolVersion.equals(that.protocolVersion) : that.protocolVersion != null) {
            return false;
        }
        if (method != null ? !method.equals(that.method) : that.method != null) {
            return false;
        }
        if (url != null ? !url.equals(that.url) : that.url != null) {
            return false;
        }
        if (queryParamsArray != null ? !queryParamsArray.equals(that.queryParamsArray) : that.queryParamsArray != null) {
            return false;
        }
        if (postParamsArray != null ? !postParamsArray.equals(that.postParamsArray) : that.postParamsArray != null) {
            return false;
        }
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) {
            return false;
        }
        if (body != null ? !body.equals(that.body) : that.body != null) {
            return false;
        }
        return files != null ? files.equals(that.files) : that.files == null;
    }

    @Override
    public int hashCode() {
        int result = protocolVersion != null ? protocolVersion.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (queryParamsArray != null ? queryParamsArray.hashCode() : 0);
        result = 31 * result + (postParamsArray != null ? postParamsArray.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpRequestEntity{" +
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
