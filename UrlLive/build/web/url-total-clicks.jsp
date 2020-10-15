<%@page import="dao.UrlDAO"%>
<%@page import="dao.CountsDAO"%>
<%@page import="model.Url"%>
<%@page import="model.Counts"%>
<%
UrlDAO ud = new UrlDAO();
CountsDAO cd = new CountsDAO();
Counts C = null;
Url U = null;
String short_url;
short_url = request.getParameter("url");
U = ud.searchShortUrl(short_url);
C = cd.searchShortUrlCount(short_url);
//String https_website = "http://www.urllive.in:8084/";
//String website = "urllive.in/";
//int n = short_url.lastIndexOf("/z");
//out.println(short_url);
//int n = short_url.lastIndexOf("/");
//String url = short_url.substring(n+1);
//out.println(U.getShort_url()+"   "+C.getC_short_url());

%>
<%
if(ud.checkUrl(short_url) && cd.checkUrlCount(short_url))
{
    long counting = 
            Long.parseLong(C.getC_os_android()) +
            Long.parseLong(C.getC_os_blackberry()) +
            Long.parseLong(C.getC_os_ipad()) +
            Long.parseLong(C.getC_os_iphone()) +
            Long.parseLong(C.getC_os_kindle()) +
            Long.parseLong(C.getC_os_linux()) +
            Long.parseLong(C.getC_os_macintosh()) +
            Long.parseLong(C.getC_os_others()) +
            Long.parseLong(C.getC_os_palm()) +
            Long.parseLong(C.getC_os_playbook()) +
            Long.parseLong(C.getC_os_win_phone()) +
            Long.parseLong(C.getC_os_windows())
            ;
%>
<!--code-->
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="http://www.urllive.in:8084/UrlLive/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="http://www.urllive.in:8084/UrlLive/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Count URL Click</title>

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
<body>
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
                        <li class="active">
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
                        <li>
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
	                    <a class="navbar-brand">Count Clicks</a>
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
                                        <h2 class="title"><b>Total URL Clicks</b></h2>
	                            </div>
	                            <div class="content">
                                        <span class="text-left">The total number of clicks that your link has received so far.The total number of clicks that your link has received so far.</span>
                                        <div class="clearfix margin"></div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <big style="font-size:75px"><b><%= counting %></b></big>
                                                    <%if(counting>0){%><big>&nbsp;clicks on this link.</big>&nbsp;<a href="" class="text-success" title="REFRESH"><i class="fa fa-refresh fa-spin"></i></a><%}
                                                    else{%><big>&nbsp;(no any clicks on this link)</big>&nbsp;<a href="" class="text-success" title="REFRESH"><i class="fa fa-refresh fa-spin"></i></a><%}%>
                                                </div>
                                            </div>
                                            <div class="col-md-12 text-left">
                                                <big><b>URL: <a target="_blank" href="<%= C.getC_short_url() %>"><%= C.getC_short_url() %></a></b></big>
                                                <br><br><br><br>
                                                <big><b>Our tool will count each click as one hit to the long URL, even if there are multiple clicks from the same person or device.</b></big>
                                                <br><br>
                                                <big><b>Check <a href="http://www.urllive.in:8084/UrlLive/url/clicks/">the total number of clicks</a> from other URL.</b></big>
                                            </div>
                                            <br>
                                            <div class="col-md-12 text-center">
                                                <a href="http://www.urllive.in:8084/UrlLive/url/shorter">
                                                    <button type="button" class="btn btn-default btn-sm">Create other shortened URL&nbsp;<i class="fa fa-link"></i></button>
                                                </a>
                                            </div>
                                        </div>
	                                <div class="clearfix"></div>
                                        <br>
                                        <!--extra information-->
                                        <button class="btn btn-wd btn-fill text-info" data-toggle="collapse" data-target="#analyze"><i class="ti-stats-up"></i>&nbsp;Analyze URL<i class="fa fa-angle-right"></i></button>
                                        <br><br>
                                        <div class="nav-tabs-navigation collapse" id="analyze">
                                            <div class="nav-tabs-wrapper">
                                                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                                    <li><a href="#os" data-toggle="tab"><b>OS</b></a></li>
                                                    <li><a href="#browser" data-toggle="tab"><b>BROWSER</b></a></li>
                                                    <li><a href="#owner" data-toggle="tab"><b>URL OWNER</b></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div id="my-tab-content" class="tab-content">
                                            <div class="tab-pane" id="os">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12">
                                                        <div class="card">
                                                            <div class="header">
                                                                <h4 class="title">OS VISITOR'S</h4>
                                                                <p class="category">Number of times link visited by OS:</p>
                                                            </div>
                                                            <div class="content table-responsive table-full-width">
                                                                <table class="table table-striped">
                                                                    <thead>
                                                                        <th><b>Operating System</b></th>
                                                                        <th><b>Number of Visitors</b></th>
                                                                        <th><b>Device Type</b></th>
                                                                    </thead>
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>Android</td>
                                                                            <td><%= C.getC_os_android() %></td>
                                                                            <td>Smart Phone</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>BlackBerry</td>
                                                                            <td><%= C.getC_os_blackberry() %></td>
                                                                            <td>Smart Phone</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>I-Phone</td>
                                                                            <td><%= C.getC_os_iphone() %></td>
                                                                            <td>Smart Phone</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Palm</td>
                                                                            <td><%= C.getC_os_palm() %></td>
                                                                            <td>Smart Phone</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Window Phone</td>
                                                                            <td><%= C.getC_os_win_phone() %></td>
                                                                            <td>Smart Phone</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>I-Pad</td>
                                                                            <td><%= C.getC_os_ipad() %></td>
                                                                            <td>Tablet</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Kindle</td>
                                                                            <td><%= C.getC_os_kindle() %></td>
                                                                            <td>Tablet</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Playbook</td>
                                                                            <td><%= C.getC_os_playbook() %></td>
                                                                            <td>Tablet</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Linux</td>
                                                                            <td><%= C.getC_os_linux() %></td>
                                                                            <td>Computer/Laptop</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Macintosh</td>
                                                                            <td><%= C.getC_os_macintosh() %></td>
                                                                            <td>Computer/Laptop</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Windows</td>
                                                                            <td><%= C.getC_os_windows() %></td>
                                                                            <td>Computer/Laptop</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Others</td>
                                                                            <td><%= C.getC_os_others() %></td>
                                                                            <td>Smart Phone/Tablet/Computer/Laptop</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="3"><b><u>REPORT</u></b>: If data glitches occur from the server-side, the default OS count will be in Android or Windows.</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Total Clicks</td>
                                                                            <td><%= counting %></td>
                                                                            <td>Information is fetched by client machine</td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="browser">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12">
                                                        <div class="card">
                                                            <div class="header">
                                                                <h4 class="title">BROWSER VISITOR'S</h4>
                                                                <p class="category">Number of times link visited on browser:</p>
                                                            </div>
                                                            <div class="content table-responsive table-full-width">
                                                                <table class="table table-striped">
                                                                    <thead>
                                                                        <th><b>Operating System</b></th>
                                                                        <th><b>Number of Visitors</b></th>
                                                                    </thead>
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>Chrome</td>
                                                                            <td><%= C.getC_browser_chrome() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Firefox</td>
                                                                            <td><%= C.getC_browser_firefox() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Safari</td>
                                                                            <td><%= C.getC_browser_safari() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Microsoft Edge</td>
                                                                            <td><%= C.getC_browser_internetexp() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Mozilla</td>
                                                                            <td><%= C.getC_browser_mozilla() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Opera</td>
                                                                            <td><%= C.getC_browser_opera() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>BlackBerry</td>
                                                                            <td><%= C.getC_browser_blackberry() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Others</td>
                                                                            <td><%= C.getC_browser_others() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="2"><b><u>REPORT</u></b>: Information is fetched from client machine or local internet connection.</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Total Clicks</td>
                                                                            <td><%= counting %></td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="owner">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12">
                                                        <div class="card">
                                                            <div class="header">
                                                                <h4 class="title">URL OWNER's INFO</h4>
                                                                <p class="category">Basic details of URL owner's:</p>
                                                            </div>
                                                            <div class="content table-responsive table-full-width">
                                                                <table class="table table-striped">
                                                                    <thead>
                                                                        <th><b>Query</b></th>
                                                                        <th><b>Detail</b></th>
                                                                    </thead>
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>Short URL</td>
                                                                            <td><a href='<%= U.getShort_url() %>' target='_blank'><%= U.getShort_url() %></a></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Purpose</td>
                                                                            <td><%= U.getUrl_type() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Creation Date</td>
                                                                            <td><%= U.getUrl_date() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Creation Time</td>
                                                                            <td><%= U.getUrl_time() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>OS</td>
                                                                            <td><%= U.getUrl_os() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Browser</td>
                                                                            <td><%= U.getUrl_browser() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>IP-Address</td>
                                                                            <td><a href='https://whatismyipaddress.com/ip/<%= U.getUrl_ip_add() %>' target='_blank' title='IP Address is based on system/device'><%= U.getUrl_ip_add() %></a>&nbsp;&nbsp;&nbsp;<i style="cursor:pointer;" class="fa fa-info-circle" onclick="alert('This information should not be used for emergency purposes, trying to find someone\'s exact physical address, or other purposes that would require 100% accuracy.');"></i>&nbsp;&nbsp;&nbsp;<b style="cursor:pointer;" onclick="alert('The IP address routes internet traffic to your computer. To clarify, it does not reveal your location. If someone was able to get your IP address they could learn a bit about your internet service, such as which provider you use to connect to the internet, but they really can\'t locate you, your home, or your office.');"><u>safe or not</u></b></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Location</td>
                                                                            <td><a href='https://www.google.com/maps/place/<%= U.getUrl_location() %>' target='_blank'><%= U.getUrl_location() %></a></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Country Code</td>
                                                                            <td><%= U.getUrl_country() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Region Area</td>
                                                                            <td><%= U.getUrl_region() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>City</td>
                                                                            <td><%= U.getUrl_city() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Postal Code</td>
                                                                            <td><%= U.getUrl_postal() %></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="2"><b><u>REPORT</u></b>: Information may be varies on the basis of mobile network or local area tower.</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="2">The IP address routes internet traffic to your computer. To clarify, it does not reveal your location. If someone was able to get your IP address they could learn a bit about your internet service, such as which provider you use to connect to the internet, but they really can't locate you, your home, or your office.</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="2"><code style="cursor:pointer;"><b>This information should not be used for emergency purposes, trying to find someone's exact physical address, or other purposes that would require 100% accuracy.</b></code></td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
<%
}
else
{
    //response.sendRedirect("http://www.urllive.in:8084/UrlLive/error/");
%>
<!--code-->
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="http://www.urllive.in:8084/UrlLive/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="http://www.urllive.in:8084/UrlLive/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Count URL Click</title>

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
<body>
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
                        <li class="active">
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
	                    <a class="navbar-brand">Count Clicks</a>
	                </div>
	                <div class="collapse navbar-collapse">
	                    <ul class="nav navbar-nav navbar-right">
	                        <li>
	                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                                <i class="ti-panel"></i>
									<p>Stats</p>
	                            </a>
	                        </li>
	                        <li class="dropdown">
	                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                                    <i class="ti-bell"></i>
	                                    <p class="notification">5</p>
										<p>Notifications</p>
										<b class="caret"></b>
	                              </a>
	                              <ul class="dropdown-menu">
	                                <li><a href="#">Notification 1</a></li>
	                                <li><a href="#">Notification 2</a></li>
	                                <li><a href="#">Notification 3</a></li>
	                                <li><a href="#">Notification 4</a></li>
	                                <li><a href="#">Another notification</a></li>
	                              </ul>
	                        </li>
							<li>
	                            <a href="#">
									<i class="ti-settings"></i>
									<p>Settings</p>
	                            </a>
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
                                        <h2 class="title"><b>Total URL Clicks</b></h2>
	                            </div>
	                            <div class="content">
                                        <span class="text-left">The total number of clicks that your link has received so far.The total number of clicks that your link has received so far.</span>
                                        <div class="clearfix margin"></div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <big style="font-size:75px"><b>0</b></big>
                                                    <big>&nbsp;(Invalid URL)</big>&nbsp;<a href="" class="text-success" title="REFRESH"><i class="fa fa-refresh fa-spin"></i></a>
                                                </div>
                                            </div>
                                            <div class="col-md-12 text-left">
                                                <br>
                                                <big><b>Our tool will count each click as one hit to the long URL, even if there are multiple clicks from the same person or device.</b></big>
                                                <br><br>
                                                <big><b>Check <a href="http://www.urllive.in:8084/UrlLive/url/clicks/">the total number of clicks</a> from other URL.</b></big>
                                            </div>
                                            <br>
                                            <div class="col-md-12 text-center">
                                                <a href="http://www.urllive.in:8084/UrlLive/url/shorter/">
                                                    <button type="button" class="btn btn-default btn-sm">Create other shortened URL&nbsp;<i class="fa fa-link"></i></button>
                                                </a>
                                            </div>
                                        </div>
	                                <div class="clearfix"></div>
                                        <br>
                                        <!--extra information-->
                                        <button class="btn btn-wd btn-fill text-info" data-toggle="collapse" data-target="#analyze"><i class="ti-stats-up"></i>&nbsp;Analyze URL<i class="fa fa-angle-right"></i></button>
                                        <br><br>
                                        <div class="nav-tabs-navigation collapse" id="analyze">
                                            <div class="nav-tabs-wrapper">
                                                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                                    <li><b>No record found...</b></li>
                                                </ul>
                                            </div>
                                        </div>
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
	                            <a href="#">
	                                Dashboard
	                            </a>
	                        </li>
	                    </ul>
	                </nav>
					<div class="copyright pull-right">
	                    <i class="ti-link"></i> <a href="http://www.urllive.in:8084/UrlLive/dashboard.jsp">urllive.in</a> | &copy; <script>document.write(new Date().getFullYear())</script>
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
<%
}
%>