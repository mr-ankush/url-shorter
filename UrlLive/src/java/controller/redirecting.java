package controller;
import dao.CountsDAO;
import dao.ReportDAO;
import dao.UrlDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Counts;
import model.Report;
import model.Url;
@MultipartConfig(maxFileSize = 16177215)          // 16MB Data
public class redirecting extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try 
        {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>URL REDIRECTING</title>");
            out.println("<link rel=\"icon\" href='http://www.urllive.in:8084/UrlLive/assets/img/favicon.png'/>");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/bootstrap.min.css\" rel=\"stylesheet\" />");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/animate.min.css\" rel=\"stylesheet\"/>");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/paper-dashboard.css\" rel=\"stylesheet\"/>");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/demo.css\" rel=\"stylesheet\" />");
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
            out.println("<link href=\"http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css\" rel=\"stylesheet\">");
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/themify-icons.css\" rel=\"stylesheet\">");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/all.css\" rel=\"stylesheet\">");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/all.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/fontawesome.css\" rel=\"stylesheet\">");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/fontawesome.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"http://www.urllive.in:8084/UrlLive/assets/css/font-awesome.min.css\" rel=\"stylesheet\">");
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");    
            out.println("<style>.centered{display: block;margin-left: auto;margin-right: auto;margin-top: 5%;width: 400px;}</style>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/server.gif\" alt=\"Loading...\" class=\"centered\">");
            String os=null, browser=null, url=null;
            os = request.getParameter("os");
            browser = request.getParameter("browser");
            url = request.getParameter("url");
            //out.println(os+" "+browser+" "+url+" ");
            UrlDAO ud = new UrlDAO();
            Url U = null;
            CountsDAO cd = new CountsDAO();
            Counts C = null;        
            if(ud.checkUrl(url))
            {
                U = ud.searchShortUrl(url);
                String action = U.getLong_url();
                int addOS = 0;
                String AddOS;
                int addB = 0;
                String AddB;
                C = cd.searchShortUrlCount(url);
                ReportDAO rd = new ReportDAO();
                Report R = null;
                Report R1 = null;
                R1 = rd.searchReportedUrlCount(url);
//                if(!rd.searchReportedUrls(url))
//                {
//                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/server.gif\" alt=\"Loading...\" class=\"centered\">");
//                }
                //out.println(action);
                if(os.equalsIgnoreCase("Windows Phone"))
                {
                    // os is Windows Phone
                    addOS = Integer.parseInt(C.getC_os_win_phone());
                    addOS +=1;
                    AddOS = String.valueOf(addOS);
                    cd.OS_WindowPhoneCount(AddOS,url);
                    // browser is ...
                    if(browser.equalsIgnoreCase("Chrome"))
                    {
                        // browser is Chrome
                        addB = Integer.parseInt(C.getC_browser_chrome());
                        addB +=1;
                        AddB = String.valueOf(addB);
                        cd.Browser_ChromeCount(AddB,url);
                        //response.sendRedirect(action);
                        if(rd.searchReportedUrls(url))
                        {
                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");                            
                            out.println("<center>");
                            if(R1.getCounts().equals("1"))
                            {
                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                            }
                            else
                            {
                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                            }
                            out.println("<a href='"+action+"'>"
                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                    + "</a>"
                                    + "&nbsp;&nbsp;&nbsp;"
                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                    + "</center>");
                        }
                        else
                        {
                            response.sendRedirect(action);
                        }
                    }
                    else
                    {
                        if(browser.equalsIgnoreCase("Firefox"))
                        {
                            // browser is Firefox
                            addB = Integer.parseInt(C.getC_browser_firefox());
                            addB +=1;
                            AddB = String.valueOf(addB);
                            cd.Browser_FirefoxCount(AddB,url);
                            //response.sendRedirect(action);
                            if(rd.searchReportedUrls(url))
                            {
                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                out.println("<center>");
                                if(R1.getCounts().equals("1"))
                                {
                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                }
                                else
                                {
                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                }
                                out.println("<a href='"+action+"'>"
                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                        + "</a>"
                                        + "&nbsp;&nbsp;&nbsp;"
                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                        + "</center>");
                            }
                            else
                            {
                                response.sendRedirect(action);
                            }
                        }
                        else
                        {
                            if(browser.equalsIgnoreCase("Safari"))
                            {
                                // browser is Safari
                                addB = Integer.parseInt(C.getC_browser_safari());
                                addB +=1;
                                AddB = String.valueOf(addB);
                                cd.Browser_SafariCount(AddB,url);
                                //response.sendRedirect(action);
                                if(rd.searchReportedUrls(url))
                                {
                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                    out.println("<center>");
                                    if(R1.getCounts().equals("1"))
                                    {
                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                    }
                                    else
                                    {
                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                    }
                                    out.println("<a href='"+action+"'>"
                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                            + "</a>"
                                            + "&nbsp;&nbsp;&nbsp;"
                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                            + "</center>");
                                }
                                else
                                {
                                    response.sendRedirect(action);
                                }
                            }
                            else
                            {
                                if(browser.equalsIgnoreCase("Internet Explorer"))
                                {
                                    // browser is Internet Explorer
                                    addB = Integer.parseInt(C.getC_browser_internetexp());
                                    addB +=1;
                                    AddB = String.valueOf(addB);
                                    cd.Browser_InternetExpCount(AddB,url);
                                    //response.sendRedirect(action);
                                    if(rd.searchReportedUrls(url))
                                    {
                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                        out.println("<center>");
                                        if(R1.getCounts().equals("1"))
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                        }
                                        else
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                        }
                                        out.println("<a href='"+action+"'>"
                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                + "</a>"
                                                + "&nbsp;&nbsp;&nbsp;"
                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                + "</center>");
                                    }
                                    else
                                    {
                                        response.sendRedirect(action);
                                    }
                                }
                                else
                                {
                                    if(browser.equalsIgnoreCase("Opera"))
                                    {
                                        // browser is Opera
                                        addB = Integer.parseInt(C.getC_browser_opera());
                                        addB +=1;
                                        AddB = String.valueOf(addB);
                                        cd.Browser_OperaCount(AddB,url);
                                        //response.sendRedirect(action);
                                        if(rd.searchReportedUrls(url))
                                        {
                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                            out.println("<center>");
                                            if(R1.getCounts().equals("1"))
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                            }
                                            else
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                            }
                                            out.println("<a href='"+action+"'>"
                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                    + "</a>"
                                                    + "&nbsp;&nbsp;&nbsp;"
                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                    + "</center>");
                                        }
                                        else
                                        {
                                            response.sendRedirect(action);
                                        }
                                    }
                                    else
                                    {
                                        if(browser.equalsIgnoreCase("BlackBerry"))
                                        {
                                            // browser is BlackBerry
                                            addB = Integer.parseInt(C.getC_browser_blackberry());
                                            addB +=1;
                                            AddB = String.valueOf(addB);
                                            cd.Browser_BlackberryCount(AddB,url);
                                            //response.sendRedirect(action);
                                            if(rd.searchReportedUrls(url))
                                            {
                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                out.println("<center>");
                                                if(R1.getCounts().equals("1"))
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                }
                                                else
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                }
                                                out.println("<a href='"+action+"'>"
                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                        + "</a>"
                                                        + "&nbsp;&nbsp;&nbsp;"
                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                        + "</center>");
                                            }
                                            else
                                            {
                                                response.sendRedirect(action);
                                            }
                                        }
                                        else
                                        {
                                            if(browser.equalsIgnoreCase("Mozilla"))
                                            {
                                                // browser is Mozilla
                                                addB = Integer.parseInt(C.getC_browser_mozilla());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_MozillaCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                            else
                                            {
                                                // browser is Others
                                                addB = Integer.parseInt(C.getC_browser_others());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_OthersCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    if(os.equalsIgnoreCase("Windows"))
                    {
                        // os is Windows
                        addOS = Integer.parseInt(C.getC_os_windows());
                        addOS +=1;
                        AddOS = String.valueOf(addOS);
                        cd.OS_WindowsCount(AddOS,url);
                        // browser is ...
                        if(browser.equalsIgnoreCase("Chrome"))
                        {
                            // browser is Chrome
                            addB = Integer.parseInt(C.getC_browser_chrome());
                            addB +=1;
                            AddB = String.valueOf(addB);
                            cd.Browser_ChromeCount(AddB,url);
                            //response.sendRedirect(action);
                            if(rd.searchReportedUrls(url))
                            {
                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                out.println("<center>");
                                if(R1.getCounts().equals("1"))
                                {
                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                }
                                else
                                {
                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                }
                                out.println("<a href='"+action+"'>"
                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                        + "</a>"
                                        + "&nbsp;&nbsp;&nbsp;"
                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                        + "</center>");
                            }
                            else
                            {
                                response.sendRedirect(action);
                            }
                        }
                        else
                        {
                            if(browser.equalsIgnoreCase("Firefox"))
                            {
                                // browser is Firefox
                                addB = Integer.parseInt(C.getC_browser_firefox());
                                addB +=1;
                                AddB = String.valueOf(addB);
                                cd.Browser_FirefoxCount(AddB,url);
                                //response.sendRedirect(action);
                                if(rd.searchReportedUrls(url))
                                {
                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                    out.println("<center>");
                                    if(R1.getCounts().equals("1"))
                                    {
                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                    }
                                    else
                                    {
                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                    }
                                    out.println("<a href='"+action+"'>"
                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                            + "</a>"
                                            + "&nbsp;&nbsp;&nbsp;"
                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                            + "</center>");
                                }
                                else
                                {
                                    response.sendRedirect(action);
                                }
                            }
                            else
                            {
                                if(browser.equalsIgnoreCase("Safari"))
                                {
                                    // browser is Safari
                                    addB = Integer.parseInt(C.getC_browser_safari());
                                    addB +=1;
                                    AddB = String.valueOf(addB);
                                    cd.Browser_SafariCount(AddB,url);
                                    //response.sendRedirect(action);
                                    if(rd.searchReportedUrls(url))
                                    {
                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                        out.println("<center>");
                                        if(R1.getCounts().equals("1"))
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                        }
                                        else
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                        }
                                        out.println("<a href='"+action+"'>"
                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                + "</a>"
                                                + "&nbsp;&nbsp;&nbsp;"
                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                + "</center>");
                                    }
                                    else
                                    {
                                        response.sendRedirect(action);
                                    }
                                }
                                else
                                {
                                    if(browser.equalsIgnoreCase("Internet Explorer"))
                                    {
                                        // browser is Internet Explorer
                                        addB = Integer.parseInt(C.getC_browser_internetexp());
                                        addB +=1;
                                        AddB = String.valueOf(addB);
                                        cd.Browser_InternetExpCount(AddB,url);
                                        //response.sendRedirect(action);
                                        if(rd.searchReportedUrls(url))
                                        {
                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                            out.println("<center>");
                                            if(R1.getCounts().equals("1"))
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                            }
                                            else
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                            }
                                            out.println("<a href='"+action+"'>"
                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                    + "</a>"
                                                    + "&nbsp;&nbsp;&nbsp;"
                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                    + "</center>");
                                        }
                                        else
                                        {
                                            response.sendRedirect(action);
                                        }
                                    }
                                    else
                                    {
                                        if(browser.equalsIgnoreCase("Opera"))
                                        {
                                            // browser is Opera
                                            addB = Integer.parseInt(C.getC_browser_opera());
                                            addB +=1;
                                            AddB = String.valueOf(addB);
                                            cd.Browser_OperaCount(AddB,url);
                                            //response.sendRedirect(action);
                                            if(rd.searchReportedUrls(url))
                                            {
                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                out.println("<center>");
                                                if(R1.getCounts().equals("1"))
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                }
                                                else
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                }
                                                out.println("<a href='"+action+"'>"
                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                        + "</a>"
                                                        + "&nbsp;&nbsp;&nbsp;"
                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                        + "</center>");
                                            }
                                            else
                                            {
                                                response.sendRedirect(action);
                                            }
                                        }
                                        else
                                        {
                                            if(browser.equalsIgnoreCase("BlackBerry"))
                                            {
                                                // browser is BlackBerry
                                                addB = Integer.parseInt(C.getC_browser_blackberry());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_BlackberryCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                            else
                                            {
                                                if(browser.equalsIgnoreCase("Mozilla"))
                                                {
                                                    // browser is Mozilla
                                                    addB = Integer.parseInt(C.getC_browser_mozilla());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_MozillaCount(AddB,url);
                                                    //response.sendRedirect(action);
                                                    if(rd.searchReportedUrls(url))
                                                    {
                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                        out.println("<center>");
                                                        if(R1.getCounts().equals("1"))
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                        }
                                                        else
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                        }
                                                        out.println("<a href='"+action+"'>"
                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                + "</a>"
                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                + "</center>");
                                                    }
                                                    else
                                                    {
                                                        response.sendRedirect(action);
                                                    }
                                                }
                                                else
                                                {
                                                    // browser is Others
                                                    addB = Integer.parseInt(C.getC_browser_others());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_OthersCount(AddB,url);
                                                    response.sendRedirect(action);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if(os.equalsIgnoreCase("iPhone"))
                        {
                            // os is iPhone
                            addOS = Integer.parseInt(C.getC_os_iphone());
                            addOS +=1;
                            AddOS = String.valueOf(addOS);
                            cd.OS_IphoneCount(AddOS,url);
                            // browser is ...
                            if(browser.equalsIgnoreCase("Chrome"))
                            {
                                // browser is Chrome
                                addB = Integer.parseInt(C.getC_browser_chrome());
                                addB +=1;
                                AddB = String.valueOf(addB);
                                cd.Browser_ChromeCount(AddB,url);
                                //response.sendRedirect(action);
                                if(rd.searchReportedUrls(url))
                                {
                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                    out.println("<center>");
                                    if(R1.getCounts().equals("1"))
                                    {
                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                    }
                                    else
                                    {
                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                    }
                                    out.println("<a href='"+action+"'>"
                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                            + "</a>"
                                            + "&nbsp;&nbsp;&nbsp;"
                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                            + "</center>");
                                }
                                else
                                {
                                    response.sendRedirect(action);
                                }
                            }
                            else
                            {
                                if(browser.equalsIgnoreCase("Firefox"))
                                {
                                    // browser is Firefox
                                    addB = Integer.parseInt(C.getC_browser_firefox());
                                    addB +=1;
                                    AddB = String.valueOf(addB);
                                    cd.Browser_FirefoxCount(AddB,url);
                                    //response.sendRedirect(action);
                                    if(rd.searchReportedUrls(url))
                                    {
                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                        out.println("<center>");
                                        if(R1.getCounts().equals("1"))
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                        }
                                        else
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                        }
                                        out.println("<a href='"+action+"'>"
                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                + "</a>"
                                                + "&nbsp;&nbsp;&nbsp;"
                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                + "</center>");
                                    }
                                    else
                                    {
                                        response.sendRedirect(action);
                                    }
                                }
                                else
                                {
                                    if(browser.equalsIgnoreCase("Safari"))
                                    {
                                        // browser is Safari
                                        addB = Integer.parseInt(C.getC_browser_safari());
                                        addB +=1;
                                        AddB = String.valueOf(addB);
                                        cd.Browser_SafariCount(AddB,url);
                                        //response.sendRedirect(action);
                                        if(rd.searchReportedUrls(url))
                                        {
                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                            out.println("<center>");
                                            if(R1.getCounts().equals("1"))
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                            }
                                            else
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                            }
                                            out.println("<a href='"+action+"'>"
                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                    + "</a>"
                                                    + "&nbsp;&nbsp;&nbsp;"
                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                    + "</center>");
                                        }
                                        else
                                        {
                                            response.sendRedirect(action);
                                        }
                                    }
                                    else
                                    {
                                        if(browser.equalsIgnoreCase("Internet Explorer"))
                                        {
                                            // browser is Internet Explorer
                                            addB = Integer.parseInt(C.getC_browser_internetexp());
                                            addB +=1;
                                            AddB = String.valueOf(addB);
                                            cd.Browser_InternetExpCount(AddB,url);
                                            //response.sendRedirect(action);
                                            if(rd.searchReportedUrls(url))
                                            {
                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                out.println("<center>");
                                                if(R1.getCounts().equals("1"))
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                }
                                                else
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                }
                                                out.println("<a href='"+action+"'>"
                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                        + "</a>"
                                                        + "&nbsp;&nbsp;&nbsp;"
                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                        + "</center>");
                                            }
                                            else
                                            {
                                                response.sendRedirect(action);
                                            }
                                        }
                                        else
                                        {
                                            if(browser.equalsIgnoreCase("Opera"))
                                            {
                                                // browser is Opera
                                                addB = Integer.parseInt(C.getC_browser_opera());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_OperaCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                            else
                                            {
                                                if(browser.equalsIgnoreCase("BlackBerry"))
                                                {
                                                    // browser is BlackBerry
                                                    addB = Integer.parseInt(C.getC_browser_blackberry());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_BlackberryCount(AddB,url);
                                                    //response.sendRedirect(action);
                                                    if(rd.searchReportedUrls(url))
                                                    {
                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                        out.println("<center>");
                                                        if(R1.getCounts().equals("1"))
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                        }
                                                        else
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                        }
                                                        out.println("<a href='"+action+"'>"
                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                + "</a>"
                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                + "</center>");
                                                    }
                                                    else
                                                    {
                                                        response.sendRedirect(action);
                                                    }
                                                }
                                                else
                                                {
                                                    if(browser.equalsIgnoreCase("Mozilla"))
                                                    {
                                                        // browser is Mozilla
                                                        addB = Integer.parseInt(C.getC_browser_mozilla());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_MozillaCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        // browser is Others
                                                        addB = Integer.parseInt(C.getC_browser_others());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_OthersCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            if(os.equalsIgnoreCase("iPad"))
                            {
                                // os is iPad
                                addOS = Integer.parseInt(C.getC_os_ipad());
                                addOS +=1;
                                AddOS = String.valueOf(addOS);
                                cd.OS_IpadCount(AddOS,url);
                                // browser is ...
                                if(browser.equalsIgnoreCase("Chrome"))
                                {
                                    // browser is Chrome
                                    addB = Integer.parseInt(C.getC_browser_chrome());
                                    addB +=1;
                                    AddB = String.valueOf(addB);
                                    cd.Browser_ChromeCount(AddB,url);
                                    //response.sendRedirect(action);
                                    if(rd.searchReportedUrls(url))
                                    {
                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                        out.println("<center>");
                                        if(R1.getCounts().equals("1"))
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                        }
                                        else
                                        {
                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                        }
                                        out.println("<a href='"+action+"'>"
                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                + "</a>"
                                                + "&nbsp;&nbsp;&nbsp;"
                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                + "</center>");
                                    }
                                    else
                                    {
                                        response.sendRedirect(action);
                                    }
                                }
                                else
                                {
                                    if(browser.equalsIgnoreCase("Firefox"))
                                    {
                                        // browser is Firefox
                                        addB = Integer.parseInt(C.getC_browser_firefox());
                                        addB +=1;
                                        AddB = String.valueOf(addB);
                                        cd.Browser_FirefoxCount(AddB,url);
                                        //response.sendRedirect(action);
                                        if(rd.searchReportedUrls(url))
                                        {
                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                            out.println("<center>");
                                            if(R1.getCounts().equals("1"))
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                            }
                                            else
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                            }
                                            out.println("<a href='"+action+"'>"
                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                    + "</a>"
                                                    + "&nbsp;&nbsp;&nbsp;"
                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                    + "</center>");
                                        }
                                        else
                                        {
                                            response.sendRedirect(action);
                                        }
                                    }
                                    else
                                    {
                                        if(browser.equalsIgnoreCase("Safari"))
                                        {
                                            // browser is Safari
                                            addB = Integer.parseInt(C.getC_browser_safari());
                                            addB +=1;
                                            AddB = String.valueOf(addB);
                                            cd.Browser_SafariCount(AddB,url);
                                            //response.sendRedirect(action);
                                            if(rd.searchReportedUrls(url))
                                            {
                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                out.println("<center>");
                                                if(R1.getCounts().equals("1"))
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                }
                                                else
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                }
                                                out.println("<a href='"+action+"'>"
                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                        + "</a>"
                                                        + "&nbsp;&nbsp;&nbsp;"
                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                        + "</center>");
                                            }
                                            else
                                            {
                                                response.sendRedirect(action);
                                            }
                                        }
                                        else
                                        {
                                            if(browser.equalsIgnoreCase("Internet Explorer"))
                                            {
                                                // browser is Internet Explorer
                                                addB = Integer.parseInt(C.getC_browser_internetexp());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_InternetExpCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                            else
                                            {
                                                if(browser.equalsIgnoreCase("Opera"))
                                                {
                                                    // browser is Opera
                                                    addB = Integer.parseInt(C.getC_browser_opera());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_OperaCount(AddB,url);
                                                    //response.sendRedirect(action);
                                                    if(rd.searchReportedUrls(url))
                                                    {
                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                        out.println("<center>");
                                                        if(R1.getCounts().equals("1"))
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                        }
                                                        else
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                        }
                                                        out.println("<a href='"+action+"'>"
                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                + "</a>"
                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                + "</center>");
                                                    }
                                                    else
                                                    {
                                                        response.sendRedirect(action);
                                                    }
                                                }
                                                else
                                                {
                                                    if(browser.equalsIgnoreCase("BlackBerry"))
                                                    {
                                                        // browser is BlackBerry
                                                        addB = Integer.parseInt(C.getC_browser_blackberry());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_BlackberryCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(browser.equalsIgnoreCase("Mozilla"))
                                                        {
                                                            // browser is Mozilla
                                                            addB = Integer.parseInt(C.getC_browser_mozilla());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_MozillaCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            // browser is Others
                                                            addB = Integer.parseInt(C.getC_browser_others());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_OthersCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else
                            {
                                if(os.equalsIgnoreCase("Kindle"))
                                {
                                    // os is Kindle
                                    addOS = Integer.parseInt(C.getC_os_kindle());
                                    addOS +=1;
                                    AddOS = String.valueOf(addOS);
                                    cd.OS_KindleCount(AddOS,url);
                                    // browser is ...
                                    if(browser.equalsIgnoreCase("Chrome"))
                                    {
                                        // browser is Chrome
                                        addB = Integer.parseInt(C.getC_browser_chrome());
                                        addB +=1;
                                        AddB = String.valueOf(addB);
                                        cd.Browser_ChromeCount(AddB,url);
                                        //response.sendRedirect(action);
                                        if(rd.searchReportedUrls(url))
                                        {
                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                            out.println("<center>");
                                            if(R1.getCounts().equals("1"))
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                            }
                                            else
                                            {
                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                            }
                                            out.println("<a href='"+action+"'>"
                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                    + "</a>"
                                                    + "&nbsp;&nbsp;&nbsp;"
                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                    + "</center>");
                                        }
                                        else
                                        {
                                            response.sendRedirect(action);
                                        }
                                    }
                                    else
                                    {
                                        if(browser.equalsIgnoreCase("Firefox"))
                                        {
                                            // browser is Firefox
                                            addB = Integer.parseInt(C.getC_browser_firefox());
                                            addB +=1;
                                            AddB = String.valueOf(addB);
                                            cd.Browser_FirefoxCount(AddB,url);
                                            //response.sendRedirect(action);
                                            if(rd.searchReportedUrls(url))
                                            {
                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                out.println("<center>");
                                                if(R1.getCounts().equals("1"))
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                }
                                                else
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                }
                                                out.println("<a href='"+action+"'>"
                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                        + "</a>"
                                                        + "&nbsp;&nbsp;&nbsp;"
                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                        + "</center>");
                                            }
                                            else
                                            {
                                                response.sendRedirect(action);
                                            }
                                        }
                                        else
                                        {
                                            if(browser.equalsIgnoreCase("Safari"))
                                            {
                                                // browser is Safari
                                                addB = Integer.parseInt(C.getC_browser_safari());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_SafariCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                            else
                                            {
                                                if(browser.equalsIgnoreCase("Internet Explorer"))
                                                {
                                                    // browser is Internet Explorer
                                                    addB = Integer.parseInt(C.getC_browser_internetexp());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_InternetExpCount(AddB,url);
                                                    //response.sendRedirect(action);
                                                    if(rd.searchReportedUrls(url))
                                                    {
                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                        out.println("<center>");
                                                        if(R1.getCounts().equals("1"))
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                        }
                                                        else
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                        }
                                                        out.println("<a href='"+action+"'>"
                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                + "</a>"
                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                + "</center>");
                                                    }
                                                    else
                                                    {
                                                        response.sendRedirect(action);
                                                    }
                                                }
                                                else
                                                {
                                                    if(browser.equalsIgnoreCase("Opera"))
                                                    {
                                                        // browser is Opera
                                                        addB = Integer.parseInt(C.getC_browser_opera());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_OperaCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(browser.equalsIgnoreCase("BlackBerry"))
                                                        {
                                                            // browser is BlackBerry
                                                            addB = Integer.parseInt(C.getC_browser_blackberry());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_BlackberryCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(browser.equalsIgnoreCase("Mozilla"))
                                                            {
                                                                // browser is Mozilla
                                                                addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_MozillaCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                // browser is Others
                                                                addB = Integer.parseInt(C.getC_browser_others());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_OthersCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    if(os.equalsIgnoreCase("Android"))
                                    {
                                        // os is Android
                                        addOS = Integer.parseInt(C.getC_os_android());
                                        addOS +=1;
                                        AddOS = String.valueOf(addOS);
                                        cd.OS_AndroidCount(AddOS,url);
                                        // browser is ...
                                        if(browser.equalsIgnoreCase("Chrome"))
                                        {
                                            // browser is Chrome
                                            addB = Integer.parseInt(C.getC_browser_chrome());
                                            addB +=1;
                                            AddB = String.valueOf(addB);
                                            cd.Browser_ChromeCount(AddB,url);
                                            //response.sendRedirect(action);
                                            if(rd.searchReportedUrls(url))
                                            {
                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                out.println("<center>");
                                                if(R1.getCounts().equals("1"))
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                }
                                                else
                                                {
                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                }
                                                out.println("<a href='"+action+"'>"
                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                        + "</a>"
                                                        + "&nbsp;&nbsp;&nbsp;"
                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                        + "</center>");
                                            }
                                            else
                                            {
                                                response.sendRedirect(action);
                                            }
                                        }
                                        else
                                        {
                                            if(browser.equalsIgnoreCase("Firefox"))
                                            {
                                                // browser is Firefox
                                                addB = Integer.parseInt(C.getC_browser_firefox());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_FirefoxCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                            else
                                            {
                                                if(browser.equalsIgnoreCase("Safari"))
                                                {
                                                    // browser is Safari
                                                    addB = Integer.parseInt(C.getC_browser_safari());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_SafariCount(AddB,url);
                                                    //response.sendRedirect(action);
                                                    if(rd.searchReportedUrls(url))
                                                    {
                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                        out.println("<center>");
                                                        if(R1.getCounts().equals("1"))
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                        }
                                                        else
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                        }
                                                        out.println("<a href='"+action+"'>"
                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                + "</a>"
                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                + "</center>");
                                                    }
                                                    else
                                                    {
                                                        response.sendRedirect(action);
                                                    }
                                                }
                                                else
                                                {
                                                    if(browser.equalsIgnoreCase("Internet Explorer"))
                                                    {
                                                        // browser is Internet Explorer
                                                        addB = Integer.parseInt(C.getC_browser_internetexp());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_InternetExpCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(browser.equalsIgnoreCase("Opera"))
                                                        {
                                                            // browser is Opera
                                                            addB = Integer.parseInt(C.getC_browser_opera());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_OperaCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(browser.equalsIgnoreCase("BlackBerry"))
                                                            {
                                                                // browser is BlackBerry
                                                                addB = Integer.parseInt(C.getC_browser_blackberry());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_BlackberryCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                if(browser.equalsIgnoreCase("Mozilla"))
                                                                {
                                                                    // browser is Mozilla
                                                                    addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_MozillaCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    // browser is Others
                                                                    addB = Integer.parseInt(C.getC_browser_others());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_OthersCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        if(os.equalsIgnoreCase("PlayBook"))
                                        {
                                            // os is PlayBook
                                            addOS = Integer.parseInt(C.getC_os_playbook());
                                            addOS +=1;
                                            AddOS = String.valueOf(addOS);
                                            cd.OS_PlaybookCount(AddOS,url);
                                            // browser is ...
                                            if(browser.equalsIgnoreCase("Chrome"))
                                            {
                                                // browser is Chrome
                                                addB = Integer.parseInt(C.getC_browser_chrome());
                                                addB +=1;
                                                AddB = String.valueOf(addB);
                                                cd.Browser_ChromeCount(AddB,url);
                                                //response.sendRedirect(action);
                                                if(rd.searchReportedUrls(url))
                                                {
                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                    out.println("<center>");
                                                    if(R1.getCounts().equals("1"))
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                    }
                                                    out.println("<a href='"+action+"'>"
                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                            + "</a>"
                                                            + "&nbsp;&nbsp;&nbsp;"
                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                            + "</center>");
                                                }
                                                else
                                                {
                                                    response.sendRedirect(action);
                                                }
                                            }
                                            else
                                            {
                                                if(browser.equalsIgnoreCase("Firefox"))
                                                {
                                                    // browser is Firefox
                                                    addB = Integer.parseInt(C.getC_browser_firefox());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_FirefoxCount(AddB,url);
                                                    //response.sendRedirect(action);
                                                    if(rd.searchReportedUrls(url))
                                                    {
                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                        out.println("<center>");
                                                        if(R1.getCounts().equals("1"))
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                        }
                                                        else
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                        }
                                                        out.println("<a href='"+action+"'>"
                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                + "</a>"
                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                + "</center>");
                                                    }
                                                    else
                                                    {
                                                        response.sendRedirect(action);
                                                    }
                                                }
                                                else
                                                {
                                                    if(browser.equalsIgnoreCase("Safari"))
                                                    {
                                                        // browser is Safari
                                                        addB = Integer.parseInt(C.getC_browser_safari());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_SafariCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(browser.equalsIgnoreCase("Internet Explorer"))
                                                        {
                                                            // browser is Internet Explorer
                                                            addB = Integer.parseInt(C.getC_browser_internetexp());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_InternetExpCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(browser.equalsIgnoreCase("Opera"))
                                                            {
                                                                // browser is Opera
                                                                addB = Integer.parseInt(C.getC_browser_opera());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_OperaCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                if(browser.equalsIgnoreCase("BlackBerry"))
                                                                {
                                                                    // browser is BlackBerry
                                                                    addB = Integer.parseInt(C.getC_browser_blackberry());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_BlackberryCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(browser.equalsIgnoreCase("Mozilla"))
                                                                    {
                                                                        // browser is Mozilla
                                                                        addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                        addB +=1;
                                                                        AddB = String.valueOf(addB);
                                                                        cd.Browser_MozillaCount(AddB,url);
                                                                        //response.sendRedirect(action);
                                                                        if(rd.searchReportedUrls(url))
                                                                        {
                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                            out.println("<center>");
                                                                            if(R1.getCounts().equals("1"))
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                            }
                                                                            else
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                            }
                                                                            out.println("<a href='"+action+"'>"
                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                    + "</a>"
                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                    + "</center>");
                                                                        }
                                                                        else
                                                                        {
                                                                            response.sendRedirect(action);
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        // browser is Others
                                                                        addB = Integer.parseInt(C.getC_browser_others());
                                                                        addB +=1;
                                                                        AddB = String.valueOf(addB);
                                                                        cd.Browser_OthersCount(AddB,url);
                                                                        //response.sendRedirect(action);
                                                                        if(rd.searchReportedUrls(url))
                                                                        {
                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                            out.println("<center>");
                                                                            if(R1.getCounts().equals("1"))
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                            }
                                                                            else
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                            }
                                                                            out.println("<a href='"+action+"'>"
                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                    + "</a>"
                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                    + "</center>");
                                                                        }
                                                                        else
                                                                        {
                                                                            response.sendRedirect(action);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else
                                        {
                                            if(os.equalsIgnoreCase("BlackBerry"))
                                            {
                                                // os is BlackBerry
                                                addOS = Integer.parseInt(C.getC_os_blackberry());
                                                addOS +=1;
                                                AddOS = String.valueOf(addOS);
                                                cd.OS_BlackberryCount(AddOS,url);
                                                // browser is ...
                                                if(browser.equalsIgnoreCase("Chrome"))
                                                {
                                                    // browser is Chrome
                                                    addB = Integer.parseInt(C.getC_browser_chrome());
                                                    addB +=1;
                                                    AddB = String.valueOf(addB);
                                                    cd.Browser_ChromeCount(AddB,url);
                                                    //response.sendRedirect(action);
                                                    if(rd.searchReportedUrls(url))
                                                    {
                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                        out.println("<center>");
                                                        if(R1.getCounts().equals("1"))
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                        }
                                                        else
                                                        {
                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                        }
                                                        out.println("<a href='"+action+"'>"
                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                + "</a>"
                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                + "</center>");
                                                    }
                                                    else
                                                    {
                                                        response.sendRedirect(action);
                                                    }
                                                }
                                                else
                                                {
                                                    if(browser.equalsIgnoreCase("Firefox"))
                                                    {
                                                        // browser is Firefox
                                                        addB = Integer.parseInt(C.getC_browser_firefox());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_FirefoxCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(browser.equalsIgnoreCase("Safari"))
                                                        {
                                                            // browser is Safari
                                                            addB = Integer.parseInt(C.getC_browser_safari());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_SafariCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(browser.equalsIgnoreCase("Internet Explorer"))
                                                            {
                                                                // browser is Internet Explorer
                                                                addB = Integer.parseInt(C.getC_browser_internetexp());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_InternetExpCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                if(browser.equalsIgnoreCase("Opera"))
                                                                {
                                                                    // browser is Opera
                                                                    addB = Integer.parseInt(C.getC_browser_opera());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_OperaCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(browser.equalsIgnoreCase("BlackBerry"))
                                                                    {
                                                                        // browser is BlackBerry
                                                                        addB = Integer.parseInt(C.getC_browser_blackberry());
                                                                        addB +=1;
                                                                        AddB = String.valueOf(addB);
                                                                        cd.Browser_BlackberryCount(AddB,url);
                                                                        //response.sendRedirect(action);
                                                                        if(rd.searchReportedUrls(url))
                                                                        {
                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                            out.println("<center>");
                                                                            if(R1.getCounts().equals("1"))
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                            }
                                                                            else
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                            }
                                                                            out.println("<a href='"+action+"'>"
                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                    + "</a>"
                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                    + "</center>");
                                                                        }
                                                                        else
                                                                        {
                                                                            response.sendRedirect(action);
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        if(browser.equalsIgnoreCase("Mozilla"))
                                                                        {
                                                                            // browser is Mozilla
                                                                            addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                            addB +=1;
                                                                            AddB = String.valueOf(addB);
                                                                            cd.Browser_MozillaCount(AddB,url);
                                                                            //response.sendRedirect(action);
                                                                            if(rd.searchReportedUrls(url))
                                                                            {
                                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                out.println("<center>");
                                                                                if(R1.getCounts().equals("1"))
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                }
                                                                                out.println("<a href='"+action+"'>"
                                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                        + "</a>"
                                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                        + "</center>");
                                                                            }
                                                                            else
                                                                            {
                                                                                response.sendRedirect(action);
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            // browser is Others
                                                                            addB = Integer.parseInt(C.getC_browser_others());
                                                                            addB +=1;
                                                                            AddB = String.valueOf(addB);
                                                                            cd.Browser_OthersCount(AddB,url);
                                                                            //response.sendRedirect(action);
                                                                            if(rd.searchReportedUrls(url))
                                                                            {
                                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                out.println("<center>");
                                                                                if(R1.getCounts().equals("1"))
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                }
                                                                                out.println("<a href='"+action+"'>"
                                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                        + "</a>"
                                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                        + "</center>");
                                                                            }
                                                                            else
                                                                            {
                                                                                response.sendRedirect(action);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                if(os.equalsIgnoreCase("Macintosh"))
                                                {
                                                    // os is Macintosh
                                                    addOS = Integer.parseInt(C.getC_os_macintosh());
                                                    addOS +=1;
                                                    AddOS = String.valueOf(addOS);
                                                    cd.OS_MacintoshCount(AddOS,url);
                                                    // browser is ...
                                                    if(browser.equalsIgnoreCase("Chrome"))
                                                    {
                                                        // browser is Chrome
                                                        addB = Integer.parseInt(C.getC_browser_chrome());
                                                        addB +=1;
                                                        AddB = String.valueOf(addB);
                                                        cd.Browser_ChromeCount(AddB,url);
                                                        //response.sendRedirect(action);
                                                        if(rd.searchReportedUrls(url))
                                                        {
                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                            out.println("<center>");
                                                            if(R1.getCounts().equals("1"))
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                            }
                                                            out.println("<a href='"+action+"'>"
                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                    + "</a>"
                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                    + "</center>");
                                                        }
                                                        else
                                                        {
                                                            response.sendRedirect(action);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(browser.equalsIgnoreCase("Firefox"))
                                                        {
                                                            // browser is Firefox
                                                            addB = Integer.parseInt(C.getC_browser_firefox());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_FirefoxCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(browser.equalsIgnoreCase("Safari"))
                                                            {
                                                                // browser is Safari
                                                                addB = Integer.parseInt(C.getC_browser_safari());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_SafariCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                if(browser.equalsIgnoreCase("Internet Explorer"))
                                                                {
                                                                    // browser is Internet Explorer
                                                                    addB = Integer.parseInt(C.getC_browser_internetexp());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_InternetExpCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(browser.equalsIgnoreCase("Opera"))
                                                                    {
                                                                        // browser is Opera
                                                                        addB = Integer.parseInt(C.getC_browser_opera());
                                                                        addB +=1;
                                                                        AddB = String.valueOf(addB);
                                                                        cd.Browser_OperaCount(AddB,url);
                                                                        //response.sendRedirect(action);
                                                                        if(rd.searchReportedUrls(url))
                                                                        {
                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                            out.println("<center>");
                                                                            if(R1.getCounts().equals("1"))
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                            }
                                                                            else
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                            }
                                                                            out.println("<a href='"+action+"'>"
                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                    + "</a>"
                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                    + "</center>");
                                                                        }
                                                                        else
                                                                        {
                                                                            response.sendRedirect(action);
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        if(browser.equalsIgnoreCase("BlackBerry"))
                                                                        {
                                                                            // browser is BlackBerry
                                                                            addB = Integer.parseInt(C.getC_browser_blackberry());
                                                                            addB +=1;
                                                                            AddB = String.valueOf(addB);
                                                                            cd.Browser_BlackberryCount(AddB,url);
                                                                            //response.sendRedirect(action);
                                                                            if(rd.searchReportedUrls(url))
                                                                            {
                                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                out.println("<center>");
                                                                                if(R1.getCounts().equals("1"))
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                }
                                                                                out.println("<a href='"+action+"'>"
                                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                        + "</a>"
                                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                        + "</center>");
                                                                            }
                                                                            else
                                                                            {
                                                                                response.sendRedirect(action);
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            if(browser.equalsIgnoreCase("Mozilla"))
                                                                            {
                                                                                // browser is Mozilla
                                                                                addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                                addB +=1;
                                                                                AddB = String.valueOf(addB);
                                                                                cd.Browser_MozillaCount(AddB,url);
                                                                                //response.sendRedirect(action);
                                                                                if(rd.searchReportedUrls(url))
                                                                                {
                                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                    out.println("<center>");
                                                                                    if(R1.getCounts().equals("1"))
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                    }
                                                                                    out.println("<a href='"+action+"'>"
                                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                            + "</a>"
                                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                            + "</center>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    response.sendRedirect(action);
                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                // browser is Others
                                                                                addB = Integer.parseInt(C.getC_browser_others());
                                                                                addB +=1;
                                                                                AddB = String.valueOf(addB);
                                                                                cd.Browser_OthersCount(AddB,url);
                                                                                //response.sendRedirect(action);
                                                                                if(rd.searchReportedUrls(url))
                                                                                {
                                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                    out.println("<center>");
                                                                                    if(R1.getCounts().equals("1"))
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                    }
                                                                                    out.println("<a href='"+action+"'>"
                                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                            + "</a>"
                                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                            + "</center>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    response.sendRedirect(action);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                else
                                                {
                                                    if(os.equalsIgnoreCase("Linux"))
                                                    {
                                                        // os is Linux
                                                        addOS = Integer.parseInt(C.getC_os_linux());
                                                        addOS +=1;
                                                        AddOS = String.valueOf(addOS);
                                                        cd.OS_LinuxCount(AddOS,url);
                                                        // browser is ...
                                                        if(browser.equalsIgnoreCase("Chrome"))
                                                        {
                                                            // browser is Chrome
                                                            addB = Integer.parseInt(C.getC_browser_chrome());
                                                            addB +=1;
                                                            AddB = String.valueOf(addB);
                                                            cd.Browser_ChromeCount(AddB,url);
                                                            //response.sendRedirect(action);
                                                            if(rd.searchReportedUrls(url))
                                                            {
                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                out.println("<center>");
                                                                if(R1.getCounts().equals("1"))
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                }
                                                                else
                                                                {
                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                }
                                                                out.println("<a href='"+action+"'>"
                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                        + "</a>"
                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                        + "</center>");
                                                            }
                                                            else
                                                            {
                                                                response.sendRedirect(action);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            if(browser.equalsIgnoreCase("Firefox"))
                                                            {
                                                                // browser is Firefox
                                                                addB = Integer.parseInt(C.getC_browser_firefox());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_FirefoxCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                if(browser.equalsIgnoreCase("Safari"))
                                                                {
                                                                    // browser is Safari
                                                                    addB = Integer.parseInt(C.getC_browser_safari());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_SafariCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(browser.equalsIgnoreCase("Internet Explorer"))
                                                                    {
                                                                        // browser is Internet Explorer
                                                                        addB = Integer.parseInt(C.getC_browser_internetexp());
                                                                        addB +=1;
                                                                        AddB = String.valueOf(addB);
                                                                        cd.Browser_InternetExpCount(AddB,url);
                                                                        //response.sendRedirect(action);
                                                                        if(rd.searchReportedUrls(url))
                                                                        {
                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                            out.println("<center>");
                                                                            if(R1.getCounts().equals("1"))
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                            }
                                                                            else
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                            }
                                                                            out.println("<a href='"+action+"'>"
                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                    + "</a>"
                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                    + "</center>");
                                                                        }
                                                                        else
                                                                        {
                                                                            response.sendRedirect(action);
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        if(browser.equalsIgnoreCase("Opera"))
                                                                        {
                                                                            // browser is Opera
                                                                            addB = Integer.parseInt(C.getC_browser_opera());
                                                                            addB +=1;
                                                                            AddB = String.valueOf(addB);
                                                                            cd.Browser_OperaCount(AddB,url);
                                                                            //response.sendRedirect(action);
                                                                            if(rd.searchReportedUrls(url))
                                                                            {
                                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                out.println("<center>");
                                                                                if(R1.getCounts().equals("1"))
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                }
                                                                                out.println("<a href='"+action+"'>"
                                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                        + "</a>"
                                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                        + "</center>");
                                                                            }
                                                                            else
                                                                            {
                                                                                response.sendRedirect(action);
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            if(browser.equalsIgnoreCase("BlackBerry"))
                                                                            {
                                                                                // browser is BlackBerry
                                                                                addB = Integer.parseInt(C.getC_browser_blackberry());
                                                                                addB +=1;
                                                                                AddB = String.valueOf(addB);
                                                                                cd.Browser_BlackberryCount(AddB,url);
                                                                                //response.sendRedirect(action);
                                                                                if(rd.searchReportedUrls(url))
                                                                                {
                                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                    out.println("<center>");
                                                                                    if(R1.getCounts().equals("1"))
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                    }
                                                                                    out.println("<a href='"+action+"'>"
                                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                            + "</a>"
                                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                            + "</center>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    response.sendRedirect(action);
                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                if(browser.equalsIgnoreCase("Mozilla"))
                                                                                {
                                                                                    // browser is Mozilla
                                                                                    addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                                    addB +=1;
                                                                                    AddB = String.valueOf(addB);
                                                                                    cd.Browser_MozillaCount(AddB,url);
                                                                                    //response.sendRedirect(action);
                                                                                    if(rd.searchReportedUrls(url))
                                                                                    {
                                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                        out.println("<center>");
                                                                                        if(R1.getCounts().equals("1"))
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                        }
                                                                                        out.println("<a href='"+action+"'>"
                                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                + "</a>"
                                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                + "</center>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        response.sendRedirect(action);
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    // browser is Others
                                                                                    addB = Integer.parseInt(C.getC_browser_others());
                                                                                    addB +=1;
                                                                                    AddB = String.valueOf(addB);
                                                                                    cd.Browser_OthersCount(AddB,url);
                                                                                    //response.sendRedirect(action);
                                                                                    if(rd.searchReportedUrls(url))
                                                                                    {
                                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                        out.println("<center>");
                                                                                        if(R1.getCounts().equals("1"))
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                        }
                                                                                        out.println("<a href='"+action+"'>"
                                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                + "</a>"
                                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                + "</center>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        response.sendRedirect(action);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if(os.equalsIgnoreCase("Palm"))
                                                        {
                                                            // os is Palm
                                                            addOS = Integer.parseInt(C.getC_os_palm());
                                                            addOS +=1;
                                                            AddOS = String.valueOf(addOS);
                                                            cd.OS_PalmCount(AddOS,url);
                                                            // browser is ...
                                                            if(browser.equalsIgnoreCase("Chrome"))
                                                            {
                                                                // browser is Chrome
                                                                addB = Integer.parseInt(C.getC_browser_chrome());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_ChromeCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                if(browser.equalsIgnoreCase("Firefox"))
                                                                {
                                                                    // browser is Firefox
                                                                    addB = Integer.parseInt(C.getC_browser_firefox());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_FirefoxCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(browser.equalsIgnoreCase("Safari"))
                                                                    {
                                                                        // browser is Safari
                                                                        addB = Integer.parseInt(C.getC_browser_safari());
                                                                        addB +=1;
                                                                        AddB = String.valueOf(addB);
                                                                        cd.Browser_SafariCount(AddB,url);
                                                                        //response.sendRedirect(action);
                                                                        if(rd.searchReportedUrls(url))
                                                                        {
                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                            out.println("<center>");
                                                                            if(R1.getCounts().equals("1"))
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                            }
                                                                            else
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                            }
                                                                            out.println("<a href='"+action+"'>"
                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                    + "</a>"
                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                    + "</center>");
                                                                        }
                                                                        else
                                                                        {
                                                                            response.sendRedirect(action);
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        if(browser.equalsIgnoreCase("Internet Explorer"))
                                                                        {
                                                                            // browser is Internet Explorer
                                                                            addB = Integer.parseInt(C.getC_browser_internetexp());
                                                                            addB +=1;
                                                                            AddB = String.valueOf(addB);
                                                                            cd.Browser_InternetExpCount(AddB,url);
                                                                            //response.sendRedirect(action);
                                                                            if(rd.searchReportedUrls(url))
                                                                            {
                                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                out.println("<center>");
                                                                                if(R1.getCounts().equals("1"))
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                }
                                                                                out.println("<a href='"+action+"'>"
                                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                        + "</a>"
                                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                        + "</center>");
                                                                            }
                                                                            else
                                                                            {
                                                                                response.sendRedirect(action);
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            if(browser.equalsIgnoreCase("Opera"))
                                                                            {
                                                                                // browser is Opera
                                                                                addB = Integer.parseInt(C.getC_browser_opera());
                                                                                addB +=1;
                                                                                AddB = String.valueOf(addB);
                                                                                cd.Browser_OperaCount(AddB,url);
                                                                                //response.sendRedirect(action);
                                                                                if(rd.searchReportedUrls(url))
                                                                                {
                                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                    out.println("<center>");
                                                                                    if(R1.getCounts().equals("1"))
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                    }
                                                                                    out.println("<a href='"+action+"'>"
                                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                            + "</a>"
                                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                            + "</center>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    response.sendRedirect(action);
                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                if(browser.equalsIgnoreCase("BlackBerry"))
                                                                                {
                                                                                    // browser is BlackBerry
                                                                                    addB = Integer.parseInt(C.getC_browser_blackberry());
                                                                                    addB +=1;
                                                                                    AddB = String.valueOf(addB);
                                                                                    cd.Browser_BlackberryCount(AddB,url);
                                                                                    //response.sendRedirect(action);
                                                                                    if(rd.searchReportedUrls(url))
                                                                                    {
                                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                        out.println("<center>");
                                                                                        if(R1.getCounts().equals("1"))
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                        }
                                                                                        out.println("<a href='"+action+"'>"
                                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                + "</a>"
                                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                + "</center>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        response.sendRedirect(action);
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(browser.equalsIgnoreCase("Mozilla"))
                                                                                    {
                                                                                        // browser is Mozilla
                                                                                        addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                                        addB +=1;
                                                                                        AddB = String.valueOf(addB);
                                                                                        cd.Browser_MozillaCount(AddB,url);
                                                                                        //response.sendRedirect(action);
                                                                                        if(rd.searchReportedUrls(url))
                                                                                        {
                                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                            out.println("<center>");
                                                                                            if(R1.getCounts().equals("1"))
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                            }
                                                                                            out.println("<a href='"+action+"'>"
                                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                    + "</a>"
                                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                    + "</center>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            response.sendRedirect(action);
                                                                                        }
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        // browser is Others
                                                                                        addB = Integer.parseInt(C.getC_browser_others());
                                                                                        addB +=1;
                                                                                        AddB = String.valueOf(addB);
                                                                                        cd.Browser_OthersCount(AddB,url);
                                                                                        //response.sendRedirect(action);
                                                                                        if(rd.searchReportedUrls(url))
                                                                                        {
                                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                            out.println("<center>");
                                                                                            if(R1.getCounts().equals("1"))
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                            }
                                                                                            out.println("<a href='"+action+"'>"
                                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                    + "</a>"
                                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                    + "</center>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            response.sendRedirect(action);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else
                                                        {
                                                            // os is Others
                                                            addOS = Integer.parseInt(C.getC_os_others());
                                                            addOS +=1;
                                                            AddOS = String.valueOf(addOS);
                                                            cd.OS_OthersCount(AddOS,url);
                                                            // browser is ...
                                                            if(browser.equalsIgnoreCase("Chrome"))
                                                            {
                                                                // browser is Chrome
                                                                addB = Integer.parseInt(C.getC_browser_chrome());
                                                                addB +=1;
                                                                AddB = String.valueOf(addB);
                                                                cd.Browser_ChromeCount(AddB,url);
                                                                //response.sendRedirect(action);
                                                                if(rd.searchReportedUrls(url))
                                                                {
                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                    out.println("<center>");
                                                                    if(R1.getCounts().equals("1"))
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                    }
                                                                    else
                                                                    {
                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                    }
                                                                    out.println("<a href='"+action+"'>"
                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                            + "</a>"
                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                            + "</center>");
                                                                }
                                                                else
                                                                {
                                                                    response.sendRedirect(action);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                if(browser.equalsIgnoreCase("Firefox"))
                                                                {
                                                                    // browser is Firefox
                                                                    addB = Integer.parseInt(C.getC_browser_firefox());
                                                                    addB +=1;
                                                                    AddB = String.valueOf(addB);
                                                                    cd.Browser_FirefoxCount(AddB,url);
                                                                    //response.sendRedirect(action);
                                                                    if(rd.searchReportedUrls(url))
                                                                    {
                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                        out.println("<center>");
                                                                        if(R1.getCounts().equals("1"))
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                        }
                                                                        else
                                                                        {
                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                        }
                                                                        out.println("<a href='"+action+"'>"
                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                + "</a>"
                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                + "</center>");
                                                                    }
                                                                    else
                                                                    {
                                                                        response.sendRedirect(action);
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if(browser.equalsIgnoreCase("Safari"))
                                                                    {
                                                                        // browser is Safari
                                                                        addB = Integer.parseInt(C.getC_browser_safari());
                                                                        addB +=1;
                                                                        AddB = String.valueOf(addB);
                                                                        cd.Browser_SafariCount(AddB,url);
                                                                        //response.sendRedirect(action);
                                                                        if(rd.searchReportedUrls(url))
                                                                        {
                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                            out.println("<center>");
                                                                            if(R1.getCounts().equals("1"))
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                            }
                                                                            else
                                                                            {
                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                            }
                                                                            out.println("<a href='"+action+"'>"
                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                    + "</a>"
                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                    + "</center>");
                                                                        }
                                                                        else
                                                                        {
                                                                            response.sendRedirect(action);
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        if(browser.equalsIgnoreCase("Internet Explorer"))
                                                                        {
                                                                            // browser is Internet Explorer
                                                                            addB = Integer.parseInt(C.getC_browser_internetexp());
                                                                            addB +=1;
                                                                            AddB = String.valueOf(addB);
                                                                            cd.Browser_InternetExpCount(AddB,url);
                                                                            //response.sendRedirect(action);
                                                                            if(rd.searchReportedUrls(url))
                                                                            {
                                                                                out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                out.println("<center>");
                                                                                if(R1.getCounts().equals("1"))
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                }
                                                                                out.println("<a href='"+action+"'>"
                                                                                        + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                        + "</a>"
                                                                                        + "&nbsp;&nbsp;&nbsp;"
                                                                                        + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                        + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                        + "</center>");
                                                                            }
                                                                            else
                                                                            {
                                                                                response.sendRedirect(action);
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            if(browser.equalsIgnoreCase("Opera"))
                                                                            {
                                                                                // browser is Opera
                                                                                addB = Integer.parseInt(C.getC_browser_opera());
                                                                                addB +=1;
                                                                                AddB = String.valueOf(addB);
                                                                                cd.Browser_OperaCount(AddB,url);
                                                                                //response.sendRedirect(action);
                                                                                if(rd.searchReportedUrls(url))
                                                                                {
                                                                                    out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                    out.println("<center>");
                                                                                    if(R1.getCounts().equals("1"))
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                    }
                                                                                    out.println("<a href='"+action+"'>"
                                                                                            + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                            + "</a>"
                                                                                            + "&nbsp;&nbsp;&nbsp;"
                                                                                            + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                            + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                            + "</center>");
                                                                                }
                                                                                else
                                                                                {
                                                                                    response.sendRedirect(action);
                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                if(browser.equalsIgnoreCase("BlackBerry"))
                                                                                {
                                                                                    // browser is BlackBerry
                                                                                    addB = Integer.parseInt(C.getC_browser_blackberry());
                                                                                    addB +=1;
                                                                                    AddB = String.valueOf(addB);
                                                                                    cd.Browser_BlackberryCount(AddB,url);
                                                                                    //response.sendRedirect(action);
                                                                                    if(rd.searchReportedUrls(url))
                                                                                    {
                                                                                        out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                        out.println("<center>");
                                                                                        if(R1.getCounts().equals("1"))
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                        }
                                                                                        out.println("<a href='"+action+"'>"
                                                                                                + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                + "</a>"
                                                                                                + "&nbsp;&nbsp;&nbsp;"
                                                                                                + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                + "</center>");
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        response.sendRedirect(action);
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(browser.equalsIgnoreCase("Mozilla"))
                                                                                    {
                                                                                        // browser is Mozilla
                                                                                        addB = Integer.parseInt(C.getC_browser_mozilla());
                                                                                        addB +=1;
                                                                                        AddB = String.valueOf(addB);
                                                                                        cd.Browser_MozillaCount(AddB,url);
                                                                                        //response.sendRedirect(action);
                                                                                        if(rd.searchReportedUrls(url))
                                                                                        {
                                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                            out.println("<center>");
                                                                                            if(R1.getCounts().equals("1"))
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                            }
                                                                                            out.println("<a href='"+action+"'>"
                                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                    + "</a>"
                                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                    + "</center>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            response.sendRedirect(action);
                                                                                        }
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        // browser is Others
                                                                                        addB = Integer.parseInt(C.getC_browser_others());
                                                                                        addB +=1;
                                                                                        AddB = String.valueOf(addB);
                                                                                        cd.Browser_OthersCount(AddB,url);
                                                                                        //response.sendRedirect(action);
                                                                                        if(rd.searchReportedUrls(url))
                                                                                        {
                                                                                            out.println("<img src=\"http://www.urllive.in:8084/UrlLive/assets/img/loading1.gif\" alt=\"Loading...\" class=\"centered\">");
                                                                                            out.println("<center>");
                                                                                            if(R1.getCounts().equals("1"))
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user.</big><br><br>");
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                out.println("<big style='cursor:pointer;font-size:20px;margin-top:-50px;' class='btn-default btn-fill badge'>URL is reported malicious by "+R1.getCounts()+" user's.</big><br><br>");
                                                                                            }
                                                                                            out.println("<a href='"+action+"'>"
                                                                                                    + "<button type=\"button\" class=\"btn btn-danger btn-fill btn-wd\"><i class=\"ti-alert\"></i>&nbsp;Visit Website</button>"
                                                                                                    + "</a>"
                                                                                                    + "&nbsp;&nbsp;&nbsp;"
                                                                                                    + "<button type=\"button\" class=\"btn btn-info btn-fill btn-wd\" onclick=\"self.close()\"><i class=\"ti-info-alt\"></i>&nbsp;Back Safely</button>"
                                                                                                    + "<br><br><span>If Window not closed by Back Button, Then <b>Exit Window</b>.</span>"
                                                                                                    + "</center>");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            response.sendRedirect(action);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //out.println("VALID");
            }
            else
            {
                //out.println("NOT VALID");
                response.sendRedirect("http://www.urllive.in:8084/UrlLive/error/");
            }
            //response.sendRedirect(action);
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e)
        {
            Logger.getLogger(redirecting.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}