package org.catmint.core.tools.common;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>Title:ip获取工具类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class IpUtils {
    /**
    * <p>Title:获取所有IPV4的IP</p>
    * <p>Description:适用于VPN环境</p>
    * @author QIQI
    * @params []
    * @return java.util.List<java.lang.String>
    * @throws 
    * @date 2019-12-23 23:47 
    */
    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }

    /**
    * <p>Title:获取本机IP</p>
    * <p>Description:</p>
    * @author QIQI
    * @params []
    * @return java.lang.String
    * @throws 
    * @date 2019-12-23 23:48 
    */
    public static String getLocalIp() throws UnknownHostException {
        InetAddress inetAddress=InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }
}
