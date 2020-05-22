package com.safeway.emclclient.emcl.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.List;

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

    public static String convertHexToDecimal(List<String> byteList) {
        if (CollectionUtils.isEmpty(byteList)){
            return StringUtils.EMPTY;
        }
        StringBuilder decimalValueBuilder = new StringBuilder("");
        /*if (byteList.size() == 2) {
            decimalValueBuilder.append(byteList.get(1));
            decimalValueBuilder.append(byteList.get(0));
        } else if (byteList.size() == 4) {
            decimalValueBuilder.append(byteList.get(3));
            decimalValueBuilder.append(byteList.get(2));
            decimalValueBuilder.append(byteList.get(1));
            decimalValueBuilder.append(byteList.get(0));
        }*/
        for (int i = byteList.size()-1; i>=0; i--){
            decimalValueBuilder.append(byteList.get(i));
        }
        int decimalValue = new BigInteger(decimalValueBuilder.toString(), 16).intValue();
        return String.valueOf(decimalValue);

    }

    public static int convertHexToDecimal(String byteStr) {
        int decimalValue = new BigInteger(byteStr, 16).intValue();
        return decimalValue;
    }

    public static String bin2hex(byte[] in) {
        StringBuilder sb = new StringBuilder(in.length * 2);
        for (byte b : in) {
            sb.append(
                    forDigit((b & 0xF0) >> 4)
            ).append(
                    forDigit(b & 0xF)
            );
        }
        return sb.toString();
    }
    public static char forDigit(int digit) {
        if (digit < 10) {
            return (char) ('0' + digit);
        }
        return (char) ('A' - 10 + digit);
    }
}
