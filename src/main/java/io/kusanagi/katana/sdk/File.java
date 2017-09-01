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
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.component.ExceptionMessage;
import io.kusanagi.katana.api.component.Key;
import io.kusanagi.katana.api.component.utils.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class File {

    /**
     * File name
     */
    private String name;

    /**
     * The host and path to the file, which MUST be formed by the file server of the component and the file path; It
     * MUST also include the "http://" protocol scheme. In case of files being uploaded by a service, path would be the
     * full path to the local file, including the "file://" protocol scheme
     */
    @JsonProperty(Key.FILE_PATH)
    private String path;

    /**
     * The access token, as defined in the configuration for the component, which MUST NOT exist when a file is being
     * uploaded
     */
    @JsonProperty(Key.FILE_MIME)
    private String mime;

    /**
     * The original filename of the upload to the Gateway or file provided by a Service, which MUST NOT contain the path
     */
    @JsonProperty(Key.FILE_FILENAME)
    private String filename;

    /**
     * The size of the file in bytes
     */
    @JsonProperty(Key.FILE_SIZE)
    private String size;

    /**
     * The mime-type of the file content, which SHOULD default to "text/plain"
     */
    @JsonProperty(Key.FILE_TOKEN)
    private String token;

    private boolean exists;

    public File() {
        // Default constructor to make possible the serialization of this object.
        this.path = "";
        this.mime = "text/plain";
        this.token = "";
    }

    public File(String name, String path, String mime, String filename, String size, String token, boolean exists) {
        this.name = name;
        this.path = path;
        this.mime = mime;
        this.filename = filename;
        this.size = size;
        this.token = token;
        this.exists = exists;
    }

    public File(File other) {
        this.name = other.name;
        this.path = other.path;
        this.mime = other.mime;
        this.filename = other.filename;
        this.size = other.size;
        this.token = other.token;
        this.exists = other.exists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    //SDK Methods

    /**
     * @return the name of the parameter for the file.
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * @return the full path for the file, if local the path MUST begin with "file://", otherwise "http://".
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the MIME type of the file.
     */
    public String getMime() {
        return mime;
    }

    /**
     * @return the filename of the file, but MUST NOT include the file path.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @return the size of the file in bytes.
     */
    public String getSize() {
        return size;
    }

    /**
     * @return the token for the file server where the file is hosted, otherwise it MUST return an
     */
    public String getToken() {
        return token;
    }

    /**
     * determine if a path is defined for the file.
     *
     * @return true if a path is defined for the file.
     */
    public boolean exists() {
        return exists;
    }

    /**
     * determine if a local file beggining with "file://" has been defined for the file.
     *
     * @return true if a local file beggining with "file://" has been defined for the file.
     */
    @JsonIgnore
    public boolean isLocal() {
        try {
            URI uri = new URI(path);
            return "file".equals(uri.getScheme());
        } catch (URISyntaxException e) {
            Logger.log(e);
            return false;
        }
    }

    /**
     * @return the contents of the file. If the path property begins with "file://", the contents MUST be accessed from
     * the file at that path on the local file system. If the path property begins with "http://", the contents of the
     * file MUST be accessed by making a GET request to that path, providing the value of the token property in a header
     * of the HTTP request named "X-Token".
     */
    public String read() {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            Logger.log(e);
            throw new IllegalArgumentException(String.format(ExceptionMessage.FILE_DOES_NOT_EXIST_IN_PATH, path));
        }
    }

    /**
     * @param name File name
     * @return a new instance of the File object, which MUST use the existing attributes of the current instance, but
     * MUST update the name to identify the file in the request with the given value of the REQUIRED name argument.
     */
    public File copyWithName(String name) {
        return new File(name, path, mime, filename, size, token, exists);
    }

    /**
     * @param mime Mime of the file
     * @return a new instance of the File object, which MUST use the existing attributes of the current instance, but
     * MUST update the MIME type of the file with the given value of the REQUIRED mime argument.
     */
    public File copyWithMime(String mime) {
        return new File(name, path, mime, filename, size, token, exists);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        File file = (File) o;

        if (exists != file.exists) {
            return false;
        }
        if (name != null ? !name.equals(file.name) : file.name != null) {
            return false;
        }
        if (path != null ? !path.equals(file.path) : file.path != null) {
            return false;
        }
        if (mime != null ? !mime.equals(file.mime) : file.mime != null) {
            return false;
        }
        if (filename != null ? !filename.equals(file.filename) : file.filename != null) {
            return false;
        }
        if (size != null ? !size.equals(file.size) : file.size != null) {
            return false;
        }
        return token != null ? token.equals(file.token) : file.token == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (mime != null ? mime.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (exists ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", mime='" + mime + '\'' +
                ", filename='" + filename + '\'' +
                ", size='" + size + '\'' +
                ", token='" + token + '\'' +
                ", exists=" + exists +
                '}';
    }
}
