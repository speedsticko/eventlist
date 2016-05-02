<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        <div class="mdl-layout__tab-panel is-active" id="overview">
            <div class="mdl-grid" style="max-width: 860px;">
            <div class="mdl-cell mdl-cell--3-col mdl-cell--2-col-tablet">
            <form action="#">
  <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input class="mdl-textfield__input" type="text" id="datepicker"/>
    <label class="mdl-textfield__label" for="datepicker">Start Date</label>
  </div>
</form>
</div>
<div class="mdl-cell mdl-cell--4-col mdl-cell--2-col-tablet" style="padding: 20px 0;">
<button class="mdl-button mdl-js-button mdl-button--icon"><i class="material-icons">&#xE5CB;</i></button>
<button class="mdl-button mdl-js-button mdl-button--icon"><i class="material-icons">&#xE5CC;</i></button>          current range
            </div>
			<div class="mdl-cell mdl-cell--5-col mdl-cell--4-col-tablet" style="padding: 20px 0;">
			<label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-1">
			  <input type="radio" id="option-1" class="mdl-radio__button" name="options" value="1" checked>
			  <span class="mdl-radio__label">Week</span>
			</label>
			<label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-2">
			  <input type="radio" id="option-2" class="mdl-radio__button" name="options" value="2">
			  <span class="mdl-radio__label">Month</span>
			</label>
			<label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-3">
			  <input type="radio" id="option-3" class="mdl-radio__button" name="options" value="3">
			  <span class="mdl-radio__label">Quarter</span>
			</label>
			</div>
            </div>

          <section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
            <div class="mdl-card mdl-cell mdl-cell--12-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
            <table id="events-datatable" class="mdl-data-table mdl-shadow--2dp" cellspacing="0" width="100%">
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
        </div>
      </main>
    </div>

    <script src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js"></script>
    <!-- build:js(app/) ../../scripts/main.min.js -->
    <script src="scripts/lib/jquery-1.12.3.min.js"></script>
    <script src="scripts/lib/pikaday.js"></script>
	<script type="text/javascript" src="scripts/lib/DataTables/datatables.min.js"></script>
	<script src="scripts/main.js"></script>
    <script>
    var picker = new Pikaday({ field: document.getElementById('datepicker') });
    $(document).ready(function() {
        $('#events-datatable').DataTable( {
        	"bDeferRender": true,
        	"bFilter": false,
        	"iDisplayLength": 25,
        	"bLengthChange": false,
        	"bPaginate": true,
            columnDefs: [
                {
                    targets: [ 0, 1, 2 ],
                    className: 'mdl-data-table__cell--non-numeric',
                }],
            aaData : [['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details'],['2016-01-01','type','summary',123,'details']]
        } );
    } );
	</script>
    <!-- endbuild -->

    <!-- Google Analytics: change UA-XXXXX-X to be your site's ID -->
    <script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
      ga('create', 'UA-XXXXX-X', 'auto');
      ga('send', 'pageview');
    </script>
    <!-- Built with love using Web Starter Kit -->
  </body>
</html>
