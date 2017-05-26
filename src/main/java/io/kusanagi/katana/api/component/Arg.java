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

package io.kusanagi.katana.api.component;

/**
 * Created by jega on 7/03/17.
 */
public class Arg {

    /**
     * short argument key for the version of the KATANA™ Framework.
     */
    public static final String SHORT_FRAMEWORK_VERSION_ARG = "-f";

    /**
     * argument key for the version of the KATANA™ Framework.
     */
    public static final String FRAMEWORK_VERSION_ARG = "--framework-version";

    /**
     * short argument key for the type of the component, either "service" or "middleware".
     */
    public static final String SHORT_COMPONENT_ARG = "-c";

    /**
     * argument key for the type of the component, either "service" or "middleware".
     */
    public static final String COMPONENT_ARG = "--component";

    /**
     * short argument key for the name of the component, as defined in the configuration file.
     */
    public static final String SHORT_NAME_ARG = "-n";

    /**
     * argument key for the name of the component, as defined in the configuration file.
     */
    public static final String NAME_ARG = "--name";

    /**
     * short argument key for the version of the component, as defined in the configuration file.
     */
    public static final String SHORT_VERSION_ARG = "-v";

    /**
     * argument key for the version of the component, as defined in the configuration file.
     */
    public static final String VERSION_ARG = "--version";

    /**
     * short argument key for the socket name that ZeroMQ MUST use to open an IPC socket to receive messages. If
     * this option is present the -t or --tcp option MUST NOT be specified.
     */
    public static final String SHORT_SOCKET_ARG = "-s";

    /**
     * argument key for the socket name that ZeroMQ MUST use to open an IPC socket to receive messages. If this
     * option is present the -t or --tcp option MUST NOT be specified.
     */
    public static final String SOCKET_ARG = "--socket";

    /**
     * short argument key for the port that ZeroMQ MUST use for TCP communication with the framework. If this
     * option is present the -s or --socket option MUST NOT be specified.
     */
    public static final String SHORT_TCP_ARG = "-t";

    /**
     * argument key for the port that ZeroMQ MUST use for TCP communication with the framework. If this option
     * is present the -s or --socket option MUST NOT be specified.
     */
    public static final String TCP_ARG = "--tcp";

    /**
     * short argument key for the variable defined in the configuration file for the engine, and formatted as
     * "name=value". Note that the userland source file MUST accept multiple -V or --var options with varying values.
     * Repeating values SHOULD be ignored.
     */
    public static final String SHORT_VAR_ARG = "-V";

    /**
     * argument key for the variable defined in the configuration file for the engine, and formatted as "name=value".
     * Note that the userland source file MUST accept multiple -V or --var options with varying values. Repeating values
     * SHOULD be ignored.
     */
    public static final String VAR_ARG = "--var";

    /**
     * short argument key for the switch, which if defined MUST enable full property names in all payloads.
     */
    public static final String SHORT_DISABLE_COMPACT_NAMES_ARG = "-d";

    /**
     * argument key for the switch, which if defined MUST enable full property names in all payloads.
     */
    public static final String DISABLE_COMPACT_NAMES_ARG = "--disable-compact-names";

    /**
     * short argument key for the switch, which if defined MUST enable debug mode.
     */
    public static final String SHORT_DEBUG_ARG = "-D";

    /**
     * argument key for the switch, which if defined MUST enable debug mode.
     */
    public static final String DEBUG_ARG = "--debug";

    /**
     * short argument for an OPTIONAL action name to be called in a single execution. If present, a *payload* MUST be provided through *stdin*
     */
    public static final String SHORT_ACTION_ARG = "-A";

    /**
     * An OPTIONAL action name to be called in a single execution. If present, a *payload* MUST be provided through *stdin*
     */
    public static final String ACTION_ARG = "--action";

    /**
     * short argument key for the switch, which if defined MUST disable all logs, even if -D or --debug is present.
     */
    public static final String SHORT_QUIET_ARG = "-q";

    /**
     * argument key for the switch, which if defined MUST disable all logs, even if -D or --debug is present.
     */
    public static final String QUIET_ARG = "--quiet";

    private Arg() {

    }
}
