package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myconnection.MyCon;
import model.Url;
public class UrlDAO 
{
    // url generate
    public boolean urlGenerate(Url U)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "insert into "
                    // table predonor
                    + "url"
                    // field start
                    + "("
                    // fields
                    + "long_url,short_url,"
                    + "url_date,url_time,url_os,url_browser,url_ip_add,url_type,"
                    + "url_city,url_country,url_region,url_postal,url_location"
                    ///+ "data14,data15"
                    // field close
                    + ") "
                    // values
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";  // ,?,?
            ps = con.prepareStatement(sql);
            // data
            ps.setString(1,U.getLong_url());
            ps.setString(2,U.getShort_url());
            ps.setString(3,U.getUrl_date());
            ps.setString(4,U.getUrl_time());
            ps.setString(5,U.getUrl_os());
            ps.setString(6,U.getUrl_browser());
            ps.setString(7,U.getUrl_ip_add());
            ps.setString(8,U.getUrl_type());
            ps.setString(9,U.getUrl_city());
            ps.setString(10,U.getUrl_country());
            // extra fields
            ps.setString(11,U.getUrl_region());
            ps.setString(12,U.getUrl_postal());
            ps.setString(13,U.getUrl_location());
            // ps.setString(14,U.getData14());
            // ps.setString(15,U.getData15());
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UrlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // check url valid or not
    public boolean checkUrl(String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            //Admin A = new Admin();
            String sql;
            con = MyCon.getConnection();
            //sql = "select * from url where short_url=?";
            sql = "select * from url where short_url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,short_url);
            rs = ps.executeQuery();
            if(rs.next())
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UrlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // access data of url
    public Url searchShortUrl(String short_url)
    {
        Url U = new Url();
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;
            con = MyCon.getConnection();
            sql = "Select * from url where short_url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,short_url);
            rs = ps.executeQuery();
            if(rs.next())
            {
                U.setLong_url(rs.getString(1));
                U.setShort_url(rs.getString(2));
                U.setUrl_date(rs.getString(3));
                U.setUrl_time(rs.getString(4));
                U.setUrl_os(rs.getString(5));
                U.setUrl_browser(rs.getString(6));
                U.setUrl_ip_add(rs.getString(7));
                U.setUrl_type(rs.getString(8));
                U.setUrl_city(rs.getString(9));
                U.setUrl_country(rs.getString(10));
                U.setUrl_region(rs.getString(11));
                U.setUrl_postal(rs.getString(12));
                U.setUrl_location(rs.getString(13));
                // extra fields
                U.setData14(rs.getString(14));
                U.setData15(rs.getString(15));
            }
            else
                U=null;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UrlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U;
    }
}