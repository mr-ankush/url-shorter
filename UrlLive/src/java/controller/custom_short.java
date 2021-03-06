package controller;
import dao.UrlDAO;
import model.Url;
import dao.CountsDAO;
import model.Counts;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@MultipartConfig(maxFileSize = 16177215)          // 16MB Data
public class custom_short extends HttpServlet 
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
            out.println("<title>Custom URL Shorter</title>");
            out.println("<link rel=\"icon\" href='http://www.urllive.in:8084/UrlLive/assets/img/favicon.png'/>");
            out.println("</head>");
            out.println("<body>");
            // fields
            String website = "http://www.urllive.in:8084/UrlLive/";
            String long_url="", short_url="", keyword="",
                    date="", url_date="", url_time="", url_os="",
                    url_browser="", url_ip_add="", url_type="", url_city="",
                    url_country="", url_region="", url_postal="", url_location="";
            long_url = request.getParameter("url");
            out.println(long_url+"<br>");
            keyword = request.getParameter("keyword");
            out.println(keyword+"<br>");
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss a");    // date format
            LocalDateTime dtf = LocalDateTime.now();
            date = dtf1.format(dtf);
            url_date = date.substring(0,10);
            url_time = date.substring(11);
            //out.println(date+"<br>");
            out.println(url_date+"<br>");
            out.println(url_time+"<br>");
            url_os = request.getParameter("os");
            out.println(url_os+"<br>");
            url_browser = request.getParameter("browser");
            out.println(url_browser+"<br>");
            url_ip_add = request.getParameter("ip");
            out.println(url_ip_add+"<br>");
            url_type = request.getParameter("type");
            out.println(url_type+"<br>");
            url_city = request.getParameter("city");
            out.println(url_city+"<br>");
            url_country = request.getParameter("country");
            out.println(url_country+"<br>");
            url_region = request.getParameter("region");
            out.println(url_region+"<br>");
            url_postal = request.getParameter("postal");
            out.println(url_postal+"<br>");
            url_location = request.getParameter("location");
            out.println(url_location+"<br>");
            // url generate
            int i,lenght = 4;
            String NUMBERS1 = "ABcdEFghIJklMNopQRstUVwxYZabCDefGHijKLmnOPqrSTuvWXy9876543210123456789"; 
            String NUMBERS2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy0123456789";
            String NUMBERS3 = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ0123456789";
            String[] a= 
            {
                "ABcdEFghIJklMNopQRstUVwxYZabCDefGHijKLmnOPqrSTuvWXyz9876543210123456789",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
                "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789",
                "0123456789AbCdEfGhIjKlMnOpQrStUvWxYzaBcDeFgHiJkLmNoPqRsTuVwXyZ",
                "AbCdEfGhIjKlMnOpQrStUvWxYzaBcDeFgHiJkLmNoPqRsTuVwXyZ9876543210"
            };
            Random rnd= new Random();
            String NUMBERS = a[rnd.nextInt(a.length)];
            //out.println(NUMBERS+"<br>");
            Random random = new Random();
            char[] url = new char[lenght];
            int index = 0;
            for(i=0; i<lenght; i++)
            {
                url[i] = NUMBERS.charAt(random.nextInt(NUMBERS.length()));
            }
            short_url = url[0]+""+url[1]+""+url[2]+""+url[3];
            short_url = website+keyword+"/"+short_url;
            out.println(short_url+"<br>");
            Url U = new Url();
            UrlDAO ud = new UrlDAO(); //call method
            Counts C = new Counts();
            CountsDAO cd = new CountsDAO(); //call method
            if(!"".equals(long_url) && !"".equals(short_url))
            {
                if(url_type.equals("Custom URL"))
                {
                    if(!ud.checkUrl(short_url))
                    {
                        U.setLong_url(long_url);
                        U.setShort_url(short_url);
                        U.setUrl_date(url_date);
                        U.setUrl_time(url_time);
                        U.setUrl_os(url_os);
                        U.setUrl_browser(url_browser);
                        U.setUrl_ip_add(url_ip_add);
                        U.setUrl_type(url_type);
                        U.setUrl_city(url_city);
                        U.setUrl_country(url_country);
                        U.setUrl_region(url_region);
                        U.setUrl_postal(url_postal);
                        U.setUrl_location(url_location);
                        if(ud.urlGenerate(U) && cd.urlDefaultCount(short_url,url_type))
                        {
                            //out.println("Success...");
    //                        session.setAttribute("msg",
    //                            "Don't refresh page otherwise shorted URL may be changed.");
                            session.setAttribute("short_url",short_url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/url/custom/");
                        }
                        else
                        {
                            //out.println("Unsuccess...");
    //                        session.setAttribute("msg",
    //                            "There is an Technical error.");
                            session.setAttribute("short_url",short_url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/error/");
                        }
                    }
                    else
                    {
                        // url generate
                        for(i=0; i<lenght; i++)
                        {
                            url[i] = NUMBERS.charAt(random.nextInt(NUMBERS.length()));
                        }
                        short_url = url[0]+""+url[1]+""+url[2]+""+url[3];
                        short_url = website+keyword+"/"+short_url;
                        // regenerate short url
                        U.setLong_url(long_url);
                        U.setShort_url(short_url);
                        U.setUrl_date(url_date);
                        U.setUrl_time(url_time);
                        U.setUrl_os(url_os);
                        U.setUrl_browser(url_browser);
                        U.setUrl_ip_add(url_ip_add);
                        U.setUrl_type(url_type);
                        U.setUrl_city(url_city);
                        U.setUrl_country(url_country);
                        U.setUrl_region(url_region);
                        U.setUrl_postal(url_postal);
                        U.setUrl_location(url_location);
                        if(ud.urlGenerate(U) && cd.urlDefaultCount(short_url,url_type))
                        {
                            //out.println("Success...");
    //                        session.setAttribute("msg",
    //                            "Don't refresh page otherwise shorted URL may be changed.");
                            session.setAttribute("short_url",short_url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/url/custom/");
                        }
                        else
                        {
                            //out.println("Unsuccess...");
    //                        session.setAttribute("msg",
    //                            "There is an Technical error.");
                            session.setAttribute("short_url",short_url);
                            response.sendRedirect("http://www.urllive.in:8084/UrlLive/error/");
                        }
                    }
                }
                else
                {
                    response.sendRedirect("http://www.urllive.in:8084/UrlLive/error/");
                }
            }
            else
            {
                // technical error
//                session.setAttribute("msg",
//                    "There is an Technical error.");
                session.setAttribute("short_url",short_url);
                response.sendRedirect("http://www.urllive.in:8084/UrlLive/error/");
            }
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e)
        {
            Logger.getLogger(custom_short.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}