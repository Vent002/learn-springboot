package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.core.exception.MyException;
import org.omg.CORBA.portable.UnknownException;

import java.io.IOException;
import java.net.*;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-08 11:37
 */
public class UDPDataGramSocketClient {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            ds.setSoTimeout(1000);
            ds.connect(InetAddress.getByName("localhost"),6666);

            byte[] data = "hello".getBytes();
            DatagramPacket packet = new DatagramPacket(data,data.length);
            ds.send(packet);
            String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println("localhost:6666:resp:[ "+resp+" ]");
            ds.disconnect();

        } catch (IOException e){
            e.printStackTrace();
            throw new MyException(e.getMessage());
        }
    }
}
