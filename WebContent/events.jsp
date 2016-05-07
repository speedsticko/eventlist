<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Events</title>

        <!-- Color the status bar on mobile devices -->
        <meta name="theme-color" content="#2F3BA2">

        <link rel="stylesheet" type="text/css" href="scripts/lib/material.min.css">
        <link rel="stylesheet" type="text/css" href="scripts/lib/dataTables.material.min.css">

        <!-- Material Design icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <!-- Your styles -->
        <link rel="stylesheet" href="scripts/lib/pikaday.css">
        <link rel="stylesheet" type="text/css" href="scripts/lib/dialog-polyfill.css" />
        <link rel="stylesheet" href="styles/main.css"> 
        
    </head>
    <body class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
        <div class="mdl-layout mdl-js-layout ">
            <main class="mdl-layout__content">
                <div class="mdl-grid" style="max-width: 980px;">
                    <div class="mdl-cell mdl-cell--3-col mdl-cell--2-col-tablet">
                        <form action="#">
                            <div class="mdl-compact mdl-textfield mdl-js-textfield ">
                                <input class="mdl-textfield__input" type="text" id="datepicker"/>
                            </div>
                        </form>
                    </div>
                    <div class="mdl-cell mdl-cell--6-col mdl-cell--2-col-tablet">
                        <button id="prev-date" class=".mdl-compact mdl-button mdl-js-button mdl-button--icon"><i class="material-icons">&#xE5CB;</i></button>
                        <button id="next-date" class=".mdl-compact mdl-button mdl-js-button mdl-button--icon"><i class="material-icons">&#xE5CC;</i></button>  <span id="startDate"></span> to <span id="endDate"></span>
                    </div>
                    <div id="period-type-group" class="mdl-cell mdl-cell--3-col mdl-cell--4-col-tablet">
                        <label class=".mdl-compact mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-1">
                            <input type="radio" id="option-1" class="mdl-radio__button" name="period-type" value="week" checked>
                            <span class="mdl-radio__label">Week</span>
                        </label>
                        <label class=".mdl-compact mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-2">
                            <input type="radio" id="option-2" class="mdl-radio__button" name="period-type" value="month">
                            <span class="mdl-radio__label">Month</span>
                        </label>
                        <label class=".mdl-compact mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-3">
                            <input type="radio" id="option-3" class="mdl-radio__button" name="period-type" value="quarter">
                            <span class="mdl-radio__label">Quarter</span>
                        </label>
                    </div>
                </div>

                <section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
                    <div class="mdl-card mdl-cell mdl-cell--12-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                        <table id="events-datatable" class="compact mdl-data-table mdl-shadow--2dp" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Type</th>
                                    <th>Summary</th>
                                    <th>Metric</th>
                                    <th>Details</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </section>
            </main>
        </div>
<dialog class="mdl-dialog" id="dialog">
    <div class="mdl-dialog__content">
        <div>
        <h5>Event Summary</h5>
            <table class="mdl-data-table mdl-shadow--2dp" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Type</th>
                        <th>Summary</th>
                        <th>Metric</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><span id="dialog-event-id"></span></td>
                        <td><span id="dialog-event-date"></span></td>
                        <td><span id="dialog-event-type"></span></td>
                        <td><span id="dialog-event-summary"></span></td>
                        <td><span id="dialog-event-size"></span></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <h5>Event Details</h5>
        <textarea id="modal-content"  rows="20" style="width:100%"></textarea>
    </div>
    <div class="mdl-dialog__actions mdl-dialog__actions--full-width">
        <button type="button" class="mdl-button close">Close</button>
    </div>
</dialog>
        <script src="scripts/lib/material.min.js"></script>
        <!-- build:js(app/) ../../scripts/main.min.js -->
        <script src="scripts/lib/jquery-1.12.3.min.js"></script>
        <script src="scripts/lib/pikaday.js"></script>
        <script src="scripts/lib/dialog-polyfill.js"></script>
        <script src="scripts/lib/date.js"></script>
        <script src="scripts/lib/DataTables/datatables.min.js"></script>
        <script src="scripts/lib/datatables.mdl.js"></script>
        <script src="scripts/main.js"></script>
        <script>

        </script>
        <!-- endbuild -->
    </body>
</html>
