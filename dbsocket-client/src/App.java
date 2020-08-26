import Service.ServiceProxy;
import ViewWindow.ClientWindow;

import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost",9091);
        ServiceProxy proxy = new ServiceProxy(socket);
        ClientWindow window = new ClientWindow(proxy);
        window.setVisible(true);
    }
}
