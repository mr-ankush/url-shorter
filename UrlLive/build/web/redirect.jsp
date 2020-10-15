<%@page import="model.Url"%>
<%@page import="dao.UrlDAO"%>
<%
        // fetching url
        String scheme=null;
        String serverName=null;
        int serverPort;
        String uri=null;
        String prmstr=null;
        String url=null;
        scheme = request.getScheme();             
        serverName = request.getServerName(); 
        serverPort = request.getServerPort();    
        uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
        prmstr = (String) request.getAttribute("javax.servlet.forward.query_string");
        if(prmstr==null)
        {
         url = scheme + "://" +serverName + ":" + serverPort + uri;
        }
        else
        {
         url = scheme + "://" +serverName + ":" + serverPort + uri + "?" + prmstr;
        }
        //out.println(url);
        //UrlDAO ud = new UrlDAO();
        //Url U = null;
        //U = ud.searchShortUrl(url);
        //out.println(U.getLong_url());
        //String action = U.getLong_url();
        //response.sendRedirect(action);
        %>
<!doctype html>
<html>
    <head>
        <title>URL REDIRECTING</title>
        <meta charset="UTF-8"/>	<!-- language supporter -->
        <html dir="ltr" lang="en">
        <link rel="apple-touch-icon" sizes="76x76" href="http://www.urllive.in:8084/UrlLive/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" sizes="96x96" href="http://www.urllive.in:8084/UrlLive/assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />
        <meta name="referrer" content="origin-when-crossorigin" id="meta_referrer"/>
        <meta http-equiv="refresh" content="0"/>	<!-- refresh page in time interval -->
	<meta name="dc.language" content="english">	<!-- language supporter -->
        <meta property="og:locale" content="en_US" />
        <meta property="og:locale:alternate" content="en_In" />
        <meta name="subject" content="Short your link - analyze URL">	<!-- descrition of web-site to S.E.O. -->
	<meta name="description" content="Free Web S.E.O. Tool"/>	<!-- descrition of web-site to S.E.O. -->
	<meta name="keywords" content="Short URL, Custom Url Create, Click Counts in real time"/>	<!-- keywords to S.E.O. -->
        <link rel="canonical" href="<%= url %>">
        <meta property="og:site_name" content="<%= url %>">
        <meta property="og:url" content="<%= url %>">
        <meta property="og:title" content="<%= url %>">
        <meta property="og:description" content="<%= url %>">
        <meta property="og:image" content="<%= url %>">
        <meta property="og:image:width" content="200">
        <meta property="og:image:height" content="200">
        <meta name="apple-itunes-app" content="app-id=544007664" app-argument="<%= url %>">
        <meta name="twitter:url" content="<%= url %>">
        <meta name="twitter:title" content="<%= url %>">
        <meta name="twitter:description" content="<%= url %>">
        <meta name="twitter:image" content="<%= url %>">
        <link rel="alternate" href="<%= url %>">
        <meta name="google" content="notranslate">
        <meta name="format-detection" content="telephone=no">
        <meta name="theme-color" content="#1BA691" />
        <meta name="mobile-web-app-capable" content="yes" />
        <style>
        .centered
        {
            display: block;
            margin-left: auto;
            margin-right: auto;
            margin-top: 10%;
            width: 400px;
        }
        .centered2
        {
            display: block;
            margin-left: auto;
            margin-right: auto;
            margin-top: 0px;
            width: 600px;
        }
        </style>
    </head>
    <body style="background:#f1f1f1;">     
    <input type="hidden" name='os' id='os'>
    <br>
    <input type="hidden" name='browser' id='browser'>
<!--    <img src="http://www.urllive.in:8084/UrlLive/assets/img/server.gif" alt="Loading..." class="centered">-->
    <img src="http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif" alt="Loading..." class="centered">
        
    </body>
<script>
(function () {
    'use strict';
    
    var module = {
        options: [],
        header: [navigator.platform, navigator.userAgent, navigator.appVersion, navigator.vendor, window.opera],
        dataos: [
            { name: 'Windows Phone', value: 'Windows Phone', version: 'OS' },
            { name: 'Windows', value: 'Win', version: 'NT' },
            { name: 'iPhone', value: 'iPhone', version: 'OS' },
            { name: 'iPad', value: 'iPad', version: 'OS' },
            { name: 'Kindle', value: 'Silk', version: 'Silk' },
            { name: 'Android', value: 'Android', version: 'Android' },
            { name: 'PlayBook', value: 'PlayBook', version: 'OS' },
            { name: 'BlackBerry', value: 'BlackBerry', version: '/' },
            { name: 'Macintosh', value: 'Mac', version: 'OS X' },
            { name: 'Linux', value: 'Linux', version: 'rv' },
            { name: 'Palm', value: 'Palm', version: 'PalmOS' }
        ],
        databrowser: [
            { name: 'Chrome', value: 'Chrome', version: 'Chrome' },
            { name: 'Firefox', value: 'Firefox', version: 'Firefox' },
            { name: 'Safari', value: 'Safari', version: 'Safari' },
            { name: 'Internet Explorer', value: 'MSIE', version: 'MSIE' },
            { name: 'Opera', value: 'Opera', version: 'Opera' },
            { name: 'BlackBerry', value: 'CLDC', version: 'CLDC' },
            { name: 'Mozilla', value: 'Mozilla', version: 'Mozilla' }
        ],
        init: function () {
            var agent = this.header.join(' '),
                os = this.matchItem(agent, this.dataos),
                browser = this.matchItem(agent, this.databrowser);
            
            return { os: os, browser: browser };
        },
        matchItem: function (string, data) {
            var i = 0,
                j = 0,
                html = '',
                regex,
                regexv,
                match,
                matches,
                version;
            
            for (i = 0; i < data.length; i += 1) {
                regex = new RegExp(data[i].value, 'i');
                match = regex.test(string);
                if (match) {
                    regexv = new RegExp(data[i].version + '[- /:;]([\\d._]+)', 'i');
                    matches = string.match(regexv);
                    version = '';
                    if (matches) { if (matches[1]) { matches = matches[1]; } }
                    if (matches) {
                        matches = matches.split(/[._]+/);
                        for (j = 0; j < matches.length; j += 1) {
                            if (j === 0) {
                                version += matches[j] + '.';
                            } else {
                                version += matches[j];
                            }
                        }
                    } else {
                        version = '0';
                    }
                    return {
                        name: data[i].name,
                        version: parseFloat(version)
                    };
                }
            }
            return { name: 'unknown', version: 0 };
        }
    };
    
    var e = module.init(),
        debug = '';
    
    debug += 'os.name = ' + e.os.name + '<br/>';
    debug += 'os.version = ' + e.os.version + '<br/>';
    debug += 'browser.name = ' + e.browser.name + '<br/>';
    debug += 'browser.version = ' + e.browser.version + '<br/>';
    
    debug += '<br/>';
    debug += 'navigator.userAgent = ' + navigator.userAgent + '<br/>';
    debug += 'navigator.appVersion = ' + navigator.appVersion + '<br/>';
    debug += 'navigator.platform = ' + navigator.platform + '<br/>';
    debug += 'navigator.vendor = ' + navigator.vendor + '<br/>';
    var os =e.os.name;
    var browser =e.browser.name;
    //document.getElementById('log').innerHTML = debug;
    document.getElementById('os').value = os;
    document.getElementById('browser').value = browser;
    window.location.replace("http://www.urllive.in:8084/UrlLive/redirecting?os="+e.os.name+"&browser="+e.browser.name+"&url=<%= url %>");
    //close();
}());
</script>
</html>