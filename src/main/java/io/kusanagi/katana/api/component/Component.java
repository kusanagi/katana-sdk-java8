package io.kusanagi.katana.api.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.commands.Mapping;
import io.kusanagi.katana.api.commands.common.CommandPayload;
import io.kusanagi.katana.api.component.utils.Factory;
import io.kusanagi.katana.api.component.utils.Logger;
import io.kusanagi.katana.api.component.utils.Option;
import io.kusanagi.katana.api.component.utils.OptionManager;
import io.kusanagi.katana.api.replies.ErrorPayload;
import io.kusanagi.katana.api.replies.common.CommandReplyResult;
import io.kusanagi.katana.sdk.ActionSchema;
import io.kusanagi.katana.sdk.Callable;
import io.kusanagi.katana.sdk.Error;
import io.kusanagi.katana.sdk.ServiceSchema;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.*;

/**
 * Created by juan on 27/08/16.
 * Katana Java SDK
 */

/**
 * @param <T>
 * @param <S>
 */
public abstract class Component<T extends Api, S extends CommandReplyResult, R extends Component> implements ComponentWorker.WorkerListener {


    protected static final Option[] APP_OPTIONS = new Option[]{
            new Option(new String[]{Arg.SHORT_FRAMEWORK_VERSION_ARG, Arg.FRAMEWORK_VERSION_ARG}, true, true, true),
            new Option(new String[]{Arg.SHORT_COMPONENT_ARG, Arg.COMPONENT_ARG}, true, true, true),
            new Option(new String[]{Arg.SHORT_NAME_ARG, Arg.NAME_ARG}, true, true, true),
            new Option(new String[]{Arg.SHORT_VERSION_ARG, Arg.VERSION_ARG}, true, true, true),
            new Option(new String[]{Arg.SHORT_SOCKET_ARG, Arg.SOCKET_ARG}, true, false, true),
            new Option(new String[]{Arg.SHORT_TCP_ARG, Arg.TCP_ARG}, true, false, true),
            new Option(new String[]{Arg.SHORT_VAR_ARG, Arg.VAR_ARG}, false, false, true),
            new Option(new String[]{Arg.SHORT_DISABLE_COMPACT_NAMES_ARG, Arg.DISABLE_COMPACT_NAMES_ARG}, true, false, false),
            new Option(new String[]{Arg.SHORT_DEBUG_ARG, Arg.DEBUG_ARG}, true, false, false),
            new Option(new String[]{Arg.SHORT_CALLBACK_ARG, Arg.CALLBACK_ARG}, true, false, true),
            new Option(new String[]{Arg.SHORT_QUIET_ARG, Arg.QUIET_ARG}, true, false, false),
    };

    private final String workerEndpoint;

    private String componentName;

    private boolean disableCompactName;

    private String name;

    private String version;

    private String frameworkVersion;

    private String socket;

    private String tcp;

    private boolean debug;

    private Map<String, String> var;

    private String callback;

    private boolean quiet;

    private Map<String, Callable<T>> resources;

    protected EventCallable<R> startupCallable;

    protected EventCallable<R> shutdownCallable;

    protected EventCallable<R> errorCallable;

    protected List<ComponentWorker> workers;

    private ZMQ.Socket router;

    private ZMQ.Context context;

    private Serializer serializer;

    private ZMQ.Socket dealer;

    private OptionManager optionManager;

    private boolean stopped;

    /**
     * Initialize the componentName with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Component(String[] args) {
        this.var = new HashMap<>();
        this.resources = new HashMap<>();
        this.serializer = Factory.getSerializer();
        this.optionManager = Factory.getOptionManager();
        this.optionManager.setOptions(Arrays.asList(APP_OPTIONS));
        this.workers = new ArrayList<>();

        setArgs(args);

        if (this.tcp == null && this.socket == null) {
            generateDefaultSocket();
        }

        if (isDebug() && !this.quiet) {
            Logger.activate();
        } else {
            Logger.deactivate();
        }

        this.workerEndpoint = String.format(Constants.WORKER_ENDPOINT_STRING, Constants.WORKER_ENDPOINT, UUID.randomUUID().toString());
    }

    public ZMQ.Context getContext() {
        return context;
    }

    public String getComponent() {
        return componentName;
    }

    public void setComponent(String component) {
        this.componentName = component;
    }

    public boolean isDisableCompactName() {
        return disableCompactName;
    }

    public void setDisableCompactName(boolean disableCompactName) {
        this.disableCompactName = disableCompactName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFrameworkVersion() {
        return frameworkVersion;
    }

    public void setFrameworkVersion(String frameworkVersion) {
        this.frameworkVersion = frameworkVersion;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getTcp() {
        return tcp;
    }

    public void setTcp(String tcp) {
        this.tcp = tcp;
    }

    public Map<String, String> getVar() {
        return var;
    }

    public void setVar(Map<String, String> var) {
        this.var = var;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public boolean isQuiet() {
        return quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    // SDK METHODS

    /**
     * take a function which executes and returns a resource to store under the REQUIRED case sensitive name argument.
     * If the function does not return a value it MUST be treated as an error.
     *
     * @param name     resource name
     * @param resource resource function
     * @return true
     */
    public boolean setResource(String name, Callable<T> resource) {
        this.resources.put(name, resource);
        return true;
    }

    /**
     * determine if a resource has been stored under the REQUIRED case sensitive name argument.
     *
     * @param name resource name
     * @return true if the resource exist
     */
    public boolean hasResource(String name) {
        return this.resources.containsKey(name);
    }

    /**
     * return the resource stored under the REQUIRED case sensitive name argument. If a resource is not stored under
     * the specified name it MUST be treated as an error.
     *
     * @param name resource name
     * @return the resource stored under the REQUIRED case sensitive name argument
     */
    public Callable<T> getResource(String name) {
        return this.resources.get(name);
    }

    /**
     * take a function, which SHOULD be executed upon first running the userland source file, and return the instance of
     * the object.
     * <p>
     * The instance of the Component class MUST be provided as the first argument of the callback function, while any
     * value returned by the callback function MUST be ignored.
     *
     * @param callback callback function
     * @return the component
     */
    public Component<T, S, R> startup(EventCallable<R> callback) {
        this.startupCallable = callback;
        return this;
    }

    /**
     * take a function, which SHOULD be executed if the SDK receives a signal to terminate its process, and return the
     * instance of the object.
     * <p>
     * The instance of the Component class MUST be provided as the first argument of the callback function, while any
     * value returned by the callback function MUST be ignored.
     *
     * @param callback callback function
     * @return the component
     */
    public Component<T, S, R> shutdown(EventCallable<R> callback) {
        this.shutdownCallable = callback;
        return this;
    }

    /**
     * function MUST take a function, which SHOULD be executed whenever an error is thrown or returned from a callback
     * when processing a message in userland, and return the instance of the object.
     * <p>
     * The instance of the error object caught or returned MUST be provided as the first argument of the callback
     * function. The type of object SHOULD be the most acceptable to the implementation language. No return value is
     * expected from this callback function, and any value returned SHOULD be ignored.
     *
     * @param callback callback function
     * @return the component
     */
    public Component<T, S, R> error(EventCallable<R> callback) {
        this.errorCallable = callback;
        return this;
    }

    /**
     * This is where ZeroMQ and MessagePack are implemented, and the long running process initialized to receive incoming
     * messages.
     * <p>
     * Upon executing the userland source code any callback functions registered MUST be stored so they MAY be referenced
     * by either "request" or "response" in the case of Middleware, or by the specific action name in the case of a
     * Service. This reference SHOULD then be used to effectively route messages received by the SDK to their relevant
     * callback function.
     */
    public void run() {
        startSocket();

        setWorkers();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (shutdownCallable != null) {
                runShutdown();
            }

            if (!stopped) {
                stopSocket();
            }
        }));

        ZMQ.proxy(router, dealer, null);
    }

    /**
     * send a string representation of the value argument to stdout as a "DEBUG" log, with a length limit on the value
     * of 100,000 characters (not including the other elements of the log message, such as the timestamp), and return
     * true. If the component is not running in debug mode this function MUST NOT send a log, and SHOULD return false.
     *
     * @param value String to log
     * @return true if the value gets logged
     */
    public boolean log(String value) {
        Logger.log(Logger.DEBUG, value);
        return this.debug;
    }

    protected abstract void runShutdown();

    private void setWorkers() {
        int workerCount = 1;
        if (this.var.containsKey(Constants.WORKERS)) {
            workerCount = Integer.valueOf(this.var.get(Constants.WORKERS));
            workerCount = workerCount < 1 ? 1 : workerCount;
        }

        for (int i = 0; i < workerCount; i++) {
            ComponentWorker componentWorker = new ComponentWorker(workerEndpoint);
            workers.add(componentWorker);
            componentWorker.setWorkerListener(this);
            componentWorker.start();
        }
    }

    private void startSocket() {
        context = ZMQ.context(1);
        router = context.socket(ZMQ.ROUTER);
        dealer = context.socket(ZMQ.DEALER);
        bindSocket();
    }

    private void bindSocket() {
        if (this.tcp != null) {
//            router.bind(String.format(TCP_HOST_STRING, TCP, System.getProperty("katanaip"), this.tcp));
            router.bind(String.format(Constants.TCP_HOST_STRING, Constants.TCP, "127.0.0.1", this.tcp));
        } else {
            router.bind(String.format(Constants.IPC_HOST_STRING, Constants.IPC, this.socket));
        }

        dealer.bind(this.workerEndpoint);
    }

    public void stopSocket() {
        for (ComponentWorker worker : this.workers) {
            worker.stopSocket();
        }

        dealer.close();
        router.close();
//        context.term();

        this.stopped = true;
    }

    private void generateDefaultSocket() {
        this.socket = String.format(Constants.KATANA_DEFAULT_SOCKET_STRING, this.componentName, this.name, this.version);
    }

    /**
     * @param componentType
     * @param commandBytes
     * @return
     */
    @Override
    public byte[][] onRequestReceived(String componentType, byte[] mappings, byte[] commandBytes) {
        try {
            CommandPayload<T> command = serializer.deserialize(commandBytes, getCommandPayloadClass(componentType));
            Mapping mapping = deserializeMappings(mappings);
            S commandReply = processRequest(componentType, mapping, command);
            return new byte[][]{getReplyMetadata(commandReply), serializer.serializeInBytes(commandReply)};
        } catch (Exception e) {
            Logger.log(e);
            runErrorCallback();
            try {
                return new byte[][]{new byte[]{0x00}, serializer.serializeInBytes(getErrorPayload(e))};
            } catch (JsonProcessingException e1) {
                Logger.log(e1);
                return new byte[][]{new byte[]{0x00}, new byte[0]};
            }
        }
    }

    protected abstract void runErrorCallback();

    public static ErrorPayload getErrorPayload(Exception e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setCode(1);
        error.setStatus(Constants.INTERNAL_SERVER_ERROR_STATUS);

        ErrorPayload errorPayload = new ErrorPayload();
        errorPayload.setError(error);
        return errorPayload;
    }

    private Mapping deserializeMappings(byte[] mappings) throws IOException {
        if (mappings == null) {
            return null;
        }

        Mapping mapping = new Mapping();

        Map<String, Object> schemas = serializer.deserialize(mappings, Map.class);
        for (Map.Entry serviceKey : schemas.entrySet()) {
            Map<String, Object> versionMap = (Map) schemas.get((String) serviceKey.getKey());
            for (Map.Entry versionKey : versionMap.entrySet()) {
                Map<String, Object> serviceSchemaMap = (Map) versionMap.get((String) versionKey.getKey());

                String jsonServiceSchema = serializer.serializeInJson(serviceSchemaMap);
                ServiceSchema serviceSchema = serializer.deserialize(jsonServiceSchema, ServiceSchema.class);
                serviceSchema.setName((String) serviceKey.getKey());
                serviceSchema.setVersion((String) versionKey.getKey());
                for (String action : serviceSchema.getActions()) {
                    ActionSchema actionSchema = serviceSchema.getActionSchema(action);
                    actionSchema.setName(action);
                    for (Map.Entry param : actionSchema.getParams().entrySet()) {
                        actionSchema.getParamSchema((String) param.getKey()).setName((String) param.getKey());
                    }
                }

                Map<String, ServiceSchema> newVersionMap = new HashMap<>();
                Map<String, Map<String, ServiceSchema>> newServiceSchema = new HashMap<>();

                newVersionMap.put((String) versionKey.getKey(), serviceSchema);
                newServiceSchema.put((String) serviceKey.getKey(), newVersionMap);

                mapping.setServiceSchema(newServiceSchema);
            }
        }

        return mapping;
    }

    /**
     * @param componentType
     * @return
     */
    protected abstract Class<? extends CommandPayload> getCommandPayloadClass(String componentType);

    /**
     * @param componentType
     * @param command
     */
    protected void setBaseCommandAttrs(String componentType, Mapping mapping, T command) {
        command.setComponent(this);
        command.setName(this.getName());
        command.setProtocolVersion(this.getVersion());
        command.setPlatformVersion(this.getFrameworkVersion());
        command.setDebug(this.isDebug());
        command.setVariables(this.getVar());
        command.setMapping(mapping);
    }

    /**
     * @param componentType
     * @param response
     * @return
     */
    protected abstract S getCommandReplyPayload(String componentType, T response);

    /**
     * @param response
     * @return
     */
    protected abstract CommandReplyResult getReply(String componentType, T response);

    private S processRequest(String componentType, Mapping mapping, CommandPayload<T> commandPayload) {
        T command = commandPayload.getCommand().getArgument();
        setBaseCommandAttrs(componentType, mapping, command);
        getCallable(componentType).run(command);

        return getCommandReplyPayload(componentType, command);
    }

    protected abstract byte[] getReplyMetadata(S reply);

    protected abstract Callable<T> getCallable(String componentType);

    private void setArgs(String[] args) throws IllegalArgumentException {
        List<Option> currentOptions = optionManager.extractOptions(args);
        setMembers(currentOptions);
    }

    private void setMembers(List<Option> options) {
        for (Option option : options) {
            switch (option.getNames()[0]) {
                case Arg.SHORT_FRAMEWORK_VERSION_ARG:
                    this.frameworkVersion = option.getValue();
                    if (!this.frameworkVersion.matches(Constants.VERSION_PATTERN)) {
                        throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_FRAMEWORK_VERSION, this.frameworkVersion));
                    }
                    break;
                case Arg.SHORT_COMPONENT_ARG:
                    this.componentName = option.getValue();
                    if (!this.componentName.equals(Constants.SERVICE) && !this.componentName.equals(Constants.MIDDLEWARE)) {
                        throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_COMPONENT_NAME, this.componentName));
                    }
                    break;
                case Arg.SHORT_NAME_ARG:
                    this.name = option.getValue();
                    break;
                case Arg.SHORT_VERSION_ARG:
                    this.version = option.getValue();
                    if (!this.version.matches(Constants.VERSION_PATTERN)) {
                        throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_VERSION, this.version));
                    }
                    break;
                case Arg.SHORT_SOCKET_ARG:
                    this.socket = option.getValue();
                    break;
                case Arg.SHORT_TCP_ARG:
                    this.tcp = option.getValue();
                    break;
                case Arg.SHORT_VAR_ARG:
                    String[] varObject = option.getValue().split("=");
                    if (varObject.length < 2) {
                        throw new IllegalArgumentException(String.format(ExceptionMessage.INVALID_VARIABLE, option.getValue()));
                    }
                    String varName = varObject[0];
                    String varValue = varObject[1];
                    this.var.put(varName, varValue);
                    break;
                case Arg.SHORT_DISABLE_COMPACT_NAMES_ARG:
                    this.disableCompactName = true;
                    break;
                case Arg.SHORT_DEBUG_ARG:
                    this.debug = true;
                    break;
                case Arg.SHORT_CALLBACK_ARG:
                    this.callback = option.getValue();
                    break;
                case Arg.SHORT_QUIET_ARG:
                    this.quiet = true;
                    break;
                default:
                    Logger.log(Logger.ERROR, String.format(ExceptionMessage.UNSUPPORTED_PARAMETER, option.getNames()[0]));
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Component{" +
                "componentName='" + componentName + '\'' +
                ", disableCompactName=" + disableCompactName +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", frameworkVersion='" + frameworkVersion + '\'' +
                ", socket='" + socket + '\'' +
                ", " + Constants.TCP + "='" + tcp + '\'' +
                ", debug=" + debug +
                ", var=" + var +
                ", callback=" + callback +
                ", quiet=" + quiet +
                '}';
    }
}
