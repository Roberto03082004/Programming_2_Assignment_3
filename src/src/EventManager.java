package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import util.DateTime;

public class EventManager {
    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String eventName = "";
            boolean validName = false;
            String host = "";
            String location = "";


            DateTime startDateTime = null;
            DateTime endDateTime = null;

            while (!validName) {
                System.out.print("Enter event name: ");
                eventName = scanner.nextLine();
                if (eventName.contains(",")) {
                    System.out.println("Error: Event name cannot contain a comma (,). Please re-enter.");
                } else {
                    validName = true; // Exit the loop only if the input is valid
                }
            }


            while (true) {
                startDateTime = getValidDateTime("Enter start date and time (MM/DD/YYYY @hh:mm:ss am/pm): ", scanner);
                endDateTime = getValidDateTime("Enter end date and time (MM/DD/YYYY @hh:mm:ss am/pm): ", scanner);

                if (endDateTime != null && startDateTime != null) {
                    // Check if the end date and time are before the start date and time
                    if (endDateTime.before(startDateTime)) {
                        System.out.println("Error: End date and time must not be before the start date and time. Please re-enter.");
                    } else {
                        break;
                    }
                }
            }

            // Input validation for host
            boolean validHost = false;
            while (!validHost) {
                System.out.print("Enter host: ");
                host = scanner.nextLine();
                if (host.contains(",")) {
                    System.out.println("Error: Host name cannot contain a comma (,). Please re-enter.");
                } else {
                    validHost = true; // Exit the loop only if the input is valid
                }
            }

            System.out.print("Enter number of invitees: ");
            int invitees = getValidInt(scanner);

            // Input validation for location
            boolean validLocation = false;
            while (!validLocation) {
                System.out.print("Enter location: ");
                location = scanner.nextLine();
                if (location.contains(",")) {
                    System.out.println("Error: Location cannot contain a comma (,). Please re-enter.");
                } else {
                    validLocation = true; // Exit the loop only if the input is valid
                }
            }

            try {
                Event event = new Event(eventName, startDateTime, endDateTime, host, invitees, location);
                events.add(event);
                System.out.println("Event added");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("Would you like to add another event? (yes/no): ");
            String addAnother = scanner.nextLine().toLowerCase();
            if (!addAnother.equals("yes")) {
                break;
            }
        }



        while (true) {
            System.out.print("Enter a command (print, happening on MM/DD/YYYY @hh:mm:ss am/pm, hosted by hostname, or quit): ");
            String command = scanner.nextLine();
            if (command.equals("quit")) {
                break; // Exit the loop if the user enters "quit"
            } else if (command.equals("print")) {
                printEvents(events);
            } else if (command.startsWith("happening on")) {
                String dateTimeStr = command.substring("happening on".length()).trim();
                DateTime dateTimeToCheck = DateTime.parse(dateTimeStr);

                if (dateTimeToCheck != null) {
                    // Create a list to store events happening on the specified date and time
                    List<Event> matchingEvents = new ArrayList<>();

                    // Iterate through the events to find matches
                    for (Event event : events) {
                        DateTime start = event.getStartDate();
                        DateTime end = event.getEndDate();

                        // Check if the specified date and time falls within the event's start and end times
                        if (dateTimeToCheck.compareTo(start) >= 0 && dateTimeToCheck.compareTo(end) <= 0) {
                            matchingEvents.add(event);
                        }
                    }

                    if (!matchingEvents.isEmpty()) {
                        System.out.println("Events happening on " + dateTimeStr + ":");
                        for (Event event : matchingEvents) {
                            System.out.println(event);
                        }
                    } else {
                        System.out.println("No events happening on the specified date and time.");
                    }
                } else {
                    System.out.println("Invalid date and time format. Please use MM/DD/YYYY @hh:mm:ss am/pm.");
                }


            } else if (command.startsWith("hosted by")) {
                String hostname = command.substring("hosted by".length()).trim();
                printEventsHostedBy(events, hostname);
            }
        }

    }

    // Helper method to validate and parse a valid DateTime from user input
    private static DateTime getValidDateTime(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String dateTimeStr = scanner.nextLine();
            DateTime dateTime = DateTime.parse(dateTimeStr);
            if (dateTime != null) {
                // Validate if the date and time are not in the past
                DateTime currentDateTime = DateTime.getCurrentDateTime();
                if (dateTime.before(currentDateTime)) {
                    System.out.println("Error: Date and time must not be in the past.");
                } else {
                    return dateTime;
                }
            } else {
                System.out.println("Invalid date and time format. Please use MM/DD/YYYY @hh:mm:ss am/pm.");
            }
        }
    }

    // Helper method to validate and parse a valid integer from user input
    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input. Please enter a valid number.");
            }
        }
    }

    private static void printEvents(List<Event> events) {
        if (events.isEmpty()) {
            System.out.println("No events to print.");
            return;
        }

        // Sort events based on the specified criteria
        Collections.sort(events);
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private static void printEventsHostedBy(List<Event> events, String hostname) {
        // Implement the logic to filter and print events hosted by the given hostname
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getHost().equalsIgnoreCase(hostname)) {
                filteredEvents.add(event);
            }
        }

        if (filteredEvents.isEmpty()) {
            System.out.println("No events hosted by the specified hostname.");
            return;
        }

        // Sort the filtered events and print them
        Collections.sort(filteredEvents);
        for (Event event : filteredEvents) {
            System.out.println(event);
        }
    }
}
