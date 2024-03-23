package com.example.Util;

import cn.hutool.core.util.StrUtil;
import ch.qos.logback.core.util.CachingDateFormatter;
import org.springframework.boot.web.context.WebServerApplicationContext;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * <h3>First_Project</h3>
 *
 * @Description TGG
 * @Author 南城雨
 * @Date 2024-03-23 11:53
 **/
public class IpAddressUtils {
    /**
     * 获取项目启动的IP地址
     * 注: 仅限springboot项目
     **/
    public static List<String> getIpAddressOfStartUp(WebServerApplicationContext context) {
        List<String> addressList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface networkInterface : Collections.list(interfaces)) {
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                        String address = StrUtil.utf8Str(inetAddress.getHostAddress());
                        int port = context.getWebServer().getPort();
                        String ipAddress = StrUtil.format("http://{}:{}", address, port);
                        addressList.add(ipAddress);
                    }
                }

            }
            return addressList;
        } catch (SocketException e) {
            e.printStackTrace();
            return addressList;
        }
    }
}
