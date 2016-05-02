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
    <link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">

    <!-- Material Design icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <!-- Your styles -->
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="scripts/lib/pikaday.css">
  </head>
  <body class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
      <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
          <a href="#overview" class="mdl-layout__tab is-active">Overview</a>
          <a href="#features" class="mdl-layout__tab">Features</a>
          <a href="#features" class="mdl-layout__tab">Details</a>
          <a href="#technology" class="mdl-layout__tab">Technology</a>
          <a href="#features" class="mdl-layout__tab">FAQ</a>
          <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored mdl-shadow--4dp mdl-color--accent" id="add">
            <i class="material-icons" role="presentation">add</i>
            <span class="visuallyhidden">Add</span>
          </button>
        </div>
      </header>
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
            <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp full-width ">
  <thead>
    <tr>
      <th class="mdl-data-table__cell--non-numeric">Material</th>
      <th>Quantity</th>
      <th>Unit price</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td class="mdl-data-table__cell--non-numeric">Acrylic (Transparent)</td>
      <td>25</td>
      <td>$2.90</td>
    </tr>
    <tr>
      <td class="mdl-data-table__cell--non-numeric">Plywood (Birch)</td>
      <td>50</td>
      <td>$1.25</td>
    </tr>
    <tr>
      <td class="mdl-data-table__cell--non-numeric">Laminate (Gold on Blue)</td>
      <td>10</td>
      <td>$2.35</td>
    </tr>
  </tbody>
</table>
            </div>
            
          </section>
          
        </div>
        <div class="mdl-layout__tab-panel" id="features">
          <section class="section--center mdl-grid mdl-grid--no-spacing">
            <div class="mdl-cell mdl-cell--12-col">
              <h4>Features</h4>
              Minim duis incididunt est cillum est ex occaecat consectetur. Qui sint ut et qui nisi cupidatat. Reprehenderit nostrud proident officia exercitation anim et pariatur ex.
              <ul class="toc">
                <h4>Contents</h4>
                <a href="#lorem1">Lorem ipsum</a>
                <a href="#lorem2">Lorem ipsum</a>
                <a href="#lorem3">Lorem ipsum</a>
                <a href="#lorem4">Lorem ipsum</a>
                <a href="#lorem5">Lorem ipsum</a>
              </ul>

              <h5 id="lorem1">Lorem ipsum dolor sit amet</h5>
              Excepteur et pariatur officia veniam anim culpa cupidatat consequat ad velit culpa est non.
              <ul>
                <li>Nisi qui nisi duis commodo duis reprehenderit consequat velit aliquip.</li>
                <li>Dolor consectetur incididunt in ipsum laborum non et irure pariatur excepteur anim occaecat officia sint.</li>
                <li>Lorem labore proident officia excepteur do.</li>
              </ul>

              <p>
                Sit qui est voluptate proident minim cillum in aliquip cupidatat labore pariatur id tempor id. Proident occaecat occaecat sint mollit tempor duis dolor cillum anim. Dolore sunt ea mollit fugiat in aliqua consequat nostrud aliqua ut irure in dolore. Proident aliqua culpa sint sint exercitation. Non proident occaecat reprehenderit veniam et proident dolor id culpa ea tempor do dolor. Nulla adipisicing qui fugiat id dolor. Nostrud magna voluptate irure veniam veniam labore ipsum deserunt adipisicing laboris amet eu irure. Sunt dolore nisi velit sit id. Nostrud voluptate labore proident cupidatat enim amet Lorem officia magna excepteur occaecat eu qui. Exercitation culpa deserunt non et tempor et non.
              </p>
            </div>
          </section>
        </div>
      </main>
    </div>

    <script src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js"></script>
    <!-- build:js(app/) ../../scripts/main.min.js -->
    <script src="scripts/main.js"></script>
    <script src="scripts/lib/pikaday.js"></script>
    <script>
    var picker = new Pikaday({ field: document.getElementById('datepicker') });
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
