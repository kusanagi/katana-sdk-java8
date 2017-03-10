package io.kusanagi.katana.api.component;

/**
 * Created by jega on 7/03/17.
 */
public class ExceptionMessage {

    //Errors
    public static final String INVALID_FRAMEWORK_VERSION = "Invalid framework version %s";
    public static final String INVALID_COMPONENT_NAME = "Invalid componentName %s";
    public static final String INVALID_VERSION = "Invalid version %s";
    public static final String INVALID_VARIABLE = "Invalid variable %s";
    public static final String UNSUPPORTED_PARAMETER = "Unsupported parameter %s";
    public static final String FILE_SERVER_NOT_CONFIGURED = "File server not configured: \"%s\" (%s)";
    public static final String CALL_NOT_CONFIGURED = "Call not configured, connection to action on \"%s\" (%s) aborted: \"%s\"";
    public static final String CANNOT_REFERENCE_LOCAL_FILE = "Cannot reference local file, connection to action on \"%s\" (%s) aborted: \"%s\"";
    public static final String REMOTE_CALL_NOT_CONFIGURED = "Remote call not configured, connection to action on [\"%s\"] \"%s\" (%s) aborted: \"%s\"";
    public static final String CANNOT_SET_A_RETURN_VALUE = "Cannot set a return value in \"%s\" (%s) for action: \"%s\"";
    public static final String INVALID_RETURN_TYPE = "Invalid return type given in %s %s for action: %s";
    public static final String DEFERRED_CALL_NOT_CONFIGURED = "Deferred call not configured, connection to action on \"%s\" (%s) aborted: \"%s\"";
    public static final String CANNOT_RESOLVE_ENTITY = "Cannot resolve entity for action: %s";
    public static final String CANNOT_RESOLVE_SCHEMA_FOR_PARAMETER = "Cannot resolve schema for parameter: %s";
    public static final String CANNOT_RESOLVE_SCHEMA_FOR_FILE = "Cannot resolve schema for file: %s";
    public static final String FILE_DOES_NOT_EXIST_IN_PATH = "File does not exist in path: %s";
    public static final String CANNOT_RESOLVE_SCHEMA_FOR_ACTION = "Cannot resolve schema for action: %s";
    public static final String CANNOT_RESOLVE_SCHEMA_FOR_SERVICE = "Cannot resolve schema for service: %s (%s)";

    private ExceptionMessage() {
        // private constructor to block the instantiation of this object
    }
}
