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

package io.kusanagi.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.sdk.Error;

/**
 * Created by juan on 26/09/16.
 */
public class ErrorPayload {

    /**
     * The internal error while processing the payload, which MUST NOT contain an error from the userland code
     */
    @JsonProperty(Key.ERROR)
    private Error error;

    public ErrorPayload() {
        //Empty constructor for serialization
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorPayload)) {
            return false;
        }

        ErrorPayload that = (ErrorPayload) o;

        return getError().equals(that.getError());

    }

    @Override
    public int hashCode() {
        return getError().hashCode();
    }

    @Override
    public String toString() {
        return "ErrorPayload{" +
                "error=" + error +
                '}';
    }
}
