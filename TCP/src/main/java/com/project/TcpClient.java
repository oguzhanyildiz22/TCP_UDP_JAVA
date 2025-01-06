package com.project;

import java.io.*;
import java.net.*;

public class TcpClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8080)) {
            System.out.println("Sunucuya bağlanıldı...");

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Bir mesaj girin: ");
            String message = consoleReader.readLine();

            out.println(message);
            String response = in.readLine();
            System.out.println("Sunucudan gelen cevap: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
