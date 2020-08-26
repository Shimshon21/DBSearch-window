package core;

import com.google.gson.Gson;
import model.Emp;
import model.Request;
import model.Response;
import repository.EmpRepository;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Worker implements Runnable {
    Socket sock;
    EmpRepository empRepository = new EmpRepository();
    Gson gson;

    public Worker(Socket sock) {
        this.sock = sock;
        this.gson = new Gson();
    }

    public void run() {
        DataInputStream din = null;
        DataOutputStream dout = null;

        try {
            din = new DataInputStream(sock.getInputStream());
            dout = new DataOutputStream(sock.getOutputStream());
            while (true) {
                String inputJson = din.readUTF();
                System.out.println("Obtained request - " + inputJson);
                Request request = gson.fromJson(inputJson, Request.class);
                List<Emp> empList = empRepository.findByNamePattern(request.getName(),request.getByType());
                Response response = new Response(Response.SUCCESS, empList);
                String outputJson = gson.toJson(response);
                System.out.println("Result sent - " + outputJson);
                dout.writeUTF(outputJson);
            }
        } catch(Exception e) {
            if (! (e instanceof EOFException)) {
                e.printStackTrace();
                Response response = new Response(Response.FAILED, e.toString(), null);
                String outputJson = gson.toJson(response);
                try {
                    dout.writeUTF(outputJson);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            close(din);
            close(dout);
        }
    }

    private void close(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
