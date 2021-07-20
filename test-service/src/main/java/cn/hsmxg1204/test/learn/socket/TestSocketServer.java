package cn.hsmxg1204.test.learn.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-06-21 16:48
 */
public class TestSocketServer {
        public static void main(String[] args) throws Exception{
            ServerSocket socket = new ServerSocket(1204);
            System.out.println("Server is running...");
            for(;;){
                Socket ss = socket.accept();
                System.out.println("connected from " + ss.getRemoteSocketAddress());
                Thread t = new Handler(ss);
                t.start();
            }
        }
    }

class Handler extends Thread {
    Socket socket;
    public Handler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(InputStream input = this.socket.getInputStream()){
            try(OutputStream outputStream = this.socket.getOutputStream()){
                handle(input,outputStream);
            }
        }catch (Exception e){
            try{
                this.socket.close();
            }catch (IOException ioe){
            }
            System.out.println("client disconnected.");
        }
    }
    private void handle(InputStream inputStream,OutputStream outputStream) throws IOException{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        writer.write("hello \n");
        writer.flush();
        for(;;){
            String s = reader.readLine();
            if("bye".equals(s)){
                writer.write("bye \n");
                writer.flush();
                break;
            }
            writer.write("ok:  "+ s +"\n");
            writer.flush();
        }
    }
}
