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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.sdk.*;
import io.kusanagi.katana.sdk.ErrorEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportEntity implements CommandReplyResult {

    /**
     * The meta-information about the Transport
     */
    @JsonProperty(Key.TRANSPORT_META)
    private TransportMeta meta;

    /**
     * An object containing the semantics of the file to download in the response from the Gateway to the client, if no
     * file is set this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_BODY)
    private File body;

    /**
     * An object containing the semantics of the files uploaded to the Gateway or sent by a Service, if no files are set
     * this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_FILES)
    private Map<String, Map<String, Map<String, Map<String, List<File>>>>> files;

    /**
     * The data stored by each Service for the response, if no data exists this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_DATA)
    private Map<String, Map<String, Map<String, Map<String, Object>>>> data;

    /**
     * The relationships defined by each Service with others, if no relations exist this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_RELATIONS)
    private Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations;

    /**
     * The hyperlinks defined by each Service for the response, where each property is the name of the link, while the
     * value is the URI [17] itself, if no links exist this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_LINKS)
    private Map<String, Map<String, Map<String, String>>> links;

    /**
     * The calls to other Services within this request, if no calls exist this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_CALLS)
    private Map<String, Map<String, List<CallEntity>>> calls;

    /**
     * The transactions registered by each Service, if no transactions exist this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_TRANSACTIONS)
    private TransactionEntity transactions;

    /**
     * The errors returned by each Service, if no errors exist this property SHOULD NOT be defined
     */
    @JsonProperty(Key.TRANSPORT_ERRORS)
    private Map<String, Map<String, Map<String, List<ErrorEntity>>>> errors;

    /**
     *
     */
    public TransportEntity() {
        this.links = new HashMap<>();
    }

    public TransportMeta getMeta() {
        return meta;
    }

    public void setMeta(TransportMeta meta) {
        this.meta = meta;
    }

    public File getBody() {
        return body;
    }

    public void setBody(File body) {
        this.body = body;
    }

    public Map<String, Map<String, Map<String, Map<String, List<File>>>>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Map<String, Map<String, Map<String, List<File>>>>> files) {
        this.files = files;
    }

    public Map<String, Map<String, Map<String, Map<String, Object>>>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Map<String, Map<String, Object>>>> data) {
        this.data = data;
    }

    public Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> getRelations() {
        return relations;
    }

    public void setRelations(Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations) {
        this.relations = relations;
    }

    public Map<String, Map<String, Map<String, String>>> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Map<String, Map<String, String>>> links) {
        this.links = links;
    }

    public Map<String, Map<String, List<CallEntity>>> getCalls() {
        return calls;
    }

    public void setCalls(Map<String, Map<String, List<CallEntity>>> calls) {
        this.calls = calls;
    }

    public TransactionEntity getTransactions() {
        return transactions;
    }

    public void setTransactions(TransactionEntity transactions) {
        this.transactions = transactions;
    }

    public Map<String, Map<String, Map<String, List<ErrorEntity>>>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Map<String, Map<String, List<ErrorEntity>>>> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransportEntity that = (TransportEntity) o;

        if (meta != null ? !meta.equals(that.meta) : that.meta != null) {
            return false;
        }
        if (body != null ? !body.equals(that.body) : that.body != null) {
            return false;
        }
        if (files != null ? !files.equals(that.files) : that.files != null) {
            return false;
        }
        if (data != null ? !data.equals(that.data) : that.data != null) {
            return false;
        }
        if (relations != null ? !relations.equals(that.relations) : that.relations != null) {
            return false;
        }
        if (links != null ? !links.equals(that.links) : that.links != null) {
            return false;
        }
        if (calls != null ? !calls.equals(that.calls) : that.calls != null) {
            return false;
        }
        if (transactions != null ? !transactions.equals(that.transactions) : that.transactions != null) {
            return false;
        }
        return errors != null ? errors.equals(that.errors) : that.errors == null;
    }

    @Override
    public int hashCode() {
        int result = meta != null ? meta.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (relations != null ? relations.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        result = 31 * result + (calls != null ? calls.hashCode() : 0);
        result = 31 * result + (transactions != null ? transactions.hashCode() : 0);
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransportEntity{" +
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
}
