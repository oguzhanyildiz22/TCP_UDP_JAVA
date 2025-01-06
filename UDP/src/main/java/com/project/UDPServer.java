package com.project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) {
        try(DatagramSocket serverSocket = new DatagramSocket(8080)) {

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            System.out.println("UDP sunucu başlatıldı ve bağlantı bekleniyor...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("İstemciden gelen: " + received);

                String response = received.toUpperCase();
                sendBuffer = response.getBytes();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Cevap gönderildi: " + response);

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
