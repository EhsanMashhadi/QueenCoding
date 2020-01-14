package q2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Util class for some useful methods
 */

class Util {

    /**
     * Converting date to epoch
     *
     * @param date Input date
     */
    static String getEpoch(Date date) {
        return String.valueOf(date.getTime());
    }

    /**
     * Converting date to string
     *
     * @param date Input date
     */
    static String dateToString(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss+SSSS", Locale.US);
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String output = outputFormat.format(date);
        return output;
    }

    /**
     * Converting date to string
     *
     * @param date Input date
     */
    static String dateToStringComment(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat(
                "dd/MMM/yy HH:mm", Locale.US);
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String output = outputFormat.format(date);
        return output;
    }

    /**
     * Converting string to date
     *
     * @param dateString Input date
     */
    static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sourceFormat.parse(dateString);
        return date;
    }

    /**
     * Converting list<String> to string
     *
     * @param list Input list
     */

    static String listToString(List<Comment> list) {
        String finalString = "";
        for (Comment comment : list) {
            finalString += comment.toString() + "\n";
        }
        return finalString;
    }
}
