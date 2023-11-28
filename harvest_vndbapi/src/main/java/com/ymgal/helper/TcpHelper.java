package com.ymgal.helper;

import java.io.*;
import java.net.Socket;

import static com.ymgal.Constants.*;

/**
 * @Auther: lyl
 * @Date: 2023/10/23 20:21
 * @Description:
 */
public class TcpHelper {

    private static Socket socket;

    private static OutputStream outputStream;
    private static InputStream inputStream;


    public static void Login() {
        try {
            socket = new Socket(ApiDomain, ApiPort);
            //socket = new Socket("localhost", 8168);
            System.out.println("客户端建立成功！");
            String printText = "login {\"protocol\":1,\"client\":\"AwesomeClient\",\"clientver\":\"1.0\"}" + EotChar + "\n";
            outputStream = socket.getOutputStream();        //建立客户端信息输出流

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write(printText);
            bw.flush();

            inputStream = socket.getInputStream();
            StringBuilder builder = new StringBuilder();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer, 0, buffer.length)) > 0) {
                builder.append(new String(buffer, 0, len));
                if (buffer[--len] == EotChar)
                    break;
            }


            String data = builder.toString();
            System.out.println("登陆：" + data);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendData(String cmd) {

        if (!socket.isConnected()) {
            Login();
        }
        String printText = cmd + EotChar;
//            OutputStream outputStream = socket.getOutputStream();        //建立客户端信息输出流
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(printText);
        printWriter.flush();
        //socket.shutdownOutput();

    }

    public static String getResponse() {
        try {

            if (!socket.isConnected()) {
                Login();
            }

            StringBuilder builder = new StringBuilder();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer, 0, buffer.length)) > 0) {
                builder.append(new String(buffer, 0, len));
                if (buffer[--len] == EotChar)
                    break;
            }
            String data = builder.toString();
            System.out.println("Response：" + data);
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void Loginout() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
