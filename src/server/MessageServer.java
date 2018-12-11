package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class MessageServer {
	
	
    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        int port = 9095;
        ServerSocket listener = new ServerSocket(port);
        System.out.println("Server started on 9095");
        try {
            while (true) {
                Socket socket = listener.accept();
                System.out.println("making new thread");
                new ServerThread(socket).start();     
                System.out.println("made new thread");
            }
        }
        finally {
            listener.close();
        }
    }

}
