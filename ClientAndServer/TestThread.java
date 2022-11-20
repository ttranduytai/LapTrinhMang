import java.io.*;
import java.net.Socket;

public class TestThread extends Thread {
    Socket socket;

    public TestThread(Socket socket) {
        this.socket = socket;
    }
//10.10.11.66

    public String acceptFromClient() throws IOException {
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        PrintWriter out = null;
        String ip = null;
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
            String line = "";
            line = br.readLine(); // Receive data from client
            System.out.println("From " + socket.getInetAddress().getHostAddress() + ">" + line);
            ip = socket.getInetAddress().getHostAddress();
            out.println("Request accepted:>>" + line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            br.close();
            out.close();
            socket.close();
        }

        return ip;
    }

    public void sendRequestToServer(String ip) throws IOException {
        Socket sk = null;
        DataOutputStream ot = null;
        DataInputStream b = null;
        try {
            System.out.println("Send request to:" + ip + " ...");
            sk = new Socket(ip, 100);
            System.out.println("Connected: " + sk);
            ot = new DataOutputStream(sk.getOutputStream());
            ot.writeUTF("1 34 5 28 2 93 109 238 12 4792 402");
            b = new DataInputStream(sk.getInputStream());
            String l;
            while (!(l = b.readUTF()).equalsIgnoreCase("quit")) {
                System.out.println("Result:" + l);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Connection closed:" + ip);
            if (b != null) {
                b.close();
                b = null;
            }
            if (ot != null) {
                ot.close();
                ot = null;
            }
            if (sk != null) {
                sk.close();
                sk = null;
            }
        }
    }
        @Override
        public void run () {
            try {
                String ip = acceptFromClient();
                sendRequestToServer(ip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }