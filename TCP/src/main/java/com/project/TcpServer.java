package com.project;

import java.io.*;
import java.net.*;

public class TcpServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Sunucu başlatıldı ve bağlantı bekleniyor...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Bir istemci bağlandı: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String received = in.readLine();
                System.out.println("İstemciden gelen: " + received);

                String response = received.toUpperCase();
                out.println(response);
                System.out.println("Cevap gönderildi: " + response);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
