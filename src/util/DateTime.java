package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime implements Comparable<DateTime> {
    private Date date;
    private int hour;
    private int minute;
    private int second;
    private boolean am;

    public DateTime(Date date, int hour, int minute, int second, boolean am) {
        this.date = date;
        this.hour = hour == 12 ? 0 : hour;
        this.minute = minute;
        this.second = second;
        this.am = am;
    }

    @Override
    public int compareTo(DateTime other) {
        if (this.date.compareTo(other.date) != 0)
            return this.date.compareTo(other.date);
        if (this.am != other.am)
            return this.am ? -1 : 1;
        if (this.hour != other.hour)
            return this.hour - other.hour;
        if (this.minute != other.minute)
            return this.minute - other.minute;
        return this.second - other.second;
    }

    public boolean before(DateTime other) {
        if (this.date.compareTo(other.date) < 0) {
            return true;
        } else if (this.date.compareTo(other.date) == 0) {
            if (this.hour < other.hour) {
                return true;
            } else if (this.hour == other.hour) {
                if (this.minute < other.minute) {
                    return true;
                } else if (this.minute == other.minute) {
                    return this.second < other.second;
                }
            }
        }
        return false;
    }

    public boolean after(DateTime other) {
        if (this.date.compareTo(other.date) > 0) {
            return true;
        } else if (this.date.compareTo(other.date) == 0) {
            if (this.hour > other.hour) {
                return true;
            } else if (this.hour == other.hour) {
                if (this.minute > other.minute) {
                    return true;
                } else if (this.minute == other.minute) {
                    return this.second > other.second;
                }
            }
        }
        return false;
    }

    public static DateTime getCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        boolean am = cal.get(Calendar.AM_PM) == Calendar.AM;

        return new DateTime(currentDate, hour, minute, second, am);
    }

    public static DateTime parse(String dateTimeString) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy @hh:mm:ss a");
        try {
            java.util.Date parsedDate = dateFormat.parse(dateTimeString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);
            Date date = new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            boolean am = cal.get(Calendar.AM_PM) == Calendar.AM;

            return new DateTime(date, hour, minute, second, am);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%s @ %02d:%02d:%02d %s", date, hour == 0 ? 12 : hour, minute, second, am ? "am" : "pm");
    }
}
