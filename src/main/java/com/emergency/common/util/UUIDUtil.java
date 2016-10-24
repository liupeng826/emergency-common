package com.emergency.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * 唯一ID生成
 */
public class UUIDUtil {
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static UUIDUtil instance = new UUIDUtil();
    private String hostAddr;
    private Random random = new SecureRandom();
    private MessageDigest mHasher;
    private UniqTimer timer = new UniqTimer();
    private UUIDUtil() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostAddr = addr.getHostAddress();
        } catch (IOException e) {
            hostAddr = String.valueOf(System.currentTimeMillis());
        }
        if (StringUtil.isBlank(hostAddr) || "127.0.0.1".equals(hostAddr)) {
            hostAddr = String.valueOf(System.currentTimeMillis());
        }
        try {
            mHasher = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nex) {
            mHasher = null;
        }
    }

    public static UUIDUtil getInstance() {
        return instance;
    }

    public String getUniqID() {
        StringBuffer buffer = new StringBuffer();
        long t = timer.getCurrentTime();
        buffer.append(t);
        buffer.append("-");
        buffer.append(random.nextInt(8999) + 1000);
        buffer.append("-");
        buffer.append(hostAddr);
        buffer.append("-");
        buffer.append(Thread.currentThread().hashCode());
        return buffer.toString();
    }

    public String getUniqIDHash() {
        String id = getUniqID();
        if (mHasher != null) {
            synchronized (mHasher) {
                byte[] bt = mHasher.digest(id.getBytes());
                int l = bt.length;
                char[] out = new char[l << 1];
                for (int i = 0, j = 0; i < l; i++) {
                    out[j++] = digits[(0xF0 & bt[i]) >>> 4];
                    out[j++] = digits[0x0F & bt[i]];
                }
                return new String(out);
            }
        } else {
            return id;
        }
    }

    private class UniqTimer {
        private long lastTime = System.currentTimeMillis();
        public synchronized long getCurrentTime() {
            long currTime = System.currentTimeMillis();
            lastTime = Math.max(lastTime + 1, currTime);
            return lastTime;
        }
    }

    public synchronized String generateSerialNumber() {
        int num = (int)(1000000 + new Random().nextFloat() * 9000000);
        return DateFormatUtils.format(new Date(), String.format("yyyyMMddHHmmssSSS%s", num));

    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.getInstance().generateSerialNumber().length());
    }
}
