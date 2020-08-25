package top.mrl.net;

import java.net.*;

/**
 * 发信端
 */

public class udpClient1 {
    public static void main(String[] args) throws Exception {
        System.out.println("发送方正准备发送...");
        // 创建Socket
        DatagramSocket socket = new DatagramSocket(8888);
        // 准备数据
        String str = "Hello Socket";
        byte[] data = str.getBytes();
        // 准备包裹
        DatagramPacket packet = new DatagramPacket(data, 0, data.length,
                new InetSocketAddress("localhost", 9999));
        // 发送
        socket.send(packet);
        // 关闭
        socket.close();
    }
}
