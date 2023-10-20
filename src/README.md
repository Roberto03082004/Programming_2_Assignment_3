# Event Manager README

## Functionality

The Event Manager is a command-line application that allows you to manage events. Here are the functionalities successfully implemented:

1. **Add Events**: You can add new events, providing details like event name, start date and time, end date and time, host, number of invitees, and location. The application enforces the following restrictions:
    - Event names, hosts, and locations cannot contain commas (,).
    - End date and time must be after or equal to the start date and time.

2. **List Events**: You can list all added events. The events are sorted based on the start date and time.

3. **Filter Events by Date and Time**: You can view events happening on a specific date and time using the "happening on" command. Example usage:
    - To view events happening on a specific date and time:
      ```
      happening on MM/DD/YYYY @hh:mm:ss am/pm
      ```

4. **Filter Events by Host**: You can view events hosted by a specific person using the "hosted by" command. Example usage:
    - To view events hosted by a specific host:
      ```
      hosted by hostname
      ```

5. **Quit**: You can exit the application using the "quit" command.

## Usage

1. **Add Events**:
    - Run the application and provide event details when prompted.
    - If you encounter any errors or restrictions, the application will ask you to re-enter the details.
    - You can add multiple events in one session. When asked, you can choose to add another event by typing "yes" or exit the application by typing "no."

2. **List Events**:
    - To list all events, use the "print" command.

3. **Filter Events by Date and Time**:
    - To view events happening on a specific date and time, use the "happening on" command:
      ```
      happening on MM/DD/YYYY @hh:mm:ss am/pm
      ```

4. **Filter Events by Host**:
    - To view events hosted by a specific person, use the "hosted by" command:
      ```
      hosted by hostname
      ```

5. **Quit**:
    - To exit the application, use the "quit" command.

## Restrictions

The application enforces restrictions on event names, hosts, locations, and date/time inputs. You will receive error messages and be prompted to re-enter the details if any restrictions are violated.

Make sure to use valid inputs and adhere to the restrictions to use the application effectively.

## Dependencies

The application uses Java for its implementation and does not have any external dependencies.

## Authors

Roberto Callejas

[rcall037@fiu.edu](rcall037@fiu.edu)

[GitHub](https://github.com/Roberto03082004)


