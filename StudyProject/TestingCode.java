import java.util.*;
import java.lang.String;


public class TestingCode {
    public static enum Enum {
        ONE, TWO, THREE
    }

    static class CEnum {
        public Enum e;
    }

    static class CInt {
        public int i;
    }

    public static void main(String[] args) {
        CEnum[] enums = new CEnum[8192];
        CInt[] ints = new CInt[8192];

        for (int i = 0 ; i < 8192 ; i++) {
            enums[i] = new CEnum();
            ints[i] = new CInt();
            ints[i].i = 1 + (i % 3);
            if (i % 3 == 0) {
                enums[i].e = Enum.ONE;
            } else if (i % 3 == 1) {
                enums[i].e = Enum.TWO;
            } else {
                enums[i].e = Enum.THREE;
            }
        }
        int k=0; //calculate something to prevent tests to be optimized out

        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);
        k+=test1(enums);

        System.out.println();

        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);
        k+=test2(ints);

        System.out.println(k);



    }

    private static int test2(CInt[] ints) {
        long t;
        int k = 0;
        for (int i = 0 ; i < 1000 ; i++) {
            k+=test(ints);
        }

        t = System.nanoTime();
        k+=test(ints);
        System.out.println((System.nanoTime() - t)/100 + "ns");
        return k;
    }

    private static int test1(CEnum[] enums) {
        int k = 0;
        for (int i = 0 ; i < 1000 ; i++) {
            k+=test(enums);
        }

        long t = System.nanoTime();
        k+=test(enums);
        System.out.println((System.nanoTime() - t)/100 + "ns");
        return k;
    }

    private static int test(CEnum[] enums) {
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;

        for (int j = 100 ; j != 0 ; --j)
            for (int i = 0 ; i < 8192 ; i++) {
                CEnum c = enums[i];
                if (c.e == Enum.ONE) {
                    i1++;
                } else if (c.e == Enum.TWO) {
                    i2++;
                } else {
                    i3++;
                }
            }

        return i1 + i2*2 + i3*3;
    }

    private static int test(CInt[] enums) {
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;

        for (int j = 100 ; j != 0 ; --j)
            for (int i = 0 ; i < 8192 ; i++) {
                CInt c = enums[i];
                if (c.i == 1) {
                    i1++;
                } else if (c.i == 2) {
                    i2++;
                } else {
                    i3++;
                }
            }

        return i1 + i2*2 + i3*3;
    }
}