package com.godwin.finalshellcreak.service;

import com.alibaba.fastjson.JSON;
import com.godwin.finalshellcreak.pojo.FinalShellConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class DecoderService {
    /**
     * 解码
     *
     * @param shellConfig 配置
     * @return {@link String }
     * @throws Exception 异常
     */
    public String decode(FinalShellConfig shellConfig) throws Exception {
        // 主要信息: ip/端口/用户名/密码
        // FinalShellConfig shellConfig = JSON.parseObject(s, FinalShellConfig.class);
        // 密文改明文
        String password = shellConfig.getPassword();
        if (StringUtils.isNotBlank(password)) {
            shellConfig.setPassword(decodePass(password));
        }
        String s1 = JSON.toJSONString(shellConfig);
        System.err.println(s1);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("主机", shellConfig.getHost());
        map.put("端口", shellConfig.getPort());
        map.put("用户名", shellConfig.getUserName());
        map.put("密码", shellConfig.getPassword());
        String result = JSON.toJSONString(map);
        System.err.println(result);
        return result;

    }

    public byte[] desDecode(byte[] data, byte[] head) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(head);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, securekey, sr);
        return cipher.doFinal(data);
    }

    public String decodePass(String data) throws Exception {
        if (data == null) {
            return null;
        } else {
            String rs;
            byte[] buf = Base64.getDecoder().decode(data);
            byte[] head = new byte[8];
            System.arraycopy(buf, 0, head, 0, head.length);
            byte[] d = new byte[buf.length - head.length];
            System.arraycopy(buf, head.length, d, 0, d.length);
            byte[] bt = desDecode(d, ranDomKey(head));
            rs = new String(bt);
            return rs;
        }
    }

    private byte[] ranDomKey(byte[] head) {
        long ks = 3680984568597093857L / (long) (new Random((long) head[5])).nextInt(127);
        Random random = new Random(ks);
        int t = head[0];
        for (int i = 0; i < t; ++i) {
            random.nextLong();
        }
        long n = random.nextLong();
        Random r2 = new Random(n);
        long[] ld = new long[]{(long) head[4], r2.nextLong(), (long) head[7], (long) head[3], r2.nextLong(), (long) head[1], random.nextLong(), (long) head[2]};
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        int var14 = ld.length;

        for (int var13 = 0; var13 < var14; ++var13) {
            long l = ld[var13];
            try {
                dos.writeLong(l);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] keyData = bos.toByteArray();
        keyData = md5(keyData);
        return keyData;
    }

    private byte[] md5(byte[] data) {
        byte[] res = null;

        try {
            MessageDigest m;
            m = MessageDigest.getInstance("MD5");
            m.update(data, 0, data.length);
            res = m.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }


}
