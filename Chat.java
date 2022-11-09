import java.io.*;
import java.net.Socket;

public class Chat {
    public final static String SERVER_IP = "10.10.10.59";
    public final static int SERVER_PORT = 66;
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            //System.out.println("Connected: " + socket);
            //DataInputStream is = new DataInputStream(socket.getInputStream()) ;
            //DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            String sentence_to_server;
            String sentence_from_server;
            //Socket clientSocket = new Socket("10.10.10.59", 66);
            while(true) {
                //Tạo Inputstream(từ bàn phím)
                System.out.print("Input from client: ");
                BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
                //Lấy chuỗi ký tự nhập từ bàn phím
                sentence_to_server = "tai: " + inFromUser.readLine();
        //    
                //Tạo socket cho client kết nối đến server qua ID address và port number
            
                //Tạo OutputStream nối với Socket
                DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());
            
                //Tạo inputStream nối với Socket
                BufferedReader inFromServer =
                    new BufferedReader(new
                    InputStreamReader(clientSocket.getInputStream()));
        //     
                //Gửi chuỗi ký tự tới Server thông qua outputStream đã nối với Socket (ở trên)
                outToServer.writeBytes(sentence_to_server + '\n');
                }
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}