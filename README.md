# eventlist

Java Servlet SPA to list events

Requirements
1. JDK 1.7+
2. Tomcat 8
3. Maven 3.3.9

How to build .war file
1. At a command window, navigate to folder containing the pom.xml 
2. Enter the command:
mvn install -DskipTests -DskipITs
3. copy the .war file into your tomcat's webapp folder