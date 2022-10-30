import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SortAndTotal {
    public static void main(String[]ags) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("input.txt")));
        sort(input);
    }
    public static ArrayList<Float> sort(String input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input," ");
        int i = 1;
        float tong = 0;
        ArrayList<Float> arrayList = new ArrayList<Float>();
        while (stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            Float value = Float.parseFloat(token);
            arrayList.add(value);
            tong += value;
            i++;
        }
        Collections.sort(arrayList);
        try {
            FileWriter output = new FileWriter("output.txt");
            output.write("Tang dan: " + arrayList.toString().substring(1, arrayList.toString().length()-1) + "\n");
            output.write("Tong: " + tong);
            System.out.println("Tang dan: " + arrayList.toString().substring(1, arrayList.toString().length()-1));
            System.out.println("Tong: " + tong);
            output.close();
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
        return arrayList;
    }
}