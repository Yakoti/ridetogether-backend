Ride Together - Web Transport Sharing to Office App 

### Full Vision

#### Overview
The transport-sharing to office app is designed to connect drivers and passengers with overlapping schedules and proximity, facilitating efficient carpooling to workplaces. The app leverages a user-friendly interface, real-time matching, and integration with the Google Maps API for route planning. It aims to reduce commuting costs, environmental impact, and traffic congestion by optimizing shared rides.

Basic sketch
<img width="1204" height="754" alt="image" src="https://github.com/user-attachments/assets/f18db753-0a7e-4b5c-931e-f9d431492532" />


#### Core Features and Functionality

1. **User Roles and Profiles**
    - **Drivers**: Individuals willing to offer rides, inputting their home address, office address, preferred arrival time range (e.g., 10:00-10:30 AM), and willingness to prolong their trip (e.g., +15 minutes and +2 km).
    - **Passengers**: Individuals seeking rides, providing their home address, office address, and preferred arrival time range (e.g., 10:00-10:30 AM)
    - **Profile Page**: A dedicated page where users can view and edit their details, including addresses, time preferences, and contact information.

2. **Matching and Sorting Logic**

    - **Driver View (Passenger Selection)**:
        - Drivers open the app’s homepage, which displays a list of available passengers sorted by proximity.
        - Only passengers with overlapping arrival time ranges are shown.
        - Drivers can invite 2-3 passengers (based on available free spaces, e.g., 3 seats) and see updated routes via Google Maps API links after each selection.
        - Invites are prioritized based on proximity.
        - 
    - **Passenger View (Driver Selection)**:
        - Passengers see a list of available drivers on the homepage, sorted by:
            1. Overlapping arrival time ranges (primary metric).
            2. Deviation caused to the driver’s original path (e.g., a driver with a 5 km route and a 1 km deviation ranks higher than one with a 2 km deviation).
        - Passengers can apply directly to drivers, who can then accept or decline.
    - **Capacity Management**: Drivers indicate free spaces (e.g., 3), and the app prevents overbooking. If multiple passengers apply, drivers manually select based on preference.


3. **Route Planning and Updates**
    - The app integrates with the Google Maps API to generate routes.
    - When a driver open home page to select passenger, the app makes a call for every recommended passenger to the api for the new route (including the passenger’s address) and provides the new Google Maps link + showing increased distance (+km), time (+minutes), and expenses (+lv) (compared to driver's standard route).
    This way driver can take fully informed decision.
   

4. **Communication System**
    - **Chat Page**: A second page where one-on-one/group chats are created after a driver selects 2-3 passengers or a passenger applies and is accepted.
    - **Notifications**: Chat invitations appear at the top of the chat page, similar to a new message alert. Passengers must accept to join.
    - **Features**: Initially text-based, with plans for future image, voice or video options. Users can send messages to coordinate pick-up times, locations, or other details.
    - **Generated Messages**: The app auto-generates messages with route details (e.g., departure time, pick-up, arrival) and contact info for convenience.
    - **Chat is end-to-end encrypted using AES** which maybe we should change if we want to store the messages?
    - **Swear words** and **NSFW pics** are censored.
   
5. **User Interface**
    - **Homepage**: Displays lists of passengers (for drivers) or drivers (for passengers) with key details (e.g., distance, time overlap, deviation).
    - **Chat Page**: Shows active chats and notifications, with options to see full routes.
    - **Profile Page**: Allows users to update addresses, time ranges, and preferences.
    - **Navigation**: Simple tabs or buttons to switch between pages, with a “See Full Route” option for selected passengers.

#### Workflow Example
- **Driver Setup**: A driver (e.g., John) inputs home address, office address, arrival time (10:00-10:20 AM), and willingness to delay (+10 minutes, +1 km). He has 3 free spaces.
- **Passenger Setup**: A passenger (e.g., Alice) inputs her details (10:05-10:25 AM, +5 minutes flexibility) and applies to John, causing a 0.5 km deviation.
- **Matching**: John sees Alice as the closest match (due to low deviation and time overlap) and invites her and two others (Bob and Carol). The app updates the route (+1.5 km, +6 minutes).
- **Chat Initiation**: A group chat is created, and Alice, Bob, and Carol receive notifications. They accept and coordinate pick-up times.
- **Route Confirmation**: John views the final Google Maps link and sends a generated message with departure, pick-up, and arrival times.

#### Technical Considerations
- **API Integration**: Google Maps API handles route calculations and link generation.
- **Real-Time Updates**: The app refreshes passenger/driver lists and routes as users join or adjust preferences.
- **NSFW** censoring libraries should be included without slowing down the app.
- **Future enhancements**:  Including filters (e.g., car type), rating system, private access and matching only for company employees.

#### Benefits
- Reduces individual car usage, lowering carbon footprints.
- Saves time and money for both drivers and passengers.
- Flexible matching accommodates diverse schedules and routes.
