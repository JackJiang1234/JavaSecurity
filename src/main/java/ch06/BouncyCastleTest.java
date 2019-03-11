package ch06;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;


public class BouncyCastleTest {
    public static void main(String[] args){
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "AES"));
            String result = Arrays.toString(cipher.doFinal("QWEASDZS".getBytes("UTF-8")));
            System.out.println(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


    }
}
