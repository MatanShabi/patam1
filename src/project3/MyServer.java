package test;

import java.net.Socket;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.ServerSocket;

public class MyServer {

    int port;
    ClientHandler clientHandler;
    boolean stop;

    public MyServer(int port, ClientHandler clientHandler) {
        this.port = port;
        this.stop = false;
        this.clientHandler = clientHandler;
    }

    public void start() {
        new Thread(this::StartServer).start();
    }

    public void StartServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(this.port);
            serverSocket.setSoTimeout(1000);
            while (!stop) {
                try (Socket client = serverSocket.accept()) {
                    clientHandler.handleClient(client.getInputStream(), client.getOutputStream());
                    clientHandler.close();
                } catch (SocketTimeoutException e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.close();
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.stop = true;
    }
}
