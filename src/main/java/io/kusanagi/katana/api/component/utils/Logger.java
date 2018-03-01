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

package io.kusanagi.katana.api.component.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by juan on 30/09/16.
 */
public class Logger {

    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
    public static final int DEBUG = 4;

    public static final String STANDARD_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String LOG_PATTERN = "%s %s (%s) [%s] [SDK] %s";

    private static boolean isActive;

    private static String id;
    private static String component;
    private static String componentName;
    private static String version;
    private static String frameworkVersion;

    private Logger() {
        // private constructor to block the instantiation of this object
    }

    public static void activate() {
        isActive = true;
    }

    public static void deactivate() {
        isActive = false;
    }

    public static void setId(String id) {
        Logger.id = id;
    }

    public static void setComponent(String component) {
        Logger.component = component;
    }

    public static void setComponentName(String componentName) {
        Logger.componentName = componentName;
    }

    public static void setVersion(String version) {
        Logger.version = version;
    }

    public static void setFrameworkVersion(String frameworkVersion) {
        Logger.frameworkVersion = frameworkVersion;
    }

    public static void log(int type, String message) {
        if (isActive) {
            logToStdout(getLog(type, message));
        }
    }

    public static void log(Exception e) {
        log(Logger.ERROR, e.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            log(Logger.ERROR, stackTraceElement.toString());
        }
    }

    private static String getLog(int type, String message) {
        SimpleDateFormat standardDateFormat = new SimpleDateFormat(STANDARD_DATE_PATTERN);
        Date date = Calendar.getInstance().getTime();

        String time = standardDateFormat.format(date);
        return String.format(LOG_PATTERN,
                time,
                component + " " +componentName+"/"+version,
                frameworkVersion,
                getType(type),
                message + (id == null ? "" : " |" + id + "|")
        );
    }

    private static String getType(int type) {
        switch (type) {
            case WARNING:
                return "WARNING";
            case ERROR:
                return "ERROR";
            case DEBUG:
                return "DEBUG";
            default:
                return "INFO";
        }
    }

    private static void logToStdout(String message) {
//        java.util.logging.Logger.getGlobal().log(Level.INFO, message);
        System.out.println(message);
    }
}
