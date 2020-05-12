package com.safeway.emclclient.emcl.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

public class HexUtil {
    public static byte[] hexToBin(String str) {
        int len = str.length();
        byte[] out = new byte[len/2];
        int endIndx;
        for (int i = 0; i < len; i = i + 2) {
            endIndx = i + 2;
            if (endIndx > len)
                endIndx = len - 1;
            out[i/2] = (byte) Integer.parseInt(str.substring(i, endIndx), 16);
        }
        return out;
    }

    public static String intToHex(int num) {
        String hex = String.format("%08x", num);
        StringBuffer temp = new StringBuffer("");
        for(int i = hex.length()-2; i>=0; i-=2) {
            temp.append(hex.substring(i, i+2));
        }
        return temp.toString();
    }

    public static String shortToHex(short num) {
        String hex = String.format("%04x", num);
        StringBuffer temp = new StringBuffer("");
        for(int i = hex.length()-2; i>=0; i-=2) {
            temp.append(hex.substring(i, i+2));
        }
        return temp.toString();
    }

    public static String toHexString(byte[] ba) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < ba.length; i++)
            str.append(String.format("%02x", ba[i]));
        return str.toString();
    }

    public static String convertHexToASCII(String hex) {
        if (hex.length() % 2 != 0) {

            return null;
        }
        StringBuilder sb = new StringBuilder();
        // Convert Hex 0232343536AB into two characters stream.
        for (int i = 0; i < hex.length() - 1; i += 2) {
            /*
             * Grab the hex in pairs
             */
            String output = hex.substring(i, (i + 2));
            /*
             * Convert Hex to Decimal
             */
            int decimal = new BigInteger(output, 16).intValue();
            sb.append((char) decimal);
        }
        return StringUtils.trim(sb.toString());
    }
}
