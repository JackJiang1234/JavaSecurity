package ch03;

import common.MethodHelper;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.*;
import java.util.Arrays;
import java.util.Map;

import static java.lang.System.out;

/**
 *
 * */
public class DigestTest {
    public static void main(String[] args) {
        MethodHelper.runStatic(DigestTest.class, "testProvider");
    }

    private static void testProvider() {
        for (Provider p :
                Security.getProviders()) {
            System.out.println(p);

            for (Map.Entry<Object, Object> entry : p.entrySet()) {
                System.out.println("\t" + entry.getKey());
            }
        }
    }

    private static void testMessageDigest() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            out.println(Arrays.toString(sha.digest("sha".getBytes())));
        } catch (NoSuchAlgorithmException ex) {
            out.println();
        }
    }

    private static void testDigestInputStream() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] input = "sha".getBytes();
            DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(input), md);
            dis.read(input, 0, input.length);
            byte[] result = dis.getMessageDigest().digest();
            out.println(Arrays.toString(result));
        } catch (NoSuchAlgorithmException ex) {
            out.println(ex);
        } catch (IOException ex) {
            out.println(ex);
        }
    }

    public static void testDigestSha() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("md5.txt");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
            //必须把文件读取完毕才能拿到md5
            byte[] buffer = new byte[4096];
            while (digestInputStream.read(buffer) > -1) {
            }
            MessageDigest digest = digestInputStream.getMessageDigest();
            digestInputStream.close();
            byte[] md5 = digest.digest();
            System.out.println(Arrays.toString(md5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testDigestOutputStream() {
        try {
            byte[] input = "md5".getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            DigestOutputStream dos = new DigestOutputStream(new ByteArrayOutputStream(), md);
            dos.write(input, 0, input.length);
            byte[] output = dos.getMessageDigest().digest();
            out.println(Arrays.toString(output));
            dos.flush();
            dos.close();
        } catch (Exception ex) {
            out.println(ex);
        }
    }

    public static void testMac(){
        byte[] input = "MAC".getBytes();
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            SecretKey secretKey = keyGenerator.generateKey();
            Mac mac  = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            out.println(Arrays.toString(mac.doFinal(input)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
