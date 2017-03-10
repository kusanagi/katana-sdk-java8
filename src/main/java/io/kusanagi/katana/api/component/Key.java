package io.kusanagi.katana.api.component;

/**
 * Created by jega on 7/03/17.
 */
public class Key {

    //Command Payload
    public static final String COMMAND_META_SCOPE = "s";
    public static final String COMMAND_NAME = "n";
    public static final String COMMAND_ARGUMENT = "a";
    public static final String COMMAND_PAYLOAD_COMMAND_META = "m";
    public static final String COMMAND_PAYLOAD_COMMAND = "c";

    //Command Reply
    public static final String REPLY_PAYLOAD_COMMAND_REPLY = "cr";
    public static final String COMMAND_REPLY_NAME = "n";
    public static final String COMMAND_REPLY_RESULT = "r";
    public static final String CALL_RESULT_REQUEST_CALL = "c";
    public static final String ERROR = "E";
    public static final String RESPONSE_RESULT_HTTP_RESPONSE = "R";
    public static final String RETURN_RESULT_TRANSPORT = "T";
    public static final String RETURN_RESULT_RETURN_OBJECT = "R";
    public static final String TRANSPORT_RESULT_TRANSPORT = "T";
    public static final String TRANSPORT_RESULT_RETURN_OBJECT = "R";

    //Action
    public static final String ACTION_PARAMS = "p";
    public static final String ACTION_TRANSPORT = "T";
    public static final String ACTION_RETURN_OBJECT = "R";

    //Http Action Schema
    public static final String ACTION_HTTP_SCHEMA_GATEWAY = "g";
    public static final String ACTION_HTTP_SCHEMA_PATH = "p";
    public static final String ACTION_HTTP_SCHEMA_METHOD = "m";
    public static final String ACTION_HTTP_SCHEMA_INPUT = "i";
    public static final String ACTION_HTTP_SCHEMA_BODY = "b";

    //Http Param Schema
    public static final String ACTION_PARAM_HTTP_SCHEMA_GATEWAY = "g";
    public static final String ACTION_PARAM_HTTP_SCHEMA_INPUT = "i";
    public static final String ACTION_PARAM_HTTP_SCHEMA_PARAM = "p";

    //Param Action Schema
    public static final String ACTION_PARAM_SCHEMA_NAME = "n";
    public static final String ACTION_PARAM_SCHEMA_TYPE = "t";
    public static final String ACTION_PARAM_SCHEMA_FORMAT = "f";
    public static final String ACTION_PARAM_SCHEMA_ARRAY_FORMAT = "af";
    public static final String ACTION_PARAM_SCHEMA_PATTERN = "p";
    public static final String ACTION_PARAM_SCHEMA_ALLOW_EMPTY = "e";
    public static final String ACTION_PARAM_SCHEMA_DEFAULT_VALUE = "d";
    public static final String ACTION_PARAM_SCHEMA_REQUIRED = "r";
    public static final String ACTION_PARAM_SCHEMA_ITEMS = "i";
    public static final String ACTION_PARAM_SCHEMA_MAX = "mx";
    public static final String ACTION_PARAM_SCHEMA_EXCLUSIVE_MAX = "ex";
    public static final String ACTION_PARAM_SCHEMA_MIN = "mn";
    public static final String ACTION_PARAM_SCHEMA_EXCLUSIVE_MIN = "en";
    public static final String ACTION_PARAM_SCHEMA_MAX_LENGTH = "xl";
    public static final String ACTION_PARAM_SCHEMA_MIN_LENGTH = "nl";
    public static final String ACTION_PARAM_SCHEMA_MAX_ITEMS = "xi";
    public static final String ACTION_PARAM_SCHEMA_MIN_ITEMS = "ni";
    public static final String ACTION_PARAM_SCHEMA_UNIQUE_ITEMS = "ui";
    public static final String ACTION_PARAM_SCHEMA_ENUMERATION = "em";
    public static final String ACTION_PARAM_SCHEMA_MULTIPLE_OF = "mo";
    public static final String ACTION_PARAM_SCHEMA_HTTP = "h";

    // Action Schema
    public static final String ACTION_SCHEMA_TIMEOUT = "x";
    public static final String ACTION_SCHEMA_ENTITY_PATH = "e";
    public static final String ACTION_SCHEMA_PATH_DELIMITER = "d";
    public static final String ACTION_SCHEMA_PRIMARY = "k";
    public static final String ACTION_SCHEMA_COLLECTION = "c";
    public static final String ACTION_SCHEMA_CALLS = "C";
    public static final String ACTION_SCHEMA_DEFERRED_CALLS = "dc";
    public static final String ACTION_SCHEMA_REMOTE_CALLS = "rc";
    public static final String ACTION_SCHEMA_FALLBACKS = "F";
    public static final String ACTION_SCHEMA_DEPRECATED = "D";
    public static final String ACTION_SCHEMA_HTTP = "h";
    public static final String ACTION_SCHEMA_PARAMS = "p";
    public static final String ACTION_SCHEMA_FILES = "f";
    public static final String ACTION_SCHEMA_ENTITY = "E";
    public static final String ACTION_SCHEMA_RELATIONS = "r";
    public static final String ACTION_SCHEMA_RETURN_OBJECT = "R";

    //Call
    public static final String CALL_NAME = "n";
    public static final String CALL_VERSION = "v";
    public static final String CALL_ACTION = "a";
    public static final String CALL_CALLER = "C";
    public static final String CALL_GATEWAY = "g";
    public static final String CALL_TIMEOUT = "t";
    public static final String CALL_PARAMS = "p";

    //Callee
    public static final String CALLEE_ACTION = "action";
    public static final String CALLEE_CALLEE_INFO = "callee";
    public static final String CALLEE_TRANSPORT = "transport";
    public static final String CALLEE_PARAM = "params";
    public static final String CALLEE_FILES = "files";

    //Entity Schema
    public static final String ENTITY_SCHEMA_FIELD = "f";
    public static final String ENTITY_SCHEMA_FIELDS = "F";
    public static final String ENTITY_SCHEMA_VALIDATE = "V";

    //Error
    public static final String ERROR_MESSAGE = "m";
    public static final String ERROR_CODE = "c";
    public static final String ERROR_STATUS = "s";

    //Field Schema
    public static final String FIELD_SCHEMA_NAME = "n";
    public static final String FIELD_SCHEMA_TYPE = "t";
    public static final String FIELD_SCHEMA_OPTIONAL = "o";

    //File
    public static final String FILE_PATH = "p";
    public static final String FILE_MIME = "m";
    public static final String FILE_FILENAME = "f";
    public static final String FILE_SIZE = "s";
    public static final String FILE_TOKEN = "t";

    //Http File Schema
    public static final String FILE_HTTP_SCHEMA_GATEWAY = "g";
    public static final String FILE_HTTP_SCHEMA_PARAM = "p";

    //File Schema
    public static final String FILE_SCHEMA_MIME = "m";
    public static final String FILE_SCHEMA_REQUIRED = "r";
    public static final String FILE_SCHEMA_MAX = "mx";
    public static final String FILE_SCHEMA_EXCLUSIVE_MAX = "ex";
    public static final String FILE_SCHEMA_MIN = "mn";
    public static final String FILE_SCHEMA_EXCLUSIVE_MIN = "en";
    public static final String FILE_SCHEMA_HTTP = "h";

    //Http Request
    public static final String HTTP_REQUEST_PROTOCOL_VERSION = "v";
    public static final String HTTP_REQUEST_METHOD = "m";
    public static final String HTTP_REQUEST_URL = "u";
    public static final String HTTP_REQUEST_QUERY_PARAMS_ARRAY = "q";
    public static final String HTTP_REQUEST_POST_PARAMS_ARRAY = "p";
    public static final String HTTP_REQUEST_HEADERS = "h";
    public static final String HTTP_REQUEST_BODY = "b";
    public static final String HTTP_REQUEST_FILES = "f";

    //Http Response
    public static final String HTTP_RESPONSE_PROTOCOL_VERSION = "v";
    public static final String HTTP_RESPONSE_STATUS = "s";
    public static final String HTTP_RESPONSE_HEADERS = "h";
    public static final String HTTP_RESPONSE_BODY = "b";

    //Http Schema
    public static final String HTTP_SCHEMA_GATEWAY = "g";
    public static final String HTTP_SCHEMA_BASE_PATH = "b";

    //Meta
    public static final String META_VERSION = "v";
    public static final String META_ID = "i";
    public static final String META_DATETIME = "d";
    public static final String META_TYPE = "t";
    public static final String META_PROTOCOL = "p";
    public static final String META_GATEWAY = "g";
    public static final String META_CLIENT = "c";

    //Object Field Schema
    public static final String OBJECT_FIELD_SCHEMA_NAME = "n";
    public static final String OBJECT_FIELD_SCHEMA_OPTIONAL = "o";
    public static final String OBJECT_FIELD_SCHEMA_FIELD = "f";
    public static final String OBJECT_FIELD_SCHEMA_FIELDS = "F";

    //Param
    public static final String PARAM_NAME = "n";
    public static final String PARAM_VALUE = "v";
    public static final String PARAM_TYPE = "t";

    //Relation Schema
    public static final String RELATION_SCHEMA_NAME = "n";
    public static final String RELATION_SCHEMA_TYPE = "t";

    //Request
    public static final String REQUEST_META = "m";
    public static final String REQUEST_HTTP_REQUEST = "r";
    public static final String REQUEST_REQUEST_CALL = "c";

    //Request Call
    public static final String REQUEST_CALL_SERVICE = "s";
    public static final String REQUEST_CALL_VERSION = "v";
    public static final String REQUEST_CALL_ACTION = "a";
    public static final String REQUEST_CALL_PARAMS = "p";

    //Response Meta
    public static final String RESPONSE_META = "m";
    public static final String RESPONSE_HTTP_REQUEST = "r";
    public static final String RESPONSE_HTTP_RESPONSE = "R";
    public static final String RESPONSE_TRANSPORT = "T";

    //Return Schema
    public static final String RETURN_SCHEMA_TYPE = "t";
    public static final String RETURN_SCHEMA_ALLOW_EMPTY = "e";

    //Service Schema
    public static final String SERVICE_SCHEMA_ADDRESS = "a";
    public static final String SERVICE_SCHEMA_FILES = "f";
    public static final String SERVICE_SCHEMA_HTTP_SCHEMA = "h";
    public static final String SERVICE_SCHEMA_ACTION_SCHEMAS = "ac";

    //Service Transaction
    public static final String SERVICE_TRANSACTION_NAME = "n";
    public static final String SERVICE_TRANSACTION_VERSION = "v";
    public static final String SERVICE_TRANSACTION_ACTION = "a";
    public static final String SERVICE_TRANSACTION_CALLER = "c";
    public static final String SERVICE_TRANSACTION_PARAMS = "p";

    //Transaction
    public static final String TRANSACTION_COMMIT = "c";
    public static final String TRANSACTION_ROLLBACK = "r";
    public static final String TRANSACTION_COMPLETE = "C";

    //Transport
    public static final String TRANSPORT_META = "m";
    public static final String TRANSPORT_BODY = "b";
    public static final String TRANSPORT_FILES = "f";
    public static final String TRANSPORT_DATA = "d";
    public static final String TRANSPORT_RELATIONS = "r";
    public static final String TRANSPORT_LINKS = "l";
    public static final String TRANSPORT_CALLS = "C";
    public static final String TRANSPORT_TRANSACTIONS = "t";
    public static final String TRANSPORT_ERRORS = "e";
    public static final String TRANSPORT_META_VERSION = "v";
    public static final String TRANSPORT_META_ID = "i";
    public static final String TRANSPORT_META_DATETIME = "d";
    public static final String TRANSPORT_META_GATEWAY = "g";
    public static final String TRANSPORT_META_ORIGIN = "o";
    public static final String TRANSPORT_META_LEVEL = "l";
    public static final String TRANSPORT_META_FALLBACK = "f";
    public static final String TRANSPORT_META_PROPERTIES = "p";

    //Transport Schema
    public static final String TRANSPORT_SCHEMA_PROPERTIES = "p";
    public static final String TRANSPORT_SCHEMA_DATA = "d";
    public static final String TRANSPORT_SCHEMA_RELATIONS = "r";
    public static final String TRANSPORT_SCHEMA_LINKS = "l";
    public static final String TRANSPORT_SCHEMA_ERRORS = "e";

    //Value Schema
    public static final String VALUE_SCHEMA_TYPE = "t";
    public static final String VALUE_SCHEMA_VALUE = "v";
    public static final String VALUE_SCHEMA_ITEMS = "i";

    private Key() {
        // private constructor to block the instantiation of this object
    }
}
