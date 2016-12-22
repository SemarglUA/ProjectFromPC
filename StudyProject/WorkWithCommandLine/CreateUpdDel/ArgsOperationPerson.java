package WorkWithCommandLine.CreateUpdDel;

import java.util.Date;

public class ArgsOperationPerson{

    public static void addPerson(String name, String sex, Date d){
        MainClass.Person person = new MainClass.Person(name, sex, d);
        MainClass.allPeople.add(person);
        System.out.println(person.getId());
    }

    public static void updatePerson(String id, String name, String sex, Date date){
        for(MainClass.Person p: MainClass.allPeople){
            if(p.getId() == Integer.parseInt(id)){
                System.out.println(p.update(name, sex, date));
                System.out.println();
            }else
                System.out.println("Update fail");
        }
    }

    public static void removePerson(String id){
        for (MainClass.Person p: MainClass.allPeople){
            if(p.getId() == Integer.parseInt(id)){
                MainClass.allPeople.remove(p);
                System.out.println("Remove complete");
            }else
                System.out.println("Remove fail");
        }

    }

    public static void outputInformationAboutPerson(String id){
        for(MainClass.Person p: MainClass.allPeople){
            if(p.getId() == Integer.parseInt(id)){
                System.out.println(p);
            }
        }
    }
}