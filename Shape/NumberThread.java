import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class NumberThread extends Thread {

  Socket socket;

  public NumberThread(Socket socket) {
    this.socket = socket;
  }

  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  @Override
  public void run() {
    try {
      OutputStream os = socket.getOutputStream();
      InputStream is = socket.getInputStream();
      //  BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
      DataInputStream inputStream = new DataInputStream(is);
      String line = "";
      int i, j;
      while (!Thread.currentThread().isInterrupted()) {
        line = inputStream.readUTF();
        System.out.println("From " + socket.getInetAddress().getHostAddress() + ">" + line);
        StringTokenizer st = new StringTokenizer(line);
        DataOutputStream outToClient = new DataOutputStream(os);
        int dem = 0, a;
        String hinh="";
        String sts="";
        while (st.hasMoreTokens()) {
          hinh +=sts;
          sts= st.nextToken();
          // line = CHUNHAT 11 10
          // hinh = CHUNHAT
          if (isNumeric(sts)) {
            switch (hinh.trim()){
              case "VUONG":
                vuong(Integer.parseInt(sts));
                break;
              case "CHUNHAT":
                System.out.println("3223234");
                String lengthHCN = line.substring(hinh.length() + 1);
                String[] abc = lengthHCN.split(" ");
                hcn(Integer.parseInt(abc[0]), Integer.parseInt(abc[1]));
              //  hcn()
                // String : length line - 7 = " 11 10"
                // method hcn: split : 11 | 10
                break;
              case "TRON":
                System.out.println("TRONG");
                tron(Integer.parseInt(sts));
                break;
              default:
                System.out.println("deo in j");
                break;
            }
          } else {
            System.out.println("NO");
            System.out.println();
          }

          if(hinh.equalsIgnoreCase("CHUNHAT")) break;
        }
        if (line == null || line.equalsIgnoreCase("quit")) break;
//        out.println("Response from K61 server:>>" + line);
        System.out.println("HET");

      }
      socket.close();
    } catch (IOException e) {
      System.out.println("Connection lost from: " + socket.getInetAddress().getHostAddress());
    }
  }

  public void hcn(int a, int b) throws IOException {
    OutputStream os = socket.getOutputStream();
    DataOutputStream outToClient = new DataOutputStream(os);
    int i, j;
    for (i = 0; i < a; i++) {
      for (j = 0; j < b; j++) {
        if (i == 0 || i == a - 1 || j == 0 || j == b - 1) {
          outToClient.writeBytes("* ");
        } else {
          outToClient.writeBytes("  ");
        }
      }
      outToClient.writeBytes("\n");
    }
    outToClient.writeBytes("q");
    outToClient.writeBytes("\n");
    outToClient.flush();
  }
  public void vuong(int a) throws IOException {
    OutputStream os = socket.getOutputStream();
    DataOutputStream outToClient = new DataOutputStream(os);
    int i, j;
    System.out.println(a);
    for (i = 0; i < a; i++) {
      for (j = 0; j < a; j++) {
        if (i == 0 || i == a - 1 || j == 0 || j == a - 1) {
          outToClient.writeBytes("*\t");
        } else {
          outToClient.writeBytes(" \t");
        }
      }
      outToClient.writeBytes("\n");
    }
    outToClient.writeBytes("q");
    outToClient.writeBytes("\n");
    outToClient.flush();
  }
  private static int Path(int x, int y) {
    return (int) (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
  }

  public void tron(int a) throws IOException {
    OutputStream os = socket.getOutputStream();
    DataOutputStream outToClient = new DataOutputStream(os);
    int width = a;
    int length = (int) (width * 1.5);
    int y = width;
      while (y >= -width) {
      int x = -length;
      while (x <= length) {
        if (Path(x, y) == a) {
          outToClient.writeBytes("*");
        } else {
          outToClient.writeBytes(" ");
        }
        x += 1;
      }
      outToClient.writeBytes("\n");
      y -= 2;

    }
    outToClient.writeBytes("q");
    outToClient.writeBytes("\n");
    outToClient.flush();

  }
}