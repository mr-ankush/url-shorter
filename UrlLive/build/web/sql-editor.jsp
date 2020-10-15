<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="http://www.urllive.in:8084/UrlLive/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="http://www.urllive.in:8084/UrlLive/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Online SQL Editor</title>

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
<body onload="IDE(); result1();">
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
	                    <a class="navbar-brand">Online IDE HTML Editor</a>
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
                                        <li class="active"><a href="http://www.urllive.in:8084/UrlLive/editor/html/">HTML IDE</a></li>
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
                                        <big><b>LAYERED IDE</b></big>
                                        &nbsp;
                                        <label class="switch">
                                            <input type="checkbox" id="ide" onchange="IDE();" checked>
                                            <span class="slider round"></span>
                                        </label>
                                        &nbsp;
                                        <big><b>BASIC IDE</b></big>
                                        <script>
                                            function IDE()
                                            {
                                                var ide = document.getElementById("ide");
                                                if(ide.checked==true)
                                                {
                                                    //alert("checked");
                                                    document.getElementById("layered").style.display = "none";
                                                    document.getElementById("basic").style.display = "block";
                                                    //document.getElementById("js2").innerHTML = "";
                                                    //result1();
                                                }
                                                else
                                                {
                                                    //alert("unchecked");
                                                    document.getElementById("layered").style.display = "block";
                                                    document.getElementById("basic").style.display = "none";
                                                    //document.getElementById("js2").innerHTML = "";
                                                    //result1();
                                                }
                                            }
                                        </script>
                                        <style>
                                        .switch {
                                          position: relative;
                                          display: inline-block;
                                          width: 55px;
                                          height: 25px;
                                        }

                                        .switch input { 
                                          opacity: 0;
                                          width: 0;
                                          height: 0;
                                        }

                                        .slider {
                                          position: absolute;
                                          cursor: pointer;
                                          top: 0;
                                          left: 0;
                                          right: 0;
                                          bottom: 0;
                                          background-color: slateblue;
                                          -webkit-transition: .4s;
                                          transition: .4s;
                                        }

                                        .slider:before {
                                          position: absolute;
                                          content: "";
                                          height: 18px;
                                          width: 18px;
                                          left: 5px;
                                          bottom: 4px;
                                          background-color: white;
                                          -webkit-transition: .4s;
                                          transition: .4s;
                                        }

                                        input:checked + .slider {
                                          background-color: #2196F3;
                                        }

                                        input:focus + .slider {
                                          box-shadow: 0 0 1px #2196F3;
                                        }

                                        input:checked + .slider:before {
                                          -webkit-transform: translateX(26px);
                                          -ms-transform: translateX(26px);
                                          transform: translateX(26px);
                                        }

                                        /* Rounded sliders */
                                        .slider.round {
                                          border-radius: 9px;
                                        }

                                        .slider.round:before {
                                          border-radius: 50%;
                                        }
                                        </style>
	                            </div>
	                            <div class="content" id="basic">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label><i>index.html</i></label><span style="cursor:pointer;" onclick="copyhtml1(); demo.showNotificationClipboard('top','right','.html file copied')">&nbsp;&nbsp;<i class="fa fa-clipboard"></i>copy</span>
                                                    <textarea rows="15" id="html1" onkeyup="result1()" class="form-control border-input" spellcheck="false"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label><i>style.css</i></label><span style="cursor:pointer;" onclick="copycss1(); demo.showNotificationClipboard('top','right','.css file copied')">&nbsp;&nbsp;<i class="fa fa-clipboard"></i>copy</span>
                                                    <textarea rows="15" id="css1" onkeyup="result1()" class="form-control border-input" spellcheck="false"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label><i>style.js</i></label><span style="cursor:pointer;" onclick="demo.showNotificationClipboard('top','right','.js platform is under development');">&nbsp;&nbsp;<i class="fa fa-clipboard"></i>copy</span>
                                                    <textarea rows="15" id="js1" onkeyup="result1()" class="form-control border-input" spellcheck="false" readonly>IDE JavaScript is under development.</textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <script>
                                            function copyhtml1()
                                            {
                                                var copyhtml1 = document.getElementById("html1");
                                                copyhtml1.select();
                                                document.execCommand("Copy");
                                                //alert("Copied the text: " + copyText.value);    
                                            }
                                            function copycss1()
                                            {
                                                var copycss1 = document.getElementById("css1");
                                                copycss1.select();
                                                document.execCommand("Copy");
                                                //alert("Copied the text: " + copyText.value);    
                                            }
                                            function copyjs1()
                                            {
                                                var copyjs1 = document.getElementById("js1");
                                                copyjs1.select();
                                                document.execCommand("Copy");
                                                //alert("Copied the text: " + copyText.value);    
                                            }
                                        </script>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label><b class="text-success">RESULT</b></label>&nbsp;<i class="fa fa-desktop text-success"></i>&nbsp;<i class="fa fa-tablet text-success"></i><i class="fa fa-mobile text-success"></i>
                                                    <div id="results1" class="col-lg-12 col-md-12 col-sm-12 sidebar-wrapper" style="background-color:#f2f2f2;min-height:600px;"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="content" id="layered" style="display:none;">
                                        <div>
                                            <div class="nav-tabs-wrapper">
                                                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                                    <li class="active"><a href="#html" data-toggle="tab"><b>HTML</b></a></li>
                                                    <li><a href="#css" data-toggle="tab"><b>CSS</b></a></li>
                                                    <li><a href="#js" data-toggle="tab"><b>JS</b></a></li>
                                                    <li><a href="#result" data-toggle="tab"><b>RESULT</b></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div id="my-tab-content" class="tab-content">
                                            <div class="tab-pane active" id="html">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12">
                                                        <div class="card">
                                                            <div class="content table-responsive table-full-width">
                                                                <label><i>index.html</i></label><span style="cursor:pointer;" onclick="copyhtml2(); demo.showNotificationClipboard('top','right','.html file copied')">&nbsp;&nbsp;<i class="fa fa-clipboard"></i>copy</span>
                                                                <textarea rows="18" id="html2" onkeyup="result1()" class="form-control border-input" spellcheck="false"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="css">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12">
                                                        <div class="card">
                                                            <div class="content table-responsive table-full-width">
                                                                <label><i>style.css</i></label><span style="cursor:pointer;" onclick="copycss2(); demo.showNotificationClipboard('top','right','.css file copied')">&nbsp;&nbsp;<i class="fa fa-clipboard"></i>copy</span>
                                                                <textarea rows="18" id="css2" onkeyup="result1()" class="form-control border-input" spellcheck="false"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="js">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12">
                                                        <div class="card">
                                                            <div class="content table-responsive table-full-width">
                                                                <label><i>style.js</i></label><span style="cursor:pointer;" onclick="demo.showNotificationClipboard('top','right','.js platform is under development');">&nbsp;&nbsp;<i class="fa fa-clipboard"></i>copy</span>
                                                                <textarea rows="18" id="js2" onkeyup="result1()" class="form-control border-input" spellcheck="false" readonly>IDE JavaScript is under development.</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <script>
                                            function copyhtml2()
                                            {
                                                var copyhtml2 = document.getElementById("html2");
                                                copyhtml2.select();
                                                document.execCommand("Copy");
                                                //alert("Copied the text: " + copyText.value);    
                                            }
                                            function copycss2()
                                            {
                                                var copycss2 = document.getElementById("css2");
                                                copycss2.select();
                                                document.execCommand("Copy");
                                                //alert("Copied the text: " + copyText.value);    
                                            }
                                            function copyjs2()
                                            {
                                                var copyjs2 = document.getElementById("js2");
                                                copyjs2.select();
                                                document.execCommand("Copy");
                                                //alert("Copied the text: " + copyText.value);    
                                            }
                                            </script>
                                            <div class="tab-pane" id="result">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12">
                                                        <div class="card">
                                                            <div class="header">
                                                                <label><b class="text-success">RESULT</b></label>&nbsp;<i class="fa fa-desktop text-success"></i>&nbsp;<i class="fa fa-tablet text-success"></i><i class="fa fa-mobile text-success"></i>
                                                            </div>
                                                            <div class="content table-responsive table-full-width">
                                                                <div id="results2" class="col-lg-12 col-md-12 col-sm-12 sidebar-wrapper" style="background-color:#f2f2f2;min-height:600px;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <script>
                                    function result1()
                                    {
                                        var ide = document.getElementById("ide");
                                        if(ide.checked==true)
                                        {
                                            // basic
                                            var html1 = document.getElementById("html1").value;
                                            var css1 = document.getElementById("css1").value;
                                            var js1 = document.getElementById("js1").value;
                                            document.getElementById('results1').innerHTML = html1+"\n"+"<style>"+css1+"</style>"+"\n"+"<script>"+js1+"&lt/script>";
                                            //document.getElementById("html2").val() = "";
                                            //document.getElementById("css2").val() = "";
                                            //document.getElementById('results2').innerHTML = "";
                                            //alert(js1);
                                        }
                                        else
                                        {
                                            // layered
                                            var html2 = document.getElementById("html2").value;
                                            var css2 = document.getElementById("css2").value;
                                            var js2 = document.getElementById("js2").value;
                                            document.getElementById('results2').innerHTML = html2+"\n"+"<style>"+css2+"</style>"+"\n"+"<script>"+js2+"&lt/script>";
                                            //document.getElementById("html1").val()) = "";
                                            //document.getElementById("css1").val() = "";
                                            //document.getElementById('results1').innerHTML = "";
                                        }
                                    }
                                    </script>
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