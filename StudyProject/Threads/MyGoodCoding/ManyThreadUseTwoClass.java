package Threads.MyGoodCoding;

import java.util.List;
import java.util.ArrayList;

public class ManyThreadUseTwoClass {
    public static void main(String args[]){
        try {
            NoteThreade firstObject = new NoteThreade("First");
            Thread firstThread = firstObject;
            firstThread.start();
            firstThread.join();
            NoteThreade secondObject = new NoteThreade("Second");
            Thread secondThread = secondObject;
            secondThread.start();
            secondThread.join();
            NoteThreade thirdObject = new NoteThreade("Third");
            Thread thirdThread = thirdObject;
            thirdThread.start();
            thirdThread.join();

            System.out.println(firstObject.note.get(400));
            System.out.println(secondObject.note.get(500));
            System.out.println(thirdObject.note.get(600));

            System.out.println(firstObject.note.removeNote(firstThread.getName(), 400));
            System.out.println(secondObject.note.removeNote(secondThread.getName(), 500));
            System.out.println(thirdObject.note.removeNote(secondThread.getName(), 600));

            System.out.println(firstObject.note.get(400));
            System.out.println(secondObject.note.get(500));
            System.out.println(thirdObject.note.get(600));
        }catch (InterruptedException e){
            System.out.println("Interrupted in main thread");
        }


    }



    public static class Note{
        private List<String>  noteList = new ArrayList<String>();

        Note(){
        }

        public void addNote(String nameThread, int threadIndex){
            noteList.add(nameThread + "-Note" + threadIndex);
        }

        public String removeNote(String nameThread, int index){
            noteList.remove(index);
            return nameThread + ", " + index  + " was remove";
        }

        public String get(int index){
            return noteList.get(index);
        }
    }

    public static class NoteThreade extends Thread{
        int index;
        Note note = new Note();

        NoteThreade(String name){
            super(name);

        }

        @Override
        public void run(){
            for(int i = 0; i < 999; i++){
                index++;
                note.addNote(getName(), index);
            }
        }
    }
}
