package com.project;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try(DatagramSocket clientSocket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 8080;

            System.out.print("Bir mesaj girin: ");
            String message = scanner.nextLine();
            byte[] sendBuffer = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Sunucudan gelen cevap: " + response);

        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
