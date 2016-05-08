Quick Start
===========
You will need the following software installed and available on your machine.

Required Software
======================
JDK 1.7+ - The java compiler and runtime environment.
Tomcat 8 - The web application server.
Maven 3.3.9 - The Maven build automation tool.


Step-by-Step Setup Instructions
=================================
1 Make sure you have the required software installed
2 In a command prompt, navigate to the folder of the EventList project containing the pom.xml file
3 Enter: mvn clean install -DskipTests -DskipITs
4 In the target subfolder you will find a eventlist-0.0.1-SNAPSHOT.war file.
5 Copy the eventlist-0.0.1-SNAPSHOT.war to your tomcat installation's webapps folder
6 Rename eventlist-0.0.1-SNAPSHOT.war to eventlist.war and start tomcat.
  * A new eventsDatabase Derby DB database will be created and initialized if necessary.
7 Populate your database with the Upload Page (http://localhost:8080/eventlist/upload.jsp)
8 Browse your events with the Events Page (http://localhost:8080/eventlist/events.jsp)

Testing
===========
To run the JUnit tests, enter: mvn install -DskipITs
To run the Selenium tests, install the IE web driver and , enter: mvn verify

Third-Party Dependencies
=================================
The following third-party tools and libraries were use in this project.

Derby Db - http://db.apache.org/derby/ - for relational data storage
Google GSON - https://github.com/google/gson - for json serialization/deserialization
JodaTime - http://www.joda.org/joda-time/ - for working with DateTimes
jQuery 1.12.3 - https://jquery.com/ - for simplifying JavaScript code
Date.js 1.0 Alpha-1 - http://www.datejs.com/ - for working with Dates in JavaScript
DataTables.js 1.10.11 - https://datatables.net - for displaying tabular data
Pikaday 1.4.0 - https://github.com/dbushell/Pikaday - for starting date input
Google Material Design Lite - https://github.com/google/material-design-lite - for design and UX

Test On
===========
Windows 8.1
Ubuntu Linux 14.04