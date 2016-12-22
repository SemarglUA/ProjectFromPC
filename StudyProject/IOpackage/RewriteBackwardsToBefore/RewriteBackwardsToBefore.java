package IOpackage.RewriteBackwardsToBefore;

import java.io.*;
import java.util.Stack;

public class RewriteBackwardsToBefore {
    public static void main(String args[]){
        System.out.println("Program starting");
        RewriteBackwardsToBefore t = new RewriteBackwardsToBefore();
        try {
            t.readAndPrintInReverseOrder();
        }catch(IOException e){
            System.out.println("IOException flew out from readAndPrintInReverseOrder");
        }
        System.out.println();
        System.out.printf("Happy end program");

    }

    public void readAndPrintInReverseOrder ()throws IOException {

        String path = "d:/study_in/data.txt";

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(path));
            Stack<Integer> lines = new Stack<Integer>();
            int line = br.read();
            while (line != -1) {
                lines.push(line);
                line = br.read();
            }

            String pathOut = "d:/study_out/StackWrite.txt";
            bw = new BufferedWriter(new FileWriter(pathOut));

            while (!lines.empty()) {
//                    System.out.println(lines.pop());
                bw.write(lines.pop());
            }

        } finally {
            if (br != null | bw != null) {
                try {
                    br.close();
                    bw.close();
                } catch (IOException e) {
                    // can't help it
                }
            }

        }
    }
}
