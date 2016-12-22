package ArhiveZIPandGZIP.ZipGZIPArhivator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class OneGZIPArhiv {
    public static void main(String[] args)
            throws IOException {
        if(args.length == 0) {
            System.out.println(
                    "Usage: \nGZIPcompress file\n" +
                            "\tUses GZIP compression to compress " +
                            "the file to test.gz");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(
                new FileReader(args[0]));
        BufferedOutputStream out = new BufferedOutputStream(
                new GZIPOutputStream(
                        new FileOutputStream("D://test.gz")));
        System.out.println("Writing file");
        int c;
        while((c = in.read()) != -1)
            out.write(c);
        in.close();
        out.close();
        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(new GZIPInputStream(
                        new FileInputStream("D://test.gz"))));
        String s;
        while((s = in2.readLine()) != null)
            System.out.println(s);
    }
}