package marketplace.selfapps.rav.marketplace.utils;

import android.content.Context;

/**
 * Universal log portal
 * For enable / disable logging need to change logging value
 *
 */

public class Logs {
    private static boolean logging = true; //To disable logging need to change logging value

    /**
     * Send debug level messages
     * @param context - need to get Class name for show it in log header
     * @param message - text which you want to show
     */
    public static void log(Context context, String message){
        if(logging){
            String tag = context.getClass().getName();
            android.util.Log.d(tag, message);
        }
    }


    /**
     * Send info level messages
     * @param context - need to get Class name for show it in log header
     * @param message - text which you want to show
     */
    public static void logInfo(Context context,String message){
        if(logging){
            String tag = context.getClass().getName();
            android.util.Log.i(tag, message);
        }
    }

    /**
     * Send error level messages
     * @param context - need to get Class name for show it in log header
     * @param e
     * @param message - text which you want to show
     */
    public static void logError(Context context, Exception e, String message){
        if(logging){
            String tag = context.getClass().getName();
            if(e == null) android.util.Log.e(tag, message);
            else if(message == null) android.util.Log.e(tag, e.getMessage(), e.getCause());
            else android.util.Log.e(tag, message, e.getCause());
        }
    }

    /**
     * Send debug level messages
     * @param clz - need to get Class name for show it in log header
     * @param message - text which you want to show
     */
    public static void log(Class clz, String message){
        if(logging){
            String tag = clz.getName();
            android.util.Log.d(tag, message);
        }
    }


    /**
     * Send info level messages
     * @param clz - need to get Class name for show it in log header
     * @param message - text which you want to show
     */
    public static void logInfo(Class clz,String message){
        if(logging){
            String tag = clz.getName();
            android.util.Log.i(tag, message);
        }
    }

    /**
     * Send error level messages
     * @param clz - need to get Class name for show it in log header
     * @param e
     * @param message - text which you want to show
     */
    public static void logError(Class clz, Exception e, String message){
        if(logging){
            String tag = clz.getName();
            if(e == null) android.util.Log.e(tag, message);
            else if(message == null) android.util.Log.e(tag, e.getMessage(), e.getCause());
            else android.util.Log.e(tag, message, e.getCause());
        }
    }
}
