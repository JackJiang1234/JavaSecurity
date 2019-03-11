package ch03;

import common.MethodHelper;

import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import static java.lang.System.out;

public class KeyTest {
    public static void main(String[] args) {
        MethodHelper.runStatic(KeyTest.class);

        Key key = null;
        SecretKey secretKey = null;
        PublicKey publicKey = null;
        PrivateKey privateKey = null;
        KeyPair keyPair = null;
        KeyPairGenerator keyPairGenerator;
        KeyFactory keyFactory;

        AlgorithmParameters algorithmParameters = null;
        AlgorithmParameterGenerator algorithmParameterGenerator = null;

        SecureRandom secureRandom;

        Signature signature;

        Timestamp timestamp;

        KeySpec keySpec;
        AlgorithmParameterSpec algorithmParameterSpec;
    }

    public static void testGenerateKey(){
        KeyPairGenerator keyPairGenerator  = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(1024);
        KeyPair keys = keyPairGenerator.generateKeyPair();
        out.println(keys.getPrivate());
        out.println(keys.getPublic());
    }

    public static void testSignature(){
        byte[] data = "Date Signature".getBytes();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            Signature signature = Signature.getInstance(keyPairGenerator.getAlgorithm());
            signature.initSign(keyPair.getPrivate());
            signature.update(data);
            byte[] sign = signature.sign();

            signature.initVerify(keyPair.getPublic());
            signature.update(data);
            out.println(signature.verify(sign));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }

    public static void testKeyStore(){
        out.println(KeyStore.getDefaultType());
    }
}
