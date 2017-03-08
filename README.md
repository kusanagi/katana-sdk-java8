KATANA SDK for JAVA 8
=====================

[badges]

JAVA SDK to interface with the **KATANA**™ framework (https://katana.kusanagi.io).

Requirements
------------

* KATANA Framework 1.0+
* JDK 1.8

Installation
------------

In order to install the SDK you need to install the JDK 1.8:

#### JDK 1.8:

1. ```sudo apt-add-repository ppa:webupd8team/java```
2. ```sudo apt-get update```
3. ```sudo apt-get install oracle-java8-installer```

#### Build:

The **KATANA** SDK can be built with either Maven or Gradle:

**Maven**:

Add the following in `pom.xml`:
```xml
<dependency>
    <groupId>com.kusanagi</groupId>
    <artifactId>katana-sdk-java8</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle**:

Add the following in `build.gradle`:
```gradle
dependencies {
    compile group: 'com.kusanagi', name: 'katana-sdk-java8', version: '1.0.0'
}
```

Getting Started
---------------

The **KATANA** SDK is fairly simple to use, the following is an example that uses the SDK to create a **Service** and run an action:

```java
import com.katana.sdk.Action;
import com.katana.sdk.Callable;
import com.katana.sdk.Service;

public class Service {

    public static void main(String[] args) {
        Service service = new Service(args);

        service.action("actionName", action -> {
               // logic ...
               return action;
           });

        service.run();
    }
}
```

The following is an example that uses the SDK to create a **Middleware** and handle both a **Request** and a **Response**:

```java
import com.katana.sdk.Request;
import com.katana.sdk.Response;
import com.katana.sdk.Callable;
import com.katana.sdk.Middleware;

public class Middleware {

    public static void main(String[] args) {
        Middleware middleware = new Middleware(args);

        middleware.request(request -> {
               // logic ...
               return request;
           });

        middleware.response(response -> {
               // logic ...
               return response;
           });

        middleware.run();
    }
}
```

Examples
--------

The following is a User **Service** with a read action, which retrieves a user from a List and returns the User according to the `user_id` parameter:

```java
package com.katana.example;

import com.katana.sdk.Action;
import com.katana.sdk.Callable;
import com.katana.sdk.Service;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static void main(String[] args) {

        final List<User> users = new ArrayList<>();
        users.add(new User(1, "James"));
        users.add(new User(2, "Jeronimo"));
        users.add(new User(3, "Fernando"));
        users.add(new User(4, "Ricardo"));
        users.add(new User(5, "Hugo"));

        Service service = new Service(args);
        service.action("read", action -> {
               int userId = (Integer) action.getParam("id").getValue();

               User entity = null;
               for (User user : users) {
                   if (user.getId() == userId) {
                       entity = user;
                       break;
                   }
               }

               if (entity == null) {
                   action.error("User does not exist", 1, "404 Not Found");
               } else {
                   action.setEntity(entity);
                   action.setLink("self", "/0.1.0/users/" + userId);
               }

               return action;
           });
        service.run();
    }
}
```

The following is a REST **Middleware** which translates HTTP requests to CRUD actions depending on the `verbs` and parameters:

```java
package com.katana.example;

import com.katana.sdk.Request;
import com.katana.sdk.Middleware;
import com.katana.sdk.Callable;

import java.util.Iterator;
import java.util.Map;

public class Rest {

    public static void main(String[] args) {
        Middleware middleware = new Middleware(args);
        middleware.request(request -> {
               // /{version}/{service}/{extra}

               String[] parts = request.getHttpRequest().getUrlPath().split("/");
               request.setServiceVersion(parts[1]);
               request.setServiceName(parts[2]);
               boolean hasExtraPath = parts.length == 4 && !parts[3].isEmpty();

               String method = request.getHttpRequest().getMethod();

               String actionName = null;
               switch (method) {
                   case "GET":
                       actionName = hasExtraPath ? "read" : "list";
                       break;
                   case "POST":
                       actionName = "create";
                       break;
                   case "PUT":
                       actionName = "replace";
                       break;
                   case "PATCH":
                       actionName = "update";
                       break;
                   case "DELETE":
                       actionName = "delete";
                       break;
               }

               if (actionName != null) {
                   request.setActionName(actionName);
               }

               return request;
           });
        middleware.run();
    }
}
```

Documentation
-------------

See the [API](https://kusanagi.io/app#katana/docs/sdk) for a technical reference of the SDK, or read the full [specification](https://kusanagi.io/app#katana/docs/sdk/specification).

For help using the framework check the [documentation](https://kusanagi.io/app#katana/docs), or join the [community](https://kusanagi.io/app#katana/community).

Support
-------

Please first read our [contribution guidelines](https://kusanagi.io/app#katana/open-source/contributing).

* [Requesting help](https://kusanagi.io/app#katana/open-source/help)
* [Reporting a bug](https://kusanagi.io/app#katana/open-source/bug)
* [Submitting a patch](https://kusanagi.io/app#katana/open-source/patch)
* [Security issues](https://kusanagi.io/app#katana/open-source/security)

We use [milestones](https://github.com/kusanagi/katana-sdk-%LANGUAGE%/milestones) to track upcoming releases inline with our [versioning](https://kusanagi.io/app#katana/versioning) strategy, and as defined in our [roadmap](https://kusanagi.io/app#katana/roadmap).

For commercial support see the [solutions](https://kusanagi.io/solutions) available or [contact us](https://kusanagi.io/contact) for more information.

Contributing
------------

If you'd like to know how you can help and support our Open Source efforts see the many ways to [get involved](https://kusanagi.io/app#katana/open-source/get-involved).

Please also be sure to review our [community guidelines](https://kusanagi.io/app#katana/community/conduct).

License
-------

Copyright 2016-2017 KUSANAGI S.L. (https://kusanagi.io). All rights reserved.

KUSANAGI, the sword logo, KATANA and the "K" logo are trademarks and/or registered trademarks of KUSANAGI S.L. All other trademarks are property of their respective owners.

Licensed under the [MIT License](https://kusanagi.io/app#katana/open-source/license). Redistributions of the source code included in this repository must retain the copyright notice found in each file.
