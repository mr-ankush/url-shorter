package dao;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import model.Coder;

public class CoderDAO
{
    public Coder decode(String msg)
    {
        Coder C = new Coder();
        try 
        {
            String decodeURL=msg;
            decodeURL=URLDecoder.decode( decodeURL, "UTF-8" );
            C.setDecode(decodeURL);
            return C;
        }
        catch (UnsupportedEncodingException e)
        {
                return C=null;
        }
    }
    public Coder encode(String msg)
    {
        Coder C = new Coder();
        try 
        {
            String encodeURL=URLEncoder.encode( msg, "UTF-8" );
            C.setEncode(encodeURL);
            return C;
        } 
        catch (UnsupportedEncodingException e) 
        {
            return C=null;
        }
    }
}
