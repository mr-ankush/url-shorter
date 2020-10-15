package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myconnection.MyCon;
import model.Counts;
import model.Url;
public class CountsDAO
{
    // url generate
//    public boolean DefaultCount(Counts C)
//    {
//        try 
//        {
//            Connection con = null;
//            PreparedStatement ps = null;
//            String sql;
//            con = MyCon.getConnection();
//            sql = "insert into "
//                    // table predonor
//                    + "counts"
//                    // field start
//                    + "("
//                    // fields
//                    + "c_short_url,c_url_type"
//                    // field close
//                    + ") "
//                    // values
//                    + "values(?,?)";
//            ps = con.prepareStatement(sql);
//            // data
//            ps.setString(1,C.getC_short_url());
//            ps.setString(2,C.getC_url_type());
//            // code execution
//            if(ps.executeUpdate()>0)
//                return true;
//        }
//        catch (SQLException ex) 
//        {
//            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    public boolean urlDefaultCount(String c_short_url, String c_url_type)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "insert into "
                    // table predonor
                    + "counts"
                    // field start
                    + "("
                    // fields
                    + "c_short_url,c_url_type,c_os_win_phone,c_os_windows,"
                    + "c_os_iphone,c_os_ipad,c_os_kindle,c_os_android,"
                    + "c_os_playbook,c_os_blackberry,c_os_macintosh,c_os_linux,"
                    + "c_os_palm,c_os_others,c_browser_chrome,c_browser_firefox,"
                    + "c_browser_safari,c_browser_iternetexp,c_browser_opera,"
                    + "c_browser_blackberry,c_browser_mozilla,c_browser_others,"
                    // extra fields
                    + "data23,data24,data25,data26,data27,data28,data29,data30"
                    // field close
                    + ") "
                    // values
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            // data
            ps.setString(1,c_short_url);
            ps.setString(2,c_url_type);
            ps.setString(3,"0");
            ps.setString(4,"0");
            ps.setString(5,"0");
            ps.setString(6,"0");
            ps.setString(7,"0");
            ps.setString(8,"0");
            ps.setString(9,"0");
            ps.setString(10,"0");
            ps.setString(11,"0");
            ps.setString(12,"0");
            ps.setString(13,"0");
            ps.setString(14,"0");
            ps.setString(15,"0");
            ps.setString(16,"0");
            ps.setString(17,"0");
            ps.setString(18,"0");
            ps.setString(19,"0");
            ps.setString(20,"0");
            ps.setString(21,"0");
            ps.setString(22,"0");
            // extra fields
            ps.setString(23,"");
            ps.setString(24,"");
            ps.setString(25,"");
            ps.setString(26,"");
            ps.setString(27,"");
            ps.setString(28,"");
            ps.setString(29,"");
            ps.setString(30,"");
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // check url valid or not
    public boolean checkUrlCount(String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            //Admin A = new Admin();
            String sql;
            con = MyCon.getConnection();
            sql = "select * from counts where c_short_url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,short_url);
            rs = ps.executeQuery();
            if(rs.next())  
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // access data of url
    public Counts searchShortUrlCount(String short_url)
    {
        Counts C = new Counts();
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;
            con = MyCon.getConnection();
            sql = "Select * from counts where c_short_url=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,short_url);
            rs = ps.executeQuery();
            if(rs.next())
            {
                C.setC_short_url(rs.getString(1));
                C.setC_url_type(rs.getString(2));
                C.setC_os_win_phone(rs.getString(3));
                C.setC_os_windows(rs.getString(4));
                C.setC_os_iphone(rs.getString(5));
                C.setC_os_ipad(rs.getString(6));
                C.setC_os_kindle(rs.getString(7));
                C.setC_os_android(rs.getString(8));
                C.setC_os_playbook(rs.getString(9));
                C.setC_os_blackberry(rs.getString(10));
                C.setC_os_macintosh(rs.getString(11));
                C.setC_os_linux(rs.getString(12));
                C.setC_os_palm(rs.getString(13));
                C.setC_os_others(rs.getString(14));
                C.setC_browser_chrome(rs.getString(15));
                C.setC_browser_firefox(rs.getString(16));
                C.setC_browser_safari(rs.getString(17));
                C.setC_browser_internetexp(rs.getString(18));
                C.setC_browser_opera(rs.getString(19));
                C.setC_browser_blackberry(rs.getString(20));
                C.setC_browser_mozilla(rs.getString(21));
                C.setC_browser_others(rs.getString(22));
                // extra fields
                C.setData23(rs.getString(23));                
                C.setData24(rs.getString(24));
                C.setData25(rs.getString(25));
                C.setData26(rs.getString(26));
                C.setData27(rs.getString(27));
                C.setData28(rs.getString(28));
                C.setData29(rs.getString(29));
                C.setData30(rs.getString(30));
            }
            else
                C=null;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UrlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }
    // update os counting for window phone
    public boolean OS_WindowPhoneCount(String Update_window_phone_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_win_phone=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_window_phone_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for windows
    public boolean OS_WindowsCount(String Update_windows_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_windows=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_windows_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for iphone
    public boolean OS_IphoneCount(String Update_iphone_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_iphone=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_iphone_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for ipad
    public boolean OS_IpadCount(String Update_ipad_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_ipad=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_ipad_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for kindle
    public boolean OS_KindleCount(String Update_kindle_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_kindle=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_kindle_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for android
    public boolean OS_AndroidCount(String Update_android_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_android=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_android_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for playbook
    public boolean OS_PlaybookCount(String Update_playbook_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_playbook=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_playbook_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for blackberry
    public boolean OS_BlackberryCount(String Update_blackberry_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_blackberry=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_blackberry_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for macintosh
    public boolean OS_MacintoshCount(String Update_macintosh_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_macintosh=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_macintosh_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for linux
    public boolean OS_LinuxCount(String Update_linux_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_linux=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_linux_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for palm
    public boolean OS_PalmCount(String Update_palm_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_palm=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_palm_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update os counting for others type
    public boolean OS_OthersCount(String Update_others_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_os_others=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_others_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for chrome
    public boolean Browser_ChromeCount(String Update_chrome_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_chrome=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_chrome_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for firefox
    public boolean Browser_FirefoxCount(String Update_firefox_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_firefox=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_firefox_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for safari
    public boolean Browser_SafariCount(String Update_safari_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_safari=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_safari_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for internet explorer
    public boolean Browser_InternetExpCount(String Update_internet_exp_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_iternetexp=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_internet_exp_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for opera
    public boolean Browser_OperaCount(String Update_opera_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_opera=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_opera_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for blackberry
    public boolean Browser_BlackberryCount(String Update_blackberry_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_blackberry=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_blackberry_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for mozilla
    public boolean Browser_MozillaCount(String Update_mozilla_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_mozilla=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_mozilla_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // update browser counting for others type
    public boolean Browser_OthersCount(String Update_others_Counting, String short_url)
    {
        try 
        {
            Connection con = null;
            PreparedStatement ps = null;
            String sql;
            con = MyCon.getConnection();
            sql = "update "
                    // table predonor
                    + "counts "
                    // field start
                    + "set "
                    // fields
                    + "c_browser_others=? "
                    // field close
                    + "where "
                    // values
                    + "c_short_url=?";
            ps = con.prepareStatement(sql);
            // data
            // resume/cv data
            ps.setString(1,Update_others_Counting);
            // update data
            ps.setString(2,short_url);
            // code execution
            if(ps.executeUpdate()>0)
                return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(CountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}