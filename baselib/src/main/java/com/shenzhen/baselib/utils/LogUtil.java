package com.shenzhen.baselib.utils;

import android.util.Log;

import com.shenzhen.baselib.constant.Constant;


public class LogUtil {
    private static String className;
    private static String methodName;
    private static int lineNumber;

    public static boolean isDebuggable()
    {
        return Constant.isDebug;
    }

    private static String a(String log)
    {
        StringBuffer buffer;
        (
                buffer = new StringBuffer())
                .append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    private static void a(StackTraceElement[] sElements)
    {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message)
    {
        if (!isDebuggable()) {
            return;
        }

        a(new Throwable().getStackTrace());
        Log.e(className, a(message));
    }

    public static void i(String message)
    {
        if (!isDebuggable()) {
            return;
        }
        a(new Throwable().getStackTrace());
        Log.i(className, a(message));
    }

    public static void d(String message)
    {
        if (!isDebuggable()) {
            return;
        }
        a(new Throwable().getStackTrace());
        Log.d(className, a(message));
    }

    public static void v(String message)
    {
        if (!isDebuggable()) {
            return;
        }
        a(new Throwable().getStackTrace());
        Log.v(className, a(message));
    }

    public static void w(String message)
    {
        if (!isDebuggable()) {
            return;
        }
        a(new Throwable().getStackTrace());
        Log.w(className, a(message));
    }

    public static void wtf(String message)
    {
        if (!isDebuggable()) {
            return;
        }
        a(new Throwable().getStackTrace());
        Log.wtf(className, a(message));
    }
}
