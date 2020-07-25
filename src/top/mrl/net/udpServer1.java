package top.mrl.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收方
 */

public class udpServer1 {
    public static void main(String[] args) throws Exception {
        // 创建服务端
        DatagramSocket socket = new DatagramSocket(9999);
        // 准备容器
        byte[] con = new byte[1024 * 60];
        // 准备包裹
        DatagramPacket packet = new DatagramPacket(con, 0, con.length);
        // 接收包裹
        socket.receive(packet);
        // 分析数据
        byte[] data = packet.getData();
        int len = packet.getLength();
        System.out.println(new String(data, 0, len));
        socket.close();
    }

}
