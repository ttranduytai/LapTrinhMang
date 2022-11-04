import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class NumberServer {
    public final static int SERVER_PORT = 9;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            System.out.println("PORT:" + SERVER_PORT);
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Wait user...");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("User: " + socket);
                    DataInputStream is = new DataInputStream(socket.getInputStream());
                    DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                    while (true){
                        String input = is.readUTF();
                        int total = 0;
                        StringTokenizer st = new StringTokenizer(input," ");
                        while(st.hasMoreTokens())
                            {
                                int n = 0;
                                n = Integer.parseInt(st.nextToken());
                                total += n;
                            }                    
                        os.writeUTF("" + sort(input) + "\nTotal: " + total);
                        System.out.println("User input: " + input);
                        System.out.println("User input sort: " + sort(input));
                        System.out.println("User input total: " + total);
                    }
                    
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {e1.printStackTrace();
        } finally {
            if (serverSocket != null) serverSocket.close();
        }
    }
    public static ArrayList<Long> sort(String text){
        StringTokenizer stringTokenizer = new StringTokenizer(text," ");
        ArrayList<Long> arrayList = new ArrayList<Long>();
        while (stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            long value = Long.parseLong(token);
            arrayList.add(value);
        }
        Collections.sort(arrayList);
        return arrayList;
    }
}