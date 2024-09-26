package com.godwin.finalshellcreak.util;

import com.alibaba.fastjson.JSON;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

public class FinalShellUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String md5(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(msg.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String keccak384(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("Keccak-384");
        byte[] hashBytes = md.digest(msg.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static Map getActiveCode(String code) {

        HashMap<String, Map<String, String>> activeCodeMap = new HashMap<>();

        System.out.println("版本号 < 3.9.6 (旧版)");
        try {
            Map<String ,String> lowVersion = new HashMap<>();
            lowVersion.put("高级版", md5("61305" + code + "8552").substring(8, 24));
            lowVersion.put("专业版", md5("2356" + code + "13593").substring(8, 24));

            activeCodeMap.put("版本号 < 3.9.6 (旧版)", lowVersion);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("版本号 >= 3.9.6 (新版)");
        try {
            Map<String ,String> highVersion = new HashMap<>();
            highVersion.put("高级版", keccak384(code + "hSf(78cvVlS5E").substring(12, 28));
            highVersion.put("专业版", keccak384(code + "FF3Go(*Xvbb5s2").substring(12, 28));
            activeCodeMap.put("版本号 >= 3.9.6 (新版)", highVersion);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(activeCodeMap));
        return activeCodeMap;
    }
}