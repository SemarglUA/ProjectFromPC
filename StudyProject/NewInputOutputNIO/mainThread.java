package NewInputOutputNIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* Исправить ошибку
Программа содержит всего 1 ошибку. Найди и исправь ее
*/

public class mainThread {
    static {
        System.out.println("it's Solution class");
    }

    public static void main(String... args) throws IOException {
        try (
                FileOutputStream outputStream = new FileOutputStream("d:\\study_out\\gif_file.txt");
                InputStream is = mainThread.class.getClassLoader().getResourceAsStream("320D.gif");
        ) {

            byte[] b = new byte[is.available()];
            outputStream.write(is.read(b));

            int value = 123_456_789;
            System.out.println(value);

            Example result = null;
            String s = "a";
            switch (s) {
                case "a": {
                    result = new mainThread().new A();
                    break;
                }
                case "b": {
                    result = new mainThread().new B();
                    break;
                }
                case "c": {
                    result = new mainThread().new C();
                    break;
                }
            }

            if (result instanceof C) {
                C p = (C) result;
                System.out.println(p.getClass().getSimpleName());
            }

        } catch (IOException e) {
        }
    }

    interface Example {
    }

    class A implements Example {
        {
            System.out.println("it's A class");
        }
    }

    class B implements Example {
        {
            System.out.println("it's B class");
        }
    }

    class C extends A {
        {
            System.out.println("it's C class");
        }
    }
}