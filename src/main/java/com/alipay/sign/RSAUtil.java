
package com.alipay.sign;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;

@SuppressWarnings("deprecation")
public class RSAUtil{
	
	private static final int CONSTANT_1024 = 1024;
    public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	
	public static KeyPair generateRSAKeyPair(){
	    KeyPairGenerator keygen;
        try {
            keygen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
	    SecureRandom random = new SecureRandom();
	    keygen.initialize(RSAUtil.CONSTANT_1024, random);
	    return keygen.generateKeyPair();
	}
	
	public static byte[] convertPCKS1ToPCKS8(String pcks1){
        try {
            byte [] asn1PrivateKeyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(pcks1.getBytes("US-ASCII"));
            RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(asn1PrivateKeyBytes));
            RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(rsaPrivKeySpec);
            System.out.println(privKey.getPrivateExponent());
            return privKey.getEncoded();
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    return null;
	}
	
//	public example(){
//	    String b64encoded = "MIIBOwIBAAJBALf+EfAvqNlTrTwPy1BrqpXkX21KUu2My/JVupfDHIQ84e+uNUYm"
//                + "2UrcPVYjg8+C9M5dZU83s8jvu/cwOw14MyECAwEAAQJALTVhZPng7B1yWGqtE0KR"
//                + "NKlbhTgY7kOFLTNBWN7ZF+iPENnJLIjze8zfkMsQsTaD2Sa8NCigBT1ClZDBHNLT"
//                + "QQIhAN4Jt6r6Hf81MRSaHKVogkUkIKsyMQ3jzxT9xKF0SkPZAiEA1CKdbva93MJo"
//                + "KRr9SmgYqKziAmLFj2bpXOz/tSvuhIkCIQCa4aZnsq7H/b+twk6nJv5v4mKTaKCF"
//                + "MtqZpubJRMglCQIgNmEpOmjGAvFTAjaI96n3qEWpKjNnsXsQF2Ipqqe4XQECIQCO"
//                + "19jR6lk+DIrjKl76tBHsuH5fIoJhvE0Nk1OdwLlLPA==";
//        byte [] asn1PrivateKeyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(b64encoded.getBytes("US-ASCII"));
//        RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(asn1PrivateKeyBytes));
//        RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(rsaPrivKeySpec);
//        System.out.println(privKey.getPrivateExponent());
//	}
}
