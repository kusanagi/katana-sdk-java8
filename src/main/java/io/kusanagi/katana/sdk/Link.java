package io.kusanagi.katana.sdk;

/**
 * Created by jega on 1/03/18.
 */
public class Link {

    private String address;
    private String name;
    private String link;
    private String uri;

    public Link(String address, String name, String link, String uri) {
        this.address = address;
        this.name = name;
        this.link = link;
        this.uri = uri;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getUri() {
        return uri;
    }
}
