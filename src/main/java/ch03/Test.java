package ch03;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;
import java.util.Map;
import  static java.lang.System.out;

/**
 *
 * */
public class Test {
    public static void main(String[] args){
        testMessageDigest();
        testDigestInputStream();
    }

    private static void testProvider(){
        for (Provider p :
                Security.getProviders()) {
            System.out.println(p);

            for(Map.Entry<Object, Object> entry : p.entrySet()){
                System.out.println("\t" + entry.getKey());
            }
        }
    }

    private static void testMessageDigest(){
        try {
            MessageDigest  sha = MessageDigest.getInstance("SHA");
            out.println(Arrays.toString(sha.digest("sha".getBytes())));
        }
        catch (NoSuchAlgorithmException ex){
            out.println();
        }
    }

    private static void testDigestInputStream(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] input = "sha".getBytes();
            DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(new byte[input.length]), md);
            dis.read(input, 0, input.length);
            byte[] result = dis.getMessageDigest().digest();
            out.println(Arrays.toString(result));
        }
        catch (NoSuchAlgorithmException ex){
            out.println(ex);
        }
        catch(IOException ex){
            out.println(ex);
        }
    }

}
