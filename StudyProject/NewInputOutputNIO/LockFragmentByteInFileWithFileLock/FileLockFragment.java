package NewInputOutputNIO.LockFragmentByteInFileWithFileLock;

import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileLock;
import java.io.IOException;

public class FileLockFragment {
    static final int LENGTH = 0x8FFFFFF; // 128 MB
    static FileChannel fc;
    public static void main(String[] args) throws Exception {
        fc =
                new RandomAccessFile("D://test.txt", "rw").getChannel();
        MappedByteBuffer out =
                fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for(int i = 0; i < LENGTH; i++)
            out.put((byte)'x');
        new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
        new LockAndModify(out, 0, LENGTH / 3);
    }
    private static class LockAndModify extends Thread {
        private ByteBuffer buff;
        private int start, end , a;
        LockAndModify(ByteBuffer mbb, int start, int end) {
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            buff = mbb.slice();
            start();
        }
        @Override
        public void run() {
            try {
                // Exclusive lock with no overlap:
                // Монопольная блокировка без перекрытия:
                FileLock fl = fc.lock(start, end, false);
                System.out.println("Locked: "+ start +" to "+ end);
                // Perform modification:
                // Модификация:
                while (buff.position() < buff.limit() - 1)
                    buff.put((byte) (buff.get() + 1));

//                synchronized (this.getClass()) {
//                    buff.rewind();
//                    while (buff.position() < buff.limit() - 1) {
//                        for (int i = buff.position() + 1; i < buff.limit(); i += 2) {
//                            if (i % 75 == 0) {
//                                System.out.println();
//                            } else {
//                                System.out.print((char) buff.get(i));
//                            }
//                        }
//                    }
//                }

                fl.release();
                System.out.println("Released: " + start + " to " + end);
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}