package server;

/**
 * Created by ddz on 14-2-7.
 * 服务器端用于接收数据的程序
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /**
     * 主程序入口
     * @param args
     */
    public static void main(String args[]) {
        try {
            ServerSocket s = new ServerSocket(8000);
            while (true) {
                Socket socket = s.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                System.out.println("Latitude is" + input.readDouble());
                System.out.println("Longitude is" + input.readDouble());
                input.close();
            }
        }catch (IOException e) {
            System.out.println("程序运行出错:" + e);
        }
    }
}
