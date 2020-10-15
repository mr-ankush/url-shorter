package controller;
import dao.CoderDAO;
import dao.CountsDAO;
import dao.ReportDAO;
import dao.UrlDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Coder;
import model.Counts;
import model.Report;
import model.Url;
@MultipartConfig(maxFileSize = 16177215)          // 16MB Data
public class report_url extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try 
        {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Report URL</title>");
            out.println("<link rel=\"icon\" href='http://www.urllive.in:8084/UrlLive/assets/img/favicon.png'/>");
            out.println("</head>");
            out.println("<body>");
            // fields
            //String website = "http://www.urllive.in:8084/UrlLive/";
            String url="", dated="", date="", time="", comment="", ip="";
            url = request.getParameter("url");
            //out.println(url+"<br>");
            //long_url = request.getParameter("url");
            //out.println(long_url+"<br>");
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss a");    // date format
            LocalDateTime dtf = LocalDateTime.now();
            dated = dtf1.format(dtf);
            date = dated.substring(0,10);
            time = dated.substring(11);
            //out.println(date+"<br>");
            //out.println(time+"<br>");
            comment = request.getParameter("comment");
            //out.println(comment+"<br>");
            ip = request.getParameter("ip");
            //out.println(ip+"<br>");
            UrlDAO ud = new UrlDAO();
            Url U = null;
            Report R = new Report();
            ReportDAO rd = new ReportDAO(); //call method
            if(!"".equals(url) && !"".equals(ip))
            {
                if(ud.checkUrl(url))
                {
                    int n;
                    String N;
                    if(!rd.searchReportedUrls(url) && !rd.searchReportedIpUrls(url))
                    {
                        // update 1st counts
                        R.setUrl(url);
                        R.setDate(date);
                        R.setTime(time);
                        R.setComment(comment);
                        R.setIp(ip);
                        if(rd.urlReport(R) && rd.firstReport(url))
                        {
                            //out.println("Success...");
                            //                        session.setAttribute("msg",
                            //                            "Don't refresh page otherwise shorted URL may be changed.");
                            session.setAttribute("msg","URL is reported as malicious, We will inform to upcoming users on this URL.");
                            session.setAttribute("url",url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/reported-malicious/");
                        }
                        else
                        {
                            //out.println("Unsuccess...");
                            //session.setAttribute("msg",
                            //There is an Technical error.");
                            session.setAttribute("msg","There is an technical problem in server, Try again.");
                            session.setAttribute("short_url",url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/reported-malicious/");
                        }
                    }
                    else
                    {
                        if(rd.searchReportedIpUrls(url))
                        {
                            session.setAttribute("msg","URL already reported by your IP-Address.");
                            session.setAttribute("url",url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/reported-malicious/");
                        }
                        else
                        {
                            R = rd.searchReportedUrlCount(url);                    
                            // update counts
                            n = Integer.parseInt(R.getCounts());
                            n += 1;
                            N = String.valueOf(n);
                            rd.afterFirstReport(url,N);
                            session.setAttribute("msg","URL is reported as malicious, We will inform to upcoming users on this URL.");
                            session.setAttribute("url",url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/reported-malicious/");
                        }
                    }
                }
                else
                {
                    //out.println("Success...");
//                        session.setAttribute("msg",
//                            "Don't refresh page otherwise shorted URL may be changed.");
                    session.setAttribute("msg","Invalid URL.");
                    session.setAttribute("url",url);
                    response.sendRedirect("http://www.urllive.in:8084/UrlLive/reported-malicious/");
                }
            }
            else
            {
                // technical error
//                session.setAttribute("msg",
//                    "There is an Technical error.");
//                session.setAttribute("short_url",url);
                response.sendRedirect("http://www.urllive.in:8084/UrlLive/error/");
            }
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e)
        {
            Logger.getLogger(report_url.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}