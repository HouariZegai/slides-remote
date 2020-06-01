package com.houarizegai.slidesremoteserver.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtils {

    public static String getMyIPAddress() {
        try {
            return String.valueOf(InetAddress.getLocalHost()).split("/")[1];
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
