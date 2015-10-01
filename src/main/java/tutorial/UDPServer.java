package tutorial;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by besil on 21/09/15.
 */
class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket inPacket = null; //recieving packet
        DatagramPacket outPacket = null; //sending packet
        byte[] inBuf, outBuf;
        String msg;
        final int PORT = 8888;

        try {
            socket = new DatagramSocket(PORT);
            while (true) {
                System.out.println("Waiting for client...");

                //Receiving datagram from client
                inBuf = new byte[256];
                inPacket = new DatagramPacket(inBuf, inBuf.length);
                socket.receive(inPacket);

                //Extract data, ip and port
                int source_port = inPacket.getPort();
                InetAddress source_address = inPacket.getAddress();
                msg = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("Client " + source_address + ":" + msg);

                //Send back to client as an echo
                msg = reverseString(msg.trim());
                outBuf = msg.getBytes();
                outPacket = new DatagramPacket(outBuf, 0, outBuf.length, source_address, source_port);
                socket.send(outPacket);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static String reverseString(String input) {
        StringBuilder buf = new StringBuilder(input);
        return buf.reverse().toString();
//        return input;
    }
}
