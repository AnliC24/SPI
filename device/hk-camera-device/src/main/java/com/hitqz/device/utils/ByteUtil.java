package com.hitqz.device.utils;

/**
 * 字节处理
 */
public class ByteUtil {


    /**
     * byte to int
     */
    public static Integer byte2Int(byte[] bytes) {
        int value = 0;
        value = (int) ((bytes[0] & 0xff) | ((bytes[1] & 0xff) << 8) | ((bytes[2] & 0xff) << 16) | ((bytes[3] & 0xff) << 24));
        return value;
    }

    /*
     *  int to byte
     * */
    public static byte[] int2Byte(int i) {
        byte[] res = new byte[4];
        res[3] = (byte) ((i >> 24) & 0xff);
        res[2] = (byte) ((i >> 16) & 0xff);
        res[1] = (byte) ((i >> 8) & 0xff);
        res[0] = (byte) ((i) & 0xff);
        return res;
    }

    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    public static byte[] short2byte(short s) {
        byte[] b = new byte[2];
        byte hightByte, lowByte;
        for (int i = 0; i < 2; i++) {
            int offset = 16 - (i + 1) * 8; //因为byte占4个字节，所以要计算偏移量
            b[i] = (byte) ((s >> offset) & 0xff); //把16位分为2个8位进行分别存储
        }
        hightByte = b[0];
        lowByte = b[1];
        b[1] = hightByte;
        b[0] = lowByte;
        return b;
    }

    public static Short byte2short(byte[] b) {
        short l = 0;
        for (int i = 0; i < 2; i++) {
            l <<= 8; //<<=和我们的 +=是一样的，意思就是 l = l << 8
            l |= (b[i] & 0xff); //和上面也是一样的  l = l | (b[i]&0xff)
        }
        return l;
    }


    public static byte[] double2Bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    public static void main(String[] arg) {
        String message = "FFAA4C6B0138363834343030333832393830393600000000000000000000000000000000000000000001000000020000005F007E01000170170000581B0000803E00000000000000000000000000006D";

        byte[] headB = message.getBytes();
        int head_s = 0xffaa;
        int head = ByteUtil.byte2Int(headB);

        byte[] headbyte = ByteUtil.int2Byte(head_s);

        byte[] s = new byte[]{94, 0};
        //  设备电量2byte
        byte[] electricityB = new byte[]{0x5F, 0x00};
        short electricity = ByteUtil.byte2short(electricityB);
        //  设备电压2byte
        byte[] voltageb = new byte[]{0x7E, 0X01};
        short voltage = ByteUtil.byte2short(voltageb);

        System.out.println("electricity:" + electricity);
        System.out.println("voltage:" + voltage);

    }


}
