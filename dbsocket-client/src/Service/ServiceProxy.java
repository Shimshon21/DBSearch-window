package Service;

import com.google.gson.Gson;
import model.Emp;
import model.Request;
import model.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

//Service allow to get requested data from businessDB table employee
public class ServiceProxy {
    Socket sock;
    Gson gson = new Gson();
    DataInputStream din = null;
    DataOutputStream dout = null;

    public ServiceProxy(Socket sock) {
        this.sock = sock;
        try {
            this.din = new DataInputStream(sock.getInputStream());
            this.dout = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Return the data found by 'name'.
    public List<Emp> fetchResults(String name,String type) {
        Request request = new Request(name,type);
        String inputJSON = gson.toJson(request);
        try {
            dout.writeUTF(inputJSON);
            String outputJSON = din.readUTF();
            Response response = gson.fromJson(outputJSON, Response.class);
            return  response.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        if (sock != null) {
            try {
                sock.close();
            } catch(Exception e) {}
        }
    }
}
