package IOpackage.InterestingCodeWithStandartOutputStream;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StandartSystemOutStream
{
    public static TestString testString = new TestString();

    public static void main(String[] args)
    {

        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        testString.printSomething();

        String result = outputStream.toString();
        StringBuilder sBuilder = new StringBuilder();

        String[] arreyResult = result.split(" ");
        sBuilder.append(arreyResult[0] + " " + arreyResult[1] + " " + arreyResult[2] + " " + arreyResult[3] + " ");

        int a = new Integer(arreyResult[0]);
        int b = new Integer(arreyResult[2]);

        if(arreyResult[1].equals("+")){
            sBuilder.append(a + b);
        }

        if(arreyResult[1].equals("-")){
            sBuilder.append(a - b);
        }

        if(arreyResult[1].equals("*")){
            sBuilder.append(a * b);
        }

        System.setOut(consoleStream);
        stream.close();

        System.out.println(sBuilder);
    }

    public static class TestString
    {
        public void printSomething()
        {
            System.out.println("9 * 11 = ");
        }
    }
}