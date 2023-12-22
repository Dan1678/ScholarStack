import java.security.*;

public class PasswordHashing {

    public static String hashPassword(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");      //using md5 algorithm
            messageDigest.update(password.getBytes());

            byte[] resultByteArr = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArr){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

    }




}
