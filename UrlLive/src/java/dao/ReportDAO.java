package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;
import myconnection.MyCon;

public class ReportDAO 
{
    // url report
    public boolean urlReport(Report R)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "insert into "
                    // table
                    + "report"
                    // field start
                    + "("
                    // fields
                    + "url,date,time,comment,ip"
                    // field close
                    + ") "
                    // values
                    + "values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            // data
            ps.setString(1,R.getUrl());
            ps.setString(2,R.getDate());
            ps.setString(3,R.getTime());
            ps.setString(4,R.getComment());
            ps.setString(5,R.getIp());
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // url count 1st time
    public boolean firstReport(String reported_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "insert into "
                    // table 
                    + "report_count"
                    // field start
                    + "("
                    // fields
                    + "reported_url,counts"
                    // field close
                    + ") "
                    // values
                    + "values(?,?)";
            ps = con.prepareStatement(sql);
            // data
            ps.setString(1,reported_url);
            ps.setString(2,"1");
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // url count after 1st time
    public boolean afterFirstReport(String reported_url, String count)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table
                    + "report_count "
                    // field start
                    + "set "
                    // fields
                    + "counts=? "
                    // field close
                    + "where "
                    // values
                    + "reported_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,count);
            // update data
            ps.setString(2,reported_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // access data of url
    public Report searchReportedUrlCount(String reported_url)
    {
        Report R = new Report();
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;
            con = MyCon.getConnection();
            sql = "Select * from report_count where reported_url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,reported_url);
            rs = ps.executeQuery();
            if(rs.next())
            {
                R.setReported_url(rs.getString(1));
                R.setCounts(rs.getString(2));
            }
            else
                R=null;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }
    // access data of url
    public Report searchReportedUrl(String url)
    {
        Report R = new Report();
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;
            con = MyCon.getConnection();
            sql = "Select * from report where url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,url);
            rs = ps.executeQuery();
            if(rs.next())
            {
                R.setUrl(rs.getString(1));
                R.setDate(rs.getString(2));
                R.setTime(rs.getString(3));
                R.setComment(rs.getString(4));
                R.setIp(rs.getString(5));
            }
            else
                R=null;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }
    // access data of url
    public boolean searchReportedUrls(String url)
    {
        Report R = new Report();
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;
            con = MyCon.getConnection();
            sql = "Select url from report where url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,url);
            rs = ps.executeQuery();
            if(rs.next())
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // access data of url
    public boolean searchReportedIpUrls(String url)
    {
        Report R = new Report();
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;
            con = MyCon.getConnection();
            sql = "Select ip from report where url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,url);
            rs = ps.executeQuery();
            if(rs.next())
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // access data of url
    public boolean searchReportedUrlCounts(String reported_url)
    {
        Report R = new Report();
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;
            con = MyCon.getConnection();
            sql = "Select reported_url from report_count where reported_url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,reported_url);
            rs = ps.executeQuery();
            if(rs.next())
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}