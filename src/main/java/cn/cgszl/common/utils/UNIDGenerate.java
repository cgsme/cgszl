package cn.cgszl.common.utils;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.id.Hex;
import org.apache.commons.id.uuid.UUID;

/**
 * unid生成器对象
 * <p>
 * llp@linewell.com
 */
public class UNIDGenerate {
    /**
     * 去掉缺省构造函数
     */
    private UNIDGenerate() {
    }

    /**
     * 获取32的UNID
     *
     * @return 32的UNID  String
     */
    public static String getUnid() {

        // modify by cshiyong 2014-10-13 原Unid生成方式在高并发下可能产生相同的键值，改为使用apache包
        return new String(Hex.encodeHex(UUID.randomUUID().getRawBytes()));
//		UNIDGenerate ug = new UNIDGenerate();
//		return ug.toString();
        // modify end
    }

    /**
     * 根据UNID获取随机的几位
     *
     * @param num 位数
     * @return 随机数
     */
    public static String getUnid(int num) {
        String unid = getUnid();
        if (num >= 32) {
            return unid;
        }
        int numLen = unid.length() - num;
        int beginIndex = (int) Math.random() * numLen;
        unid = unid.substring(beginIndex, num);
        return unid;
    }

    private static final int IP;

    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private static short counter = (short) 0;

    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    private String sep = "";

    /**
     * 从java 的虚拟机计算出一个值
     *
     * @return java 的虚拟机计算出一个值 int
     */
    protected int getJVM() {
        return JVM;
    }

    /**
     * 在一个毫秒单位里的唯一整数值
     *
     * @return 一个毫秒单位里的唯一整数值 int
     */
    protected short getCount() {
        synchronized (UNIDGenerate.class) {
            if (counter < 0)
                counter = 0;
            return counter++;
        }
    }

    /**
     * 局域网唯一IP地址 Unique in a local network
     *
     * @return ip地址 String
     */
    protected int getIP() {
        return IP;
    }

    /**
     * 获取毫秒
     *
     * @return 毫秒 short
     */
    protected short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    /**
     * 获取本地时间戳
     *
     * @return 本地时间戳 int
     */
    protected int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    /**
     * 将一个10进制数格式为8位的16进制数据
     *
     * @param intval 10进制数 int
     * @return 格式化后的16进制 String
     */
    protected String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    /**
     * 将一个10进制数格式为4位的16进制数
     *
     * @param shortval 10进制数 int
     * @return 8位的4进制数 String
     */
    protected String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    /**
     * 返回生成的unid
     *
     * @return unid值 String
     */
    public String toString() {
        StringBuffer sb = new StringBuffer(36).append(format(getIP())).append(
                sep).append(format(getJVM())).append(sep).append(
                format(getHiTime())).append(sep).append(format(getLoTime()))
                .append(sep).append(format(getCount()));
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return sb.toString().toUpperCase();
        }
        md5.update(sb.toString().getBytes());
        byte[] array = md5.digest();
        StringBuffer ret = new StringBuffer();
        for (int j = 0; j < array.length; ++j) {
            int b = array[j] & 0xFF;
            if (b < 0x10)
                ret.append('0');
            ret.append(Integer.toHexString(b));
        }
        return ret.toString().toUpperCase();
    }

//	/**
//	 * 返回生成的unid
//	 *
//	 * @return String-unid值
//	 */
//	public String getUnid()
//	{
//		return toString();
//	}

    /**
     * 判断输入的字符串是否为32位Unid
     *
     * @param str 输入字符串
     * @return 若是32位Unid，则返回True,否则返回False
     */
    public static boolean isUnid(String str) {
        if (str.length() == 32
                && str
                .matches("[A-Z0-9]{32}")) {
            return true;
        }
        return false;
    }

    /**
     * 将一个字节数组转换为整形数
     *
     * @param bytes 字节数组 byte[]
     * @return 10进制的整数 int
     */
    public static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }
}
