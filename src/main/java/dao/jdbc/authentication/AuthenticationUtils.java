package dao.jdbc.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 25.03.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationUtils {
    public String cryptWithMD5(String pass) throws NoSuchAlgorithmException{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
    }
}
