package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.core.exception.MyException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author gxming
 * @description UDP 服务端
 * @date 2021-07-08 11:30
 */
public class UDPDataGramSocketServer {
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket(6666);
            for(;;) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                //接收UDP数据包
                ds.receive(packet);
                // packet.getOffset(),packet.getLength() 确定数据在缓存区的起止位置
                String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
                // 回复客服端
                byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
                packet.setData(data);
                ds.send(packet);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new MyException(e.getMessage());
        }
    }

}
