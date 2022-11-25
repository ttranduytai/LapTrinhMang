import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Server {

    public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
    public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận

    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ds = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");

            while (true) { // Tạo gói tin nhận
                DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming); // Chờ nhận gói tin gởi đến

                // Lấy dữ liệu khỏi gói tin nhận
                String message = new String(incoming.getData(), 0, incoming.getLength());
                System.out.println("Received from "+ incoming.getAddress() + ": " + message);
                int total = 0;
                int min = Integer.MAX_VALUE;  
                int max = Integer.MIN_VALUE;
                StringTokenizer st = new StringTokenizer(message," ");
                        while(st.hasMoreTokens())
                            {
                                int Num = Integer.parseInt(st.nextToken());  
                                if (Num > max) {  
                                    max = Num;
                                }  
                                if (Num < min) {  
                                    min = Num;
                                }
                                total += Num;
                            }
                            System.out.println("Sort: " + sort(message));
                            System.out.println("Total: " + total);
                            System.out.println("Max: " + max);
                            System.out.println("Min: " + min);
                String data = "\nSort: "+sort(message)+"\nTotal: "+total+"\nMax: "+max+"\nMin: "+min;
                byte[] out = data.getBytes();
                // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                DatagramPacket outsending = new DatagramPacket(out, out.length,
                        incoming.getAddress(), incoming.getPort());
                ds.send(outsending);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
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