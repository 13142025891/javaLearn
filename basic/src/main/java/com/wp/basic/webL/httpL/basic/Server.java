package com.wp.basic.webL.httpL.basic;

import org.apache.commons.*;
import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 6:48 2021/7/1
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(8080);
        System.out.println(" server is running... ");
        for (;;){
            Socket sock = ss.accept();
            System.out.println(" connected from "+sock.getRemoteSocketAddress());

            Thread t=new Handler(sock);
            t.start();
        }


    }


}

class  Handler extends  Thread{
    Socket socket;
    public Handler(Socket s){
        this.socket=s;
    }

    @Override
    public void run() {
       try(InputStream input=socket.getInputStream()) {
           try (OutputStream out=socket.getOutputStream()){
               handle(input,out);
           }
       } catch (IOException e) {
           try {
               this.socket.close();
           } catch (IOException ioException) {
               ioException.printStackTrace();
           }
           System.out.println(" client disconnected.");
       }
    }

    private void handle(InputStream input, OutputStream out) throws IOException {

        BufferedReader reader=new BufferedReader(
                new InputStreamReader(input,"UTF-8")

        );

        Writer writer=new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

        boolean requestOk=false;
        String first=reader.readLine();
        if(first.startsWith("GET / HTTP/1.")){
            requestOk=true;
        }
        for (;;){
            String header = reader.readLine();
            if(StringUtils.isEmpty(header)){
                break;
            }
            System.out.println("header: "+header);
        }
        System.out.println(requestOk?"Response OK":"Response Error");
        if(!requestOk){
            writer.write("HTTP/1.0 404 Nnt Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        }
        else{
            String data="<html><body><h1>Hello, world!</h1></body></html>";
            int length=data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(data);
            writer.flush();
        }

    }
}
