package src;

import util.DateTime;

public class Event implements Comparable<Event> {
    private String eventName;
    private DateTime startDate;
    private DateTime endDate;
    private String host;
    private int invitees;
    private String location;

    public Event(String eventName, DateTime startDate, DateTime endDate, String host, int invitees, String location) {
        if (endDate.before(startDate) || startDate.after(endDate)) {
            throw new IllegalArgumentException("End date cannot be before the start date.");
        }
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.host = host;
        this.invitees = invitees;
        this.location = location;
    }

    public String getEventName() {
        return eventName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public String getHost() {
        return host;
    }

    public int getInvitees() {
        return invitees;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public int compareTo(Event other) {
        int startComparison = this.startDate.compareTo(other.startDate);
        if (startComparison != 0) {
            return startComparison;
        }
        int thisDuration = this.endDate.compareTo(this.startDate);
        int otherDuration = other.endDate.compareTo(other.startDate);
        return otherDuration - thisDuration;
    }

    @Override
    public String toString() {
        return eventName + " - " + startDate + " to " + endDate + " - Hosted by: " + host + " - Invitees: " + invitees + " - Location: " + location;
    }
}
