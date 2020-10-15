<%@page import="dao.UrlDAO"%>
<%@page import="model.Url"%>
<%
UrlDAO ud = new UrlDAO();
Url U;
String short_url;
short_url = session.getAttribute("short_url").toString();
U = ud.searchShortUrl(short_url);
String https = "http://www.urllive.in:8084/UrlLive/";
//String website = "urllive.in/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="http://www.urllive.in:8084/UrlLive/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="http://www.urllive.in:8084/UrlLive/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Generate Linked-In URL</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/themify-icons.css" rel="stylesheet">
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/all.css" rel="stylesheet">
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/all.min.css" rel="stylesheet">
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/fontawesome.css" rel="stylesheet">
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/fontawesome.min.css" rel="stylesheet">
    <link href="http://www.urllive.in:8084/UrlLive/assets/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body onload="demo.showNotificationAlert('top','right','Thanks for choosing our services');">
	<!-- start sidebar -->
	<div class="wrapper">
		<div class="sidebar" data-background-color="white" data-active-color="danger">
	    	<div class="sidebar-wrapper">
	            <div class="logo">
	                <a href="http://www.urllive.in:8084/UrlLive/dashboard/" class="simple-text">
	                    <i class="ti-link"></i>&nbsp;<big><b>URL Live</b></big>
	                </a>
	            </div>
	            <ul class="nav">
	                <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/dashboard/">
                                <i class="ti-panel"></i>
                                <p>Dashboard</p>
	                    </a>
	                </li>
                        <hr>
                        <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/url/shorter">
	                        <i class="fa fa-link"></i>
	                        <p>URL Shorter</p>
	                    </a>
	                </li>
                        <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/url/custom">
	                        <i class="fa fa-pencil"></i>
	                        <p>Custom URL</p>
	                    </a>
	                </li>
                        <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/url/clicks/">
	                        <i class="fa fa-eye"></i>
	                        <p>Count Clicks</p>
	                    </a>
	                </li>
                        <hr>
                        <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/whatsapp/api">
	                        <i class="fa fa-whatsapp"></i>
	                        <p>Whatsapp Tool</p>
	                    </a>
	                </li>
                        <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/email/api">
	                        <i class="fa fa-envelope"></i>
	                        <p>E-mail Tool</p>
	                    </a>
	                </li>
                        <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/facebook/api">
	                        <i class="fa fa-facebook-f"></i>
	                        <p>Facebook Tool</p>
	                    </a>
	                </li>
                        <li>
	                    <a href="http://www.urllive.in:8084/UrlLive/twitter/api">
	                        <i class="fa fa-twitter"></i>
	                        <p>Twitter Tool</p>
	                    </a>
	                </li>
                        <li class="active">
	                    <a href="http://www.urllive.in:8084/UrlLive/linkedin/api">
	                        <i class="fa fa-linkedin"></i>
	                        <p>Linkedin Tool</p>
	                    </a>
	                </li>
	            </ul>
	    	</div>
	    </div>
	    <!-- end sidebar -->

	    <!-- start heading -->
	    <div class="main-panel">
			<nav class="navbar navbar-default">
	            <div class="container-fluid">
	                <div class="navbar-header">
	                    <button type="button" class="navbar-toggle">
	                        <span class="sr-only">Toggle navigation</span>
	                        <span class="icon-bar bar1"></span>
	                        <span class="icon-bar bar2"></span>
	                        <span class="icon-bar bar3"></span>
	                        <span class="icon-bar bar4"></span>
	                    </button>
	                    <a class="navbar-brand">Linked-In API / URL</a>
	                </div>
	                <div class="collapse navbar-collapse">
	                    <ul class="nav navbar-nav navbar-right">
	                        <li class="dropdown">
	                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <p><b>S.E.O. Tool</b></p>
                                        <b class="caret"></b>
	                            </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="http://www.urllive.in:8084/UrlLive/SEO/meta-tag/">Meta Tag Generator</a></li>
                                        <li><a href="http://www.urllive.in:8084/UrlLive/SEO/youtube-button/">Youtube Button</a></li>
                                        <li><a href="http://www.urllive.in:8084/UrlLive/SEO/facebook-share/">Facebook Share Button</a></li>
                                        <li><a href="http://www.urllive.in:8084/UrlLive/SEO/facebook-like/">Facebook Like Button</a></li>
                                    </ul>
	                        </li>
                                <li class="dropdown">
	                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <p><b>Code Editor</b></p>
                                        <b class="caret"></b>
	                            </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="http://www.urllive.in:8084/UrlLive/editor/html/">HTML IDE</a></li>
                                        <li><a href="http://www.urllive.in:8084/UrlLive/editor/sql/">MYSQL IDE</a></li>
                                    </ul>
	                        </li>
                                <li class="dropdown">
	                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <p><b>Other Tools</b></p>
                                        <b class="caret"></b>
	                            </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="http://www.urllive.in:8084/UrlLive/SEO/password-calculator/">Password Calculator</a></li>
                                    </ul>
	                        </li>
	                    </ul>
	                </div>
	            </div>
	        </nav>
	        <!-- end heading -->

	        <!-- start body -->
	        <div class="content">
	            <div class="container-fluid">
	                <div class="row">
	                	<div class="col-lg-12 col-md-12">
	                        <div class="card">
	                            <div class="header">
                                        <h2 class="title"><b>Your shortened URL</b></h2>
	                            </div>
	                            <div class="content">
                                        <label>Copy the shortened link and share it in messages, texts, posts, websites and other locations.</label>
                                        <div class="clearfix margin-top"></div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="input-group" onclick="copyText();">
                                                    <input type="text" name="url" id="url" class="form-control border-input" style="cursor:pointer;" onclick="demo.showNotificationClipboard('top','right','URL copied')" value="<%= U.getShort_url() %>">
                                                    <span class="input-group-addon" style="cursor:pointer;"><i class="fa fa-clipboard" onclick="demo.showNotificationClipboard('top','right')"></i></span>
                                                </div>
                                                <script>
                                                function copyText() {
                                                  var copyText = document.getElementById("url");
                                                  copyText.select();
                                                  document.execCommand("Copy");
                                                  //alert("Copied the text: " + copyText.value);
                                                }
                                                </script>
                                            </div>
                                            <div class="col-md-2 text-right">
                                                <a href="<%= U.getShort_url() %>" target="_blank">
                                                    <button type="button" class="btn btn-default btn-wd">Preview&nbsp;<i class="fa fa-eye"></i></button>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12 text-left">
                                                <br><br>
                                                <big><b>Track <a class="text-info" href="http://www.urllive.in:8084/UrlLive/url/clicks?url=<%= U.getShort_url() %>">the total of clicks</a> in real-time from your shortened URL.</b></big>
                                                <br><br>
                                                <big><b>Create other <a class="text-info" href="http://www.urllive.in:8084/UrlLive/linkedin/api">shortened URL</a>.</b></big>
                                            </div>
                                        </div>
	                                <div class="clearfix"></div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <!-- end body -->

	        <!-- start footer -->
	        <footer class="footer">
	            <div class="container-fluid">
	                <nav class="pull-left">
	                    <ul>
                                <li>
	                            <a href="http://www.urllive.in:8084/UrlLive/report-malicious/"><i class="ti-receipt"></i> Report Malicious URL</a>
	                        </li>
	                    </ul>
	                </nav>
					<div class="copyright pull-right">
	                    <i class="ti-link"></i> <a href="http://www.urllive.in:8084/UrlLive/dashboard/">urllive.in</a> | &copy; <script>document.write(new Date().getFullYear())</script>
	                </div>
	            </div>
	        </footer>
	        <!-- end footer -->

	    </div>
	</div>

</body>

    <!--   Core JS Files   -->
    <script src="http://www.urllive.in:8084/UrlLive/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="http://www.urllive.in:8084/UrlLive/assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="http://www.urllive.in:8084/UrlLive/assets/js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="http://www.urllive.in:8084/UrlLive/assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="http://www.urllive.in:8084/UrlLive/assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="http://www.urllive.in:8084/UrlLive/assets/js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="http://www.urllive.in:8084/UrlLive/assets/js/demo.js"></script>
        <script src="http://www.urllive.in:8084/UrlLive/assets/js/test.js"></script>   
        
</html>