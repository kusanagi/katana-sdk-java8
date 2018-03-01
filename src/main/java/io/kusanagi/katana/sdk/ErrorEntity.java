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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Constants;
import io.kusanagi.katana.api.component.Key;

/**
 * Created by juan on 26/09/16.
 */
public class ErrorEntity {

    /**
     * The error message in natural language, if no message exists this property SHOULD NOT be defined, and "Unknown
     * error" MUST be assumed
     */
    @JsonProperty(Key.ERROR_MESSAGE)
    private String message;

    /**
     * The code for the error, if no code exists this property SHOULD NOT be defined, and 0 MUST be assumed
     */
    @JsonProperty(Key.ERROR_CODE)
    private int code;

    /**
     * The status for the response if the protocol of the Gateway is HTTP, if no status exists this property SHOULD NOT
     * be defined, and "500 Internal Server ErrorEntity" MUST be assumed
     */
    @JsonProperty(Key.ERROR_STATUS)
    private String status;

    public ErrorEntity() {
        this.message = "Unknown error";
        this.code = 0;
        this.status = Constants.INTERNAL_SERVER_ERROR_STATUS;
    }

    public ErrorEntity(ErrorEntity other) {
        this.message = other.message;
        this.code = other.code;
        this.status = other.status;
    }

    /**
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorEntity)) {
            return false;
        }

        ErrorEntity error = (ErrorEntity) o;

        if (getCode() != error.getCode()) {
            return false;
        }
        if (!getMessage().equals(error.getMessage())) {
            return false;
        }
        return getStatus().equals(error.getStatus());

    }

    @Override
    public int hashCode() {
        int result = getMessage().hashCode();
        result = 31 * result + getCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
