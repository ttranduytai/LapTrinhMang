import java.io.*;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class NumberThread extends Thread{
    Socket socket;

    public NumberThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
        
        } catch (IOException e) {
            System.out.println("Connection lost from: " + socket.getInetAddress().getHostAddress());
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