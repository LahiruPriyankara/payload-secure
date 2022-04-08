package com.lhu.payloadsecure;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class PayLoadAuthService {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final Integer LENGTH = 128;
    private static final String SECRET_KEY = "o9szYIOq1rRMiouNhNvaq96lqUvCekxR";

    public boolean authPayload(String payLoadEncodedData,String payLoad) throws Exception {
        System.out.println("payLoadEncodedData: " + payLoad);
        String encString = ecrypt(payLoad);
        System.out.println("encString: " + encString);
        System.out.println("decrypted: " + decrypt(encString));

        System.out.println("...........................");

        System.out.println("payLoadEncodedData: " + payLoadEncodedData);
        boolean isValid = encString.equals(payLoadEncodedData);
        System.out.println(isValid);

        if(!isValid){
            throw new Exception("Payload has been changed.");
        }

        return isValid;
    }


/*        public static String ecrypt(String ciphertext){
        try{
            SecretKey secretKey = getSecretKey("o9szYIOq1rRMiouNhNvaq96lqUvCekxR");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getEncoder().encode(ciphertext.getBytes(StandardCharsets.UTF_8))));
            //return new String(cipher.doFinal(base64ecode("ASDASDADS")));
        }catch (Exception e){
            return "";
        }

    }
*/

    private String ecrypt(String content) {
        try {
            SecretKey secretKey = getSecretKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(secretKey));

            byte[] result = cipher.doFinal(byteContent);
            return Base64.getEncoder().encodeToString(result);
            //return Base64.getEncoder(result);//encodeBase64String(result);
        } catch (Exception ex) {
        }
        return null;
    }

    private String decrypt(String ciphertext){
        try{
            SecretKey secretKey = getSecretKey("o9szYIOq1rRMiouNhNvaq96lqUvCekxR");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)));
            //return new String(cipher.doFinal(base64Decode("ASDASDADS")));
        }catch (Exception e){
            return "";
        }
    }

    private SecretKey getSecretKey(String secretKey) throws Exception {
        byte[] decodeSecretKey = Base64.getDecoder().decode(secretKey);
        //byte[] decodeSecretKey = base64Decode(secretKey);
        return new SecretKeySpec(decodeSecretKey, 0, decodeSecretKey.length, "AES");
    }
}
