Overview
===========
This is a database-back web application developed using Java Servlets that provides
a list and detail view of events.

Live Demo:
===============
I deployed a live demo of this project to a Linode VPS using Docker.

http://173.230.150.80:8080/eventlist/events.jsp


File Listings
============================
The zip file expands to the following file listing:

WebContent/ - JSP files
WebContent/META-INF/context.xml - JDBC connection info for the events database
WebContent/WEB-INF/database.sql - SQL script to create the events database
WebContent/WEB-INF/web.xml      - deployment descriptor for webapp
WebContent/scripts/main.js      - client-side code
WebContent/scripts/libs/        - client-side library code
WebContent/styles               - CSS
src/test                        - testing code
src/main                        - webapp server code
pom.xml                         - Maven project



Pre-Requisites
======================
You will need the following software installed and available on your machine:

* JDK 1.7+ - The java compiler and runtime environment.
* Tomcat 8 - The web application server.
* Maven 3.3.9 - The Maven build automation tool.




Step-by-Step Setup Instructions
=================================
1 Make sure you have the required software installed
2 In a command prompt, navigate to the folder where you extracted the eventlist.zip file
  * The root of this folder will contain the pom.xml file
3 Enter: mvn clean install -DskipTests -DskipITs
4 In the target subfolder you will find a "eventlist-0.0.1-SNAPSHOT.war" file.
5 Copy the "eventlist-0.0.1-SNAPSHOT.war" to your tomcat installation's webapps folder
6 Rename "eventlist-0.0.1-SNAPSHOT.war" to "eventlist.war" and start tomcat.
  * A new eventsDatabase Derby DB database will be created and initialized if necessary.
7 Populate your database with the Upload Page (http://localhost:8080/eventlist/upload.jsp)
  * This is a synchronous operation and can take some time depending on the input file size.
8 Browse your events with the Events Page (http://localhost:8080/eventlist/events.jsp)

Testing
===========
Some very basic integration-style testing are included.

JUnit Tests
 * The test is of the data access object running against an in-memory Derby database.
 * To run the JUnit tests, use the command: mvn test

Selenium Tests
 * The integration-test is of the webapp being used through a browser.
 * To run the Selenium tests, you will first need to install the IE web driver (https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver).
 * Edit the pom.xml's <webdriver.ie.driver> with the path to the IEDriverServer.exe
 * Use the command: mvn verify -DskipTests

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
