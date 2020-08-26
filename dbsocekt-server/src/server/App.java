package server;

import core.Worker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ServerSocket serSock = new ServerSocket(9091);
        while (true) {
            try {
                Socket sock = serSock.accept();
                executorService.execute(new Worker(sock));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
