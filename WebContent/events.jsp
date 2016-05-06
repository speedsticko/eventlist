<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Events</title>

        <!-- Disable tap highlight on IE -->
        <meta name="msapplication-tap-highlight" content="no">

        <!-- Web Application Manifest -->
        <link rel="manifest" href="manifest.json">

        <!-- Add to homescreen for Chrome on Android -->
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="application-name" content="Web Starter Kit">
        <link rel="icon" sizes="192x192" href="images/touch/chrome-touch-icon-192x192.png">

        <!-- Add to homescreen for Safari on iOS -->
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-title" content="Web Starter Kit">
        <link rel="apple-touch-icon" href="images/touch/apple-touch-icon.png">

        <!-- Tile icon for Win8 (144x144 + tile color) -->
        <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
        <meta name="msapplication-TileColor" content="#2F3BA2">

        <!-- Color the status bar on mobile devices -->
        <meta name="theme-color" content="#2F3BA2">

        <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
        <!--
        <link rel="canonical" href="http://www.example.com/">
        -->

        <!-- Material Design Lite page styles:
        You can choose other color schemes from the CDN, more info here http://www.getmdl.io/customize/index.html
        Format: material.color1-color2.min.css, some examples:
        material.red-teal.min.css
        material.blue-orange.min.css
        material.purple-indigo.min.css
        -->
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.1.0/material.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/dataTables.material.min.css">

        <!-- Material Design icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <!-- Your styles -->
        <link rel="stylesheet" href="scripts/lib/pikaday.css">
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
                        <!-- table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp full-width "-->
                    </div>
                </section>
            </main>
        </div>
<dialog class="mdl-dialog" id="dialog">
    <div class="mdl-dialog__content">
        <h3>Event Details</h3>
        <textarea id="modal-content"  rows="20" style="width:100%"></textarea>
    </div>
    <div class="mdl-dialog__actions mdl-dialog__actions--full-width">
        <button type="button" class="mdl-button close">Close</button>
    </div>
</dialog>
        <script src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js"></script>
        <!-- build:js(app/) ../../scripts/main.min.js -->
        <script src="scripts/lib/jquery-1.12.3.min.js"></script>
        <script src="scripts/lib/pikaday.js"></script>
        <script src="scripts/lib/date.js"></script>
        <script type="text/javascript" src="scripts/lib/DataTables/datatables.min.js"></script>
        <script src="scripts/main.js"></script>
        <script>
            function formatDate(date) {
                var d = new Date(date),
                        month = '' + (d.getMonth() + 1),
                        day = '' + d.getDate(),
                        year = d.getFullYear();

                if (month.length < 2)
                    month = '0' + month;
                if (day.length < 2)
                    day = '0' + day;

                return [year, month, day].join('-');
            }
            
            function formatStartDate(startDate, period){
                return formatDate(startDate);
            }
            
            function getEndDate(startDate, period) {
                switch(period){
                    case "week": return startDate.addWeeks(1);
                    case "month": return startDate.addMonths(1);
                    case "quarter": return startDate.addMonths(3);
                    default: return null;
                }    
            }
            
            function getPrevStartDate(startDate, period) {
                switch(period){
                    case "week": return startDate.addWeeks(-1);
                    case "month": return startDate.addMonths(-1);
                    case "quarter": return startDate.addMonths(-3);
                    default: return null;
                }    
            }
            
            function getNextStartDate(startDate, period) {
                switch(period){
                    case "week": return startDate.addWeeks(1);
                    case "month": return startDate.addMonths(1);
                    case "quarter": return startDate.addMonths(3);
                    default: return null;
                }    
            }
            
            function formatEndDate(startDate, period){
                return formatDate(getEndDate(startDate, period));
            }
            

                        
            $(document).ready(function () {
                var picker = new Pikaday({field: document.getElementById('datepicker'), 
                defaultDate: new Date(),
                setDefaultDate: true,
                onSelect: function (date) {
                        refreshDateRange();
                        $('#datepicker').parent().addClass('is-dirty');
                        table.ajax.reload();
                    }});
                
                function refreshDateRange(){
                    var date = picker.getDate();
                    var period = $('input[name=period-type]:checked', '#period-type-group').val();
                    $('#startDate').html(formatStartDate(date, period));
                    $('#endDate').html(formatEndDate(date, period));
                }
                
                var table = $('#events-datatable').DataTable({
                    "deferRender": true,
                    "searching": false,
                    "pageLength": 25,
                    "lengthChange": false,
                    "paging": true,
                    "processing": true,
                    "serverSide": true,
                    "ajax": function (data, callback, settings) {
                        console.log(data);
                        var startDate = formatDate(picker.getDate());
                        var period = $('input[name=period-type]:checked', '#period-type-group').val();
                        $.ajax({
                            method: "GET",
                            url: "events",
                            data: {date: startDate, period: period,
                                draw: data.draw, start: data.start, length: data.length}
                            })
                            .done(function (results) {
                                callback(results);
                            });

                    },
                    columnDefs: [
                        {
                            targets: [0, 1, 2, 4],
                            className: 'mdl-data-table__cell--non-numeric',
                        }, {
                        "targets": -1,
                        "data": null,
                        "defaultContent": "<button>Details</button>"
                    } ]
                });
                 //$("div.toolbar").html('&nbsp;');
                 
                $('#prev-date').click(function(e){
                    var date = picker.getDate();
                    var period = $('input[name=period-type]:checked', '#period-type-group').val();
                    picker.setDate(getPrevStartDate(date, period));
                   refreshDateRange();
                   table.ajax.reload();
                });
                
                $('#next-date').click(function(e){
                    var date = picker.getDate();
                    var period = $('input[name=period-type]:checked', '#period-type-group').val();
                    picker.setDate(getNextStartDate(date, period));
                   refreshDateRange();
                   table.ajax.reload();
                });

                $('input[name=period-type]', '#period-type-group').change(function(e){
                   refreshDateRange();
                   table.ajax.reload();
                });
                
                 var dialog = document.querySelector('#dialog');
                 if (! dialog.showModal) {
                    dialogPolyfill.registerDialog(dialog);
                 }
                 dialog.querySelector('.close').addEventListener('click', function() {
                 dialog.close();
                 });
                function setSelectionRange(input, selectionStart, selectionEnd) {
                if (input.setSelectionRange) {
                    input.focus();
                    input.setSelectionRange(selectionStart, selectionEnd);
                }
                else if (input.createTextRange) {
                    var range = input.createTextRange();
                    range.collapse(true);
                    range.moveEnd('character', selectionEnd);
                    range.moveStart('character', selectionStart);
                    range.select();
                }
                }

                function setCaretToPos (input, pos) {
                setSelectionRange(input, pos, pos);
                }

                $('#events-datatable tbody').on( 'click', 'button', function () {
                    // get id
                      var data = table.row( $(this).parents('tr') ).data();
                      var event_id = data[4];
                        $.get("eventdetails?id=" + event_id, null, function (data) {
                            $("#modal-content").val(data);
                            setCaretToPos($("#modal-content")[0], 1);
                        }, "text");                        
                        dialog.showModal();
                  } );
                  refreshDateRange();
            });
        </script>
        <!-- endbuild -->
    </body>
</html>
