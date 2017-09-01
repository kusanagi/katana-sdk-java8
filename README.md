KATANA SDK for Java 8
=====================

[![Build Status](https://travis-ci.org/kusanagi/katana-sdk-java8.svg?branch=master)](https://travis-ci.org/kusanagi/katana-sdk-java8)
[![Coverage Status](https://coveralls.io/repos/github/kusanagi/katana-sdk-java8/badge.svg?branch=master)](https://coveralls.io/github/kusanagi/katana-sdk-java8?branch=master)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Java SDK to interface with the **KATANA**™ framework (https://kusanagi.io).

Requirements
------------

* KATANA Framework 1.1
* [JDK](http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) 1.8
* [libzmq](http://zeromq.org/intro:get-the-software) 4.1.5+

Installation
------------

To install and use the Java SDK you'll need to first install the JDK 1.8. To do so using **apt** you can run the following from the command-line:

```
$ sudo apt-add-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```

The SDK can then be built with either [Maven](https://maven.apache.org) or [Gradle](https://gradle.org).

If using **Maven**, add the following in your `pom.xml` file:

```xml
<dependency>
    <groupId>io.kusanagi</groupId>
    <artifactId>katana-sdk-java8</artifactId>
    <version>1.1.0</version>
</dependency>
```

Or, if using **Gradle**, add the following in your `build.gradle` file:

```gradle
dependencies {
    compile group: 'io.kusanagi', name: 'katana-sdk-java8', version: '1.1.0'
}
```

Getting Started
---------------

The **KATANA**™ SDK is fairly simple to use. We begin by first defining the model of our components using configuration files.

Here we'll use `XML`, but the framework also supports both `JSON` and `YAML` formats.

First, create a file named `middleware.xml`, with the following configuration:

```
<?xml version="1.0" encoding="UTF-8"?>
<middleware xmlns="urn:katana:middleware" name="middleware_name" version="middleware_version" protocol="urn:katana:protocol:http">
    ...
    <engine runner="urn:katana:runner:java8" path="middleware_file.jar" single-process=true>
        <variable name="workers" value="5"/>
    ...
</middleware>
```

Then, create a file named `service.xml`, with the following configuration:

```
<?xml version="1.0" encoding="UTF-8"?>
<service xmlns="urn:katana:service" name="service_name" version="service_version">
    ...
    <engine runner="urn:katana:runner:java8" path="service_file.jar" single-process=true>
        <variable name="workers" value="5"/>
    ...
</service>
```

Now, create a file named `middleware.class.java`, and add the following source code for the **Middleware**, which handles both a **Request** and a **Response**:

```java
import io.kusanagi.katana.sdk.Middleware;
import io.kusanagi.katana.sdk.Callable;
import io.kusanagi.katana.sdk.Request;
import io.kusanagi.katana.sdk.Response;

public class Middleware {

    public static void main(String[] args) {
        Middleware middleware = new Middleware(args);
        middleware.request(request -> {
                // your logic here
                return request;
            }
        });
        middleware.response(response -> {
                // your logic here
                return response;
            }
        });
        middleware.run();
    }
}
```

And also create a file named `service.class.java`, and add the following source code for the **Service**, which registers an action:

```java
import io.kusanagi.katana.sdk.Service;
import io.kusanagi.katana.sdk.Callable;
import io.kusanagi.katana.sdk.Action;

public class Service {

    public static void main(String[] args) {
        Service service = new Service(args);
        service.action("actionName", action -> {
                // your logic here
                return action;
            }
        });
        service.run();
    }
}
```

Examples
--------

The following example is a **Middleware** which translates HTTP requests to CRUD actions based on REST conventions:

```java
package com.katana.example;

import io.kusanagi.katana.sdk.Middleware;
import io.kusanagi.katana.sdk.Callable;
import io.kusanagi.katana.sdk.Request;

import java.util.Iterator;
import java.util.Map;

public class Rest {

    public static void main(String[] args) {
        Middleware middleware = new Middleware(args);
        middleware.request(request -> {
                // the URL format expected is "/{version}/{service}/{extra}"
                String[] parts = request.getHttpRequest().getUrlPath().split("/");
                // set the Service version
                request.setServiceVersion(parts[1]);
                // set the Service name
                request.setServiceName(parts[2]);
                boolean hasExtraPath = parts.length == 4 && !parts[3].isEmpty();
                String method = request.getHttpRequest().getMethod();
                // resolve the Service action to call
                switch (method) {
                    case "GET":
                        return request.setActionName(hasExtraPath ? "read" : "list");
                    case "POST":
                        return request.setActionName("create");
                    case "PUT":
                        return request.setActionName("replace");
                    case "PATCH":
                        return request.setActionName("update");
                    case "DELETE":
                        return request.setActionName("delete");
                    default:
                        return request.setActionName(actionName);
                }
            }
        });
        middleware.run();
    }
}
```

The following example is a **Service** named "users", with a "read" action which retrieves a user from a `List` and returns the entity according to the `user_id` parameter:

```java
package com.katana.example;

import io.kusanagi.katana.sdk.Service;
import io.kusanagi.katana.sdk.Callable;
import io.kusanagi.katana.sdk.Action;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static void main(String[] args) {
        Service service = new Service(args);
        service.action("read", action -> {
                // list of users, this would normally be a DB call
                final List<User> users = new ArrayList<>();
                users.add(new User(1, "James"));
                users.add(new User(2, "Jeronimo"));
                users.add(new User(3, "Fernando"));
                users.add(new User(4, "Ricardo"));
                users.add(new User(5, "Hugo"));
                // read the incoming "id" parameter
                int userId = (Integer) action.getParam("id").getValue();
                User entity = null;
                // find the user in the list
                for (User user : users) {
                    if (user.getId() == userId) {
                        entity = user;
                        break;
                    }
                }
                if (entity == null) {
                    return action.error("User does not exist", 1, "404 Not Found");
                }
                action.setEntity(entity);
                action.setLink("self", "/0.1.0/users/" + userId);
                return action;
            }
        });
        service.run();
    }
}
```

Documentation
-------------

See the [API](https://app.kusanagi.io#katana/docs/sdk) for a technical reference of the SDK.

For help using the framework check the [documentation](https://app.kusanagi.io#katana/docs).

Support
-------

Please first read our [contribution guidelines](https://app.kusanagi.io#katana/open-source/contributing).

* [Requesting help](https://app.kusanagi.io#katana/open-source/help)
* [Reporting a bug](https://app.kusanagi.io#katana/open-source/bug)
* [Submitting a patch](https://app.kusanagi.io#katana/open-source/patch)
* [Security issues](https://app.kusanagi.io#katana/open-source/security)

We use [milestones](https://github.com/kusanagi/katana-sdk-java8/milestones) to track upcoming releases inline with our [versioning](https://app.kusanagi.io#katana/docs/framework/versions) strategy, and as defined in our [roadmap](https://app.kusanagi.io#katana/docs/framework/roadmap).

For commercial support see the [solutions](https://kusanagi.io/solutions) available or [contact us](https://kusanagi.io/contact) for more information.

Contributing
------------

If you'd like to know how you can help and support our Open Source efforts see the many ways to [get involved](https://app.kusanagi.io#katana/open-source).

Please also be sure to review our [community guidelines](https://app.kusanagi.io#katana/open-source/conduct).

License
-------

Copyright 2016-2017 KUSANAGI S.L. (https://kusanagi.io). All rights reserved.

KUSANAGI, the sword logo, KATANA and the "K" logo are trademarks and/or registered trademarks of KUSANAGI S.L. All other trademarks are property of their respective owners.

Licensed under the [MIT License](https://app.kusanagi.io#katana/open-source/license). Redistributions of the source code included in this repository must retain the copyright notice found in each file.
