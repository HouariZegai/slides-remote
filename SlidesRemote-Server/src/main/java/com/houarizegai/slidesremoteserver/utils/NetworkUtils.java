package com.houarizegai.slidesremoteserver.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.regex.Pattern;

public class NetworkUtils {
    private static final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";

    public static Map<String, String> getMyIPv4Addresses() {
        Map<String, String> networkList = new HashMap<>();

        try {
            Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) enumeration.nextElement();
                Enumeration ee = networkInterface.getInetAddresses();

                while (ee.hasMoreElements()) {
                    InetAddress ip = (InetAddress) ee.nextElement();
                    Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
                    // if it is IPv4 Address
                    if (IPV4_PATTERN.matcher(ip.getHostAddress()).matches()) {

                        NetworkInterface network = NetworkInterface.getByInetAddress(ip);

                        byte[] mac = network.getHardwareAddress();
                        if (mac != null) {
                            StringBuilder macBuilder = new StringBuilder();
                            for (int i = 0; i < mac.length; i++) {
                                macBuilder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                            }
                        }

                        String name = networkInterface.getDisplayName();
                        String ipv4 = ip.getHostAddress();
                        networkList.put(name, ipv4);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return networkList;
    }
}
