package com.finger.usbcamera.internal;

import android.hardware.usb.UsbDevice;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class LicenseUtils {

    private static  final String ENCRYPT_KEY="PoJieSiQuanJiaFuckYou!PoJieSiQuanJiaFuckYou!PoJieSiQuanJiaFuckYou!PoJieSiQuanJiaFuckYou!";
    private static final String KEY = "bmJU3ZaZAtfH9QDMP66ZgA==";
    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKeyBase64 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    private static String aesDecrypt(String encryptStr, String decryptKeyBase64) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return aesDecryptByBytes(Base64.getDecoder().decode(encryptStr), decryptKeyBase64);
    }

    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKeyBase64) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] decryptKey=Base64.getDecoder().decode(decryptKeyBase64);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey, "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
    /**
     * AES加密为base 64 code
     *
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    private static String aesEncrypt(String content, String encryptKey) throws BadPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        return Base64.getEncoder().encodeToString(aesEncryptToBytes(content, encryptKey));
    }
    /**
     * AES加密
     *
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content,String encryptKey) throws UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = getKey(encryptKey);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }
    private static byte[] getKey(String strKey) throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(strKey.getBytes());
        generator.init(128, secureRandom);
        SecretKey key = generator.generateKey();

        return key.getEncoded();
    }

    /**
     * 生成授权秘钥,授权日期截止到1910963117141 (2030-6-22)
     * @param licenseInfo 授权信息
     * @return
     */
    public static String aesEncrypt(LicenseInfo licenseInfo){
        String content = String.format("PoJieSiQuanJia:%d:%d:1910963117141", licenseInfo.getVendorId(), licenseInfo.getProductId());
        try {
            return aesEncrypt(content, ENCRYPT_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param encryptStr 授权秘钥
     * @return
     */
    public static LicenseInfo aesDecrypt(String encryptStr){
        LicenseInfo info = new LicenseInfo();
        try {
            String[] origin = aesDecrypt(encryptStr, KEY).split(":");
            info.setVendorId(Integer.parseInt(origin[1]));
            info.setProductId(Integer.parseInt(origin[2]));
            info.setTimeInMillis(Long.parseLong(origin[3]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public static boolean checkEncryptStr(String encryptStr, UsbDevice usbDevice){
        LicenseInfo licenseInfo = aesDecrypt(encryptStr);
        return licenseInfo.getVendorId() == usbDevice.getVendorId()
                && licenseInfo.getProductId() == usbDevice.getProductId() &&
                System.currentTimeMillis() < licenseInfo.getTimeInMillis();
    }

    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, UnsupportedEncodingException {
        Calendar date = Calendar.getInstance();
        date.set(2030,6,22);
        System.out.println(date.getTimeInMillis());
        //使用vid,pid联合加密 vid:pid:授权日期
        String content = "PoJieSiQuanJia:7749:32802:1910882174217";
        String encryptKey="PoJieSiQuanJiaFuckYou!PoJieSiQuanJiaFuckYou!PoJieSiQuanJiaFuckYou!PoJieSiQuanJiaFuckYou!";
        String string=aesEncrypt(content,encryptKey);
        System.out.println(string);
        byte[] key=getKey(encryptKey);
        String keyString = Base64.getEncoder().encodeToString(key);
        System.out.println(keyString);
        String origin=aesDecrypt(string,keyString);
        System.out.println(origin);

        String[] data = origin.split(":");
        if(System.currentTimeMillis() > Long.parseLong(data[3])){
            throw new RuntimeException("xxxx____xxxxx");
        }

        LicenseInfo licenseInfo = new LicenseInfo(7749, 32802, 1910882174217L);
        String encryptStr = aesEncrypt(licenseInfo);
        System.out.println(encryptStr);
        LicenseInfo info = aesDecrypt(encryptStr);
        System.out.println("OK vid:"+info.getVendorId() + " pid:"+ info.getProductId() +" time:" +info.getTimeInMillis());
    }
}
