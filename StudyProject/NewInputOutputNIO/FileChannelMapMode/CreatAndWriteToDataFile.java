package NewInputOutputNIO.FileChannelMapMode;

import java.nio.MappedByteBuffer;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;


public class CreatAndWriteToDataFile {
    static byte[] byteArray = new byte[] {'x', 'g', 'z', 'a', 'u'};
    static int length = 0x8FFFFFF; // 128 MB
    public static void main(String[] args) throws Exception {
        MappedByteBuffer out =
                new RandomAccessFile("D://test.dat", "rw").getChannel()
                        .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for(int i = 0; i < length/5; i++)
            out.put(byteArray);
        System.out.println("Finished writing");
        for(int i = length/2; i < length/2 + 6; i++)
            System.out.print((char)out.get(i));
    }
}