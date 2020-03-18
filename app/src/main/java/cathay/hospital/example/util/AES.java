package cathay.hospital.example.util;



import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    //必須為16碼
    private static final String key = "key1111111111111";

    /**
     * 对字符串加密
     *
     * @param data 源字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String data) {
        try{
            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(data.getBytes("utf-8"));
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 解密
    public static String decrypt(String data) {
        try {
            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted = Base64.decode(data, Base64.DEFAULT);//先用base64解密
            byte[] original = cipher.doFinal(encrypted);
            return new String(original,"utf-8");
        } catch (Exception ex) {
            return null;
        }
    }

}