package com.example.practica9;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread
{

    private DatagramSocket socket;

    String message;

    private MainActivity mainActivity;

    public void setObserver(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public void run()
    {
        try {
            socket = new DatagramSocket(3000);


            while(true)
            {
                byte[] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("Awaiting for datagram...");
                socket.receive(packet);

                message = new String(packet.getData()).trim();
                Log.d(">>>", "Datagram Received: " + message);

                mainActivity.whenTheMessageArrives(message);
            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg)
    {

        new Thread(
                ()->
                {
                    try {
                        InetAddress ip;
                        ip = InetAddress.getByName("192.168.1.69");
                        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ip, 2000);
                        socket.send(packet);
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

