/* Date Utility functions */
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

function formatStartDate(startDate, period) {
    return formatDate(startDate);
}

function getEndDate(startDate, period) {
    switch (period) {
        case "week":
            return startDate.addWeeks(1);
        case "month":
            return startDate.addMonths(1);
        case "quarter":
            return startDate.addMonths(3);
        default:
            return null;
    }
}

function getPrevStartDate(startDate, period) {
    switch (period) {
        case "week":
            return startDate.addWeeks(-1);
        case "month":
            return startDate.addMonths(-1);
        case "quarter":
            return startDate.addMonths(-3);
        default:
            return null;
    }
}

function getNextStartDate(startDate, period) {
    switch (period) {
        case "week":
            return startDate.addWeeks(1);
        case "month":
            return startDate.addMonths(1);
        case "quarter":
            return startDate.addMonths(3);
        default:
            return null;
    }
}

function formatEndDate(startDate, period) {
    return formatDate(getEndDate(startDate, period).addDays(-1));
}

/* Set up UI when page is ready */
$(document).ready(function () {
    var picker = new Pikaday({field: document.getElementById('datepicker'),
        defaultDate: new Date(2015, 11, 1),
        disableDayFn: function(date){
            var period = $('input[name=period-type]:checked', '#period-type-group').val();
            if(period === 'week'){
                // Only enable Sunday
                return date.getDay() !== 0;
            }else {
                return date.getDate() !== 1;
            }
        },
        setDefaultDate: true,
        onSelect: function (date) {
            refreshDateRange();
            $('#datepicker').parent().addClass('is-dirty');
            table.ajax.reload();
        }});

    function refreshDateRange() {
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
                "defaultContent": "<button class='mdl-button mdl-js-button mdl-button--icon'><i class='material-icons'>&#xE8FF;</i></button>"
            }]
    });

    $('#prev-date').click(function (e) {
        var date = picker.getDate();
        var period = $('input[name=period-type]:checked', '#period-type-group').val();
        picker.setDate(getPrevStartDate(date, period));
        refreshDateRange();
    });

    $('#next-date').click(function (e) {
        var date = picker.getDate();
        var period = $('input[name=period-type]:checked', '#period-type-group').val();
        picker.setDate(getNextStartDate(date, period));
        refreshDateRange();
    });

    $('input[name=period-type]', '#period-type-group').change(function (e) {
        var date = picker.getDate();
        if ($(this).val() !== 'week') {
            date = date.moveToFirstDayOfMonth();
        } else {
            date.moveToNthOccurrence(0, 1);
        }
        picker.setDate(date);
        
        refreshDateRange();
        table.ajax.reload();
    });

    var dialog = document.querySelector('#dialog');
    if (!dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    dialog.querySelector('.close').addEventListener('click', function () {
        dialog.close();
    });
    function setSelectionRange(input, selectionStart, selectionEnd) {
        if (input.setSelectionRange) {
            input.focus();
            input.setSelectionRange(selectionStart, selectionEnd);
        } else if (input.createTextRange) {
            var range = input.createTextRange();
            range.collapse(true);
            range.moveEnd('character', selectionEnd);
            range.moveStart('character', selectionStart);
            range.select();
        }
    }

    function setCaretToPos(input, pos) {
        setSelectionRange(input, pos, pos);
    }

    $('#events-datatable tbody').on('click', 'button', function () {
        // get id
        var data = table.row($(this).parents('tr')).data();

        var event_date = data[0];
        var event_type = data[1];
        var event_summary = data[2];
        var event_size = data[3];
        var event_id = data[4];
        $("#dialog-event-id").text(event_id);
        $("#dialog-event-date").text(event_date);
        $("#dialog-event-type").text(event_type);
        $("#dialog-event-summary").text(event_summary);
        $("#dialog-event-size").text(event_size);

        $.get("eventdetails?id=" + event_id, null, function (data) {
            $("#modal-content").val(data);
            setCaretToPos($("#modal-content")[0], 1);
        }, "text");

        dialog.showModal();
    });
    refreshDateRange();
});