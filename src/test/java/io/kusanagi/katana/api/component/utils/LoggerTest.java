/*
 * Java 7 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java7
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.api.component.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by juane on 2/11/17.
 */
public class LoggerTest {

    public static final SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        Logger.activate();
        Logger.setId(null);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
    }

    private String getMessage(String[] split) {
        String message = "";
        if (split.length >= 4) {
            for (int j = 3; j < split.length; j++) {
                if (j == 3) {
                    message = split[j];
                } else {
                    message += " " + split[j];
                }
            }
        }
        return message;
    }

    private void assertCommonValues(String[] split, String message) throws ParseException {
        Date date = STANDARD_DATE_FORMAT.parse(split[0]);
        assertEquals(date.getTime(), Calendar.getInstance().getTimeInMillis(), 1000);
        assertEquals("[SDK]", split[2]);
        assertEquals(message, getMessage(split));
    }

    @Test
    public void log_debug_debugFormatted() throws ParseException {
        Logger.log(Logger.DEBUG, "message");

        String[] errors = outContent.toString().split("\n");
        String[] split = errors[0].split(" ");

        assertEquals("[DEBUG]", split[1]);
        assertCommonValues(split, "message");
    }

    @Test
    public void log_info_InfoFormatted() throws ParseException {
        Logger.log(Logger.INFO, "message");

        String[] errors = outContent.toString().split("\n");
        String[] split = errors[0].split(" ");

        assertEquals("[INFO]", split[1]);
        assertCommonValues(split, "message");
    }

    @Test
    public void log_warning_warningFormatted() throws ParseException {
        Logger.log(Logger.WARNING, "message");

        String[] errors = outContent.toString().split("\n");
        String[] split = errors[0].split(" ");

        assertEquals("[WARNING]", split[1]);
        assertCommonValues(split, "message");
    }

    @Test
    public void log_error_errorFormatted() throws ParseException {
        Logger.log(Logger.ERROR, "message");

        String[] errors = outContent.toString().split("\n");
        String[] split = errors[0].split(" ");

        assertEquals("[ERROR]", split[1]);
        assertCommonValues(split, "message");
    }

    @Test
    public void log_exception_errorFormatted() throws ParseException {
        NullPointerException e = new NullPointerException("message");

        Logger.log(e);

        String[] errors = outContent.toString().split("\n");
        String[] split = errors[0].split(" ");
        assertEquals("[ERROR]", split[1]);
        assertCommonValues(split, "message");

        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 1; i <= stackTrace.length; i++) {
            split = errors[i].split(" ");
            assertEquals("[ERROR]", split[1]);
            assertCommonValues(split, stackTrace[i - 1].toString());
        }
    }

}