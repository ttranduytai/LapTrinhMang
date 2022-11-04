import java.io.*;
import java.net.Socket;

public class NumberClient {
    public final static String SERVER_IP = "192.168.1.5";
    public final static int SERVER_PORT = 9;
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);
            DataInputStream is = new DataInputStream(socket.getInputStream()) ;
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            String input;
            do{
                System.out.println("You:");
                input = br.readLine();
                os.writeUTF(input);
                String response = is.readUTF();
                System.out.println("Server: " + response);
            }while (!input.equalsIgnoreCase("quit"));
            os.flush();
            os.close();
            is.close();
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}