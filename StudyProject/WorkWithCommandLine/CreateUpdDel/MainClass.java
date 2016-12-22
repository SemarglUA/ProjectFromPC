package WorkWithCommandLine.CreateUpdDel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для Date
Пример параметров: -c Миронов м 15/04/1990
*/
public class MainClass {
    public static List<Person> allPeople = new ArrayList<Person>();
    public static ArgsOperationPerson operation = new ArgsOperationPerson();
    public static SimpleDateFormat formatOutput = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    public static SimpleDateFormat formatInput = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }
    public static void main(String[] args) {
        if (args[0].equals("-c")) {
            operation.addPerson(args[1], args[2], new Date(args[3]));
        }else{
            new IllegalArgumentException();
        }
        if (args[0].equals("-u")){
            operation.updatePerson(args[1], args[2], args[3], new Date(args[4]));
        }else{
            new IllegalArgumentException();
        }
        if (args[0].equals("-d")){
            operation.removePerson(args[1]);
        }else{
            new IllegalArgumentException();
        }
        if (args[0].equals("-i")){
            operation.outputInformationAboutPerson(args[1]);
        }else{
            new IllegalArgumentException();
        }
    }

    static class Person{
        private String name;
        private String sex;
        private Date date;
        private int id = count;
        private static int count;

        Person(String personName, Date personDate){
            name = personName;
            date = personDate;
            count++;
        }

        Person(String personName, String personSex, Date personDate){
            name = personName;
            sex = personSex;
            date = personDate;
            count++;
        }



        public static Person createMale(String personName, Date personDate){
            return new Person(personName, personDate);
        }

        public int getId(){
            return id;
        }

        public boolean update(String personName, String personSex, Date personDate){
            if(personDate != null && personSex!= null && personDate != null) {
                name = personName;
                sex = personSex;
                date = personDate;
                return true;
            }else {
                return false;
            }
        }

        @Override
        public String toString(){
            if(sex != null) {
                return name + " " + sex + " " + formatOutput.format(date);
            }else{
                return name + " " + formatOutput.format(date);
            }
        }
    }
}