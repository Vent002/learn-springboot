package cn.hsmxg1204.test.learn.socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-06-21 17:02
 */
public class TestSocketClient {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("localhost",1204);
        try(InputStream input = sock.getInputStream()){
            try(OutputStream output = sock.getOutputStream()){
                handle(input,output);
            }
            sock.close();
            System.out.println("disconnected.");
        }
    }
    private static void handle(InputStream input,OutputStream output) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());
        for (;;) {
            System.out.print(">>> "); // 打印提示
            String s = scanner.nextLine(); // 读取一行输入
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if (resp.equals("bye")) {
                break;
            }
        }
    }
}
