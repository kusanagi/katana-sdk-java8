package io.kusanagi.katana.sdk;

import io.kusanagi.katana.api.Api;

/**
 * Created by juan on 2/01/17.
 */
public class Resource<T extends Api> {

    private String name;

    private Callable<T> callback;

    public Resource(String name, Callable<T> callback) {
        this.name = name;
        this.callback = callback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Callable<T> getCallback() {
        return callback;
    }

    public void setCallback(Callable<T> callback) {
        this.callback = callback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Resource)) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (!getName().equals(resource.getName())) {
            return false;
        }
        return getCallback().equals(resource.getCallback());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getCallback().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", callback=" + callback +
                '}';
    }
}
