<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!doctype html>
<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Web/Mobile Developer Assignment</title>

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" type="text/css" href="scripts/lib/material.min.css">
        <style>

            .demo-ribbon {
                width: 100%;
                height: 40vh;
                background-color: #3F51B5;
                flex-shrink: 0;
            }

            .demo-main {
                margin-top: -35vh;
                flex-shrink: 0;
            }

            .demo-header .mdl-layout__header-row {
                padding-left: 40px;
            }

            .demo-container {
                max-width: 1600px;
                width: calc(100% - 16px);
                margin: 0 auto;
            }

            .demo-content {
                border-radius: 2px;
                padding: 80px 56px;
                margin-bottom: 80px;
            }

            .demo-layout.is-small-screen .demo-content {
                padding: 40px 28px;
            }

            .demo-content h3 {
                margin-top: 48px;
            }

            .demo-footer {
                padding-left: 40px;
            }

            .demo-footer .mdl-mini-footer--link-list a {
                font-size: 13px;
            }
        </style>
    </head>
    <body>
        <div class="demo-layout mdl-layout mdl-layout--fixed-header mdl-js-layout mdl-color--grey-100">
            <header class="demo-header mdl-layout__header mdl-layout__header--scroll mdl-color--grey-100 mdl-color-text--grey-800">
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout-title">Web/Mobile Developer Assignment</span>
                    <div class="mdl-layout-spacer"></div>
                </div>
            </header>
            <div class="demo-ribbon"></div>
            <main class="demo-main mdl-layout__content">
                <div class="demo-container mdl-grid">
                    <div class="mdl-cell mdl-cell--2-col mdl-cell--hide-tablet mdl-cell--hide-phone"></div>
                    <div class="demo-content mdl-color--white mdl-shadow--4dp content mdl-color-text--grey-800 mdl-cell mdl-cell--8-col">
                        <div class="demo-crumbs mdl-color-text--grey-500">
                            Documentation
                        </div>
                        <h3>Quick Start</h3>
                        <p>
                            You will need the following software installed and available on your machine. 
                        </p>
                        <b>Required Software</b>
                        <ul>
                            <li>JDK 1.7+ - The java compiler and runtime environment.</li>
                            <li>Tomcat 8 - The web application server.</li>
                            <li>Maven 3.3.9 - The Maven build automation tool. </li>
                        </ul>

                        <h3>Step-by-Step Setup Instructions</h3>
                        <ol>
                            <li>Make sure you have the required software installed</li>
                            <li>In a command prompt, navigate to the folder of the EventList project containing the <i>pom.xml</i> file</li>
                            <li>Enter: mvn clean install -DskipTests -DskipITs</li>
                            <li>In the <i>target</li> subfolder you will find a <i>eventlist-0.0.1-SNAPSHOT.war</i> file.
                            <li>Copy the <i>eventlist-0.0.1-SNAPSHOT.war</i> to your tomcat installation's <i>webapps</i> folder</li>
                            <li>Rename <i>eventlist-0.0.1-SNAPSHOT.war</i> to <i>eventlist.war</i> and start tomcat.
                                <p>A new <i>eventsDatabase</i> Derby DB database will be created and initialized if necessary.</p>
                            </li>
                            <li>Populate your database with the <a href="/eventlist/upload.jsp">Upload Page</a></li>
                            <li>Browse your events with the <a href="/eventlist/events.jsp">Events Page</a></li>
                        </ol>

                        <h3>Testing</h3>
                        <ul>
                            <li>To run the JUnit tests, enter: mvn test</li>
                            <li>To run the Selenium tests, install the IE web driver and , enter: mvn verify</li>
                        </ul>
                        
                        <h3>Third-Party Dependencies</h3>
                        <p>The following third-party tools and libraries were use in this project.</p>
                        
                        <ul>
                            <li>Derby Db - http://db.apache.org/derby/ - for relational data storage</li>
                            <li>Google GSON - https://github.com/google/gson - for json serialization/deserialization</li>
                            <li>JodaTime - http://www.joda.org/joda-time/ - for working with DateTimes </li>
                            <li>jQuery 1.12.3 - https://jquery.com/ - for simplifying JavaScript code</li>
                            <li>Date.js 1.0 Alpha-1 - http://www.datejs.com/ - for working with Dates in JavaScript</li>
                            <li>DataTables.js 1.10.11 - https://datatables.net - for displaying tabular data</li>
                            <li>Pikaday 1.4.0 - https://github.com/dbushell/Pikaday - for starting date input</li>
                            <li>Google Material Design Lite - https://github.com/google/material-design-lite - for design and UX</li>
                        </ul>
                    </div>
                </div>
            </main>
        </div>
        <script src="scripts/lib/material.min.js"></script>
    </body>
</html>
